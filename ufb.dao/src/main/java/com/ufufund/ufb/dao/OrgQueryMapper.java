package com.ufufund.ufb.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.model.db.OrgPlanDetail;
import com.ufufund.ufb.model.db.OrgPriceItem;
import com.ufufund.ufb.model.db.OrgQuery;
import com.ufufund.ufb.model.db.PayRecordQry;

public interface OrgQueryMapper{
	
	/**
	 * 根据用户编号查询机构信息
	 * @param custno
	 * @return
	 */
	public List<OrgQuery> queryOrgByCustno(String custno);
	
	/**
	 * 根据机构id查询学生信息
	 * @param orgid custno
	 * @return
	 */
	public List<OrgQuery> queryStudentByOrgid(@Param("orgid")String orgid,@Param("custno")String custno);
	
	/**
	 * 获得用户的计划详单
	 * @param orgDetail
	 * @return
	 */
	public List<OrgPlanDetail> getQueryCustplandetail(OrgPlanDetail orgDetail);
	
	/**
	 * 根据班级编号获得机构信息
	 * @param cid
	 * @return
	 */
	public OrgQuery getOrginfoByCid(String cid);
	
	/**
	 * 根据机构id查询 plandetail 信息
	 * @param id
	 * @return
	 */
	public String getOrgidByDetailid(String id);
	
	/**
	 * 更新 plandetail 信息
	 * @param orgplandetail
	 * @return
	 */
	void updateDetail(OrgPlanDetail orgplandetail);
	
	/**
	 * 查询缴费日期
	 * @param id
	 * @return
	 */
	String selectPayDate(String id);
	
	/**
	 * 缴费明细查询:分页查询
	 * @param vo
	 * @return
	 */
	public List<OrgPlanDetail> getPayRecords(PayRecordQry vo);	
	
	/**
	 * 缴费明细查询：总数
	 * @param vo
	 * @return
	 */
	public int getPayRecordCount(PayRecordQry vo);
	
	/**
	 * 查询已缴费用总额
	 * @param custno
	 * @return
	 */
	public BigDecimal getPaidTotalAmt(PayRecordQry vo);
	
	/**
	 * 查询已收到的退费总额
	 * @param custno
	 * @return
	 */
	public BigDecimal getReversedTotalAmt(PayRecordQry vo);
	
	/**
	 * 查询当月的退费金额
	 * @param vo
	 * @return
	 */
	public BigDecimal getReversedMonthAmt(PayRecordQry vo);
	
	/**
	 * 查询收费价目明细
	 * @param priceitems
	 * @return
	 */
	public List<OrgPriceItem> getPriceItemList(List<String> priceitems);
	
}
