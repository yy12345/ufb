package com.ufufund.ufb.biz.manager.org.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.AutotradeManager;
import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.biz.manager.impl.ImplCommon;
import com.ufufund.ufb.biz.manager.org.OrgPlanManager;
import com.ufufund.ufb.biz.manager.org.OrgQueryManager;
import com.ufufund.ufb.biz.manager.org.impl.helper.OrgPlanHelper;
import com.ufufund.ufb.biz.manager.org.impl.validator.OrgPlanValidator;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.common.utils.SequenceUtil;
import com.ufufund.ufb.dao.OrgDeployMapper;
import com.ufufund.ufb.dao.OrgQueryMapper;
import com.ufufund.ufb.dao.PlanDetailMapper;
import com.ufufund.ufb.dao.StudentMapper;
import com.ufufund.ufb.model.action.cust.AddAutotradeAction;
import com.ufufund.ufb.model.action.org.CreateOrgPlanAction1;
import com.ufufund.ufb.model.action.org.CreateOrgPlanAction2;
import com.ufufund.ufb.model.action.org.CreateOrgPlanAction3;
import com.ufufund.ufb.model.action.org.PersonConfirmAction;
import com.ufufund.ufb.model.action.org.PersonConfirmList;
import com.ufufund.ufb.model.action.org.UpdateOrgPlanAction1;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.Orgplan;
import com.ufufund.ufb.model.db.Orgplandetail;
import com.ufufund.ufb.model.db.Orgplandetailcharge;
import com.ufufund.ufb.model.db.Tradeaccoinfo;
import com.ufufund.ufb.model.enums.AutoTradeType;
import com.ufufund.ufb.model.enums.BasicFundinfo;
import com.ufufund.ufb.model.enums.ErrorInfo;
import com.ufufund.ufb.model.vo.OrgBankInfoVo;
import com.ufufund.ufb.model.vo.QueryCustplandetail;

@Service
public class OrgPlanManagerImpl extends ImplCommon implements OrgPlanManager {

	@Autowired
	private OrgPlanValidator orgPlanValidator;

	@Autowired
	private WorkDayManager workDayManager;

	@Autowired
	private OrgDeployMapper orgDeployMapper;
	@Autowired
	private TradeAccoManager tradeAccoManager;
	@Autowired
	private BankCardManager bankCardManager;
	@Autowired
	private AutotradeManager autotradeManager;
	@Autowired
	private PlanDetailMapper planDetailMapper;
	@Autowired
	private OrgQueryMapper orgQueryMapper;
	@Autowired
	private OrgQueryManager orgQueryManager;
	@Autowired
	private CustManager custManager;
	@Autowired
	private StudentMapper studentMapper;

	// @Autowired
	// private AutotradeManager autotradeManager;

