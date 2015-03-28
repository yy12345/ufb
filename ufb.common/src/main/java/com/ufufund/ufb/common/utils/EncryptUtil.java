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
        return encrypt("md5", inputText, null);  
    }
    
	/**
	 * md5加密   
	 * @param inputText
	 * @param charset
	 * @return
	 */
    public static String md5(String inputText, String charset) {  
        return encrypt("md5", inputText, charset);  
    } 
    
    /**
     * sha加密
     * @param inputText
     * @return
     */
    public static String sha(String inputText) {  
        return encrypt("sha-1", inputText, null);  
    } 
    
    /**
     * sha加密 
     * @param inputText
     * @param charset
     * @return
     */
    public static String sha(String inputText, String charset) {  
        return encrypt("sha-1", inputText, charset);  
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
    private static String encrypt(String algorithmName, String inputText, String charset) {  
        if (inputText == null) {  
            throw new IllegalArgumentException("请输入要加密的内容");  
        }  
        if (algorithmName == null || "".equals(algorithmName.trim())) {  
            algorithmName = "md5";  
        }  
        String encryptText = null;  
        try {  
            MessageDigest m = MessageDigest.getInstance(algorithmName);  
            byte[] source = null;
            if(charset == null || "".equals(charset)){
            	source = inputText.getBytes();
            }else{
            	source = inputText.getBytes(charset);
            }
            byte s[] = m.digest(source);
//          or ...
//          m.update(source);  
//          byte s[] = m.digest();
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
    	String str = "acc_time=20091225091010&bank_buyer_name=测试用户A&bank_card_no=6226095710221222&bank_fund_no=A2013072812000001&bank_kind_no=007&branch_bank_name=招商银行杭州分行&buyer_cert_no=360203197707136088&buyer_cert_type=1&buyer_name=测试用户A&input_charset=GBK&mobile_number=13812345678&partner=1900000107&service_version=1.0&sign_key_index=1&sign_type=MD5&spbill_create_ip=192.168.1.1&transaction_id=OP2013072812000001&user_type=1&key=111111";
        //md5加密测试  
//        String md5_1 = md5(str, "gbk");  
//        String md5_2 = md5(str, "utf-8");  
//        String md5_3 = md5(str, "iso-8859-1");  
//        System.out.println(md5_1 + "\n" + md5_2 + "\n" + md5_3);  
//        System.out.println("md5 length: " + md5_1.length());  
//        //sha加密测试  
//        String sha_1 = sha(str, "gbk");  
//        String sha_2 = sha(str, "utf-8");
//        String sha_3 = sha(str, "iso-8859-1");
//        System.out.println(sha_1 + "\n" + sha_2 + "\n" + sha_3);  
//        System.out.println("sha length: " + sha_1.length());  
        
        System.out.println(Integer.toHexString(255));
    } 
}
