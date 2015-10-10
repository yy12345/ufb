package com.ufufund.ufb.biz.manager;

import java.util.List;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.PicInfo;

public interface BankCardManager {

	/**
	 * 获取用户绑定的银行卡列表
	 * @param custno
	 * @return
	 */
	public List<Bankcardinfo> getBankcardinfoList(String custno);
	
	public void setBankCardMainFlag(String custno, String bankacco, String mainflag);
	
	public void unbindBankCard(String custno, String bankacco, String state);
	public void deleteCard(String custno, String bankacco);
	/**
	 *  
	 * 开户绑卡 1 验证身份， 2 银行快捷鉴权, 3 银行手机验证 ，4 开户
	 * @param OpenAccountAction
	 * @return 
	 */
	public OpenAccountAction openAccount1(OpenAccountAction openAccountAction) throws BizException;
	public OpenAccountAction openAccount2(OpenAccountAction openAccountAction) throws BizException;
	public OpenAccountAction openAccount3(OpenAccountAction openAccountAction) throws BizException;
	public void openAccountPerson(OpenAccountAction openAccountAction) throws BizException;
	public void openAccountOrg(OpenAccountAction openAccountAction) throws BizException;
	
	public OpenAccountAction openAccoStep1(OpenAccountAction openAccountAction) throws BizException;
	public OpenAccountAction openAccoStep2(OpenAccountAction openAccountAction) throws BizException;
	public OpenAccountAction openAccoStep3(OpenAccountAction openAccountAction) throws BizException;
	
	public void updatePicInfo(PicInfo picInfo) throws BizException;
	public void insertPicInfo(PicInfo picInfo) throws BizException;
	public PicInfo getPicInfo(PicInfo picInfo) throws BizException;
}
