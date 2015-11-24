package com.ufufund.ufb.biz.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.OrganManager;
import com.ufufund.ufb.common.constant.BisConst.Register;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.dao.OrginfoMapper;
import com.ufufund.ufb.dao.PicinfoMapper;
import com.ufufund.ufb.model.db.Orginfo;
import com.ufufund.ufb.model.db.Picinfo;

@Service
public class OrganManagerImpl implements OrganManager{

	@Autowired
	private OrginfoMapper orginfoMapper;
	@Autowired
	private PicinfoMapper picinfoMapper;
	
	
	@Override
	public int addOrginfo(Orginfo orginfo) {
		return orginfoMapper.addOrginfo(orginfo);
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
			throw new BizException("手机号为空！");
		}
		if(!RegexUtil.isMobile(orginfo.getOperator_mobile())){
			throw new BizException("手机号格式不正确！");
		}
		Orginfo corp=orginfoMapper.getOrginfo(orginfo);
		if(corp!=null&&corp.getOrgid()!=null&&!"".equals(corp.getOrgid())){
			result=true;
		}
		return result;
	}

}
