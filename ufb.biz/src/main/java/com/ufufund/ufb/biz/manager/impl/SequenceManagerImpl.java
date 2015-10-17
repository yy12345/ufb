package com.ufufund.ufb.biz.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.SequenceManager;
import com.ufufund.ufb.dao.SequenceMapper;

@Service
public class SequenceManagerImpl implements SequenceManager {

	@Autowired
	private SequenceMapper sequenceMapper;
	
	
	/*
	 * getSequence(sequenceNo, sequenceLen, type)
	 * 
	 * @Param("sequenceNo")    序列名
	 * @Param("sequenceLen")   序列长度
	 * @Param("type")		        序列类型  A-长度不够补字母或者数字  B-长度不够补0  C-数字自增长
	 * 
	 */
	@Override
	public String getCustinfoSequence() {
		// TODO Auto-generated method stub
		return sequenceMapper.getSequence("01_CUSTNO", 24, "A");
	}

	@Override
	public String getFdacfinalresultSeq() {
		// TODO Auto-generated method stub
		return sequenceMapper.getSequence("11_FDSERIALNO", 24, "A");
	}

	@Override
	public String getBankcardinfoSequence() {
		// TODO Auto-generated method stub
		return sequenceMapper.getSequence("21_BANKSERIALID", 24, "A");
	}

	@Override
	public String getAccoreqSerialSeq() {
		// TODO Auto-generated method stub
		return sequenceMapper.getSequence("22_TRADEACCO", 17, "A");
	}
	
	
	@Override
	public String getTradeaccoinfoSeq() {
		// TODO Auto-generated method stub
		return sequenceMapper.getSequence("23_TRADEACCOID", 24, "A");
	}


	@Override
	public String getAutotradeSequence() {
		// TODO Auto-generated method stub
		return sequenceMapper.getSequence("51_AUTOID", 24, "A");
	}

	@Override
	public String getGradeid() {
		return sequenceMapper.getSequence("71_GRADEID", 24, "A");
	}

	@Override
	public String getPlanid() {
		// TODO Auto-generated method stub
		return sequenceMapper.getSequence("72_PLANID", 24, "A");
	}

	@Override
	public String getPlanDetailid() {
		// TODO Auto-generated method stub
		// return "";
		return sequenceMapper.getSequence("72_PLANDETAILID", 10, "C");
	}

}
