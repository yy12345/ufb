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
		custinfo.setMobileno(registerAction.getLoginCode());
		custinfo.setPasswd(EncryptUtil.md5(registerAction.getLoginPassword()));
		custinfo.setInvtp(registerAction.getInvtp().getValue());
		custinfo.setLevel(registerAction.getLevel().getValue());
		custinfo.setOrganization(registerAction.getOrganization());
		custinfo.setBusiness(registerAction.getBusiness());
		return custinfo;
	}

	public Custinfo toOpenAccountAction(OpenAccountAction openAccountAction){
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(openAccountAction.getCustno());
		custinfo.setInvnm(openAccountAction.getInvnm());
		custinfo.setIdno(openAccountAction.getIdno());
		custinfo.setTradepwd(EncryptUtil.md5(openAccountAction.getTradepwd()));
		custinfo.setInvtp(Invtp.PERSONAL.getValue());
		custinfo.setIdtp(openAccountAction.getBankidtp());
		custinfo.setOpenaccount("Y");
		return custinfo;
	}
}
