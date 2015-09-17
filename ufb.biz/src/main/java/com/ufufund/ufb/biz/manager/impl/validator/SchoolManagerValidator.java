package com.ufufund.ufb.biz.manager.impl.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.dao.ClazzMapper;
import com.ufufund.ufb.model.db.Clazz;
import com.ufufund.ufb.model.db.ClazzType;

@Service
public class SchoolManagerValidator {

	@Autowired
	private ClazzMapper clazzMapper;
	
	public void validateAddClazz(Clazz clazz){
		
		if(clazz == null || StringUtils.isBlank(clazz.getCid())
				|| StringUtils.isBlank(clazz.getOrgid())
				|| StringUtils.isBlank(clazz.getTypeid())
				|| StringUtils.isBlank(clazz.getName())){
			throw new UserException("输入参数为空！");
		}
		
		Clazz c = new Clazz();
		c.setTypeid(clazz.getTypeid());
		if(clazzMapper.getCount(c) >= 5){
			throw new UserException("只能添加5个子班级！");
		}
	}
	
	public void validateAddClazzType(ClazzType ct){
		
		if(ct == null || StringUtils.isBlank(ct.getId())
				|| StringUtils.isBlank(ct.getOrgid())
				|| StringUtils.isBlank(ct.getName())){
			throw new UserException("输入参数为空！");
		}
	}
}
