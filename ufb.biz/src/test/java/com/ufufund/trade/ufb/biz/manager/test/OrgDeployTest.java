package com.ufufund.trade.ufb.biz.manager.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ufufund.ufb.biz.manager.SequenceManager;
import com.ufufund.ufb.biz.manager.org.OrgQueryManager;
import com.ufufund.ufb.model.action.org.QueryOrggplan;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-ufb-biz.xml" })
@TransactionConfiguration(defaultRollback = false)
public class OrgDeployTest {
	
	

	@Autowired
	private SequenceManager sequenceMapper;
	
	@Autowired
	private OrgQueryManager orgQueryManager;

	
	/*
	 * 
	 * 测试getAutotradeSequence
	 */
	//@Test
	public void testrisMobileRegister(){
		System.out.println("autotradeMapper.getGradeid :" + sequenceMapper.getGradeid());
	}
	//@Test
	public void testsgetPlanDetailid(){
		System.out.println("autotradeMapper.getPlanDetailid :" + sequenceMapper.getPlanDetailid());
	}
	//@Test
	public void testgetQueryOrggplan(){
		List<QueryOrggplan> list = orgQueryManager.getQueryOrggplan("101");
		System.out.println(list.size());
		for(QueryOrggplan queryOrggplan :list){
			System.out.println(queryOrggplan.toString());
		}
	}
}
	
