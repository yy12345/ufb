package com.ufufund.test.test2;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.ufufund.test.test3.LightTest.Light;
import com.ufufund.common.XmlCommon;

public class Test {

	public static void main(String[] args) {
		
		System.out.println("".length());
		
		TestBean a = new TestBean();
		a.setDescription("aaadwd");
		//a.setSex("nan");
		// a.setAname("");
		//a.setAllLight(Light.YELLOW);
		ListBean s1 = new ListBean();
		s1.setNaa("aaa1");
		s1.setNbb("bbb1");
		ListBean s2 = new ListBean();
		s2.setNaa("aaa2");
		s2.setNbb("bbb2");
		a.getListBean().add(s1);
		a.getListBean().add(s2);
		System.out.println(XmlCommon.convertToXml(a));
		System.out.println(a.getClass());
		System.out.println(a.getClass().getSuperclass());
		System.out.println(a.getClass().getSuperclass().getClass());
		// Class
		// c=Class.forName(a.getClass());//把要使用的类加载到内存中,并且把有关这个类的所有信息都存放到对象c中
//		Field f[] = a.getClass().getDeclaredFields();// 把属性的信息提取出来，并且存放到field类的对象中，因为每个field的对象只能存放一个属性的信息所以要用数组去接收
//		for (int i = 0; i < f.length; i++) {
//			System.out.println("属性的名称是:" + f[i].getName());// 获得属性的名字
//			System.out.println("属性的类型是:" + f[i].getType());// 获得属性的类型
//			int mod = f[i].getModifiers();// 获得修饰符的常量总和
//			System.out.println(mod);
//			System.out.println("属性的修饰符有:" + Modifier.toString(mod));// modifier类可以根据常量总和去计算到底有哪些修饰符
//			System.out
//					.println("-------------------------------------------------------");
//		}
//		Field s[] = a.getClass().getSuperclass().getDeclaredFields();// 把属性的信息提取出来，并且存放到field类的对象中，因为每个field的对象只能存放一个属性的信息所以要用数组去接收
//		for (int i = 0; i < s.length; i++) {
//			System.out.println("属性的名称是:" + s[i].getName());// 获得属性的名字
//			System.out.println("属性的类型是:" + s[i].getType());// 获得属性的类型
//			int mod = s[i].getModifiers();// 获得修饰符的常量总和
//			System.out.println(mod);
//			System.out.println("属性的修饰符有:" + Modifier.toString(mod));// modifier类可以根据常量总和去计算到底有哪些修饰符
//			System.out
//					.println("-------------------------------------------------------");
//		}
		
		
		
		
	}

	/*
	 * 
	 * Marshaller.JAXB_FORMATTED_OUTPUT
	 * �����Ƿ���ת����xmlʱͬʱ���и�ʽ����������ǩ�Զ����У�������һ�е�xml��
	 * Marshaller.JAXB_ENCODING xml�ı��뷽ʽ
	 */

	/**
	 * JavaBeanת����xml
	 * 
	 * @param obj
	 * @param encoding
	 * @return
	 */
	public static String convertToXml(Object obj, String encoding) {
		String result = null;
		StringWriter writer = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			// marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
			// if (reader != null) {
			// reader.close();
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
