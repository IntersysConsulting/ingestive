package com.intersys.data;

import java.util.StringTokenizer;

import org.codehaus.jettison.json.JSONObject;

import com.intersys.exception.GoodRecordException;
import com.intersys.exception.ParseException;

public class RecordParser {

	public void parse(String record) throws ParseException, GoodRecordException {
	
		try {
			StringTokenizer st = new StringTokenizer(record);
			
			String account = st.nextToken();
			Integer type = Integer.parseInt(st.nextToken());
			Long time = Long.parseLong(st.nextToken());
			
			JSONObject jobj = new JSONObject();
			jobj.put("account", account);
			jobj.put("type", type);
			jobj.put("time", time);

			throw new GoodRecordException(jobj, account, type, time);
			
		} catch (GoodRecordException e) {
			throw e;
		} catch (Exception e) {
			throw new ParseException(record, e.getMessage());
		}
		
		
	}
	
}
