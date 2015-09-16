package com.ufufund.ufb.dao;

import org.apache.ibatis.annotations.Param;

public interface SequenceMapper {

	/*
	 * getSequence(sequenceNo, sequenceLen, type)
	 * 
	 * @Param("sequenceNo")    序列名
	 * @Param("sequenceLen")   序列长度
	 * @Param("type")		        序列类型  A-长度不够补字母或者数字  B-长度不够补0  C-数字自增长
	 * 
	 */
	public String getSequence(
			@Param("sequenceNo")String sequenceNo, 
			@Param("sequenceLen")int sequenceLen,
			@Param("type")String type
			);
	

	
	
	
}
