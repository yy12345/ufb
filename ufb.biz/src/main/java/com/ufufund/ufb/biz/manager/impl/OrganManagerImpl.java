package com.ufufund.ufb.biz.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.OrganManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.common.utils.SequenceUtil;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.dao.BankCardInfoMapper;
import com.ufufund.ufb.dao.OrginfoMapper;
import com.ufufund.ufb.dao.PicinfoMapper;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Orginfo;
import com.ufufund.ufb.model.db.Picinfo;

@Service
public class OrganManagerImpl implements OrganManager{

	@Autowired
	private OrginfoMapper orginfoMapper;
	@Autowired
	private PicinfoMapper picinfoMapper;
	@Autowired
	private BankCardInfoMapper  bankCardInfoMapper;
	
	@Override
	public Orginfo addOrginfo(Orginfo orginfo) {
		 orginfoMapper.addOrginfo(orginfo);
		 return orginfo;
	}
	
	@Override
	public int addPicinfo(Picinfo picinfo) {
		return picinfoMapper.addPicinfo(picinfo);
	}

	@Override
	public Orginfo getOrginfo(Orginfo orginfo) {
		return orginfoMapper.getOrginfo(orginfo);
	}

	@Override
	public boolean isMobileRegister(Orginfo orginfo) {
		boolean result=false;
		if(StringUtils.isBlank(orginfo.getOperator_mobile())){
			throw new UserException("手机号为空！");
		}
		if(!RegexUtil.isMobile(orginfo.getOperator_mobile())){
			throw new UserException("手机号格式不正确！");
		}
		Orginfo corp=orginfoMapper.getOrginfo(orginfo);
		if(corp!=null&&corp.getOrgid()!=null&&!"".equals(corp.getOrgid())){
			result=true;
		}
		return result;
	}

	@Override
	public Orginfo login(Orginfo orginfo) {
		// 用户信息校验
		if(StringUtils.isBlank(orginfo.getOperator_mobile())||StringUtils.isBlank(orginfo.getPasswd())){
			throw new UserException("参数为空!");
		}
		if(!RegexUtil.isMobile(orginfo.getOperator_mobile())){
			throw new UserException("登录用户名不正确!");
		}
		
		// 获得数据库用户信息
		orginfo.setPasswd(EncryptUtil.md5(orginfo.getPasswd())); // 根据手机号和密码匹配用户信息,如不同时校验，修改对应的mapper文件(去掉密码的条件)
		Orginfo org=orginfoMapper.getOrginfo(orginfo);
		if(org==null||org.getOrgid()==null||"".equals(org.getOrgid())){
			throw new UserException("登录账号无效!");
		}
		if("5".equals(org.getState())){
			throw new UserException("登录账号已经被冻结!");
		}
		
		// 登录密码错误次数超过5次，账号被冻结
		/*if(!EncryptUtil.md5(orginfo.getPasswd()).equals(org.getPasswd())){
			org.setPasswd_err(org.getPasswd_err()+1);
			if(org.getPasswd_err()==5){
				org.setState("5");
			}
			orginfoMapper.updateForLogin(org);
			throw new UserException("登录账号无效!");
		}*/
		
		orginfoMapper.updateForLogin(org);
		return org;
	}

	@Override
	@Transactional
	public void bindOrgan(Orginfo orginfo, Picinfo picinfo, Bankcardinfo bankcardinfo) {
		picinfo.setCustno(orginfo.getOrgid());
		
		bankcardinfo.setCustno(orginfo.getOrgid());
		bankcardinfo.setState("Y");
		bankcardinfo.setCertno(orginfo.getOperator_certno());
		bankcardinfo.setCerttype("0");
		
		orginfoMapper.updateOrginfo(orginfo);
		this.addPicinfo(picinfo);
		bankcardinfo.setSerialid(SequenceUtil.getSerial());
		bankCardInfoMapper.insterBankcardinfo(bankcardinfo);
		
		// 更新用户已认证状态
		Orginfo org = new Orginfo();
		org.setOrgid(orginfo.getOrgid());
		org.setState("3");
		orginfoMapper.updateState(org);
	}

	@Override
	public boolean isCertnoRegister(String certno) {
		Orginfo orginfo = new Orginfo();
		orginfo.setOperator_certno(certno);
		orginfo=orginfoMapper.isCertnoRegister(orginfo);
		if(orginfo!=null){
			return true;
		}
		return false;
	}

}
