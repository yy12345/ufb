package com.ufufund.ufb.common.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * 处理java与xml绑定的工具类
 * @author ayis
 * 2015年3月22日
 */
@SuppressWarnings("rawtypes")
public class JaxbUtil {

	public static final boolean JAXB_FORMATTED_OUTPUT = true;
	public static final String JAXB_ENCODING = "utf-8";
	
	/**
	 * 将对象转化为xml
	 * @param obj 
	 * @param clazzes
	 * @return
	 */
	public static String toXml(Object obj, Class... clazzes) {  
        String result = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(clazzes);  
            Marshaller marshaller = context.createMarshaller();  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, JAXB_FORMATTED_OUTPUT);  
            marshaller.setProperty(Marshaller.JAXB_ENCODING, JAXB_ENCODING);  
  
            StringWriter writer = new StringWriter();  
            marshaller.marshal(obj, writer);  
            result = writer.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return result;  
    }  
  
	/**
	 * 将xml转化为绑定的对象
	 * @param xml
	 * @param clazzes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toBean(String xml, Class... clazzes) {  
        T t = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(clazzes);  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            t = (T) unmarshaller.unmarshal(new StringReader(xml));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return t;  
    }  
	
    
    /**
     * unmarshal时，预处理xml
     * @param xml
     * @param clazz
     * @return
     */
	public static String buildRequestXml(String xml, Class clazz){
    	String xsiName = getBoundTypeName(clazz);
    	String xmlRequestType = "<Request xsi:type=\""+xsiName+"\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">";
    	return xml.replace("<Request>", xmlRequestType);
    }
    
    /**
     * unmarshal时，预处理xml
     * @param xml
     * @param clazz
     * @return
     */
	public static String buildResponseXml(String xml, Class clazz){
    	String xsiName = getBoundTypeName(clazz);
    	String xmlResponseType = "<Response xsi:type=\""+xsiName+"\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">";
    	return xml.replace("<Response>", xmlResponseType);
    }
    
    /**
     * 获取类在xml中的xsi类型<br/>
     * 将类名的首字母转小写
     * @param clazz
     * @return
     */
	public static String getBoundTypeName(Class clazz){
    	String clazzName = clazz.getName();
    	clazzName = clazzName.substring(clazzName.lastIndexOf('.')+1);
    	return String.valueOf((char)(clazzName.charAt(0) + ('a' - 'A'))) + clazzName.substring(1);
    }
}
