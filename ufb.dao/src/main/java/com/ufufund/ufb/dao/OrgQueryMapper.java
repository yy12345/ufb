package com.ufufund.ufb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.common.dao.BaseDao;
import com.ufufund.ufb.model.vo.QueryCustPayInfo;
import com.ufufund.ufb.model.vo.QueryCustplandetail;
import com.ufufund.ufb.model.vo.QueryOrgPayInfo;
import com.ufufund.ufb.model.vo.QueryOrgStudent;
import com.ufufund.ufb.model.vo.QueryOrgplan;
import com.ufufund.ufb.model.vo.QueryOrgplandetail;
import com.ufufund.ufb.model.vo.QueryOrgplandetailcharge;


public interface OrgQueryMapper extends BaseDao {
	
	
	public List<QueryOrgStudent> getQueryOrgByCustno(
			@Param("custno")String custno
			);
	public List<QueryOrgStudent> getQueryStudentByOrgid(
			@Param("orgid")String orgid,
			@Param("custno")String custno
			);
	public List<QueryOrgStudent> getQueryStudentByCustno(
			@Param("custno")String custno
			);
	

	public List<QueryOrgplan> getQueryOrgplan(@Param("orgid")String orgid);
	
	
	public List<QueryOrgplandetail> getQueryOrgplandetail(
			@Param("orgid")String orgid,
			@Param("planid")String planid
			);
	
	public List<QueryOrgplandetailcharge> getQueryOrgplandetailcharge(
			@Param("orgid")String orgid,
			@Param("planid")String planid,
			@Param("detailid")String detailid
			);
	
	/*
	 * 缴费查询
	 */
	public QueryOrgPayInfo getQueryOrgPayInfo(
			@Param("orgid") String orgid,
			@Param("parameter") String parameter,
			@Param("type")  String type
			);

	/*
	 * 个人缴费查询
	 */
	public QueryCustPayInfo getQueryCustPayInfo(
			@Param("custno") String custno,
			//@Param("parameter") String parameter,
			@Param("type")  String type
			);
	
	
	public List<QueryCustplandetail> getQueryCustplandetail(
			@Param("custno")String custno,
			@Param("orgid")String orgid,
			@Param("detailid")String detailid,
			@Param("ispaylist")List ispaylist);
	public String getOrgidByDetailid(String detailid);
}
