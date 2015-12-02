package com.ufufund.ufb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufufund.ufb.biz.manager.OrganManager;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.model.db.Orginfo;
import com.ufufund.ufb.web.util.OrgUserHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 机构版，账户相关业务模块
 * @author ayis
 * 2015年11月24日
 */
@Controller
@RequestMapping(value="corp/uft")
@Slf4j
public class UftCorpController {
	
	private static final String UFT_INDEX = "corp/uft/uft_index.htm";
	private static final String UFT_INDEX_NAME = "我的账户";
	
	@Autowired
	private OrganManager organManager;
	/**
	 * 机构版，幼富通首页
	 */
	@RequestMapping(value="uft_index")
	public String uftIndex(Model model){
		
		String orgid = OrgUserHelper.getOrgid();
		try {
			Orginfo orginfo = new Orginfo();
			orginfo.setOrgid(orgid);
			orginfo=organManager.getOrginfo(orginfo);
			
			// 新用户进行账户验证
			if("2".equals(orginfo.getState())){
				return "redirect:/corp/auth/auth_index.htm";
			}
			
			// 对公银行账户验证
			if("3".equals(orginfo.getState())){
				return "redirect:/corp/auth/code_index.htm";
			}
		} catch (UserException ue) {
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", UFT_INDEX);
			model.addAttribute("back_module", UFT_INDEX_NAME);
			return "error/error";
		}
		
		return "organ/uft/uft_index";
	}

	/**
	 * 机构版，收费明细
	 */
	@RequestMapping(value="records")
	public String records(Model model){
		
		return "";
	}
}
