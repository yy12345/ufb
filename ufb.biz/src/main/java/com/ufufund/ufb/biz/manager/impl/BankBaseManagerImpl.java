package com.ufufund.ufb.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.BankBaseManager;
import com.ufufund.ufb.dao.BankBaseMapper;
import com.ufufund.ufb.model.db.BankBaseInfo;
import com.ufufund.ufb.model.db.BankCardbin;

@Service
public class BankBaseManagerImpl implements BankBaseManager {

	@Autowired
	private BankBaseMapper bankBaseMapper;
	
	@Override
	public List<BankBaseInfo> getBankBaseInfoList(String bankno) {

		BankBaseInfo bankBaseInfo = new BankBaseInfo();
		bankBaseInfo.setBankno(bankno);

		return bankBaseMapper.getBankBaseInfo(bankBaseInfo);
	}

	@Override
	public BankCardbin getBankCardbin(String bin) {
		return bankBaseMapper.getBankCardbin(bin);
	}
	
	/**
	 * 根据bankno判断银行卡是否支持幼富通
	 * parameter bankno
	 */
	@Override
	public String getLevelByBankno(String bankno) {
		return bankBaseMapper.getLevelByBankno(bankno);
	}
}
