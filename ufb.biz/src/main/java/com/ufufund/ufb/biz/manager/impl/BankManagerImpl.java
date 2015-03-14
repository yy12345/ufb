package com.ufufund.ufb.biz.manager.impl;



import org.springframework.beans.factory.annotation.Autowired;

import com.ufufund.action.BankPageAuth;
import com.ufufund.action.BankPageVerify;
import com.ufufund.action.BankSwiftAuth;
import com.ufufund.action.BankSwiftVerify;
import com.ufufund.ufb.biz.manager.BankManager;
import com.ufufund.ufb.dao.BankMapper;
import com.ufufund.ufb.dao.CustinfoMapper;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Tradeaccoinfo;




public class BankManagerImpl implements BankManager {

	@Autowired
	private BankMapper bnankMapper;
	
	
	@Override
	public void insterBankcardinfo(Bankcardinfo bankcardinfo) throws Exception {
		bnankMapper.insterBankcardinfo(bankcardinfo);
		/*
		 * 
		 * 插入变动记录表
		 */
	}


	@Override
	public void insterTradeaccoinfo(Tradeaccoinfo tradeaccoinfo) throws Exception {
		// TODO Auto-generated method stub
		bnankMapper.insterTradeaccoinfo(tradeaccoinfo);
		/*
		 * 
		 * 插入变动记录表
		 */
	}

//	@Override
//	public void bankPageAuth(BankPageAuth bankPageAuth) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void bankPageVerify(BankPageVerify bankPageVerify) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void bankSwiftAuth(BankSwiftAuth bankSwiftAuth) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void bankSwiftVerify(BankSwiftVerify bankSwiftVerify)
//			throws Exception {
//		// TODO Auto-generated method stub
//		
//	}

	


}
