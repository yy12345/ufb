package com.ufufund.ufb.biz.manager.org.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.SequenceManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.biz.manager.impl.ImplCommon;
import com.ufufund.ufb.biz.manager.org.OrgPlanManager;
import com.ufufund.ufb.biz.manager.org.impl.helper.OrgPlanHelper;
import com.ufufund.ufb.biz.manager.org.impl.validator.OrgPlanValidator;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.dao.OrgDeployMapper;
import com.ufufund.ufb.model.action.org.CreateOrgPlanAction1;
import com.ufufund.ufb.model.action.org.CreateOrgPlanAction2;
import com.ufufund.ufb.model.action.org.CreateOrgPlanAction3;
import com.ufufund.ufb.model.db.Orggplan;
import com.ufufund.ufb.model.db.Orggplandetail;
import com.ufufund.ufb.model.db.Orggplandetailcharge;
import com.ufufund.ufb.model.enums.ErrorInfo;


@Service
public class OrgPlanManagerImpl extends ImplCommon implements OrgPlanManager {

	@Autowired
	private OrgPlanValidator orgPlanValidator;
	
	@Autowired
	private SequenceManager sequenceManager;
	
	@Autowired
	private WorkDayManager workDayManager;
	
	@Autowired
	private OrgDeployMapper orgDeployMapper;
	
	
	@Override
	public void createOrgPlanAction1(CreateOrgPlanAction1 action) throws BizException {
		// TODO Auto-generated method stub
		String processId = 	this.getProcessId(action);
		orgPlanValidator.validator(action);
		if(Constant.Orggrade.CYCLE_TYPE$E.equals(action.getCycletype())){
			if (RegexUtil.isNull(action.getType())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.TYPE);
			}
			int dat = Integer.parseInt(action.getDat());
			if(dat<=0||dat>=31){
				throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Orggrade.DAT);
			}
		}else{
			if(action.getDat().length()!=8){
				throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Orggrade.DAT);
			}		
			int sysdate =  Integer.parseInt(workDayManager.getSysTime().substring(0,8));
			if(Integer.parseInt(action.getDat())<=sysdate){
				throw new BizException(processId, ErrorInfo.ORG_DATE_WRONG, BisConst.Orggrade.DAT);
			}
			
		}
	}


	@Override
	public void createOrgPlanAction2(CreateOrgPlanAction1 action) throws BizException {
		String processId = 	this.getProcessId(action);
		this.createOrgPlanAction1(action);
		log.debug(processId + " 新建收费计划学生数 ：" +action.getStudentList().size());
		if(action.getStudentList().size()==0){
			throw new BizException(processId, ErrorInfo.ORG_NO_STUDENTS);
		}
		for(CreateOrgPlanAction2 action2 : action.getStudentList()){
			if (RegexUtil.isNull(action2.getStudentid())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.STUDENT_ID);
			}
			if(action2.getChargeList().size()==0){
				throw new BizException(processId, ErrorInfo.ORG_NO_CHARGE);
			}
			for(CreateOrgPlanAction3 action3 : action2.getChargeList()){
				orgPlanValidator.validator(action3);
			}
		}
		String planid = "";
		if(action.getPlanid()!=null&&!"".equals(action.getPlanid())){
			orgDeployMapper.deleteOrggplan(planid);
			orgDeployMapper.deleteOrggplandetail(planid);
			orgDeployMapper.deleteOrggplandetailcharge(planid);
		}else{
			planid = sequenceManager.getPlanid();
		}
		Orggplan orggplan = OrgPlanHelper.converntOrggplan(action);
		orggplan.setPlanid(planid);
		if(Constant.Orggrade.CYCLE_TYPE$E.equals(action.getCycletype())){
			orggplan.setNextdate("-");
		}else{
			orggplan.setNextdate(action.getDat());
		}
		orggplan.setStats("N");
		
		List<Orggplandetail> plandetailList = new ArrayList<Orggplandetail>();
		List<Orggplandetailcharge> plandetailchargeList = new ArrayList<Orggplandetailcharge>(); 
		Orggplandetail orggplandetail = null;
		Orggplandetailcharge orggplandetailcharge = null;
		String detailid ="";
		BigDecimal payappamount = BigDecimal.ZERO;
		BigDecimal payackamount = BigDecimal.ZERO;
		for(CreateOrgPlanAction2 action2 : action.getStudentList()){
			detailid = sequenceManager.getPlanDetailid();
			payappamount = BigDecimal.ZERO;
			for(CreateOrgPlanAction3 action3 : action2.getChargeList()){
				orggplandetailcharge = OrgPlanHelper.converntOrggplandetailcharge(action3);
				orggplandetailcharge.setDetailid(detailid);
				orggplandetailcharge.setPlanid(planid);
				payappamount = payappamount.add(new BigDecimal(orggplandetailcharge.getChargeamount()));
				plandetailchargeList.add(orggplandetailcharge);
			}
			orggplandetail = new Orggplandetail();
			orggplandetail.setPlanid(planid);
			orggplandetail.setStudentid(action2.getStudentid());
			orggplandetail.setDetailid(detailid);
			orggplandetail.setPaydiscount(action2.getDiscount());
			orggplandetail.setPayappamount(payappamount.toString());
			payackamount = payappamount.subtract(new BigDecimal(orggplandetailcharge.getChargeamount()));
			orggplandetail.setPayackamount(payackamount.toString());
			plandetailList.add(orggplandetail);	
		}
		orgDeployMapper.insertOrggplan(orggplan);
		orgDeployMapper.insertOrggplandetailList(plandetailList);
		orgDeployMapper.insertOrggplandetailchargeList(plandetailchargeList);
	}
	
	
//	public static void main(String[] args) {
//		BigDecimal payappamount = new BigDecimal("100");
//		BigDecimal payackamount = new BigDecimal("20");
//		System.out.println(payappamount.subtract(payackamount));
//	}
}
