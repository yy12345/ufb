package com.ufufund.ufb.biz.manager.impl.helper;

import com.ufufund.ufb.model.action.cust.AddAutotradeAction;
import com.ufufund.ufb.model.db.Autotrade;


public  class AutotradeManagerHelper {
	
	public static Autotrade toAutotrade(AddAutotradeAction action){
		Autotrade reaction = new Autotrade();
		reaction.setAutoname(action.getAutoname());// varchar(50) default '' comment '自动交易名称',
		reaction.setCustno(action.getCustno());// char(24) default '' comment '客户编号',
		//private String state;// char(1) default null comment 'N:正常 C：删除 P：暂停  ',
		reaction.setApkind(action.getApkind().getValue());// char(3) default null comment '业务类型', AUTO开头业务类型
		reaction.setType(action.getType());// char(1) default null comment '类型 S单次，E多次',
		reaction.setCycle(action.getCycle());// char(2) default null comment 'MM=每月；WW=每周;DD 每隔多少天；',
		reaction.setDat(action.getDat());// char(2) default null comment '扣款日',
		reaction.setFrombankserialid(action.getFrombankserialid());// char(24) default null comment '源银行卡id',
		//private int    fromaccoid;// int(11) default null comment '源交易账号编号',
		//private String fromtradeacco;// varchar(17) default null comment '源交易账号',
		
		reaction.setFromfundcode(action.getFromfundcode());// varchar(6) default null comment '源基金代码',
		reaction.setFromfundcorpno(action.getFromfundcorpno());// char(24) default null comment '源归属基金公司',
		reaction.setFromchargetype(action.getFromchargetype());// char(1) default null comment '源A：前收费 B：后收费',
		
		reaction.setTobankserialid(action.getTobankserialid());// char(24) default null comment '目标银行卡id',
		//private int    toaccoid;// int(11) default null comment '目标交易账号编号',
		//private String totradeacco;// varchar(17) default null comment '目标交易账号',
		
		reaction.setTofundcode(action.getTofundcode());// varchar(6) default null comment '目标基金代码',
		reaction.setTofundcorpno(action.getTofundcorpno());// char(24) default null comment '目标归属基金公司',
		reaction.setTochargetype(action.getTochargetype());// char(1) default null comment '目标A：前收费 B：后收费',
		
		reaction.setAutoamt(action.getAutoamt());// decimal(16,2) default null comment '金额',
		reaction.setAutovol(action.getAutovol());// decimal(16,2) default null comment '份额',
		//private String lastdate;// char(8) default null comment '最近扣款日期',
		//private String nextdate;// char(8) default null comment '下一扣款日期',
		reaction.setSummary(action.getSummary());// varchar(100) default null comment '备注',
		return reaction;
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
