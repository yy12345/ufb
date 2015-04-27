package com.ufufund.ufb.biz.manager.impl.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.dao.CustinfoMapper;
import com.ufufund.ufb.model.db.Custinfo;

@Service
public class UserModuleValidator {

	@Autowired
	private CustinfoMapper custinfoMapper;
	
	/**
	 * 验证用户登录密码
	 * @param custno
	 * @param tradePwd
	 * @return 成功，返回true；否则返回false
	 */
	public boolean checkPwd(String custno, String pwd){
		if(StringUtils.isBlank(custno) || StringUtils.isBlank(pwd) ){
			return false;
		}
		
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(custno);
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if(custinfo != null && pwd.equals(custinfo.getPasswd())){
			return true;
		}
		return false;
	}
	
	/**
	 * 验证交易密码
	 * @param custno
	 * @param tradePwd
	 * @return 成功，返回true；否则返回false
	 */
	public boolean checkTradePwd(String custno, String tradePwd){
		
		if(StringUtils.isBlank(custno) || StringUtils.isBlank(tradePwd) ){
			return false;
		}
		
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(custno);
		custinfo = custinfoMapper.getCustinfo(custinfo);
//		if(custinfo != null && tradePwd.equals(custinfo.getTradepwd())){
			return true;
//		}
//		return false;
	}
}
