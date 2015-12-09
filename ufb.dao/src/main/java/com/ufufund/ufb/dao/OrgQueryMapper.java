package com.ufufund.ufb.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.common.dao.BaseDao;
import com.ufufund.ufb.model.db.OrgPlanDetail;
import com.ufufund.ufb.model.db.OrgQuery;
import com.ufufund.ufb.model.vo.PayRecordQryVo;

public interface OrgQueryMapper extends BaseDao {
	
	
	public List<OrgQuery> queryOrgByCustno(String custno);
	
	public List<OrgQuery> queryStudentByOrgid(@Param("orgid")String orgid,@Param("custno")String custno);
	
	public List<OrgPlanDetail> getQueryCustplandetail(OrgPlanDetail orgDetail);
	
	public OrgQuery getOrginfoByCid(String cid);
	
	public String getOrgidByDetailid(String id);
	
	void updateDetail(OrgPlanDetail orgplandetail);
	
	String selectPayDate(String id);
	
	
	
	
	
	
	
	
	
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
	 * 查询当月的退费金额
	 * @param vo
	 * @return
	 */
	public BigDecimal getReversedMonthAmt(PayRecordQryVo vo);
	
}
