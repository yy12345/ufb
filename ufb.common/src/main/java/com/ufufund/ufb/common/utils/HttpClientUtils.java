package com.ufufund.ufb.common.utils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpClientUtils {
	private static final Logger LOG = LoggerFactory.getLogger(HttpClientUtils.class);
	
    public static String get(String url,String charset){
        String result = null;
        CloseableHttpResponse response = null;
    	try{
        	CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity,charset);
            EntityUtils.consume(entity);
        }catch(IOException e){
        	LOG.error("调用get失败："+e.getMessage(), e);
        }finally{
        	if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                	LOG.error(e.getMessage(), e);
                }
            }
        }
        return result;
    }
    public static String post(String url,Map<String,String> map,String charset){
    	String result = null;
        
    	CloseableHttpResponse response = null;
        try {
        	CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            List <NameValuePair> nvps = map2NameValuePairs(map);
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, charset));
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity,charset);
            EntityUtils.consume(entity);
        }catch(IOException e){
        	LOG.error("调用post失败："+e.getMessage(), e);
        }finally {
        	if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                	LOG.error(e.getMessage(), e);
                }
            }
        }
        return result;
    }

    private static List<NameValuePair>  map2NameValuePairs(Map<String,String> map){
        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
        for (Entry<String, String> entry: map.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return nvps;
    }

    public static void main(String[] args) {
    	String url = "http://www.iteye.com/";
    	String charset = "utf-8";
    	String result = HttpClientUtils.get(url, charset);
    	LOG.info(result);
    }
}