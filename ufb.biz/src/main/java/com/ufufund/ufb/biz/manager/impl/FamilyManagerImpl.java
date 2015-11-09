package com.ufufund.ufb.biz.manager.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.FamilyManager;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.dao.CustinfoMapper;
import com.ufufund.ufb.dao.FamilyCodesMapper;
import com.ufufund.ufb.dao.StudentMapper;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.FamilyCodes;
import com.ufufund.ufb.model.db.Student;
import com.ufufund.ufb.model.vo.CustinfoVo;

@Service
public class FamilyManagerImpl implements FamilyManager{
	
	@Autowired
	private FamilyCodesMapper familyCodesMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private CustinfoMapper custinfoMapper;
	
	@Override
	public FamilyCodes getFamilyCodes(FamilyCodes familyCodes) {
		return familyCodesMapper.get(familyCodes);
	}

	@Override
	public Student getStudent(String sid) {
		return studentMapper.get(sid);
	}

	@Override
	public Custinfo getOrgan(String cid) {
		return custinfoMapper.getCustinfoByCid(cid);
	}

	@Override
	@Transactional
	public void bindStudent(CustinfoVo custinfo, FamilyCodes familyCodes, boolean change) {
		
		if(familyCodesMapper.useFamilyCodes(familyCodes.getCode()) > 0){
			Student s = studentMapper.get(familyCodes.getSid());
			Student s1 = new Student();
			s1.setSid(s.getSid());
			s1.setP_custno(custinfo.getCustno());
			s1.setP_date(DateUtil.format(new Date(), DateUtil.DATE_PATTERN_1));
			if(change){
				s1.setP1_name(custinfo.getInvnm());
				s1.setP1_mobile(custinfo.getMobileno());
				s1.setP2_relation(s.getP1_relation());
				s1.setP2_name(s.getP1_name());
				s1.setP2_mobile(s.getP1_mobile());
				s1.setP2_mail(s.getP1_mail());
				s1.setP2_work(s.getP1_work());
			}
			studentMapper.bindStudent(s1);
		}
	}


}
