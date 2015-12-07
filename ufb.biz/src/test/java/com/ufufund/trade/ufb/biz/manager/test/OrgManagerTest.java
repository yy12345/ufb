package com.ufufund.trade.ufb.biz.manager.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ufufund.ufb.biz.manager.org.OrgDeploy;
import com.ufufund.ufb.biz.manager.org.OrgPlanManager;
import com.ufufund.ufb.dao.OrgDeployMapper;
import com.ufufund.ufb.model.action.org.CreateOrgPlanAction1;
import com.ufufund.ufb.model.action.org.CreateOrgPlanAction2;
import com.ufufund.ufb.model.action.org.CreateOrgPlanAction3;
import com.ufufund.ufb.model.action.org.CreateOrgchargeinfoAction;
import com.ufufund.ufb.model.action.org.SaveOrgGradeAction;
import com.ufufund.ufb.model.action.org.SavetermAction;
import com.ufufund.ufb.model.action.org.UpdateOrgchargeinfoAction;
import com.ufufund.ufb.model.db.Orgchargeinfo;
import com.ufufund.ufb.model.db.Orgplan;
import com.ufufund.ufb.model.db.Orgplandetail;
import com.ufufund.ufb.model.db.Orgplandetailcharge;
import com.ufufund.ufb.model.db.Orggrade;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-ufb-biz.xml" })
@TransactionConfiguration(defaultRollback = false)
public class OrgManagerTest {
	
	

	@Autowired
	private OrgDeploy orgDeploy;
	
	@Autowired
	private OrgDeployMapper orgDeployMapper;

	@Autowired
	private OrgPlanManager orgPlanManager;
	/*
	 * 
	 * 测试getAutotradeSequence
	 */
	//@Test
	public void getOrgGradeInfo(){
		Orggrade orggrade = orgDeploy.getOrgGradeInfo("1");
		System.out.println("orggrade :" + orggrade.toString());
	}
	
	
	//@Test
	public void saveOrgGrade(){
		SaveOrgGradeAction action = new SaveOrgGradeAction();
		action.setOrgid("or12515");
		action.setGradename("2015");
		action.setStartdate("20150902");
		action.setEnddate("20160621");
		orgDeploy.saveOrgGrade(action);
			
	}
	
	//@Test
	public void saveterm1(){
		SavetermAction action = new SavetermAction();
		action.setOrgid("or12515");
		action.setStartdate("20150902");
		action.setEnddate("20151231");
		orgDeploy.saveterm1(action);
			
	}
	
	//@Test
	public void saveterm2(){
		SavetermAction action = new SavetermAction();
		action.setOrgid("or12515");
		action.setStartdate("20151231");
		action.setEnddate("20160621");
		orgDeploy.saveterm2(action);
			
	}
	//@Test
	public void closeTerm(){
		orgDeploy.closeTerm("or12515");
			
	}
	
	//@Test
	public void createOrgchargeinfo(){
		CreateOrgchargeinfoAction action = new CreateOrgchargeinfoAction();
		action.setOrgid("or12515");
		action.setChargetype("C");
		action.setChargename("xxCW费");
		action.setChargeamount("21");
		action.setCycle("Y");
		action.setCreateno("aaa");
		orgDeploy.createOrgchargeinfo(action);
				
	}
	
	//@Test
	public void updateOrgchargeinfo(){
		UpdateOrgchargeinfoAction action = new UpdateOrgchargeinfoAction();
		action.setOrgid("or12515");
		action.setChargeid("1");
		action.setChargetype("B");
		action.setChargename("xx费");
		action.setChargeamount("1121.00");
		action.setCycle("M");
		action.setCreateno("bbb");
		orgDeploy.updateOrgchargeinfo(action);
				
	}
	
	//@Test
	public void getOrgchargeinfo(){
		List<Orgchargeinfo>  list = orgDeploy.getOrgchargeinfo("or12515");
		System.out.println("---------"+list.size());
		for(Orgchargeinfo orgchargeinfo:list){
			System.out.println(orgchargeinfo.toString());
		}
	}
	
