package com.ufufund.ufb.biz.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufufund.ufb.biz.manager.AutotradeManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.dao.CustinfoMapper;
import com.ufufund.ufb.dao.OrgQueryMapper;
import com.ufufund.ufb.dao.StudentMapper;
import com.ufufund.ufb.dao.TradeAccoinfoMapper;
import com.ufufund.ufb.model.action.cust.AddAutotradeAction;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.OrgPlanDetail;
import com.ufufund.ufb.model.db.OrgPriceItem;
import com.ufufund.ufb.model.db.OrgQuery;
import com.ufufund.ufb.model.db.PayRecordQry;
import com.ufufund.ufb.model.db.Tradeaccoinfo;
import com.ufufund.ufb.model.enums.AutoTradeType;
import com.ufufund.ufb.model.enums.BasicFundinfo;


@Service
public class OrgQueryManager{

	@Autowired
	private OrgQueryMapper orgQueryMapper;
	@Autowired
	private AutotradeManager autotradeManager;
	@Autowired
	private CustinfoMapper custinfoMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private TradeAccoinfoMapper tradeAccoinfoMapper;
	
	/**
	 * 根据用户编号查询机构信息
	 * @param custno
	 * @return
	 */
	public List<OrgQuery> queryOrgByCustno(String custno){
		return orgQueryMapper.queryOrgByCustno(custno);
	}
	
	/**
	 * 查询用户的学生信息
	 * @param custno
	 * @param orgid
	 * @return
	 */
	public List<OrgQuery> queryStudentByOrgid(String orgid, String custno){
		return orgQueryMapper.queryStudentByOrgid(orgid, custno);
	}
	
	/**
	 * 个人用户查询收费计划详情
	 * @param custno
	 * @param orgid
	 * @param detailid
	 * @param ispaylist
	 * @return
	 */
	public List<OrgPlanDetail> queryCustplandetail(OrgPlanDetail orgDetail) {
		return orgQueryMapper.getQueryCustplandetail(orgDetail);
	}
	
	/**
	 * 查询当月的退费金额
	 * @param PayRecordQry
	 * 			vo
	 */
	public BigDecimal getReversedMonthAmt(PayRecordQry vo) {
		return orgQueryMapper.getReversedMonthAmt(vo);
	}
	
	/**
	 * 家长确认收费
	 * @param detailids
	 * @param d_custinfo
	 * @param paytype
	 * @return
	 */
	@Transactional
	public String confirmDetail(String detailids,Custinfo custinfo,String paytype) {
		String[] detailidArr=detailids.split(",");
		String paydate="";
		
		List<String> ispaylist=new ArrayList<String>();
		ispaylist.add("0");
		ispaylist.add("1");
		
		for(int i=0;i<detailidArr.length;i++){
			OrgPlanDetail detail = new OrgPlanDetail();
			String detailid=detailidArr[i];
			detail.setId(detailid);
			detail.setState("1");
			
			Tradeaccoinfo tradeacco = new Tradeaccoinfo();
			tradeacco.setCustno(custinfo.getCustno());
			Tradeaccoinfo tradeAcco=tradeAccoinfoMapper.getTradeaccoinfo(tradeacco);
			
			// 修改计划详情
			orgQueryMapper.updateDetail(detail);
			
			// 如果是月代扣的计划，则进行自动取现的设置
			OrgPlanDetail orgDetail = new OrgPlanDetail();
			orgDetail.setParentid(custinfo.getCustno());
			orgDetail.setId(detailid);
			orgDetail.setIspaylist(ispaylist);
			if(paytype.equals("U")){
				List<OrgPlanDetail> plan_detilList=orgQueryMapper.getQueryCustplandetail(orgDetail);
				if(plan_detilList.size()>0&&plan_detilList!=null){
					OrgPlanDetail detail_y=(OrgPlanDetail)plan_detilList.get(0);
						AddAutotradeAction action = new AddAutotradeAction();
						if(detail_y.getState().equals("1")){
							action.setCycle("MM");
							action.setDat(detail_y.getPayday());
							action.setType("E");
						}else{
							action.setType("S");
						}
						// 用户信息
						action.setCustno(custinfo.getCustno());
						action.setFromfundcorpno(Constant.HftSysConfig.HftFundCorpno);
						action.setFromfundcode(BasicFundinfo.YFB.getFundCode());
						action.setFromchargetype("A");
						action.setTobankacco(tradeAcco.getTradeacco());
						action.setTobankserialid(tradeAcco.getBankserialid());
						// 交易类型
						action.setTradetype(AutoTradeType.AUTOWITHDRAWAL);
						// 扣款日期
						action.setNextdate(detail_y.getPay_date().replace("-", ""));
						// 取现金额
						action.setAutovol(new BigDecimal(detail_y.getAmount()));
						// 备注
						action.setSummary(detail_y.getSname()+detail_y.getPlanname());
						// 计划详情id
						action.setDetailid(detail_y.getId());
						
						autotradeManager.addAutotrade(action);
					
				}
			}
		}
		paydate=orgQueryMapper.selectPayDate(detailidArr[0]); 
		
		return paydate;
	}
	
