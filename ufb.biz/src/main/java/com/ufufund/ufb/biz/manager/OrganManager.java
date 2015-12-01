package com.ufufund.ufb.biz.manager;

import com.ufufund.ufb.model.db.Bankcardinfo;
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
}
