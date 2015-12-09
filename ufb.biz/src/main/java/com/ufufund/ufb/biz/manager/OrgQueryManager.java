package com.ufufund.ufb.biz.manager;

import java.math.BigDecimal;
import java.util.List;

import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.OrgPlanDetail;
import com.ufufund.ufb.model.db.OrgQuery;
import com.ufufund.ufb.model.vo.PayRecordQryVo;



public interface OrgQueryManager {
	
	/**
	 * 根据用户编号查询机构信息
	 * @param custno
	 * @return
	 */
	public List<OrgQuery> queryOrgByCustno(String custno);
	
	/**
	 * 查询用户的学生信息
	 * @param custno
	 * @param orgid
	 * @return
	 */
	public List<OrgQuery> queryStudentByOrgid(String custno, String orgid);
	
	/**
	 * 个人用户查询收费计划详情
	 * @param custno
	 * @param orgid
	 * @param detailid
	 * @param ispaylist
	 * @return
	 */
	public List<OrgPlanDetail> queryCustplandetail(OrgPlanDetail orgDetail);
	
	/**
	 * 家长确认收费
	 * @param detailids
	 * @param d_custinfo
	 * @param paytype
	 * @return
	 */
	public String confirmDetail(String detailids,Custinfo d_custinfo,String paytype);
	
	/**
	 * 获得缴费通知书
	 * @param custno
	 * @param detailid
	 * @return
	 */
	public OrgPlanDetail getDetailNotice(String custno,String detailid);

	/**
	 * 缴费明细查询:分页查询
	 * @param vo
	 * @return
	 */
	public List<OrgPlanDetail> getPayRecords(PayRecordQryVo vo);
	
	/**
	 * 缴费明细查询：总数
	 * @param vo
	 * @return
	 */
	public int getPayRecordCount(PayRecordQryVo vo);
	
	/**
	 * 查询已缴费用总额
	 * @param custno
	 * @return
	 */
	public BigDecimal getPaidTotalAmt(PayRecordQryVo vo);
	
	/**
	 * 查询已收到的退费总额
	 * @param custno
	 * @return
	 */
	public BigDecimal getReversedTotalAmt(PayRecordQryVo vo);
	
	/**
	 * 根据detailid 查询机构信息
	 */
	public String getOrgidByDetailid(String detailid);
	
	/**
	 * 查询当月的退费金额
	 */
	public BigDecimal getReversedMonthAmt(PayRecordQryVo vo);
}
