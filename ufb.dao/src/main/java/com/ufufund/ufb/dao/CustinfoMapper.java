package com.ufufund.ufb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.Student;
import com.ufufund.ufb.model.vo.StudentVo;


public interface CustinfoMapper  {
	
	public Custinfo getCustinfo(Custinfo custinfo);
	
	public Custinfo isTradePwdSet(Custinfo custinfo);
	
	public Custinfo isIdNoBindByTradeAcco(@Param("fundcorpno")String fundcorpno, @Param("invtp")String invtp, @Param("level")String level, @Param("idno")String idno);
	
	public String getCustinfoMapping(@Param("orgNo")String orgNo, @Param("oprNo")String oprNo);
	/*
	 * 不使用  ，使用CustManager insterCustinfo方法
	 */
	public void insertCustinfo(Custinfo custinfo);
	
	public void updateCustinfo(Custinfo custinfo);
	
	public List<Student> queryStudentsByCustno(String custno);
	
	public StudentVo  queryOrgsByCid(String cid);
	
	public Custinfo getCustinfoByCid(String cid);
}
