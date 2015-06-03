package com.ufufund.ufb.dao;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.model.db.Jobcontral;

public interface JobcontralMapper {
	
	public Jobcontral getJobcontral(Jobcontral jobcontral);
	
	public int insertJobcontral(Jobcontral jobcontral);
	
	public int updateJobcontral(Jobcontral jobcontral);
	
	public int getFdacfinalresult(@Param("autoid")String autoid,@Param("workDate")String workDate);
}
