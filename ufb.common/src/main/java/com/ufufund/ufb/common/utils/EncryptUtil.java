package com.ufufund.ufb.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptUtil {
	private static final Logger LOG = LoggerFactory.getLogger(EncryptUtil.class);

	// md5加密  
    public static String md5(String inputText) {  
        return encrypt(inputText, "md5");  
    }  
  
    // sha加密  
    public static String sha(String inputText) {  
        return encrypt(inputText, "sha-1");  
    }  
  
    /** 
     * md5或者sha-1加密 
     *  
     * @param inputText 
     *            要加密的内容 
     * @param algorithmName 
     *            加密算法名称：md5或者sha-1，不区分大小写 
     * @return 
     */  
    private static String encrypt(String inputText, String algorithmName) {  
        if (inputText == null || "".equals(inputText.trim())) {  
            throw new IllegalArgumentException("请输入要加密的内容");  
        }  
        if (algorithmName == null || "".equals(algorithmName.trim())) {  
            algorithmName = "md5";  
        }  
        String encryptText = null;  
        try {  
            MessageDigest m = MessageDigest.getInstance(algorithmName);  
//            m.update(inputText.getBytes("UTF8"));  
//            byte s[] = m.digest();  
            byte s[] = m.digest(inputText.getBytes("UTF8"));
            encryptText = hex(s);  
        } catch (NoSuchAlgorithmException e) {  
            LOG.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {  
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
    
    public static void main(String[] args) {  
        //md5加密测试  
        String md5_1 = md5("123");  
        String md5_2 = md5("abc");  
        System.out.println(md5_1 + "\n" + md5_2);  
        System.out.println("md5 length: " + md5_1.length());  
        //sha加密测试  
        String sha_1 = sha("123");  
        String sha_2 = sha("abc");  
        System.out.println(sha_1 + "\n" + sha_2);  
        System.out.println("sha length: " + sha_1.length());  
    } 
}
