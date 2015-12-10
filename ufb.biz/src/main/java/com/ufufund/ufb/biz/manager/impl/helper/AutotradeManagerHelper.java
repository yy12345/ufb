package com.ufufund.ufb.biz.manager.impl.helper;

import com.ufufund.ufb.model.action.cust.AddAutotradeAction;
import com.ufufund.ufb.model.db.Apply;
import com.ufufund.ufb.model.db.Autotrade;
import com.ufufund.ufb.model.db.Fdacfinalresult;
import com.ufufund.ufb.model.db.Redeem;
import com.ufufund.ufb.model.enums.AutoTradeType;


public  class AutotradeManagerHelper {
	
	public static Autotrade toAutotrade(AddAutotradeAction action){
		Autotrade reaction = new Autotrade();
		// 充值名称
		reaction.setAutoname(action.getAutoname());
		// 用户信息
		reaction.setCustno(action.getCustno());   
		// 充值OR取现
		reaction.setTradetype(action.getTradetype().value());
		// 交易周期
		reaction.setType(action.getType());                
		reaction.setCycle(action.getCycle());             
		reaction.setDat(action.getDat());                 
		// 备注
		reaction.setSummary(action.getSummary());        
		reaction.setDetailid(action.getDetailid());
		
		if(AutoTradeType.AUTORECHARGE.equals(action.getTradetype())){
			// 银行信息
			reaction.setFrombankserialid(action.getFrombankserialid());
			// 货币信息
			reaction.setTofundcorpno(action.getTofundcorpno());
			reaction.setTofundcode(action.getTofundcode());
			reaction.setTochargetype(action.getTochargetype());
			// 金额
			reaction.setAutoamt(action.getAutoamt());
		}
		
		if(AutoTradeType.AUTOWITHDRAWAL.equals(action.getTradetype())){
			// 银行信息
			reaction.setTobankserialid(action.getTobankserialid());
			// 货币信息
			reaction.setFromfundcorpno(action.getFromfundcorpno());
			reaction.setFromfundcode(action.getFromfundcode());
			reaction.setFromchargetype(action.getFromchargetype());
			// 份额
			reaction.setAutovol(action.getAutovol());
			// 金额
			reaction.setAutoamt(action.getAutoamt());
		}
		
		return reaction;
	}

	public static Fdacfinalresult toFdacfinalresult(Autotrade vo){
		/*
		 * 写交易流水 
		 * 没有更新交易流水
		 */
		Fdacfinalresult fdacfinalresult = new Fdacfinalresult();
		fdacfinalresult.setCustno(vo.getCustno());
		fdacfinalresult.setFrombankserialid(vo.getFrombankserialid());
		fdacfinalresult.setFromtradeacco(vo.getFromtradeacco());
		fdacfinalresult.setFromfundcode(vo.getFromfundcode());
		fdacfinalresult.setFromfundcorpno(vo.getFromfundcorpno());
		fdacfinalresult.setFromchargetype(vo.getFromchargetype());
		fdacfinalresult.setTobankserialid(vo.getTobankserialid());
		fdacfinalresult.setTotradeacco(vo.getTotradeacco());
		fdacfinalresult.setTofundcode(vo.getTofundcode());
		fdacfinalresult.setTofundcorpno(vo.getTofundcorpno());
		fdacfinalresult.setTochargetype(vo.getTochargetype());
		fdacfinalresult.setAppamt(vo.getAutoamt());
		fdacfinalresult.setAppvol(vo.getAutovol());
		fdacfinalresult.setAutoid(vo.getAutoid());
		return fdacfinalresult;
	}
	
	public static Apply toApplyVo(Autotrade vo){
		Apply applyVo = new Apply();
		
		applyVo.setCustno(vo.getCustno());
		applyVo.setBankid(vo.getFrombankserialid());
		applyVo.setBankacco(vo.getFrombankacco());
		applyVo.setFundcode(vo.getTofundcode());
		applyVo.setFundcorpno(vo.getTofundcorpno());
		applyVo.setShareclass(vo.getTochargetype());
		applyVo.setAppamt(vo.getAutoamt());
		
		// for quartz
		applyVo.setApkind("51");
		applyVo.setTradeacco(vo.getTotradeacco());
		applyVo.setReferno(vo.getAutoid());
		return applyVo;
	}
	
	public static Redeem toRedeemVo(Autotrade vo){
		Redeem redeemVo = new Redeem();
		redeemVo.setCustno(vo.getCustno());
		redeemVo.setBankid(vo.getTobankserialid());
		redeemVo.setBankacco(vo.getTobankacco());
		redeemVo.setFundcode(vo.getFromfundcode());
		redeemVo.setFundcorpno(vo.getFromfundcorpno());
		redeemVo.setShareclass(vo.getFromchargetype());
		redeemVo.setAppvol(vo.getAutovol());
		
		// for quartz
		redeemVo.setApkind("52");
		redeemVo.setTradeacco(vo.getFromtradeacco());
		
		return redeemVo;
	}
}