	/**
	 * 获得缴费通知书
	 * @param custno
	 * @param detailid
	 * @return
	 */
	public OrgPlanDetail getDetailNotice(String custno,String detailid) {
		List<String> ispayList = new ArrayList<String>();
		ispayList.add("2");
		ispayList.add("5");
		
		OrgPlanDetail orgDetail = new OrgPlanDetail();
		orgDetail.setParentid(custno);
		orgDetail.setId(detailid);
		orgDetail.setIspaylist(ispayList);
		List<OrgPlanDetail> detailList = this.queryCustplandetail(orgDetail);
		OrgPlanDetail payVo =  new OrgPlanDetail();
		if(detailList.size()>0 && null != detailList){
			payVo=detailList.get(0);
			String orgid=payVo.getOrgid();
			OrgQuery org=custinfoMapper.queryOrgBankInfo(orgid);
			payVo.setOrgQuery(org);
			String studentid=payVo.getSid();
			OrgQuery stu=studentMapper.getClassNmBySid(studentid);
			if(null!=stu){
				payVo.setCname(stu.getCname());
				payVo.setCode(stu.getCode());
			}
		}
		return payVo;
	}
	
	/**
	 * 根据detailid 查询机构信息
	 * @param detailid
	 */
	public String getOrgidByDetailid(String detailid) {
		return orgQueryMapper.getOrgidByDetailid(detailid);
	}
	
	/**
	 * 缴费明细查询:分页查询
	 * @param PayRecordQry
	 * 			vo
	 * @return
	 */
	public List<OrgPlanDetail> getPayRecords(PayRecordQry vo){
		return orgQueryMapper.getPayRecords(vo);
	}
	
	/**
	 * 缴费明细查询:总数
	 * @param PayRecordQry
	 * 		   vo
	 * @return
	 */
	public int getPayRecordCount(PayRecordQry vo){
		return orgQueryMapper.getPayRecordCount(vo);
	}
	
	/**
	 * 查询已缴费用总额
	 * @param PayRecordQry
	 * 		   vo
	 * @return
	 */
	public BigDecimal getPaidTotalAmt(PayRecordQry vo){
		BigDecimal result = orgQueryMapper.getPaidTotalAmt(vo);
		if(result == null) result = new BigDecimal("0.00");
		return result;
	}
	
	/**
	 * 查询已收到的退费总额
	 * @param PayRecordQry
	 * 		   vo
	 * @return
	 */
	public BigDecimal getReversedTotalAmt(PayRecordQry vo){
		BigDecimal result = orgQueryMapper.getReversedTotalAmt(vo);
		if(result == null) result = new BigDecimal("0.00");
		return result;
	}

	/**
	 * 查询家长收费明细
	 * @param List<String>
	 * 			priceitems
	 * @return
	 */
	public List<OrgPriceItem> getPriceItemList(List<String> priceitems) {
		return orgQueryMapper.getPriceItemList(priceitems);
	}
	
}
