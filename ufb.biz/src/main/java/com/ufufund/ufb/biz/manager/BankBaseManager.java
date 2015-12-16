package com.ufufund.ufb.biz.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.dao.BankBaseMapper;
import com.ufufund.ufb.model.db.BankBaseInfo;
import com.ufufund.ufb.model.db.BankCardbin;

@Service
public class BankBaseManager{

	@Autowired
	private BankBaseMapper bankBaseMapper;
	
	/**
	 * 获取银行列表
	 * @param bankno
	 * @return
	 */
	public List<BankBaseInfo> getBankBaseInfoList(String bankno) {

		BankBaseInfo bankBaseInfo = new BankBaseInfo();
		bankBaseInfo.setBankno(bankno);

		return bankBaseMapper.getBankBaseInfo(bankBaseInfo);
	}

	/**
	 * 获取银行卡bin对象
	 * @param bin
	 * @return
	 */
	public BankCardbin getBankCardbin(String bin) {
		return bankBaseMapper.getBankCardbin(bin);
	}
	
	/**
	 * 根据bankno判断银行卡是否支持幼富通
	 * @param bankno
	 * @return
	 */
	public String getLevelByBankno(String bankno) {
		return bankBaseMapper.getLevelByBankno(bankno);
	}
}
