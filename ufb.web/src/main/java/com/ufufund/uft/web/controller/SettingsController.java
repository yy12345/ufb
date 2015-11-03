package com.ufufund.uft.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.AutotradeManager;
import com.ufufund.ufb.biz.manager.BankBaseManager;
import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.QueryManager;
import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.action.cust.ChangeAutoStateAction;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.ModifyAutotradeAction;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.db.Autotrade;
import com.ufufund.ufb.model.db.BankBaseInfo;
import com.ufufund.ufb.model.db.BankCardbin;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.Student;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.enums.AutoTradeType;
import com.ufufund.ufb.model.enums.BasicFundinfo;
import com.ufufund.ufb.model.enums.Invtp;
import com.ufufund.ufb.model.vo.Assets;
import com.ufufund.ufb.model.vo.AutotradeVo;
import com.ufufund.ufb.model.vo.BankCardVo;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.model.vo.StudentVo;
import com.ufufund.ufb.model.vo.TradeAccoVo;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.MsgCodeUtils;
import com.ufufund.ufb.web.util.UserHelper;
import com.ufufund.ufb.web.util.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping(value="family/setting")
@Slf4j
public class SettingsController {
	private static final Logger LOG = LoggerFactory.getLogger(SettingsController.class);
	private static final String BANKCARD_INDEX="family/setting/bankcard_index.htm";
	private static final String SETTING_CARD_NAME="银行卡管理";
	private static final String ACCOUNT_INDEX="family/setting/account_index.htm";
	private static final String PASSWORD_INDEX="family/setting/password_index.htm";
	@Autowired
	private CustManager custManager;
	@Autowired
	private BankCardManager bankCardManager;
	@Autowired
	private TradeAccoManager tradeAccoManager;
	@Autowired
	private QueryManager queryManager;
	
