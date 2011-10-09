package com.forrst.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class HttpRequest {

	/**
	 * GET data from Forrst using the request URI
	 * without URL parameters
	 * 
	 * @param requestURI Forrst URI requested
	 * @return JSONObject containing a response
	 */
	public JSONObject get(String requestURI) {
		return getData(requestURI);
	}
	
	/**
	 * GET data from Forrst using the request URI
	 * with URL parameters
	 * 
	 * @param requestURI Forrst URI requested
	 * @param params Map of URL parameters and their values
	 * @return JSONObject containing a response
	 */
	public JSONObject get(String requestURI, Map<String,String> params) {
		return getData(requestURI + "?" + stringifyArgs(params));
	}
	
	/**
	 * POST data from Forrst using the request URI
	 * 
	 * @param requestURI Forrst URI requested
	 * @param params Map of URL parameters and their values
	 * @return JSONObject containing a response
	 */
	public JSONObject post(String requestURI, Map<String,String> params) {
		return postData(requestURI, params);
	}
	
	/**
	 * Workhorse method that POSTs data from a URL and
	 * returns the result as a JSON object
	 * 
	 * @param requestURI The Forrst endpoint requested
	 * @return JSONObject containing the full Forrst API response
	 */
	protected JSONObject getData(String requestURI) {
		String jsonResult = "";
		
		JSONObject json = null;

		try {
			URL url = new URL(requestURI);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			
			String responseLine;
			while ((responseLine = in.readLine()) != null) {
				jsonResult += responseLine;
			}

			in.close();
			
			json = new JSONObject(jsonResult);
			json = json.getJSONObject("resp");
		} catch (MalformedURLException e) {
			throw new RuntimeException("Invalid URL requested", e);
		} catch (IOException e) {
			throw new RuntimeException("Could not read from requested stream", e);
		} catch (JSONException e) {
			throw new RuntimeException("JSON could not be formed", e);
		}
		
		return json;
	}
	
	/**
	 * Workhorse method that GETs data from a URL and
	 * returns the result as a JSON object
	 * 
	 * @param requestURI The Forrst endpoint requested
	 * @return JSONObject containing the full Forrst API response
	 */
	protected JSONObject postData(String requestURI, Map<String,String> params) {
		String jsonResult = "";
		
		JSONObject json = null;

		try {
			URL url = new URL(requestURI);
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		    urlConn.setDoOutput(true);
		    
		    OutputStreamWriter osw = new OutputStreamWriter(urlConn.getOutputStream());
		    osw.write(stringifyArgs(params));
		    osw.flush();

		    BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			
			String responseLine;
			while ((responseLine = in.readLine()) != null) {
				jsonResult += responseLine;
			}

			osw.close();
			in.close();
			
			json = new JSONObject(jsonResult);
			json = json.getJSONObject("resp");
		} catch (MalformedURLException e) {
			throw new RuntimeException("Invalid URL requested", e);
		} catch (IOException e) {
			throw new RuntimeException("Could not read from requested stream", e);
		} catch (JSONException e) {
			throw new RuntimeException("JSON could not be formed", e);
		}
		
		return json;
	}
	
	/**
	 * Creates a string version of the URL params
	 * 
	 * @param params
	 * @return
	 */
	protected String stringifyArgs(Map<String,String> params) {
		StringBuilder argString = new StringBuilder();

		for(String key : params.keySet()) {
			argString.append(key);
			argString.append('=');
			argString.append(params.get(key));
			argString.append('&');
		}

		String stringifiedArgs = argString.toString();

		return stringifiedArgs.substring(0, stringifiedArgs.length() - 1);
	}

}