	@Override
	public void createOrgPlanAction1(CreateOrgPlanAction1 action) throws BizException {
		// TODO Auto-generated method stub
		String processId = this.getProcessId(action);
		orgPlanValidator.validator(action);
		if (Constant.Orggrade.CYCLE_TYPE$M.equals(action.getCycletype())) {
			if (RegexUtil.isNull(action.getType())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.TYPE);
			}
			if (RegexUtil.isNull(action.getDat())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.DAT);
			}
			if (!RegexUtil.isInteger(action.getDat())) {
				throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Orggrade.PAY_DATE);
			}
			int dat = Integer.parseInt(action.getDat());
			if (dat <= 0 || dat >= 31) {
				throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Orggrade.DAT);
			}
		}
		if (action.getPaydate().length() != 8) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Orggrade.PAY_DATE);
		}
		int sysdate = Integer.parseInt(workDayManager.getSysTime().substring(0, 8));
		if (Integer.parseInt(action.getPaydate()) <= sysdate) {
			throw new BizException(processId, ErrorInfo.ORG_DATE_WRONG, BisConst.Orggrade.PAY_DATE);
		}

	}

	@Override
	public void createOrgPlanAction2(CreateOrgPlanAction1 action) throws BizException {
		String processId = this.getProcessId(action);
		this.createOrgPlanAction1(action);
		log.debug(processId + " 收费计划学生数 ：" + action.getStudentList().size());
		this.validator(action, processId);
		String planid = SequenceUtil.getSerial();
		String groupid = planid;
		this.saveAction(action, planid, groupid,  processId);
	}

	@Override
	public void updateOrgPlanAction2(UpdateOrgPlanAction1 action) throws BizException {
		// TODO Auto-generated method stub
		String processId = this.getProcessId(action);
		this.createOrgPlanAction1(action);
		this.validator(action, processId);
		if (RegexUtil.isNull(action.getPlanid())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.PLAN_ID);
		}
		Orgplan orggplan = new Orgplan();
		orggplan.setOrgid(action.getOrgid());
		orggplan.setPlanid(action.getPlanid());
		List<Orgplan> list =  orgDeployMapper.getOrgplan(orggplan);
		log.debug(processId + " List<Orggplan> ：" + list.size());
		if(list.size()!=1){
			throw new BizException(processId, ErrorInfo.SYSTEM_ERROR);
		}
		orggplan = (Orgplan) list.get(0);
		if("C".equals(orggplan.getStats())){
			throw new BizException(processId, ErrorInfo.SYSTEM_ERROR);
		}
		String planid = action.getPlanid();
		orgDeployMapper.deleteOrgplan(planid);
		orgDeployMapper.deleteOrgplandetail(planid);
		orgDeployMapper.deleteOrgplandetailcharge(planid);
		this.saveAction(action, planid, orggplan.getGroupid() ,processId);

	}

	private void validator(CreateOrgPlanAction1 action, String processId) throws BizException {
		if (action.getStudentList().size() == 0) {
			throw new BizException(processId, ErrorInfo.ORG_NO_STUDENTS);
		}
		for (CreateOrgPlanAction2 action2 : action.getStudentList()) {
			if (RegexUtil.isNull(action2.getStudentid())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Orggrade.STUDENT_ID);
			}
			if (action2.getChargeList().size() == 0) {
				throw new BizException(processId, ErrorInfo.ORG_NO_CHARGE);
			}
			for (CreateOrgPlanAction3 action3 : action2.getChargeList()) {
				orgPlanValidator.validator(action3);
			}
		}
	}

	private void saveAction(CreateOrgPlanAction1 action, String planid, String groupid, String processId) {
		Orgplan orggplan = OrgPlanHelper.converntOrggplan(action);
		orggplan.setPlanid(planid);
		orggplan.setPaydate(action.getPaydate());
		orggplan.setStats("N");
		orggplan.setGroupid(groupid);
		List<Orgplandetail> plandetailList = new ArrayList<Orgplandetail>();
		List<Orgplandetailcharge> plandetailchargeList = new ArrayList<Orgplandetailcharge>();
		Orgplandetail orggplandetail = null;
		Orgplandetailcharge orggplandetailcharge = null;
		String detailid = "";
		BigDecimal payappamount = BigDecimal.ZERO;
		BigDecimal payackamount = BigDecimal.ZERO;
		for (CreateOrgPlanAction2 action2 : action.getStudentList()) {
			detailid = SequenceUtil.getSerial();
			payappamount = BigDecimal.ZERO;
			for (CreateOrgPlanAction3 action3 : action2.getChargeList()) {
				orggplandetailcharge = OrgPlanHelper.converntOrggplandetailcharge(action3);
				orggplandetailcharge.setDetailid(detailid);
				orggplandetailcharge.setPlanid(planid);
				orggplandetailcharge.setOrgid(action.getOrgid());
				//System.out.println();
				payappamount = payappamount.add(new BigDecimal(orggplandetailcharge.getChargeamount()));
				plandetailchargeList.add(orggplandetailcharge);
			}
			orggplandetail = new Orgplandetail();
			orggplandetail.setOrgid(action.getOrgid());
			orggplandetail.setPlanid(planid);
			orggplandetail.setStudentid(action2.getStudentid());
			orggplandetail.setDetailid(detailid);
			orggplandetail.setPaydiscount(action2.getDiscount());
			orggplandetail.setPayappamount(payappamount.toString());
			payackamount = payappamount.subtract(new BigDecimal(orggplandetail.getPaydiscount()));
			orggplandetail.setPayackamount(payackamount.toString());
			/*
			 * 月代扣状态直接确认
			 */
			if (Constant.Orggrade.CYCLE_TYPE$M.equals(action.getCycletype())) {
				orggplandetail.setStats("F");
			}else{
				orggplandetail.setStats("N");
			}
			plandetailList.add(orggplandetail);
		}
		log.debug(processId + " List<plandetailList> ：" + plandetailList.size());
		log.debug(processId + " List<plandetailchargeList> ：" + plandetailchargeList.size());
		orgDeployMapper.insertOrgplan(orggplan);
		orgDeployMapper.insertOrgplandetailList(plandetailList);
		orgDeployMapper.insertOrgplandetailchargeList(plandetailchargeList);
	}

	@Override
	public void personConfirmPlandetail(PersonConfirmAction action) throws BizException {
		// TODO Auto-generated method stub
		String processId = this.getProcessId(action);
		orgPlanValidator.validator(action);
		if("U".equals(action.getAcktype())){
			if (!RegexUtil.isNull(action.getAcktradeacco())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, "Acktradeacco");
			}
		}
		List<PersonConfirmList> datailList = action.getPersonConfirmList();
		if(datailList.isEmpty()||datailList.size() ==0){
			throw new BizException(processId, ErrorInfo.SYSTEM_ERROR);
		}
		Orgplandetail orgplandetail = null;
		for(PersonConfirmList detail : datailList){
			orgplandetail = new Orgplandetail();
			orgplandetail.setDetailid(detail.getDetailid());
			orgplandetail.setStats("1");
			orgplandetail.setAcktype(action.getAcktype());
			orgplandetail.setAckcustno(action.getAckcustno());
			orgplandetail.setAckbankcardid(action.getAckbankcardid());
			orgplandetail.setAcktradeacco(action.getAcktradeacco());
			orgDeployMapper.updatePlandetail(orgplandetail);
		}
	}

	@Override
	@Transactional
	public String confirmDetail(String detailids,Custinfo d_custinfo,String paytype) {
		String[] detailidArr=detailids.split(",");
		String paydate="";
		List<String> ispaylist=new ArrayList<String>();
		ispaylist.add("0");
		ispaylist.add("1");
		for(int i=0;i<detailidArr.length;i++){
			Orgplandetail detail = new Orgplandetail();
			String detailid=detailidArr[i];
			detail.setDetailid(detailid);
			detail.setAckcustno(d_custinfo.getCustno());
			detail.setAcktype(paytype);
			detail.setStats("Y");
			detail.setIspay("1");
			Tradeaccoinfo tradeAcco=tradeAccoManager.getTradeaccoinfo(d_custinfo.getCustno());
			if(paytype.equals("U")){
				detail.setAckbankcardid(tradeAcco.getBankserialid());
				detail.setAcktradeacco(tradeAcco.getTradeacco());
			}else{
				Bankcardinfo bankcard =	bankCardManager.getBankcardinfo(d_custinfo.getCustno());
				detail.setAckbankcardid(bankcard.getSerialid());
			}
			
			// 修改计划详情
			planDetailMapper.updateDetail(detail);
			
			// 如果是月代扣的计划，则进行自动取现的设置
			if(paytype.equals("U")){
				List<QueryCustplandetail> plan_detilList=orgQueryMapper.getQueryCustplandetail(d_custinfo.getCustno(), null, detailid,ispaylist);
				if(plan_detilList.size()>0&&plan_detilList!=null){
						QueryCustplandetail detail_y=(QueryCustplandetail)plan_detilList.get(0);
						AddAutotradeAction action = new AddAutotradeAction();
						if(detail_y.getType().equals("1")){
							action.setCycle("MM");
							action.setDat(detail_y.getDat());
							action.setType("E");
						}else{
							action.setType("S");
						}
						// 用户信息
						action.setCustno(d_custinfo.getCustno());
						action.setFromfundcorpno(Constant.HftSysConfig.HftFundCorpno);
						action.setFromfundcode(BasicFundinfo.YFB.getFundCode());
						action.setFromchargetype("A");
						action.setTobankacco(tradeAcco.getTradeacco());
						action.setTobankserialid(tradeAcco.getBankserialid());
						// 交易类型
						action.setTradetype(AutoTradeType.AUTOWITHDRAWAL);
						// 扣款日期(计划的默认扣款日期)
						action.setNextdate(detail_y.getPlanpaydate().replace("-", ""));
						// 取现金额
						action.setAutovol(new BigDecimal(detail_y.getPayackamount()));
						// 备注
						action.setSummary(detail_y.getStudentnm()+detail_y.getPlanname());
						// 计划详情id
						action.setDetailid(detail_y.getDetailid());
						
						autotradeManager.addAutotrade(action);
					
				}
			}
		}
		paydate=planDetailMapper.selectPayDate(detailidArr[0]); 
		
		return paydate;
	}

	@Override
	public QueryCustplandetail getDetailNotice(String custno,String detailid) {
		List<String> ispayList = new ArrayList<String>();
		ispayList.add("2");
		ispayList.add("5");
		List<QueryCustplandetail> detailList = orgQueryManager.getQueryCustplandetail(custno, null, detailid, ispayList);
		QueryCustplandetail payVo = new QueryCustplandetail();
		if(detailList.size()>0&&null!=detailList){
			payVo=detailList.get(0);
			String orgid=payVo.getOrgid();
			OrgBankInfoVo org=custManager.queryOrgBankInfo(orgid);
			payVo.setOrginfo(org);
			String studentid=payVo.getStudentid();
			QueryCustplandetail stu=studentMapper.getClassNmBySid(studentid);
			if(null!=stu){
				payVo.setCname(stu.getCname());
				payVo.setCode(stu.getCode());
			}
		}
		return payVo;
	}

	

}
