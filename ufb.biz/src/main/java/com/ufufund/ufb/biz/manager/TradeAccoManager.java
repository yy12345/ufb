package com.ufufund.ufb.biz.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.dao.TradeAccoinfoMapper;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.db.Tradeaccoinfo;

/**
 * tradeAcco的manager定义
 * @author ayis
 * 2015年4月29日
 */
@Service
public class TradeAccoManager{

	@Autowired
	private TradeAccoinfoMapper tradeAccoinfoMapper;

	/**
	 * 获取带关联银行卡信息的基金交易账号列表
	 * 备注：只获取银行卡状态正常的记录
	 * @param custno
	 * @return
	 */
	public List<TradeAccoinfoOfMore> getTradeAccoList(String custno) {
		return tradeAccoinfoMapper.getTradeAccoList(custno, Constant.HftSysConfig.HftFundCorpno);
	}
	
	/**
	 * 获取带关联银行卡信息的基金交易账号列表
	 * @param custno fundcorpno
	 * @return
	 */
	public List<TradeAccoinfoOfMore> getTradeAccoList(String custno, String fundcorpno) {
		return tradeAccoinfoMapper.getTradeAccoList(custno, fundcorpno);
	}
	
	/**
	 * 获取带关联银行卡信息的基金交易账号
	 * @param custno fundcorpno bankserialid
	 * @return
	 */
	public TradeAccoinfoOfMore getTradeAcco(String custno, String fundcorpno, String bankserialid){
		return tradeAccoinfoMapper.getTradeAcco(custno, fundcorpno, bankserialid, "Y");
	}

	/**
	 * 根据用户编号查询交易账号的信息
	 * @param custno 
	 * @return
	 */
	public Tradeaccoinfo getTradeaccoinfo(String custno) {
		Tradeaccoinfo tradeacco=new Tradeaccoinfo();
		tradeacco.setCustno(custno);
		return tradeAccoinfoMapper.getTradeaccoinfo(tradeacco);
	}

}
