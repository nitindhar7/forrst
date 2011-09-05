package com.forrst.api;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.json.JSONObject;
import org.junit.Test;

public class HttpRequestTests {
	
	@Test
	public void testGet() {
		HttpRequest http = new HttpRequest();
		
		StringBuilder sampleJSON = new StringBuilder("{");
		sampleJSON.append("\"resp\": {");
		sampleJSON.append("\"user\": {");
		sampleJSON.append("\"name\": \"sample\",");
		sampleJSON.append("\"age\": 10");
		sampleJSON.append("},");
		sampleJSON.append("\"post\": {");
		sampleJSON.append("\"date\": \"2011/09/04\",");
		sampleJSON.append("\"user\": \"sample\"");
		sampleJSON.append("}}}");
		
		URL url = this.getClass().getClassLoader().getResource("sample_data.json");
		
		JSONObject sampleData = http.get(url.toString());
		
		TestCase.assertEquals(sampleJSON.toString(), sampleData.toString());
	}
	
	@Test
	public void testGetData() {
		HttpRequest http = new HttpRequest();
		
		StringBuilder sampleJSON = new StringBuilder("{");
		sampleJSON.append("\"resp\": {");
		sampleJSON.append("\"user\": {");
		sampleJSON.append("\"name\": \"sample\",");
		sampleJSON.append("\"age\": 10");
		sampleJSON.append("},");
		sampleJSON.append("\"post\": {");
		sampleJSON.append("\"date\": \"2011/09/04\",");
		sampleJSON.append("\"user\": \"sample\"");
		sampleJSON.append("}}}");
		
		URL url = this.getClass().getClassLoader().getResource("sample_data.json");
		
		JSONObject sampleData = http.getData(url.toString());
		
		TestCase.assertEquals(sampleJSON.toString(), sampleData.toString());
	}
	
	@Test
	public void testStringifyArgs() {
		HttpRequest http = new HttpRequest();
		
		Map<String,String> args = new HashMap<String,String>();
		args.put("a", "1");
		args.put("b", "2");
		args.put("b", "3");
		
		TestCase.assertEquals("?a=1&b=2&c=3", http.stringifyArgs(args));
	}

}
