package com.ufufund.trade.ufb.biz.util.test;

import org.junit.Test;

import com.ufufund.ufb.biz.util.StudentExcelUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentExcelUtilTest {

//	@Test
	public void test(){
		String orgname = "";
		String typeName = "";
		String name = StudentExcelUtil.getTemplateName(orgname, typeName);
		log.info("name="+name);
	}
	
	@Test
	public void test1(){
		String str = String.valueOf(System.currentTimeMillis());
		log.info("str="+str);
	}
}
