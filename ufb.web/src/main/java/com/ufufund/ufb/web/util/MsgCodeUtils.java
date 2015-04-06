package com.ufufund.ufb.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.web.filter.ServletHolder;

/**
 * 短信动态码工具类
 * @author ayis
 * 2015-03-14
 */
public class MsgCodeUtils {
	private static final Logger LOG = LoggerFactory.getLogger(MsgCodeUtils.class);
	
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
	 * 控制规则：
	 *   1.每两次发送时间间隔控制：<code>SECONDS</code>
	 *	 2.时间段内发送次数控制：在<code>MINUTES</code>内，最多<code>MAX_COUNT</code>次
	 * @param template 短信模版
	 */
	public static void sendMsg(String template){
		
		long now = System.currentTimeMillis();
		
		UserMsgCode userMsgCode =  (UserMsgCode)ServletHolder.getSession().getAttribute("userMsgCode");
		if(userMsgCode != null){
			// session中已存在
			List<Long> timeList = userMsgCode.getTimeList();
			/** 判断与上次发送的时间间隔 **/
			long last = timeList.get(timeList.size() -1).longValue();
			if(now - last <= SECONDS*1000 ){
				throw new BizException(SECONDS+"秒之内只能发送一次，请稍后再试！");
			}
			/** 判断时间段内，发送次数 **/
			for(int i = 0; i < timeList.size(); ){
				if(now - timeList.get(i).longValue() > MINUTES*60*1000){
					timeList.remove(i);
				}else{
					i++;
				}
			}
			if(timeList.size() >= MAX_COUNT){
				throw new BizException(MINUTES+"分钟之内只能发送"+MAX_COUNT+"次，请稍后再试！");
			}
		}else{
			// session中不存在
			userMsgCode = new UserMsgCode();
		}
		
		// 设置或者重新设置短信码
		int n = new Random().nextInt(1000000);
		if(n < 100000){
			n += 100000;
		}
		userMsgCode.setMsgCode(String.valueOf(n));
		userMsgCode.getTimeList().add(now);
		ServletHolder.getSession().setAttribute("userMsgCode", userMsgCode);
		
		LOG.debug("MsgCode="+userMsgCode.getMsgCode()+", timeList="+userMsgCode.getTimeList());
		
		// 调用短信接口，发送短信
		// code after the message interface was provided.
		
	}
	
	/**
	 * 验证短信动态码<br/>
	 * 1.验证是否匹配
	 * 2.验证是否在时效范围内
	 * @param msgCode
	 * @return 校验失败，直接提示业务类异常；否则，成功
	 */
	public static boolean validate(String msgCode){
		
		UserMsgCode userMsgCode =  (UserMsgCode)ServletHolder.getSession().getAttribute("userMsgCode");
		if(userMsgCode == null || StringUtils.isBlank(userMsgCode.getMsgCode())){
			throw new BizException("您未发送短信码或已失效，请重新发送！");
		}else if(!userMsgCode.getMsgCode().equals(msgCode)){
			throw new BizException("您输入的短信码不匹配！");
		}else {
			long now = System.currentTimeMillis();
			if(now - userMsgCode.getTimeList().get(userMsgCode.getTimeList().size() - 1) > ACTIVE_TIME*60*1000){
				throw new BizException("您的短信码已失效，请重新发送！");
			}
		}
		ServletHolder.getSession().removeAttribute("userMsgCode");
		return true;
	}
	
	private static class UserMsgCode{
		// 短信码
		private String msgCode;
		// 发送时间列表
		private List<Long> timeList = new ArrayList<Long>();
		
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
}
