package com.ufufund.ufb.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
/**
 * HTTPS请求工具类
 * 备注：https的GET方式不支持中文参数
 * @author ayis
 * 2015-03-01
 */
public class HttpsClientUtil{
	private final static Logger LOG = LoggerFactory.getLogger(HttpsClientUtil.class);
	
    private static final int CONNECT_TIMEOUT = 30000;  // 连接超时时间，单位：毫秒
 
    
    /**
     * post方式请求服务器(https协议)
     * @param url 请求地址
     * @param content 参数
     * @param charset 编码
     * @return
     */
    public static byte[] post(String url, String content, String charset) {
    	
    	HttpsURLConnection conn = null;
    	try {
//	        SSLContext sc = SSLContext.getInstance("SSL");
            SSLContext sc = SSLContext.getInstance("SSL", "SunJSSE");
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
				        new java.security.SecureRandom());
            
            URL console = new URL(url);
            conn = (HttpsURLConnection) console.openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(CONNECT_TIMEOUT);
	        conn.connect();
            // post start
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(content.getBytes(charset));
            out.flush();
            out.close();
            // post end
            
            InputStream is = conn.getInputStream();  
            if (is != null) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                is.close();
                conn.disconnect();
                return outStream.toByteArray();
            }
        } catch (Exception e) {
			LOG.error("调用post失败："+e.getMessage(), e);
		} finally{
			if(conn != null){
				try{
					conn.disconnect();
				}catch(Exception e){
				}
			}
		}
        return null;
    }
    
    /**
     * GET方式请求服务器
     * 备注：https请求参数不支持中文
     * @param url
     * @return
     */
    public static byte[] get(String url) {
    	
        HttpsURLConnection conn = null;
    	try{
//	    	SSLContext sc = SSLContext.getInstance("SSL");
	    	SSLContext sc = SSLContext.getInstance("SSL", "SunJSSE");
	        sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
	                new java.security.SecureRandom());
	     
	        URL console = new URL(url);
	        conn = (HttpsURLConnection) console.openConnection();
	        conn.setSSLSocketFactory(sc.getSocketFactory());
	        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
	        conn.setConnectTimeout(CONNECT_TIMEOUT);
	        conn.connect();
	        
	        InputStream is = conn.getInputStream();  
            if (is != null) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                is.close();
                conn.disconnect();
                return outStream.toByteArray();
            }
	    } catch (Exception e) {
	    	LOG.error("调用get失败："+e.getMessage(), e);
		}finally{
			if(conn != null){
				try{
					conn.disconnect();
				}catch(Exception e){
				}
			}
		}
        return null;
    }
    
    
    private static class TrustAnyTrustManager implements X509TrustManager {
        
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
 
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
 
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
	}
	
	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
	        public boolean verify(String hostname, SSLSession session) {
	        	return true;
	        }
	}
}
