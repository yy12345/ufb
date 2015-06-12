package com.ufufund.ufb.biz.manager.impl.helper;

import com.ufufund.ufb.model.action.cust.AddAutotradeAction;
import com.ufufund.ufb.model.db.Autotrade;
import com.ufufund.ufb.model.db.Fdacfinalresult;
import com.ufufund.ufb.model.enums.AutoTradeType;
import com.ufufund.ufb.model.vo.ApplyVo;
import com.ufufund.ufb.model.vo.RedeemVo;


public  class AutotradeManagerHelper {
	
	public static Autotrade toAutotrade(AddAutotradeAction action){
		Autotrade reaction = new Autotrade();
		// 充值名称
		reaction.setAutoname(action.getAutoname());// varchar(50) default '' comment '自动交易名称',
		// 用户信息
		reaction.setCustno(action.getCustno());// char(24) default '' comment '客户编号',
		// 充值OR取现
		reaction.setTradetype(action.getTradetype().value());// char(3) default null comment '业务类型', AUTO开头业务类型
		// 交易周期
		reaction.setType(action.getType());// char(1) default null comment '类型 S单次，E多次',
		reaction.setCycle(action.getCycle());// char(2) default null comment 'MM=每月；WW=每周;DD 每隔多少天；',
		reaction.setDat(action.getDat());// char(2) default null comment '扣款日',
		// 备注
		reaction.setSummary(action.getSummary());// varchar(100) default null comment '备注',
		
		if(AutoTradeType.AUTORECHARGE.equals(action.getTradetype())){
			// 银行信息
			reaction.setFrombankserialid(action.getFrombankserialid());// char(24) default null comment '源银行卡id',
			// 货币信息
			reaction.setTofundcorpno(action.getTofundcorpno());// char(24) default null comment '目标归属基金公司',
			reaction.setTofundcode(action.getTofundcode());// varchar(6) default null comment '目标基金代码',
			reaction.setTochargetype(action.getTochargetype());// char(1) default null comment '目标A：前收费 B：后收费',
			// 金额
			reaction.setAutoamt(action.getAutoamt());// decimal(16,2) default null comment '金额',
		}
		
		if(AutoTradeType.AUTOWITHDRAWAL.equals(action.getTradetype())){
			// 银行信息
			reaction.setTobankserialid(action.getTobankserialid());// char(24) default null comment '目标银行卡id',
			// 货币信息
			reaction.setFromfundcorpno(action.getFromfundcorpno());// char(24) default null comment '源归属基金公司',
			reaction.setFromfundcode(action.getFromfundcode());// varchar(6) default null comment '源基金代码',
			reaction.setFromchargetype(action.getFromchargetype());// char(1) default null comment '源A：前收费 B：后收费',
			// 份额
			reaction.setAutovol(action.getAutovol());// decimal(16,2) default null comment '份额',
		}
		
		//private String state;// char(1) default null comment 'N:正常 C：删除 P：暂停  ',
		//private int    fromaccoid;// int(11) default null comment '源交易账号编号',
		//private String fromtradeacco;// varchar(17) default null comment '源交易账号',
		//private int    toaccoid;// int(11) default null comment '目标交易账号编号',
		//private String totradeacco;// varchar(17) default null comment '目标交易账号',
		//private String lastdate;// char(8) default null comment '最近扣款日期',
		//private String nextdate;// char(8) default null comment '下一扣款日期',
		return reaction;
	}

	public static Fdacfinalresult toFdacfinalresult(Autotrade vo){
		/*
		 * 写交易流水 
		 * 没有更新交易流水
		 */
		Fdacfinalresult fdacfinalresult = new Fdacfinalresult();
		fdacfinalresult.setCustno(vo.getCustno());
		fdacfinalresult.setFrombankserialid(vo.getFrombankserialid());// varchar(24) default null comment '银行卡id',
		fdacfinalresult.setFromaccoid(vo.getFromaccoid());// int(11) default null comment '交易账号编号',
		fdacfinalresult.setFromtradeacco(vo.getFromtradeacco());// varchar(17) default null comment '交易账号',
		fdacfinalresult.setFromfundcode(vo.getFromfundcode());// varchar(6) default null comment '基金代码',
		fdacfinalresult.setFromfundcorpno(vo.getFromfundcorpno());// varchar(24) default null comment '归属基金公司',
		fdacfinalresult.setFromchargetype(vo.getFromchargetype());// varchar(1) default null comment 'A：前收费 B：后收费',
		fdacfinalresult.setTobankserialid(vo.getTobankserialid());// varchar(24) default null,
		fdacfinalresult.setToaccoid(vo.getToaccoid());// int(11) default null,
		fdacfinalresult.setTotradeacco(vo.getTotradeacco());// varchar(17) default null,
		fdacfinalresult.setTofundcode(vo.getTofundcode());// varchar(6) default null comment '基金代码',
		fdacfinalresult.setTofundcorpno(vo.getTofundcorpno());// varchar(24) default null,
		fdacfinalresult.setTochargetype(vo.getTochargetype());// varchar(1) default null,
		fdacfinalresult.setAppamt(vo.getAutoamt());//` decimal(16,2) default null comment '申请金额',
		fdacfinalresult.setAppvol(vo.getAutovol());//` decimal(16,2) default null comment '申请份额',
		fdacfinalresult.setAutoid(vo.getAutoid());//` decimal(16,2) default null comment '申请份额',
		return fdacfinalresult;
	}
	
	public static ApplyVo toApplyVo(Autotrade vo){
		ApplyVo applyVo = new ApplyVo();
		
		applyVo.setCustno(vo.getCustno());
		applyVo.setBankid(vo.getFrombankserialid());
		applyVo.setBankacco(vo.getFrombankacco());
		applyVo.setFundcode(vo.getTofundcode());
		applyVo.setFundcorpno(vo.getTofundcorpno());
		applyVo.setShareclass(vo.getTochargetype());
		applyVo.setAppamt(vo.getAutoamt());
		//applyVo.setDividmethod(BasicFundinfo.YFB.getDividMethod());
		return applyVo;
	}
	
	public static RedeemVo toRedeemVo(Autotrade vo){
		RedeemVo redeemVo = new RedeemVo();
		redeemVo.setCustno(vo.getCustno());
		redeemVo.setBankid(vo.getTobankserialid());
		redeemVo.setBankacco(vo.getTobankacco());
		redeemVo.setFundcode(vo.getFromfundcode());
		redeemVo.setFundcorpno(vo.getFromfundcorpno());
		redeemVo.setShareclass(vo.getFromchargetype());
		redeemVo.setAppvol(vo.getAutovol());
		//applyVo.setDividmethod(BasicFundinfo.YFB.getDividMethod());
		return redeemVo;
	}
//	public Custinfo toOpenAccountAction(OpenAccountAction openAccountAction){
//		Custinfo custinfo = new Custinfo();
//		custinfo.setCustno(openAccountAction.getCustno());
//		custinfo.setInvnm(openAccountAction.getInvnm());
//		custinfo.setIdno(openAccountAction.getIdno());
//		custinfo.setTradepwd(EncryptUtil.md5(openAccountAction.getTradepwd()));
//		custinfo.setInvtp(Invtp.PERSONAL.getValue());
//		custinfo.setIdtp(openAccountAction.getBankidtp());
//		custinfo.setOpenaccount("Y");
//		return custinfo;
//	}
}
