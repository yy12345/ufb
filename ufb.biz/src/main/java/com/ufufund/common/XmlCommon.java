package com.ufufund.common;


import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class XmlCommon {

	/**
	 * JavaBean转换成xml 默认编码UTF-8
	 * 
	 * @param obj
	 * @param writer
	 * @return
	 */
	public static String convertToXml(Object obj) {
		return convertToXml(ObjectCommon.convertObject(obj), "UTF-8");
	}

	public static String convertToXmlGbk(Object obj) {
		return convertToXml(obj, "GBK");
	}

	/**
	 * JavaBean转换成xml
	 * 
	 * @param obj
	 * @param encoding
	 * @return
	 */
	private static String convertToXml(Object obj, String encoding) {
		StringWriter writer = null;
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.flush();
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	
}

/*
 * 
 * 
 * 　一.Jaxb处理java对象和xml之间转换常用的annotation有：
 * 
 * @XmlType
 * 
 * @XmlElement
 * 
 * @XmlRootElement
 * 
 * @XmlAttribute
 * 
 * @XmlAccessorType
 * 
 * @XmlAccessorOrder
 * 
 * @XmlTransient
 * 
 * @XmlJavaTypeAdapter 　二.常用annotation使用说明
 * 
 * 
 * @XmlType
 * 　　@XmlType用在class类的注解，常与@XmlRootElement，@XmlAccessorType一起使用。它有三个属性：name
 * 、propOrder、namespace，经常使用的只有前两个属性。如：
 * 
 * @XmlType(name = "basicStruct", propOrder = { "intValue", "stringArray",
 * "stringValue" ) 在使用@XmlType的propOrder 属性时，必须列出JavaBean对象中的所有属性，否则会报错。
 * 　　2.@XmlElement
 * 　　@XmlElement将java对象的属性映射为xml的节点，在使用@XmlElement时，可通过name属性改变java对象属性在xml中显示的名称
 * 。如：
 * 
 * 　　@XmlElement(name="Address")　　 　　private String yourAddress;
 * 　　3.@XmlRootElement 　　@XmlRootElement用于类级别的注解，对应xml的跟元素，常与 @XmlType 和
 * 
 * @XmlAccessorType一起使用。如：
 * 
 * 　　@XmlType 　　@XmlAccessorType(XmlAccessType.FIELD) 　　@XmlRootElement 　　public
 * class Address {} 　　4.@XmlAttribute
 * 　　@XmlAttribute用于把java对象的属性映射为xml的属性,并可通过name属性为生成的xml属性指定别名。如：
 * 　　@XmlAttribute(name="Country") 　　private String state; 　　5.@XmlAccessorType
 * 　
 * 　@XmlAccessorType用于指定由java对象生成xml文件时对java对象属性的访问方式。常与@XmlRootElement、@XmlType一起使用
 * 。它的属性值是XmlAccessType的4个枚举值，分　　　别为：
 * 
 * 　　XmlAccessType.FIELD:java对象中的所有成员变量
 * 
 * 　　XmlAccessType.PROPERTY：java对象中所有通过getter/setter方式访问的成员变量
 * 
 * 　　XmlAccessType.PUBLIC_MEMBER：java对象中所有的public访问权限的成员变量和通过getter/setter方式访问的成员变量
 * 
 * 　　XmlAccessType.NONE:java对象的所有属性都不映射为xml的元素
 * 
 * 　　注意：@XmlAccessorType的默认访问级别是XmlAccessType.PUBLIC_MEMBER，因此，
 * 如果java对象中的private成员变量设置了public权限的getter
 * /setter方法，就不要在　　　private变量上使用@XmlElement和
 * 
 * @XmlAttribute注解，否则在由java对象生成xml时会报同一个属性在java类里存在两次的错误
 * 。同理，如果@XmlAccessorType的访问权限
 * 　　　为XmlAccessType.NONE，如果在java的成员变量上使用了@XmlElement或
 * 
 * @XmlAttribute注解，这些成员变量依然可以映射到xml文件。
 * 
 * 　　6.@XmlAccessorOrder 　　@XmlAccessorOrder用于对java对象生成的xml元素进行排序。它有两个属性值：
 * 
 * 　　AccessorOrder.ALPHABETICAL：对生成的xml元素按字母书序排序
 * 
 * 　　XmlAccessOrder.UNDEFINED:不排序
 * 
 * 　　7.@XmlTransient 　　@XmlTransient用于标示在由java对象映射xml时，忽略此属性。即，在生成的xml文件中不出现此元素。
 * 
 * 　　8.@XmlJavaTypeAdapter
 * 　　@XmlJavaTypeAdapter常用在转换比较复杂的对象时，如map类型或者格式化日期等。使用此注解时
 * ，需要自己写一个adapter类继承XmlAdapter抽象类，并实现里面的方法。
 * 
 * 　　@XmlJavaTypeAdapter(value=xxx.class),value为自己定义的adapter类
 * 
 * 　　XmlAdapter如下：
 * 
 * public abstract class XmlAdapter<ValueType,BoundType> { // Do-nothing
 * constructor for the derived classes. protected XmlAdapter() {} // Convert a
 * value type to a bound type. public abstract BoundType unmarshal(ValueType v);
 * // Convert a bound type to a value type. public abstract ValueType
 * marshal(BoundType v); } 　三.
 */

