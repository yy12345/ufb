package com.ufufund.ufb.biz.manager.impl.validator;

import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.common.utils.ThreadLocalUtil;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.enums.ErrorInfo;

@Service
public class BankCardManagerValidator {

	private final static String CUSTNO = "用户id";
	private final static String LOGINCODE = "账号";
	private final static String LOGINPASSWORD = "密码";
	private final static String LOGINPASSWORD2 = "确认密码";
	private final static String ORGANIZATION = "机构名称";
	private final static String BUSINESS = "营业执照";
	private final static String BANKNO = "银行编码";
	private final static String BANKACCO = "银行卡号";
	private final static String BANKIDTP = "银行证件类型";
	private final static String BANKIDNO = "银行证件号码";
	private final static String BANKACNM = "银行开户户名";
	private final static String BANKMOBILE = "银行开户手机号";	
	private final static String INVNM = "用户姓名";
	private final static String IDNO = "证件号码";
	private final static String TRADEPWD = "交易密码";
	private final static String MOBILE = "手机号";
	private final static String MOBILEAUTOCODE = "验证码";
	private final static String IDCARDNO = "身份证";
	private final static String MERCHANT = "开户机构";

	/**
	 * 基本信息验证（用户名、身份证、交易密码、开户机构）
	 * 绑卡2鉴权的validator
	 * 绑卡3验证的validator
	 * 绑卡4开户的validator
	 * @param action
	 * @throws BizException
	 */
	public void validator(OpenAccountAction action, String actionName) throws BizException {
		
		String processId = action.getProcessId();
		
		//基本信息验证（用户名、身份证、交易密码、开户机构）
		if("User_Base".equals(actionName)){
			// CustNo
			if (RegexUtil.isNull(action.getCustno())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, CUSTNO);
			}
			// 用户名
			if (RegexUtil.isNull(action.getInvnm())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, INVNM);
			}
			// 证件号
			if (RegexUtil.isNull(action.getIdno())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, IDNO);
			}
			if (!RegexUtil.isIdCardNo(action.getIdno())) {
				throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, IDCARDNO);
			}
			// 交易密码
			if (RegexUtil.isNull(action.getTradepwd())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, TRADEPWD);
			}
			if (!RegexUtil.isPwd(action.getTradepwd())) {
				throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, TRADEPWD);
			}
			if (RegexUtil.isNull(action.getTradepwd2())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, LOGINPASSWORD2);
			}
			if (!action.getTradepwd().equals(action.getTradepwd2())) {
				throw new BizException(processId, ErrorInfo.NOT_EQUALS_PASSWORD, TRADEPWD);
			}
			// 
			if (action.getMerchant()==null||RegexUtil.isNull(action.getMerchant().Value())) {
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, MERCHANT);
			}
			
			if("1".equals(action.getLevel())){
				//幼教机构
				if(RegexUtil.isNull(action.getOrganization())){
					throw new BizException(ThreadLocalUtil.getProccessId(), ErrorInfo.NECESSARY_EMPTY, "开户机构");
				}
				//营业执照
				if(RegexUtil.isNull(action.getBusiness())){
					throw new BizException(ThreadLocalUtil.getProccessId(), ErrorInfo.NECESSARY_EMPTY, "营业执照");
				}
			}
		}
		
		//银行基本信息验证
		if("Bank_Base".equals(actionName)){
			if (RegexUtil.isNull(action.getBankno())) {
				//银行编码
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BANKNO);
			}
			if (RegexUtil.isNull(action.getBankacnm())) {
				//银行开户户名
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BANKACNM);
			}
			if (RegexUtil.isNull(action.getBankidtp())) {
				//银行证件类型
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BANKIDTP);
			}
			if (RegexUtil.isNull(action.getBankidno())) {
				//银行证件号码
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BANKIDNO);
			}
			if (!RegexUtil.isIdCardNo(action.getBankidno())) {
				//银行证件号码 isIdCardNo
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BANKIDNO);
			}
			if (RegexUtil.isNull(action.getBankacco())) {
				//银行卡号
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BANKACCO);
			}
			if (RegexUtil.isNull(action.getBankmobile())) {
				//银行开户手机号
				throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BANKMOBILE);
			}
			if(!RegexUtil.isMobile(action.getBankmobile())){
				//银行开户手机号 isMobile
				throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, MOBILE);
			}
			
			// 绑卡验证需要验证
			if("3".equals(action.getReqSeq())){
				if(RegexUtil.isNull(action.getMobileAutoCode())){
					//银行开户手机验证吗 isNull
					throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, MOBILEAUTOCODE);
				}
			}
		}
		
		
	}
}