	/**
	 * 账户信息
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="account_index")
	public String accountIndex(CustinfoVo custinfoVo, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			custinfoVo.setCustno(s_custinfo.getCustno());;                      
			custinfoVo.setMobileno(s_custinfo.getMobileno());                    
			custinfoVo.setInvtp(s_custinfo.getInvtp()); 
			custinfoVo.setInvnm(s_custinfo.getInvnm());        
			custinfoVo.setIdtp(s_custinfo.getIdtp());     
			custinfoVo.setIdno(s_custinfo.getIdno());             
			custinfoVo.setCustst(s_custinfo.getCustst());
			custinfoVo.setLevel(s_custinfo.getLevel());
			//学生信息
			List<StudentVo> list=new ArrayList<StudentVo>();
		    List<Student> slists=custManager.queryStudentsByCustno(s_custinfo.getCustno());
		    if(slists.size()>0&&slists!=null){
		    	for(Student student:slists){
		    		StudentVo studentVo=new StudentVo();
		    		studentVo.setBirthday(student.getBirthday().substring(0, 4)+"年"+student.getBirthday().substring(5, 7)+"月"+student.getBirthday().substring(8, 10)+"日");
		    		studentVo.setName(student.getName());
		    		studentVo.setSex(student.getSex());
		    		String orgname=((StudentVo)custManager.queryOrgsByCid(student.getCid())).getOrganization();
		    		studentVo.setOrganization(orgname);
		    		list.add(studentVo);
		    	}
		    }
			model.addAttribute("CustinfoVo", custinfoVo);
			model.addAttribute("StudentList", list);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_url", ACCOUNT_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", "返回");
			return "error/error";
		}
		return "family/setting/account_index";
	}
	
	
	/**
	 * 密码设置
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="password_index")
	public String passwordIndex(CustinfoVo custinfoVo, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			custinfoVo.setCustno(s_custinfo.getCustno());;                      
			custinfoVo.setMobileno(s_custinfo.getMobileno());                    
			custinfoVo.setInvtp(s_custinfo.getInvtp()); 
			custinfoVo.setInvnm(s_custinfo.getInvnm());        
			custinfoVo.setIdtp(s_custinfo.getIdtp());     
			custinfoVo.setIdno(s_custinfo.getIdno());             
			custinfoVo.setCustst(s_custinfo.getCustst());
			custinfoVo.setLevel(s_custinfo.getLevel());
			model.addAttribute("CustinfoVo", custinfoVo);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "密码设置");
			model.addAttribute("message_url", PASSWORD_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", "返回");
			return "error/error";
		}
		return "family/setting/password_index";
	}
	
	/**
	 * 登录状态      修改登录密码
	 * @param password1
	 * @param model
	 * @return
	 */
 	@RequestMapping(value="update_loginPwd")
 	@ResponseBody
	public Map<String,Object> updateLoginPwd(String login_password0,String login_password1){
 		Map<String,Object> resultMap=new HashMap<String,Object>();
 		try{
			CustinfoVo s_custinfo=UserHelper.getCustinfoVo();
			ChangePasswordAction changePasswordAction = new ChangePasswordAction();
			changePasswordAction.setActionType("LOGIN");
			changePasswordAction.setCustno(s_custinfo.getCustno());
			changePasswordAction.setPassword1(login_password1);
			String password=s_custinfo.getLoginpwd();
			login_password0=EncryptUtil.md5(login_password0);
			if(!password.equals(login_password0)){
				resultMap.put("errCode", "0001");
				resultMap.put("errMsg", "原登录密码不正确！");
				return resultMap;
			}
			/** 修改登录密码 **/
			custManager.changePassword(changePasswordAction);
			resultMap.put("errCode", "0000");
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			resultMap.put("errCode", ue.getCode());
			resultMap.put("errMsg", ue.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		 
		return resultMap;
	}
	 
	/**
	 * 修改交易密码
	 * @param password0
	 * @param password1
	 * @param password2
	 * @param model
	 * @return
	 */
	@RequestMapping(value="update_tradePwd")
	@ResponseBody
	public Map<String,Object> setUTradePwd(String password0, String password1, String password2){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		Map<String,Object> resultMap=new HashMap<String, Object>();
		try{
			ChangePasswordAction changePasswordAction = new ChangePasswordAction();
			changePasswordAction.setActionType("TRADE");
			changePasswordAction.setCustno(s_custinfo.getCustno());
			changePasswordAction.setPassword0(password0);
			changePasswordAction.setPassword1(password1);
			changePasswordAction.setPassword2(password2);
			String tradePwd=s_custinfo.getTradepwd();
			password0=EncryptUtil.md5(password0);
			if(!password0.equals(tradePwd)){//交易密码与原交易密码不同
				resultMap.put("errCode", "0001");
				return resultMap;
			}
			/** 修改交易密码 **/
			custManager.changePassword(changePasswordAction);
			resultMap.put("errCode", "0000");
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			resultMap.put("errCode", ue.getCode());
			resultMap.put("errMsg", ue.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	
	/**
	 * 找回交易密码
	 * @param password1
	 * @param password2
	 * @param msgcode
	 * @param model
	 * @return
	 */
	@RequestMapping(value="tradePwd_back")
	@ResponseBody
	public Map<String,Object> tradePwdBack(String password1, String password2){
		Map<String,Object> resultMap=new HashMap<String, Object>();
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			ChangePasswordAction changePasswordAction = new ChangePasswordAction();
			changePasswordAction.setActionType("TRADEBACK");
			changePasswordAction.setCustno(s_custinfo.getCustno());
			changePasswordAction.setPassword1(password1);
			changePasswordAction.setPassword2(password2);
			/** 修改交易密码 **/
			custManager.changePassword(changePasswordAction);
			resultMap.put("errCode", "0000");
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			resultMap.put("errCode", ue.getCode());
			resultMap.put("errMsg", ue.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	/**
	 * 银行卡管理
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="bankcard_index")
	public String settingBankcard(CustinfoVo custinfoVo, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			custinfoVo.setCustno(s_custinfo.getCustno());;                      
			custinfoVo.setMobileno(s_custinfo.getMobileno());                    
			custinfoVo.setInvtp(s_custinfo.getInvtp()); 
			custinfoVo.setInvnm(s_custinfo.getInvnm());        
			custinfoVo.setIdtp(s_custinfo.getIdtp());     
			custinfoVo.setIdno(s_custinfo.getIdno());             
			custinfoVo.setCustst(s_custinfo.getCustst());
			custinfoVo.setLevel(s_custinfo.getLevel());
			
			// 获取交易账户列表
			List<String> tradeaccosts = new ArrayList<String>();
			tradeaccosts.add("Y");  
			tradeaccosts.add("N");  
			
			List<String> levels = new ArrayList<String>();
				levels.add("0"); 
				levels.add("1");  
				levels.add("2");  
			List<TradeAccoinfoOfMore> tradeAccoList_Y = 
					tradeAccoManager.getTradeAccoList(s_custinfo.getCustno(), null, levels, tradeaccosts);
			if(null != tradeAccoList_Y && tradeAccoList_Y.size() > 0){
				// 获取用户总资产
				Assets assets = queryManager.queryAssets(tradeAccoList_Y, BasicFundinfo.YFB.getFundCode());//20151011添加基金编码
				List<TradeAccoVo> list_y =  assets.getAccoList();
				
				model.addAttribute("cardList_Y", list_y);
				for(TradeAccoVo tradeAccoVo:list_y){
					if(tradeAccoVo.getClevel().equals("1")){
						model.addAttribute("isUfbCard", true);
						break;
					}
				}
			} else {
				model.addAttribute("cardList_Y", null);
			}
			tradeaccosts = new ArrayList<String>();
			tradeaccosts.add("C"); // 
			tradeaccosts.add("F"); // 
			List<TradeAccoinfoOfMore> tradeAccoList_N = 
					tradeAccoManager.getTradeAccoList(s_custinfo.getCustno(), null, levels, tradeaccosts);
			if(null != tradeAccoList_N && tradeAccoList_N.size() > 0){
				model.addAttribute("cardList_N", tradeAccoList_N);
			} else {
				model.addAttribute("cardList_N", null);
			}
			model.addAttribute("CustinfoVo", custinfoVo);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "银行卡管理");
			model.addAttribute("message_url", BANKCARD_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", "返回");
			return "error/error";
		}
		return "family/setting/bankcard_index";
	}
	
	/**
	 * 设置银行卡为主卡、解绑银行卡、删除银行卡
	 * @param bankacco
	 * @param model
	 * @return
	 */
	@RequestMapping(value="setting_cards")
	@ResponseBody
	public Map<String,Object> setMainCard(String bankacco,String serialid,String tradeacco,String type){
		Map<String,Object> resultMap=new HashMap<String, Object>();
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if("M".equals(type)){
				// 短信验证
				bankCardManager.setBankCardMainFlag(
						s_custinfo.getCustno(), 
						null, 
						"N");
				bankCardManager.setBankCardMainFlag(
						s_custinfo.getCustno(), 
						ServletHolder.getRequest().getParameter("bankacco"), 
						"Y");
			}
			else if("U".equals(type)){
				// 短信验证
				TradeAccoVo tradeAccoVo = queryManager.queryAssets(tradeacco, null);
				BigDecimal total = tradeAccoVo.getTotal();
				BigDecimal available = tradeAccoVo.getAvailable();
				BigDecimal realavailable = tradeAccoVo.getRealavailable();
				BigDecimal frozen = tradeAccoVo.getFrozen();
				
				if (total.compareTo(BigDecimal.ZERO) > 0
						|| available.compareTo(BigDecimal.ZERO) > 0
						|| realavailable.compareTo(BigDecimal.ZERO) > 0
						|| frozen.compareTo(BigDecimal.ZERO) > 0) {
					
					throw new UserException("对不起，您的银行卡有资金交易，暂时不能解绑！");
				}
				bankCardManager.unbindBankCard(
						s_custinfo.getCustno(), 
						ServletHolder.getRequest().getParameter("serialid"), 
						"C");
			}
			else if("D".equals(type)){
				// 短信验证
				TradeAccoVo tradeAccoVo = queryManager.queryAssets(tradeacco, null);
				BigDecimal total = tradeAccoVo.getTotal();
				BigDecimal available = tradeAccoVo.getAvailable();
				BigDecimal realavailable = tradeAccoVo.getRealavailable();
				BigDecimal frozen = tradeAccoVo.getFrozen();
				
				if (total.compareTo(BigDecimal.ZERO) > 0
						|| available.compareTo(BigDecimal.ZERO) > 0
						|| realavailable.compareTo(BigDecimal.ZERO) > 0
						|| frozen.compareTo(BigDecimal.ZERO) > 0) {
					
					throw new UserException("对不起，您的银行卡有资金交易，暂时不能删除！");
				}
				bankCardManager.deleteCard(
						s_custinfo.getCustno(), 
						serialid);
			}
			resultMap.put("errCode", "0000");
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			resultMap.put("errCode", ue.getCode());
			resultMap.put("errMsg", ue.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	
	/**
	 * 修改登录密码    未登录状态step1
	 * @param autotradeVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="findpassword_index")
	public String findpasswordIndex(AutotradeVo autotradeVo, Model model){
		try{
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "修改登录密码");
			model.addAttribute("message_content", ue.getMessage());
			return "error/error";
		}
		return "family/setting/findpassword_index";
	}
	/**
	 * 修改登录密码   未登录状态step2
	 * 20151002
	 */
	@RequestMapping(value="findpassword_confirm")
	public String findpasswordConfirm(CustinfoVo custinfoVo, Model model){
		try{
			
			model.addAttribute("CustInfoVo", custinfoVo);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "修改登录密码");
			model.addAttribute("message_url", "family/setting/findpassword_index.htm");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", "返回");
			return "error/error";
		}
		return "family/setting/findpassword_confirm";
	}
	/**
	 * 修改登录密码   未登录状态step3
	 * 20151002
	 */
	@RequestMapping(value="findpassword_success")
	@ResponseBody
	public Map<String,Object> findPasswordStep3(CustinfoVo custinfoVo,String password1, Model model){
		Map<String,Object> resultMap= new HashMap<String, Object>();
		try{
			Custinfo custinfo=custManager.getCustInfoByMobileno(custinfoVo.getMobileno());
			ChangePasswordAction changePasswordAction = new ChangePasswordAction();
			changePasswordAction.setActionType("LOGIN");
			changePasswordAction.setCustno(custinfo.getCustno());
			changePasswordAction.setPassword1(password1);
			/** 修改登录密码 **/
			custManager.changePassword(changePasswordAction);
			resultMap.put("errCode", "0000");
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			resultMap.put("errCode", ue.getCode());
			resultMap.put("errMsg", ue.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	/**
	 * 修改登录密码结果   未登录状态step3
	 * 20151002
	 */
	@RequestMapping(value="findpassword_result")
	public String findpasswordResult(AutotradeVo autotradeVo, Model model){
		try{
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "修改登录密码");
			model.addAttribute("message_content", ue.getMessage());
			return "error/error";
		}
		return "family/setting/findpassword_success";
	}
	

}
