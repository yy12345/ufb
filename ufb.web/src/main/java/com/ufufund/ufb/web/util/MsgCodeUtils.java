package com.ufufund.ufb.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ufufund.ufb.biz.manager.MobileMsgManager;
import com.ufufund.ufb.common.constant.Constant.MsgTemplate;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.web.filter.ServletHolder;

/**
 * 短信动态码工具类
 * @author ayis
 * 2015-03-14
 */
public class MsgCodeUtils {
	private static final Logger LOG = LoggerFactory.getLogger(MsgCodeUtils.class);
	
	private static MobileMsgManager mobileMsgManager;

	// 时间段内最大发送次数
	private static final int MAX_COUNT = 5;
	// 次数控制时间段，单位：分钟
	private static final int MINUTES = 10;
	// 每两次发送时间间隔，单位：秒
	private static final int SECONDS = 60;
	// 用户短信动态码有效时间，单位：分钟
	private static final int ACTIVE_TIME = 5;

	/**
	 * 发送短信动态码<br/>
	 * 控制规则： 1.每两次发送时间间隔控制：<code>SECONDS</code> 2.时间段内发送次数控制：在
	 * <code>MINUTES</code>内，最多<code>MAX_COUNT</code>次
	 * @param template 短信模版
	 */
	public static void sendMsg(String template, String mobileNo) {

		long now = System.currentTimeMillis();
		MsgCode msgCode = (MsgCode) ServletHolder.getSession()
				.getAttribute("MSGCODE");
		if (msgCode != null) {
			// session中已存在
			List<Long> timeList = msgCode.getTimeList();
			/** 判断与上次发送的时间间隔 **/
			long last = timeList.get(timeList.size() - 1).longValue();
			
			if (now - last <= SECONDS * 1000) {
				long t = SECONDS - ((now-last)/1000);
				throw new UserException(SECONDS + "秒之内只能发送一次，请稍后[" + t + "]秒再试！");
			}
			/** 判断时间段内，发送次数 **/
			for (int i = 0; i < timeList.size();) {
				if (now - timeList.get(i).longValue() > MINUTES * 60 * 1000) {
					timeList.remove(i);
				} else {
					i++;
				}
			}
			if (timeList.size() >= MAX_COUNT) {
				throw new UserException(MINUTES + "分钟之内只能发送" + MAX_COUNT + "次，请稍后再试！");
			}
		} else {
			// session中不存在
			msgCode = new MsgCode();
		}

		// 设置或者重新设置短信码
		int n = new Random().nextInt(1000000);
		if (n < 100000) {
			n += 100000;
		}
		
		msgCode.setMobileNo(mobileNo);
		msgCode.setMsgCode(String.valueOf(n));
		msgCode.getTimeList().add(now);
		ServletHolder.getSession().setAttribute("MSGCODE", msgCode);

		LOG.debug("MsgCode=" + msgCode.getMsgCode() + ", timeList="
				+ msgCode.getTimeList());

		// 调用短信接口，发送短信
		MobileMsgManager mobileMsgManager = getMobileMsgManager();
		String content = String.format(MsgTemplate.templateMap.get(template), msgCode.getMsgCode());
		mobileMsgManager.sendMobile(mobileNo, content);
	}
	
	
	/**
	 * 检验短信验证码-后台使用
	 * 备注：验证成功后，清除已使用的短信码
	 * @param msgCode
	 * @param mobileNo
	 * @exception 校验失败，直接提示业务类异常
	 */
	public static void validate(String msgCode, String mobileNo) {
		check(msgCode, mobileNo);
		// 清除已使用的短信码
		ServletHolder.getSession().removeAttribute("MSGCODE");
	}
	
	/**
	 * 检验短信验证码-前端校验使用
	 * 备注：仅检验是否正确，不清除session中的短信码
	 * @param msgCode 
	 * @param mobileNo 
	 * @exception 校验失败，直接提示业务类异常
	 */
	public static void check(String msgCode, String mobileNo){
		
		MsgCode value = (MsgCode) ServletHolder.getSession().getAttribute("MSGCODE");
		if (StringUtils.isBlank(msgCode)) {
			throw new UserException("手机验证码为空！");
		} else if (null == value || StringUtils.isBlank(value.getMsgCode())) {
			throw new UserException("手机验证码已失效，请重新发送！");
		} else if (!value.getMsgCode().equals(msgCode) || !value.getMobileNo().equals(mobileNo) ) {
			throw new UserException("手机验证码不正确！！");
		} else {
			long now = System.currentTimeMillis();
			if (now - value.getTimeList().get(value.getTimeList().size() - 1) > ACTIVE_TIME * 60 * 1000) {
				throw new UserException("手机验证码已失效，请重新发送！");
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getMsgCode(){
		MsgCode msgCode = (MsgCode) ServletHolder.getSession().getAttribute("MSGCODE");
		return msgCode.getMsgCode();
	}
	

	public static class MsgCode {
		private String mobileNo;
		// 短信码
		private String msgCode;
		// 发送时间列表
		private List<Long> timeList = new ArrayList<Long>();
		
		public String getMobileNo() {
			return mobileNo;
		}

		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}

		public String getMsgCode() {
			return msgCode;
		}

		public void setMsgCode(String msgCode) {
			this.msgCode = msgCode;
		}

		public List<Long> getTimeList() {
			return timeList;
		}
	}
	
	private static MobileMsgManager getMobileMsgManager(){
		if(mobileMsgManager == null){
			WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(ServletHolder.getRequest()
					.getSession().getServletContext());
			mobileMsgManager = applicationContext.getBean(MobileMsgManager.class);
		}
		return mobileMsgManager;
	}
}
