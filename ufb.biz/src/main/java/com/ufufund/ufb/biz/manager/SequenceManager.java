package com.ufufund.ufb.biz.manager;

/**
 * 
 * 
 * @author gaoxin
 *
 */
public interface SequenceManager {

	/*
	 * 客户类序列
	 */
	// 获取客户号
	public String getCustinfoSequence();

	/*
	 * 交易类序列 
	 */
	// 流水号
	public String getFdacfinalresultSeq();

	/*
	 * 银行类序列
	 */
	// 银行流水号
	public String getBankcardinfoSequence();

	// 交易账号
	public String getAccoreqSerialSeq();
	
	
	// 交易账ID
	public String getTradeaccoinfoSeq();

	/*
	 * 自动任务序列
	 */
	// 自动任务流水号
	public String getAutotradeSequence();

	/*
	 * 机构版序列
	 */
	// 学年ID号
	public String getGradeid();

	// 计划ID号
	public String getPlanid();

	// 计划明细ID
	public String getPlanDetailid();

}
