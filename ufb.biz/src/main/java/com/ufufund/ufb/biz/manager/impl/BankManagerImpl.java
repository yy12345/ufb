package com.ufufund.ufb.biz.manager.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.BankManager;
import com.ufufund.ufb.dao.BankMapper;



@Service
public class BankManagerImpl implements BankManager {

	@Autowired
	private BankMapper bnankMapper;
	
	
//	@Override
//	public void insterBankcardinfo(Bankcardinfo bankcardinfo) throws BizException {
//		bnankMapper.insterBankcardinfo(bankcardinfo);
//		/*
//		 * 
//		 * 插入变动记录表
//		 */
//	}
//
//
//	@Override
//	public void insterTradeaccoinfo(Tradeaccoinfo tradeaccoinfo) throws BizException {
//		// TODO Auto-generated method stub
//		bnankMapper.insterTradeaccoinfo(tradeaccoinfo);
//		/*
//		 * 
//		 * 插入变动记录表
//		 */
//	}

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
