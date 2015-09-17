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
import com.ufufund.ufb.model.vo.QueryCustPayInfo;
import com.ufufund.ufb.model.vo.QueryCustplandetail;
import com.ufufund.ufb.model.vo.QueryOrgPayInfo;
import com.ufufund.ufb.model.vo.QueryOrgplan;
import com.ufufund.ufb.model.vo.QueryOrgplandetail;
import com.ufufund.ufb.model.vo.QueryOrgplandetailcharge;

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
	// @Test
	public void testrisMobileRegister() {
		System.out.println("autotradeMapper.getGradeid :" + sequenceMapper.getGradeid());
	}

	// @Test
	public void testsgetPlanDetailid() {
		System.out.println("autotradeMapper.getPlanDetailid :" + sequenceMapper.getPlanDetailid());
	}

	// @Test
	public void testgetQueryOrggplan() {
		List<QueryOrgplan> list = orgQueryManager.getQueryOrgplan("101");
		System.out.println(list.size());
		for (QueryOrgplan queryOrggplan : list) {
			System.out.println(queryOrggplan.toString());
		}
	}

	// @Test
	public void testgetQueryOrggplandetail() {
		List<QueryOrgplandetail> list = orgQueryManager.getQueryOrgplandetail("101", "72201509152B39QNQI0A2S20");
		System.out.println(list.size());
		for (QueryOrgplandetail queryOrggplandetail : list) {
			System.out.println(queryOrggplandetail.toString());
		}
	}

	//@Test
	public void testgetQueryOrggplandetailcharge() {
		List<QueryOrgplandetailcharge> list = orgQueryManager.getQueryOrgplandetailcharge("101", "72201509152B39QNQI0A2S20", "34");
		System.out.println(list.size());
		for (QueryOrgplandetailcharge queryOrggplandetailcharge : list) {
			System.out.println(queryOrggplandetailcharge.toString());
		}
	}
	//@Test
	public void testgetQueryOrgByGrade() {
		QueryOrgPayInfo queryOrgPayInfo = orgQueryManager.getQueryOrgByGrade("101", "102");
		System.out.println(queryOrgPayInfo.toString());
	}
	//@Test
	public void testgetQueryOrgByTerm() {
		QueryOrgPayInfo queryOrgPayInfo = orgQueryManager.getQueryOrgByTerm("101", "102A");
		System.out.println(queryOrgPayInfo.toString());
	}
	//@Test
	public void testgetgetQueryOrgByMonth() {
		QueryOrgPayInfo queryOrgPayInfo = orgQueryManager.getQueryOrgByMonth("101", "201509");
		if(queryOrgPayInfo!=null){
			System.out.println(queryOrgPayInfo.toString());
		}
	}
	
	//@Test
	public void testgetQueryCustPayInfo() {
		QueryCustPayInfo queryOrgPayInfo = orgQueryManager.getQueryCustNotPayInfo("001");
		if(queryOrgPayInfo!=null){
			System.out.println(queryOrgPayInfo.toString());
		}
	}
	@Test
	public void testgetQueryCustplandetail() {
		List<QueryCustplandetail>  list = orgQueryManager.getQueryCustplandetail("001");
		System.out.println(list.size());
		for (QueryCustplandetail queryCustplandetail : list) {
			System.out.println(queryCustplandetail.toString());
		}
	}
}
