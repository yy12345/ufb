package com.ufufund.trade.ufb.biz.util.test;

import java.util.regex.Pattern;

import org.junit.Test;

import com.ufufund.ufb.biz.util.StudentExcelUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentExcelUtilTest {

//	@Test
	public void test(){
		String orgname = "";
		String typeName = "";
		String name = StudentExcelUtil.getTemplateName(orgname, typeName,"");
		log.info("name="+name);
	}
	
	@Test
	public void test1(){
		String str = String.valueOf(System.currentTimeMillis());
		log.info("str="+str);
	}
	   static boolean match(String text, String reg) {
	      return Pattern.compile(reg).matcher(text).matches();
	  }
	 
	 public static void main(String[] args) {
		 boolean  b= match("03356-48497892-13225","^((\\d{3,5}-\\d{6,8})(-\\d{1,4})?)$");
		 System.out.println(b);
	}
}
