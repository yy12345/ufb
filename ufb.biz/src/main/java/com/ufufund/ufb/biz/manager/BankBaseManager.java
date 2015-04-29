package com.ufufund.ufb.biz.manager;

import java.util.List;

import com.ufufund.ufb.model.db.BankBaseInfo;

public interface BankBaseManager {

	/**
	 * 获取银行列表
	 * @param bankno
	 * @return
	 */
	public List<BankBaseInfo> getBankBaseInfoList(String bankno);
	
//	/**
//	 *  
//	 * 开户绑卡 1 验证身份， 2 银行快捷鉴权, 3 银行手机验证 ，4 开户
//	 * @param OpenAccountAction
//	 * @return 
//	 */
//	public OpenAccountAction openAccount1(OpenAccountAction openAccountAction) throws BizException;
//	public OpenAccountAction openAccount2(OpenAccountAction openAccountAction) throws BizException;
//	public OpenAccountAction openAccount3(OpenAccountAction openAccountAction) throws BizException;
//	public void openAccount4(OpenAccountAction openAccountAction) throws BizException;
}
