package com.forrst.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class HttpRequestTests {
	
	@Test (groups={"ready"})
	public void testGet() throws MalformedURLException {
		HttpRequest http = new HttpRequest();
		
		String sampleJSON = "{\"post\":{\"date\":\"2011/09/04\",\"user\":\"sample\"},\"user\":{\"age\":10,\"name\":\"sample\"}}";
		URL url = getClass().getClassLoader().getResource("sample_data.json");
		JSONObject sampleData = http.get(url.toString());
		
		TestCase.assertEquals(sampleJSON, sampleData.toString());
	}
	
	@Test (groups={"ready"})
	public void testGetData() {
		HttpRequest http = new HttpRequest();
		
		String sampleJSON = "{\"post\":{\"date\":\"2011/09/04\",\"user\":\"sample\"},\"user\":{\"age\":10,\"name\":\"sample\"}}";
		URL url = getClass().getClassLoader().getResource("sample_data.json");
		JSONObject sampleData = http.getData(url.toString());
		
		TestCase.assertEquals(sampleJSON, sampleData.toString());
	}
	
	@Test (groups={"ready"})
	public void testStringifyArgs() {
		HttpRequest http = new HttpRequest();
		
		Map<String,String> args = new HashMap<String,String>();
		args.put("a", "1");
		args.put("b", "2");
		args.put("c", "3");
		
		TestCase.assertEquals("?b=2&c=3&a=1", http.stringifyArgs(args));
	}

}
