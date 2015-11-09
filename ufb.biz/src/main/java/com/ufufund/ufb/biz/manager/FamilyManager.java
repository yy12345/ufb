package com.ufufund.ufb.biz.manager;

import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.FamilyCodes;
import com.ufufund.ufb.model.db.Student;
import com.ufufund.ufb.model.vo.CustinfoVo;

public interface FamilyManager {

	/**
	 * 获取家庭绑定学生的识别码对象
	 * @param familyCodes
	 * @return
	 */
	FamilyCodes getFamilyCodes(FamilyCodes familyCodes);
	
	/**
	 * 获取学生对象
	 * @param sid
	 * @return
	 */
	Student getStudent(String sid);
	
	/**
	 * 获取机构对象
	 * @param cid 班级id
	 * @return
	 */
	Custinfo getOrgan(String cid);
	
	/**
	 * 绑定学生
	 * @param custinfo 家长信息
	 * @param sid 学生id
	 * @param change 是否改变家长1字段信息
	 */
	void bindStudent(CustinfoVo custinfo, FamilyCodes familyCodes, boolean change);
}
