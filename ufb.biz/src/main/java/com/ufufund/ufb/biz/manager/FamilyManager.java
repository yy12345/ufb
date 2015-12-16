package com.ufufund.ufb.biz.manager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufufund.ufb.biz.manager.FamilyManager;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.dao.FamilyCodesMapper;
import com.ufufund.ufb.dao.OrgQueryMapper;
import com.ufufund.ufb.dao.StudentMapper;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.FamilyCodes;
import com.ufufund.ufb.model.db.OrgQuery;
import com.ufufund.ufb.model.db.Student;

@Service
public class FamilyManager{
	
	@Autowired
	private FamilyCodesMapper familyCodesMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private OrgQueryMapper orgQueryMapper;
	
	/**
	 * 获取家庭绑定学生的识别码对象
	 * @param familyCodes
	 * @return
	 */
	public FamilyCodes getFamilyCodes(FamilyCodes familyCodes){
		return familyCodesMapper.get(familyCodes);
	}

	/**
	 * 获取学生对象
	 * @param sid
	 * @return
	 */
	public Student getStudent(String sid) {
		return studentMapper.get(sid);
	}

	/**
	 * 获取机构对象
	 * @param cid 班级id
	 * @return
	 */
	public OrgQuery getOrgan(String cid) {
		return orgQueryMapper.getOrginfoByCid(cid);
	}

	/**
	 * 绑定学生
	 * @param custinfo 家长信息
	 * @param sid 学生id
	 * @param change 是否改变家长1字段信息
	 */
	@Transactional
	public void bindStudent(Custinfo custinfo, FamilyCodes familyCodes, boolean change){
		
		if(familyCodesMapper.useFamilyCodes(familyCodes.getCode()) > 0){
			Student s = studentMapper.get(familyCodes.getSid());
			Student s1 = new Student();
			s1.setSid(s.getSid());
			s1.setP_custno(custinfo.getCustno());
			s1.setP_date(DateUtil.format(new Date(), DateUtil.DATE_PATTERN_1));
			if(change){
				s1.setP1_name(custinfo.getName());
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
