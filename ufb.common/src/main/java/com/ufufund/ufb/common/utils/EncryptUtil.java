package com.ufufund.ufb.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptUtil {
	private static final Logger LOG = LoggerFactory.getLogger(EncryptUtil.class);
	
	/**
     * md5加密 
     * @param inputText
     * @return
     */
    public static String md5(String inputText) {  
        return digest("md5", inputText, null);  
    }
	
	/**
	 * md5加密   
	 * @param inputText
	 * @param charset
	 * @return
	 */
    public static String md5(String inputText, String charset) {  
        return digest("md5", inputText, charset);  
    } 
    
    /**
     * md5加密
     * @param input
     * @return
     */
    public static String md5(byte[] inputBytes){
    	return digest("md5", inputBytes);
    }
    
    
    /**
     * sha加密
     * @param inputText
     * @return
     */
    public static String sha(String inputText) {  
        return digest("sha-1", inputText, null);  
    } 
    
    /**
     * sha加密 
     * @param inputText
     * @param charset
     * @return
     */
    public static String sha(String inputText, String charset) {  
        return digest("sha-1", inputText, charset);  
    }  
    
    /**
     * sha加密
     * @param input
     * @return
     */
    public static String sha(byte[] inputBytes){
    	return digest("sha-1", inputBytes);
    }
    
    /** 
     * md5或者sha-1加密 
     * @param algorithmName  加密算法名称：md5或者sha-1，不区分大小写 
     * @param inputText  要加密的文本内容 
     * @param charset 
     * @return 
     */  
    private static String digest(String algorithmName, String inputText, String charset) {  
    	String encryptText = null;
    	try {   
    		byte[] source = null;
            if(charset == null || "".equals(charset)){
            	source = inputText.getBytes();
            }else{
            	source = inputText.getBytes(charset);
            }
            encryptText = digest(algorithmName, source);
        } catch (UnsupportedEncodingException e) {  
        	LOG.error(e.getMessage(), e);
        }  
        return encryptText;
    }  
    
    /**
     * md5或者sha-1加密 
     * @param algorithmName 
     * @param input
     * @return
     */
    private static String digest(String algorithmName, byte[] input){
    	String encryptText = null;
    	try {
			MessageDigest m = MessageDigest.getInstance(algorithmName);
			byte[] s = m.digest(input);
			encryptText = hex(s);
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e.getMessage(), e);
		}
    	return encryptText;
    }
  
    // 返回十六进制字符串  
    private static String hex(byte[] arr) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < arr.length; ++i) {  
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));  
        }  
        return sb.toString();  
    }  
    
    public static void main(String args[]){
    	
    	String str = "abc321";
    	String value1 = EncryptUtil.md5(str);
    	String value2 = EncryptUtil.md5(str, "utf-8");
    	
    	System.out.println("value1="+value1);
    	System.out.println("value2="+value2);
    }
}
