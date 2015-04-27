package com.ufufund.ufb.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.dao.BankMapper;
import com.ufufund.ufb.model.db.Bankcardinfo;

@Service
public class BankCardManagerImpl implements BankCardManager{

	@Autowired
	private BankMapper bankCardMapper;
	
	@Override
	public List<Bankcardinfo> getBankcardinfoList(String custno){
		
		Bankcardinfo bankcardinfo = new Bankcardinfo();
		bankcardinfo.setCustno(custno);
		
		return bankCardMapper.getBankcardinfo(bankcardinfo);
	}
}
