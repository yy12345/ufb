package com.ufufund.ufb.biz.manager;

import java.util.List;

import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.db.Bankcardinfo;

public interface BankCardManager {

	/**
	 * 获取用户绑定的银行卡列表
	 * @param custno
	 * @return
	 */
	public List<Bankcardinfo> getBankcardinfoList(String custno);
	
	/**
	 * 获取用户银行卡列表
	 * @param custno
	 * @return
	 */
	public Bankcardinfo getBankcardinfo(String custno);
	
	/**
	 *  
	 * 开户绑卡:银行快捷鉴权
	 * @param OpenAccountAction
	 * @return 
	 */
	public OpenAccountAction openAccount2(OpenAccountAction openAccountAction);
	
	/**
	 *  
	 * 开户绑卡:银行手机验证
	 * @param OpenAccountAction
	 * @return 
	 */
	public OpenAccountAction openAccount3(OpenAccountAction openAccountAction);
	
	/**
	 *  
	 * 开户绑卡:开户
	 * @param OpenAccountAction
	 * @return 
	 */
	public OpenAccountAction openAccount4(OpenAccountAction openAccountAction);
	
	/**
	 *  
	 * 添加银行卡信息，返回上serialid
	 * @param OpenAccountAction
	 * @return 
	 */
	public String addBankCardinfo(OpenAccountAction openAccountAction);
	
	/**
	 *  
	 * 添加交易账户信息
	 * @param OpenAccountAction
	 * @return 
	 */
	public void addTradeaccoinfo(OpenAccountAction openAccountAction, String bankSerialid);
	
	/**
	 * 银联账户验证
	 * @param openAccountAction
	 */
	public void checkAccount(OpenAccountAction openAccountAction);
	
	/**
	 * 升级银行卡
	 * @param openAccountAction
	 */
	public void updateCard(OpenAccountAction openAccountAction);
	
	/**
	 * 删除银行卡信息
	 * @param serialid
	 */
	public void deleteBankCard(String serialid);
	
}
