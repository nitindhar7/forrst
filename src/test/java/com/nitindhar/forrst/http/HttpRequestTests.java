package com.nitindhar.forrst.http;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.google.common.base.Optional;
import com.nitindhar.forrst.util.ForrstAuthenticationException;

public class HttpRequestTests {

    private static final HttpRequest http = new HttpRequest(HttpProvider.ASYNC_HTTP_CLIENT);

    @SuppressWarnings("rawtypes")
    private static final Optional ABSENT = Optional.absent();

    @SuppressWarnings("unchecked")
    @Test (groups={"toBeFixed"})
    public void testGetData() {
        String sampleJSON = "{\"post\":{\"date\":\"2011/09/04\",\"user\":\"sample\"},\"user\":{\"age\":10,\"name\":\"sample\"}}";
        URL url = getClass().getClassLoader().getResource("sample_data.json");
        JSONObject sampleData = http.get(url.toString(), ABSENT);
        TestCase.assertEquals(sampleJSON, sampleData.toString());
    }

    @Test (groups={"toBeFixed"})
    public void testPost() throws MalformedURLException, ForrstAuthenticationException {
        String sampleJSON = "{\"post\":{\"date\":\"2011/09/04\",\"user\":\"sample\"},\"user\":{\"age\":10,\"name\":\"sample\"}}";
        URL url = getClass().getClassLoader().getResource("sample_data.json");
        JSONObject sampleData = http.post(url.toString(), null);
        TestCase.assertEquals(sampleJSON, sampleData.toString());
    }

    @Test (groups={"toBeFixed"})
    public void testPostData() throws ForrstAuthenticationException {
        String sampleJSON = "{\"post\":{\"date\":\"2011/09/04\",\"user\":\"sample\"},\"user\":{\"age\":10,\"name\":\"sample\"}}";
        URL url = getClass().getClassLoader().getResource("sample_data.json");
        JSONObject sampleData = http.postData(url.toString(), null);
        TestCase.assertEquals(sampleJSON, sampleData.toString());
    }

    @Test (groups={"ready"})
    public void testStringifyArgs() {
        Map<String,String> args = new HashMap<String,String>();
        args.put("a", "1");
        args.put("b", "2");
        args.put("c", "3");
        TestCase.assertEquals("b=2&c=3&a=1", http.stringifyArgs(args));
    }

}