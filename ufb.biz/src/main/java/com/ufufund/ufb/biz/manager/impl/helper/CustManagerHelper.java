package com.ufufund.ufb.biz.manager.impl.helper;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Custinfo;

@Service
public class CustManagerHelper {
	
	public Custinfo toCustinfo(RegisterAction registerAction){
		Custinfo custinfo = new Custinfo();
		custinfo.setMobileno(registerAction.getLogincode());
		custinfo.setPasswd(EncryptUtil.md5(registerAction.getPasswd()));
		custinfo.setTradepwd(EncryptUtil.md5(registerAction.getTradepwd()));
		custinfo.setState(registerAction.getState());
		return custinfo;
	}
	
	public Custinfo toCustinfo(RegisterAction registerAction, OpenAccountAction openAccountAction){
		Custinfo custinfo = new Custinfo();
		custinfo.setMobileno(registerAction.getLogincode());
		custinfo.setPasswd(EncryptUtil.md5(registerAction.getPasswd()));
		custinfo.setTradepwd(EncryptUtil.md5(registerAction.getTradepwd()));
		custinfo.setName(openAccountAction.getName());
		custinfo.setIdtp(openAccountAction.getIdtp());
		custinfo.setIdno(openAccountAction.getIdno());
		custinfo.setState(registerAction.getState());
		return custinfo;
	}

	public Custinfo toOpenAccountAction(OpenAccountAction openAccountAction){
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(openAccountAction.getCustno());
		custinfo.setName(openAccountAction.getName());
		custinfo.setIdno(openAccountAction.getIdno());
		if(!openAccountAction.isOpenaccoflag()){
			custinfo.setTradepwd(EncryptUtil.md5(openAccountAction.getTradepwd()));
		}
		custinfo.setIdtp(openAccountAction.getCerttype());
		return custinfo;
	}
	
	public Custinfo toOpenAccountOrgAction(OpenAccountAction openAccountAction){
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(openAccountAction.getCustno());
		if(!openAccountAction.isOpenaccoflag()){
			custinfo.setName(openAccountAction.getName());
			custinfo.setIdno(openAccountAction.getIdno());
			custinfo.setTradepwd(EncryptUtil.md5(openAccountAction.getTradepwd()));
			custinfo.setIdtp(openAccountAction.getCerttype());
		}
		
		custinfo.setEmail(openAccountAction.getEmail());
		return custinfo;
	}
}