	//@Test
	public void insertOrggplandetailchargeList(){
		List<Orgplandetailcharge> plandetailchargeList = new ArrayList<Orgplandetailcharge>(); 
		Orgplandetailcharge orggplandetailcharge = new Orgplandetailcharge();
		orggplandetailcharge.setChargeid("9");
		orggplandetailcharge.setChargetype("b");
		orggplandetailcharge.setChargename("c");
		orggplandetailcharge.setChargeamount("1000");
		orggplandetailcharge.setCycle("e");
		orggplandetailcharge.setDetailid("f");
		orggplandetailcharge.setPlanid("g");
		plandetailchargeList.add(orggplandetailcharge);
		orggplandetailcharge = new Orgplandetailcharge();
		orggplandetailcharge.setChargeid("8");
		orggplandetailcharge.setChargetype("2");
		orggplandetailcharge.setChargename("3");
		orggplandetailcharge.setChargeamount("4000");
		orggplandetailcharge.setCycle("5");
		orggplandetailcharge.setDetailid("6");
		orggplandetailcharge.setPlanid("7");
		plandetailchargeList.add(orggplandetailcharge);
		orgDeployMapper.insertOrgplandetailchargeList(plandetailchargeList);
	}
	
	//@Test
	public void getOrggplan(){
		Orgplan orggplan = new Orgplan();
		orggplan.setOrgid("a");
		//orggplan.setPlanid("b");
		List<Orgplan> list =  orgDeployMapper.getOrgplan(orggplan);
		orggplan = (Orgplan) list.get(0);
		System.out.println(orggplan.toString());
	}
	
	//@Test
	public void createOrgPlanAction2() {
		CreateOrgPlanAction1 orggplan = new CreateOrgPlanAction1();
		orggplan.setOrgid("101");
		orggplan.setGradeid("102");
		orggplan.setTermid("102A");
		orggplan.setPlanname("2015学费");
		orggplan.setPlantype("F");
		orggplan.setCycletype("E");
		orggplan.setType("AT");
		orggplan.setAckdat("20150920");
		orggplan.setDat("25");
		orggplan.setPaydate("20150925");
		//orggplan.setNextdate("2");
		
		orggplan.setReplanid("1010001");
		orggplan.setCreateno("Createno");
		CreateOrgPlanAction2 studentid1 = new CreateOrgPlanAction2();
		studentid1.setStudentid("Studentid1");
		studentid1.setDiscount("200");
		CreateOrgPlanAction2 studentid2 = new CreateOrgPlanAction2();
		studentid2.setStudentid("Studentid2");
		studentid2.setDiscount("300");
		CreateOrgPlanAction3  studentid1Charge =  new CreateOrgPlanAction3();
		studentid1Charge.setChargeid("1");
		studentid1Charge.setChargetype("B");
		studentid1Charge.setChargename("xx1费");
		studentid1Charge.setChargeamount("1921.00");
		studentid1Charge.setCycle("E");
		studentid1.getChargeList().add(studentid1Charge);
		
		CreateOrgPlanAction3  studentid2Charge1 =  new CreateOrgPlanAction3();
		studentid2Charge1.setChargeid("2");
		studentid2Charge1.setChargetype("C");
		studentid2Charge1.setChargename("xx2费");
		studentid2Charge1.setChargeamount("1922.00");
		studentid2Charge1.setCycle("M");
		studentid2.getChargeList().add(studentid2Charge1);
		CreateOrgPlanAction3  studentid2Charge2 =  new CreateOrgPlanAction3();
		studentid2Charge2.setChargeid("3");
		studentid2Charge2.setChargetype("D");
		studentid2Charge2.setChargename("xx3费");
		studentid2Charge2.setChargeamount("1833.00");
		studentid2Charge2.setCycle("S");
		studentid2.getChargeList().add(studentid2Charge2);
		
		orggplan.getStudentList().add(studentid1);
		orggplan.getStudentList().add(studentid2);
		orgPlanManager.createOrgPlanAction2(orggplan);
	}
}
