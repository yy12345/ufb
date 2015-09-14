package com.ufufund.ufb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.common.dao.BaseDao;
import com.ufufund.ufb.model.db.Autotrade;


public interface AutotradeMapper extends BaseDao {
	
	
//	/**
//	 * 查询手机号是否注册
//	 * @param CustinfoAction custinfoAction
//	 * @return 
//	 * 
//	 * rownum  = 1;
//	 */
//	public Custinfo getCustinfo(Custinfo custinfo);
//	
//	/*
//	 * 不使用  ，使用CustManager insterCustinfo方法
//	 */
//	public void insertCustinfo(Custinfo custinfo);
//	
//	
//	public void updateCustinfo(Custinfo custinfo);
	
	//public String  getAutotradeSequence();
	
	public int insertAutotrade(Autotrade autotrade);
	
	public int updateAutotrade(Autotrade autotrade);
	
	public List<Autotrade> getAutotradeList(Autotrade autotrade);

	public Autotrade getAutotrade(@Param("autoid")String autoid);
	
	
}
