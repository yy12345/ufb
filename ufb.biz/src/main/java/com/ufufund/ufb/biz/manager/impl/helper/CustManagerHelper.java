package com.ufufund.ufb.biz.manager.impl.helper;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.enums.Invtp;

@Service
public class CustManagerHelper {
	
	public Custinfo toCustinfo(RegisterAction registerAction){
		Custinfo custinfo = new Custinfo();
		custinfo.setMobileno(registerAction.getLogincode());
		custinfo.setLoginpwd(EncryptUtil.md5(registerAction.getLoginpwd()));
		custinfo.setTradepwd(EncryptUtil.md5(registerAction.getTradepwd()));
		custinfo.setInvtp(registerAction.getInvtp().getValue());
		custinfo.setLevel(registerAction.getLevel().getValue());
		custinfo.setOrgnm(registerAction.getOrgnm());
		custinfo.setOrgbusiness(registerAction.getOrgbusiness());
		custinfo.setCustst(registerAction.getCustst());
		return custinfo;
	}
	
	public Custinfo toCustinfo(RegisterAction registerAction, OpenAccountAction openAccountAction){
		Custinfo custinfo = new Custinfo();
		custinfo.setMobileno(registerAction.getLogincode());
		custinfo.setLoginpwd(EncryptUtil.md5(registerAction.getLoginpwd()));
		custinfo.setTradepwd(EncryptUtil.md5(registerAction.getTradepwd()));
		custinfo.setInvtp(registerAction.getInvtp().getValue());
		custinfo.setLevel(registerAction.getLevel().getValue());
		custinfo.setInvnm(openAccountAction.getInvnm());
		custinfo.setIdtp(openAccountAction.getIdtp());
		custinfo.setIdno(openAccountAction.getIdno());
		custinfo.setOrgnm(registerAction.getOrgnm());
		custinfo.setOrgbusiness(registerAction.getOrgbusiness());
		custinfo.setCustst(registerAction.getCustst());
		return custinfo;
	}

	public Custinfo toOpenAccountAction(OpenAccountAction openAccountAction){
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(openAccountAction.getCustno());
		custinfo.setInvnm(openAccountAction.getInvnm());
		custinfo.setIdno(openAccountAction.getIdno());
		if(!openAccountAction.isOpenaccoflag()){
			custinfo.setTradepwd(EncryptUtil.md5(openAccountAction.getTradepwd()));
		}
		custinfo.setInvtp(Invtp.PERSONAL.getValue());
		custinfo.setIdtp(openAccountAction.getCerttype());
		return custinfo;
	}
	
	public Custinfo toOpenAccountOrgAction(OpenAccountAction openAccountAction){
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(openAccountAction.getCustno());
		if(!openAccountAction.isOpenaccoflag()){
			custinfo.setInvnm(openAccountAction.getInvnm());
			custinfo.setIdno(openAccountAction.getIdno());
			custinfo.setTradepwd(EncryptUtil.md5(openAccountAction.getTradepwd()));
			custinfo.setInvtp(Invtp.ORGANIZATION.getValue());
			custinfo.setIdtp(openAccountAction.getCerttype());
		}
		
		custinfo.setTelno(openAccountAction.getTelno());
		custinfo.setEmailadd(openAccountAction.getEmailadd());
		custinfo.setOrgprovinceno(openAccountAction.getOrgprovinceno());
		custinfo.setOrgcityno(openAccountAction.getOrgcityno());
		custinfo.setOrgadd(openAccountAction.getOrgadd());
		custinfo.setRerpidtp(openAccountAction.getRerpidtp());
		custinfo.setRerpidno(openAccountAction.getRerpidno());
		custinfo.setRerpnm(openAccountAction.getRerpnm());
		
		return custinfo;
	}
}
