package com.ufufund.ufb.biz.manager.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.MobileMsgManager;
import com.ufufund.ufb.common.cache.RedisClient;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.common.utils.SequenceUtil;
import com.ufufund.ufb.dao.MsgRecordMapper;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.MsgRecord;
import com.ufufund.ufb.remote.bcloud.MessageSender;

import redis.clients.jedis.Jedis;

@Service
public class MobileMsgManagerImpl implements MobileMsgManager{
	
	private static final int DAY_SECONDS = 3600*24;
	
	@Autowired
	private CustManager custManager;
	@Autowired
	private MessageSender messageSender;
	@Autowired
	private MsgRecordMapper msgRecordMapper;
	
	@Value("${ufb.msg.mobile_per_day}")
	private int mobile_per_day;
	@Value("${ufb.msg.system_per_day}")
	private int system_per_day;

	@Override
	public String sendMessage(String custno, String content){
		
		Custinfo custinfo = custManager.getCustinfo(custno);
		String status = send(custinfo.getMobileno(), content);
		
		MsgRecord msgRecord = new MsgRecord();
		msgRecord.setSerailno(SequenceUtil.getSerial());
		msgRecord.setCustno(custno);
		msgRecord.setMobileno(custinfo.getMobileno());
		msgRecord.setContent(content);
		msgRecord.setStatus(status);
		msgRecordMapper.add(msgRecord);
		
		return msgRecord.getSerailno();
	} 
	
	@Override
	public String sendMobile(String mobile, String content){
		
		String status = send(mobile, content);
		
		MsgRecord msgRecord = new MsgRecord();
		msgRecord.setSerailno(SequenceUtil.getSerial());
		msgRecord.setMobileno(mobile);
		msgRecord.setContent(content);
		msgRecord.setStatus(status);
		msgRecordMapper.add(msgRecord);
		
		return msgRecord.getSerailno();
	} 
	
	/**
	 * 短信发送manager层统一入口
	 * 1.手机号发送数量控制；
	 * 2.系统总发送量控制；
	 * @param mobile
	 * @param content
	 * @return
	 */
	private String send(String mobile, String content){
		
		Jedis jedis = RedisClient.getJedis();
		String date = DateUtil.format(new Date(), DateUtil.DATE_PATTERN_1);
		
		// 每手机号每天发送量
		String str_mobile = jedis.get("ufb.msg."+date+"."+mobile);
		int mobile_per_day = Integer.parseInt(str_mobile==null?"0":str_mobile);
		// 系统每天总发送量
		String str_system = jedis.get("ufb.msg."+date+".system");
		int system_per_day = Integer.parseInt(str_system==null?"0":str_system);
		if(mobile_per_day >= this.mobile_per_day){
			throw new UserException("已超过当天用户的短信最大发送数量："+this.mobile_per_day+"条！");
		}
		if(system_per_day >= this.system_per_day){
			throw new UserException("已超过当天系统短信发送的最大数量！");
		}
		
		String status = messageSender.sendMessage(mobile, content);
		if("0".equals(status)){
			// 每手机号每天发送量
			jedis.incr("ufb.msg."+date+"."+mobile);
			if(str_mobile == null){
				jedis.expire("ufb.msg."+date+"."+mobile, DAY_SECONDS);
			}
			// 系统每天总发送量
			jedis.incr("ufb.msg."+date+".system");
			if(str_system == null){
				jedis.expire("ufb.msg."+date+".system", DAY_SECONDS);
			}
		}
		jedis.close();
		return status;
	}
}
