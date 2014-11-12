package com.intersys.mock;

import java.util.Map;

import backtype.storm.ILocalCluster;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.ClusterSummary;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.KillOptions;
import backtype.storm.generated.NotAliveException;
import backtype.storm.generated.RebalanceOptions;
import backtype.storm.generated.StormTopology;
import backtype.storm.generated.SubmitOptions;
import backtype.storm.generated.TopologyInfo;

public class MockedLocalCluster implements ILocalCluster {

	public MockedLocalCluster() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void submitTopology(String topologyName, Map conf,
			StormTopology topology) throws AlreadyAliveException,
			InvalidTopologyException {
		// TODO Auto-generated method stub

	}

	@Override
	public void submitTopologyWithOpts(String topologyName, Map conf,
			StormTopology topology, SubmitOptions submitOpts)
			throws AlreadyAliveException, InvalidTopologyException {
		// TODO Auto-generated method stub

	}

	@Override
	public void killTopology(String topologyName) throws NotAliveException {
		// TODO Auto-generated method stub

	}

	@Override
	public void killTopologyWithOpts(String name, KillOptions options)
			throws NotAliveException {
		// TODO Auto-generated method stub

	}

	@Override
	public void activate(String topologyName) throws NotAliveException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivate(String topologyName) throws NotAliveException {
		// TODO Auto-generated method stub

	}

	@Override
	public void rebalance(String name, RebalanceOptions options)
			throws NotAliveException {
		// TODO Auto-generated method stub

	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTopologyConf(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StormTopology getTopology(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClusterSummary getClusterInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TopologyInfo getTopologyInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map getState() {
		// TODO Auto-generated method stub
		return null;
	}

}
