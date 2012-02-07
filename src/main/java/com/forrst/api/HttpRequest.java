package com.forrst.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONException;
import org.json.JSONObject;

import com.forrst.api.util.ForrstAuthenticationException;

public class HttpRequest {
    
    private static final String ENCODING = "UTF-8";

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
	 * @throws ForrstAuthenticationException when authentication fails
	 */
	public JSONObject post(String requestURI, Map<String,String> params) throws ForrstAuthenticationException {
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
			URLConnection urlConn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), ENCODING));
			
			String responseLine;
			while ((responseLine = in.readLine()) != null) {
				jsonResult += responseLine;
			}

			in.close();

			json = new JSONObject(jsonResult.trim());
			json = json.getJSONObject("resp");
		} catch (MalformedURLException e) {
			throw new RuntimeException("Invalid URL requested", e);
		} catch (IOException e) {
			throw new RuntimeException("Could not read from requested stream", e);
		} catch (JSONException e) {
			throw new RuntimeException("JSON [" + jsonResult + "] could not be formed for URI: [" + requestURI + "]", e);
		}
		
		return json;
	}
	
	/**
	 * Workhorse method that GETs data from a URL and
	 * returns the result as a JSON object
	 * 
	 * @param requestURI The Forrst endpoint requested
	 * @return JSONObject containing the full Forrst API response
	 * @throws ForrstAuthenticationException when authentication fails
	 */
	protected JSONObject postData(String requestURI, Map<String,String> params) throws ForrstAuthenticationException {
		String jsonResult = "";
		
		JSONObject json = null;
		HttpsURLConnection urlConnHttps = null;
		HttpURLConnection urlConn = null;

		try {
			URL url = new URL(requestURI);
			
			if (url.getProtocol().toLowerCase().equals("https")) {
                trustAllHosts();
                urlConnHttps = (HttpsURLConnection) url.openConnection();
                urlConnHttps.setHostnameVerifier(DO_NOT_VERIFY);
                urlConn = urlConnHttps;
                
            } else {
                urlConn = (HttpURLConnection) url.openConnection();
            }

		    urlConn.setDoOutput(true);
		    
		    OutputStreamWriter osw = new OutputStreamWriter(urlConn.getOutputStream());
		    osw.write(stringifyArgs(params));
		    osw.flush();
		    
		    BufferedReader in = null;
		    
		    try {
		        in = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), ENCODING));
		    } catch (IOException e) {
		        throw new ForrstAuthenticationException("Could not authenticate");
		    }
			
			String responseLine;
			while ((responseLine = in.readLine()) != null) {
				jsonResult += responseLine;
			}

			osw.close();
			in.close();
			
			json = new JSONObject(jsonResult.trim());
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
	    if(params == null)
	        return "";
	    
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
	
	/**
     * always verify the host - dont check for certificate
     */
    protected final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

   /**
    * Trust every server - dont check for any certificate
    * 1. Create a trust manager that does not validate certificate chains
    * 2. Install the all-trusting trust manager
    */
    protected static void trustAllHosts() {
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }
        
            public void checkClientTrusted(X509Certificate[] chain, String authType) {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) {}
        }};

        SSLContext sc;
        try {
            sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error installing all-trusting trust manager: algorithm not found", e);
        } catch (KeyManagementException e) {
            throw new RuntimeException("Error installing all-trusting trust manager: problems managing key", e);
        }
    }

}
