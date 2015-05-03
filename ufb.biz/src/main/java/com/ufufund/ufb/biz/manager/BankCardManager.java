package com.ufufund.ufb.biz.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.db.BankCardWithTradeAcco;
import com.ufufund.ufb.model.db.Bankcardinfo;

public interface BankCardManager {

	/**
	 * 获取用户绑定的银行卡列表
	 * @param custno
	 * @return
	 */
	public List<Bankcardinfo> getBankcardinfoList(String custno);
	
	/**
	 * 获取带关联银行卡信息的基金交易账号列表
	 * @param custno
	 * @return
	 */
	public List<BankCardWithTradeAcco> getBankCardWithTradeAccoList(String custno, String state); 
	
	/**
	 *  
	 * 开户绑卡 1 验证身份， 2 银行快捷鉴权, 3 银行手机验证 ，4 开户
	 * @param OpenAccountAction
	 * @return 
	 */
	public OpenAccountAction openAccount1(OpenAccountAction openAccountAction) throws BizException;
	public OpenAccountAction openAccount2(OpenAccountAction openAccountAction) throws BizException;
	public OpenAccountAction openAccount3(OpenAccountAction openAccountAction) throws BizException;
	public void openAccount4(OpenAccountAction openAccountAction) throws BizException;
}
