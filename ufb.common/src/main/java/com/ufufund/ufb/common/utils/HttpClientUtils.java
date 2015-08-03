package com.ufufund.ufb.common.utils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ufufund.ufb.common.exception.SysException;


public class HttpClientUtils {
	private static final Logger LOG = LoggerFactory.getLogger(HttpClientUtils.class);
	
    public static String get(String url, String charset){
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
        	throw new SysException("调用get失败："+e.getMessage());
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
    
    public static String post(String url, Map<String,String> map, String charset){
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
        	throw new SysException("调用post失败："+e.getMessage());
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
    
    public static String post(String url, String dataStr, String charset){
    	String result = null;
        
    	CloseableHttpResponse response = null;
        try {
        	CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(dataStr, charset));
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity,charset);
            EntityUtils.consume(entity);
        }catch(IOException e){
        	LOG.error("调用post失败："+e.getMessage(), e);
        	throw new SysException("调用post失败："+e.getMessage());
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
    
    /**
     * 下载文件
     * @param url 下载地址
     * @param filepath 保存路径
     * @return
     */
    public static void down(String url, String filepath){
    	CloseableHttpResponse response = null;
     	try{
         	CloseableHttpClient httpClient = HttpClients.createDefault();
             HttpGet httpGet = new HttpGet(url);
             
             response = httpClient.execute(httpGet);
             HttpEntity entity = response.getEntity();
             InputStream is = entity.getContent();
             
             File file = new File(filepath);  
             file.getParentFile().mkdirs();  
             FileOutputStream fileout = new FileOutputStream(file);  
             /** 
              * 根据实际运行效果 设置缓冲区大小 
              */  
             int cache = 1024*2;
             byte[] buffer=new byte[cache];  
             int ch = 0;  
             while ((ch = is.read(buffer)) != -1) {  
                 fileout.write(buffer,0,ch);  
             }  
             is.close();  
             fileout.flush();  
             fileout.close();
         }catch(IOException e){
         	LOG.error("下载文件失败："+e.getMessage(), e);
         	throw new SysException("下载文件失败："+e.getMessage());
         }finally{
         	 if (response!=null){
	             try {
	                 response.close();
	             } catch (IOException e) {
	             	LOG.error(e.getMessage(), e);
	             }
	         }
         }
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