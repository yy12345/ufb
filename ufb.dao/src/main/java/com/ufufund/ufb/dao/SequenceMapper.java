package com.ufufund.ufb.dao;

import org.apache.ibatis.annotations.Param;

public interface SequenceMapper {

//	public String getCustNo();
//	
//	public String getSerialNO();
	
	public String getSequence(
			@Param("sequenceNo")String sequenceNo, 
			@Param("sequenceLen")int sequenceLen,
			@Param("type")String type
			);
	
	//public String getSequenceByInt(String sequenceNo);
	
	
	
}
