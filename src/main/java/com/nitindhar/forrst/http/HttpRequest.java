package com.nitindhar.forrst.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.collections.MapUtils;
import org.json.JSONObject;

import com.google.common.base.Optional;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.Response;
import com.nitindhar.forrst.util.ForrstAuthenticationException;

public class HttpRequest {

    private static final int MAX_HTTP_GET_WAIT = 2;
    private static final String ENCODING = "UTF-8";

    private AsyncHttpClient asyncHttpClient;
    private final HttpProvider httpProvider;

    public HttpRequest(HttpProvider httpProvider) {
        this.httpProvider = httpProvider;
        if(httpProvider == HttpProvider.ASYNC_HTTP_CLIENT) {
            asyncHttpClient = new AsyncHttpClient();
        }
    }

    /**
     * Workhorse method that POSTs data from a URL and
     * returns the result as a JSON object
     *
     * @param requestURI The Forrst endpoint requested
     * @return JSONObject containing the full Forrst API response
     */
    @SuppressWarnings("deprecation")
    public JSONObject get(String requestURI, Optional<Map<String,String>> params) {
        JSONObject json = null;

        try {
            if(httpProvider == HttpProvider.ASYNC_HTTP_CLIENT) {
                BoundRequestBuilder builder = asyncHttpClient.prepareGet(requestURI);
                if(params.isPresent()) {
                    Map<String,String> queryParams = params.get();
                    for(String key : queryParams.keySet()) {
                        builder.addQueryParameter(URLEncoder.encode(key), URLEncoder.encode(queryParams.get(key)));
                    }
                }
                Future<Response> f = builder.execute();
                Response resp = f.get(MAX_HTTP_GET_WAIT, TimeUnit.SECONDS);

                json = new JSONObject(resp.getResponseBody());
            } else {
                String jsonResult = "";

                if(params.isPresent()) {
                    requestURI = requestURI + "?" + stringifyArgs(params.get());
                }

                URL url = new URL(requestURI);
                URLConnection urlConn = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), ENCODING));

                String responseLine;
                while ((responseLine = in.readLine()) != null) {
                    jsonResult += responseLine;
                }

                in.close();

                json = new JSONObject(jsonResult.trim());
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to get data from: " + requestURI, e);
        }

        return json;
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
     * Workhorse method that GETs data from a URL and
     * returns the result as a JSON object
     *
     * @param requestURI The Forrst endpoint requested
     * @return JSONObject containing the full Forrst API response
     * @throws ForrstAuthenticationException when authentication fails
     */
    public JSONObject postData(String requestURI, Map<String,String> params) throws ForrstAuthenticationException {
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
            } catch (Exception e) {
                throw new RuntimeException("Unable to post data to: " + requestURI, e);
            }

        return json;
    }

    /**
     * Creates a string version of the URL params
     *
     * @param params
     * @return
     */
    public String stringifyArgs(Map<String,String> params) {
        StringBuilder argString = new StringBuilder();

        try {
            if(MapUtils.isEmpty(params)) {
                return "";
            }

            for(String key : params.keySet()) {
                argString.append(URLEncoder.encode(key, ENCODING));
                argString.append('=');
                argString.append(URLEncoder.encode(params.get(key), ENCODING));
                argString.append('&');
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unable to stringify parameters: " + params.toString(), e);
        }

        return argString.toString().substring(0, argString.length() - 1);
    }

    /**
     * always verify the host - dont check for certificate
     */
    protected final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        @Override
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
        TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[] {};
                }

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {}
            }
        };

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