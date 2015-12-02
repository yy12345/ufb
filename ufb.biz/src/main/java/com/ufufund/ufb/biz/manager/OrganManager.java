package com.ufufund.ufb.biz.manager;

import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.OrgCodes;
import com.ufufund.ufb.model.db.Orginfo;
import com.ufufund.ufb.model.db.Picinfo;

public interface OrganManager {

	/**
	 * 添加机构信息
	 * @param orginfo
	 * @return
	 */
	Orginfo addOrginfo(Orginfo orginfo);
	
	/**
	 * 添加图片信息
	 * @param picinfo
	 * @return
	 */
	int addPicinfo(Picinfo picinfo);
	
	/**
	 * 获得机构信息
	 * @param orginfo
	 * @return
	 */
	Orginfo getOrginfo(Orginfo orginfo);
	
	/**
	 * 手机号是否注册
	 * @param orginfo
	 * @return
	 */
	boolean isMobileRegister(Orginfo orginfo);
	
	/**
	 * 身份证是否注册
	 * @param orginfo
	 * @return
	 */
	boolean isCertnoRegister(String certno);
	
	/**
	 * 登录
	 */
	public Orginfo login(Orginfo orginfo);
	
	/**
	 * 修改机构基本信息、添加机构证件信息、添加机构银行信息
	 * @param orginfo
	 * @param picinfo
	 * @param bankcardinfo
	 */
	public void bindOrgan(Orginfo orginfo,Picinfo picinfo,Bankcardinfo bankcardinfo);
	
	/**
	 * 更新机构用户的状态
	 * @param state
	 * @param orgid
	 */
	public void updateState(String state,String orgid);
	
	/**
	 * 判断金额验证的失效时间
	 * @param orgcode
	 * @return
	 */
	public boolean getAmtInvalid(OrgCodes orgcode);
	/**
	 * 获得金额校验
	 * @param orgCodes
	 * @return
	 */
	public boolean getOrgCodes(OrgCodes orgCodes);
	/**
	 * 发送1元随机金额
	 * @param orgid
	 */
	public void sendAmt(String orgid);
}
