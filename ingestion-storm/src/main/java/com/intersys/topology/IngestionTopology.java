package com.intersys.topology;

	// Begin imports

import com.gerken.gumbo.storm.TaskHook;
	
import com.intersys.spout.*;
import com.intersys.bolt.*;
import com.intersys.util.AlarmClock;
import com.intersys.util.IngestionLogger;
import com.intersys.util.IIngestionLogger;

import backtype.storm.Config;
import backtype.storm.ILocalCluster;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.NotAliveException;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.BoltDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

	// End imports
	
public class IngestionTopology {

    static final String DEFAULT_TARGET  = "dev";

	public static boolean quiesce = false;

    private static Config config = null;
    static List<String> TARGETS = Arrays.asList(new String[] {"dev", "prod"});

    private static IIngestionLogger log = IngestionLogger.getInstance();
    
    private static ILocalCluster localCluster = new LocalCluster();

    public static void main(String[] args) throws Exception {

			// Begin topology execution code

    	String target = DEFAULT_TARGET;
    	
    	for (int i = 0; i < args.length; i++) {
    		System.out.println("args["+i+"] = "+args[i]);
    	}
     
    	if (args.length > 0 && TARGETS.contains(args[0])) {
    		target = args[0];
        } else if (args.length > 0) {
        	System.out.println("Invalid target environment specified");
        	return;
        }

        config = loadConfig(target);
       	
        if ("true".equals(config.get("storm.monitor.enabled").toString())) {
        	TaskHook.registerTo(config);
        }
        
       	if (isRunLocally()) {
       		submitTopologyLocal();
       	} else {
       		submitTopology();
       	}
       	
			// End topology execution code

    }
    
    public static void submitTopology() {
    
    	StormTopology topology = createTopology();
       	String topologyName = config.get("topology.name").toString();

       	try {
			StormSubmitter.submitTopology(topologyName, config, topology);
		} catch (AlreadyAliveException e) {
			log.severe("IngestionTopology","submitToplogy","Topology was already alive: "+e);
		} catch (InvalidTopologyException e) {
			log.severe("IngestionTopology","submitToplogy","Invalid topology: "+e);
		}

    }
    
    public static void submitTopologyLocal() {
    
    	StormTopology topology = createTopology();
       	String topologyName = config.get("topology.name").toString();

		try {
			getLocalCluster().submitTopology(topologyName, config, topology);
			
			int sleepMinutes;
			try {
				sleepMinutes = ((Integer) config.get("sleep.after.submit.minutes")).intValue();
			} catch (NumberFormatException e) {
				sleepMinutes = 1;
			}
			AlarmClock.go(sleepMinutes * 60000,5*60000);
			
			getLocalCluster().killTopology(topologyName);
			getLocalCluster().shutdown();
		} catch (AlreadyAliveException e) {
			log.severe("IngestionTopology","submitTopologyLocal","Topology was already alive: "+e);
		} catch (InvalidTopologyException e) {
			log.severe("IngestionTopology","submitTopologyLocal","Invalid topology: "+e);
		} catch (NotAliveException e) {
			log.severe("IngestionTopology","submitTopologyLocal","Topology is not yet alive: "+e);
		}

    }

    public static Config loadConfig(String env) {
        log.fine("IngestionTopology","submitToplogy","Initializing " + env + " topology");
        Properties props = new Properties();
        Config config = new Config();
        try {
            props.load(IngestionTopology.class.getResourceAsStream("/" + env + ".properties"));
           for (Object prop : props.keySet()) {
				Object value = props.get(prop);
				try {
					// see if it's an Integer
					int intValue = Integer.parseInt(String.valueOf(value).trim());
					config.put(prop.toString(), intValue);
				} catch (NumberFormatException e) {
					config.put(prop.toString(), value);					
				}
				
				log.fine("IngestionTopology","submitToplogy",prop.toString() + "=" + value);
			}
            config.put(Config.TOPOLOGY_FALL_BACK_ON_JAVA_SERIALIZATION, true);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static StormTopology createTopology() {

        TopologyBuilder builder = new TopologyBuilder();
		BoltDeclarer boltDeclarer;
		
		int parallelismHint = 0;

        parallelismHint = (Integer)config.get("KafkaReader.parallelismHint");
        builder.setSpout("KafkaReader", new KafkaReaderSpout(), parallelismHint);

        parallelismHint = (Integer)config.get("Archival.parallelismHint");
        boltDeclarer = builder.setBolt("Archival", new ArchivalBolt(), parallelismHint);
        boltDeclarer.shuffleGrouping("KafkaReader","ArchiveCopies");

        parallelismHint = (Integer)config.get("RecordParser.parallelismHint");
        boltDeclarer = builder.setBolt("RecordParser", new RecordParserBolt(), parallelismHint);
        boltDeclarer.shuffleGrouping("KafkaReader","RawRecords");

        parallelismHint = (Integer)config.get("ErrorLog.parallelismHint");
        boltDeclarer = builder.setBolt("ErrorLog", new ErrorLogBolt(), parallelismHint);
        boltDeclarer.shuffleGrouping("RecordParser","Errors");

        parallelismHint = (Integer)config.get("Account.parallelismHint");
        boltDeclarer = builder.setBolt("Account", new AccountBolt(), parallelismHint);
        boltDeclarer.fieldsGrouping("RecordParser","Accounts",new Fields("id"));

        parallelismHint = (Integer)config.get("NewAccountProcessor.parallelismHint");
        boltDeclarer = builder.setBolt("NewAccountProcessor", new NewAccountProcessorBolt(), parallelismHint);
        boltDeclarer.shuffleGrouping("Account","NewAccounts");

        parallelismHint = (Integer)config.get("EventProcessor.parallelismHint");
        boltDeclarer = builder.setBolt("EventProcessor", new EventProcessorBolt(), parallelismHint);
        boltDeclarer.shuffleGrouping("RecordParser","Events");

        return builder.createTopology();
    }
    
    public static IIngestionLogger getLogger() {
    	return log;
    }
    
    private static ILocalCluster getLocalCluster() {
    	return localCluster;
    }
    
    public static void setLocalCluster(ILocalCluster cluster) {
    	localCluster = cluster;
    }
    
    public static boolean isRunLocally() {
        return Boolean.parseBoolean(config.get("topology.run.locally").toString());
    }

	// Begin custom methods
	
	
	
	// End custom methods

}
