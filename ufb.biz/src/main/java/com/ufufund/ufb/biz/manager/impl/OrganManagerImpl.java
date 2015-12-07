package com.ufufund.ufb.biz.manager.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.OrganManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.common.utils.SequenceUtil;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.dao.BankCardInfoMapper;
import com.ufufund.ufb.dao.OrgCodesMapper;
import com.ufufund.ufb.dao.OrginfoMapper;
import com.ufufund.ufb.dao.PicinfoMapper;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.OrgCodes;
import com.ufufund.ufb.model.db.Orginfo;
import com.ufufund.ufb.model.db.Picinfo;

@Service
public class OrganManagerImpl implements OrganManager{

	@Autowired
	private OrginfoMapper orginfoMapper;
	@Autowired
	private PicinfoMapper picinfoMapper;
	@Autowired
	private BankCardInfoMapper  bankCardInfoMapper;
	@Autowired
	private OrgCodesMapper orgCodesMapper;
	@Autowired
	private WorkDayManager  workDayManager;
	@Autowired
	private BankCardManager bankCardManager;
	
	@Override
	public Orginfo addOrginfo(Orginfo orginfo) {
		 orginfoMapper.addOrginfo(orginfo);
		 return orginfo;
	}
	
	@Override
	public int addPicinfo(Picinfo picinfo) {
		return picinfoMapper.addPicinfo(picinfo);
	}

	@Override
	public Orginfo getOrginfo(Orginfo orginfo) {
		return orginfoMapper.getOrginfo(orginfo);
	}

	@Override
	public boolean isMobileRegister(Orginfo orginfo) {
		boolean result=false;
		if(StringUtils.isBlank(orginfo.getOperator_mobile())){
			throw new UserException("手机号为空！");
		}
		if(!RegexUtil.isMobile(orginfo.getOperator_mobile())){
			throw new UserException("手机号格式不正确！");
		}
		Orginfo corp=orginfoMapper.isRegister(orginfo);
		if(corp!=null&&corp.getOrgid()!=null&&!"".equals(corp.getOrgid())){
			result=true;
		}
		return result;
	}

	@Override
	public Orginfo login(Orginfo orginfo) {
		// 用户信息校验
		if(StringUtils.isBlank(orginfo.getOperator_mobile())||StringUtils.isBlank(orginfo.getPasswd())){
			throw new UserException("参数为空!");
		}
		if(!RegexUtil.isMobile(orginfo.getOperator_mobile())){
			throw new UserException("登录用户名不正确!");
		}
		
		// 获得数据库用户信息
		orginfo.setPasswd(EncryptUtil.md5(orginfo.getPasswd())); // 根据手机号和密码匹配用户信息,如不同时校验，修改对应的mapper文件(去掉密码的条件)
		Orginfo org=orginfoMapper.getOrginfoLogin(orginfo);
		if(org==null||org.getOrgid()==null||"".equals(org.getOrgid())){
			throw new UserException("登录账号无效!");
		}
		if("5".equals(org.getState())){
			throw new UserException("登录账号已经被冻结!");
		}
		
		// 登录密码错误次数超过5次，账号被冻结
		/*if(!EncryptUtil.md5(orginfo.getPasswd()).equals(org.getPasswd())){
			org.setPasswd_err(org.getPasswd_err()+1);
			if(org.getPasswd_err()==5){
				org.setState("5");
			}
			orginfoMapper.updateForLogin(org);
			throw new UserException("登录账号无效!");
		}*/
		
		orginfoMapper.updateForLogin(org);
		return org;
	}

	@Override
	@Transactional
	public void bindOrgan(Orginfo orginfo, Picinfo picinfo, Bankcardinfo bankcardinfo) {
		picinfo.setCustno(orginfo.getOrgid());
		
		bankcardinfo.setCustno(orginfo.getOrgid());
		bankcardinfo.setState("Y");
		bankcardinfo.setCertno(orginfo.getOperator_certno());
		bankcardinfo.setCerttype("0");
		
		orginfoMapper.updateOrginfo(orginfo);
		this.addPicinfo(picinfo);
		bankcardinfo.setSerialid(SequenceUtil.getSerial());
		bankCardInfoMapper.insterBankcardinfo(bankcardinfo);
		
		// 更新用户状态
		this.updateState("3", orginfo.getOrgid());
	}

	@Override
	public void updateState(String state,String orgid){
		Orginfo org = new Orginfo();
		org.setOrgid(orgid);
		org.setState(state);
		orginfoMapper.updateState(org);
	}
	
	@Override
	public boolean isCertnoRegister(String certno) {
		Orginfo orginfo = new Orginfo();
		orginfo.setOperator_certno(certno);
		orginfo=orginfoMapper.isRegister(orginfo);
		if(orginfo!=null){
			return true;
		}
		return false;
	}

	@Override
	public void sendAmt(String orgid) {
		 // 生成随机金额数 调接口  later...
		 Random r = new Random();
		 int n = r.nextInt(100);
		 if(n == 0)n++;
		 double amt = ((double)n)/100;
		 
		 OrgCodes orgCodes = new OrgCodes();
		 orgCodes.setOrgid(orgid);
		 orgCodes.setAmt_code(String.valueOf(amt));
		 String now = workDayManager.getCurrentWorkDay();
		 orgCodes.setAmt_invalid(workDayManager.getNextWorkDay(now, 10));
		 orgCodesMapper.updateAmtCode(orgCodes);
	}

	@Override
	public boolean getOrgCodes(OrgCodes orgCodes) {
		List<OrgCodes> orgcodeList = orgCodesMapper.getOrgCodeList(orgCodes);
		if(orgcodeList.size()>0&&orgcodeList!=null){
			return true;
		}
		return false;
	}

	@Override
	public boolean getAmtInvalid(OrgCodes orgcode){
		List<OrgCodes> orgcodeList = orgCodesMapper.getOrgCodeList(orgcode);
		if(orgcodeList.size()>0&&orgcodeList!=null){
			orgcode = (OrgCodes)orgcodeList.get(0);
			
			// 判断验证金额是否发送成功
			if(StringUtils.isBlank(orgcode.getAmt_code())){
				return false;
			}
						
			// 判断金额验证的失效的时间
			String amtvalid = orgcode.getAmt_invalid();
			String nowdate = DateUtil.format(new Date(), DateUtil.DATE_PATTERN_1);
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			try {
				long c = sf.parse(amtvalid).getTime()-sf.parse(nowdate).getTime();
				if(c < 0){
					return false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		return true;
	}

	@Override
	@Transactional
	public void remove(String orgid) {
		Orginfo orginfo = new Orginfo();
		Bankcardinfo bankcardinfo = new Bankcardinfo();
		bankcardinfo=bankCardManager.getBankcardinfo(orgid);
		if(bankcardinfo!=null){
			bankCardInfoMapper.deleteBankCard(bankcardinfo.getSerialid());
		}
		if(picinfoMapper.getPicinfo(orgid)!=null){
			picinfoMapper.deletePicinfo(orgid);
		}
		orginfo.setOrgid(orgid);
		orginfoMapper.updateOrginfo(orginfo);
	}

	@Override
	public void createCodes(int n) {
		List<OrgCodes> newlist = new ArrayList<OrgCodes>();
		List<OrgCodes> codeList = orgCodesMapper.getOrgCodeList(null);
		Set<String> sets = new HashSet<String>();
		for(OrgCodes code : codeList){
			sets.add(code.getId());
		}
		
		for(int i=0;i<n;i++){
			OrgCodes orgCodes = new OrgCodes();
			Random r = new Random();
			int c = r.nextInt(1000);
			Iterator<String> it = sets.iterator();
			while(it.hasNext()){
				if(c == Integer.parseInt(it.next())){
					c = Integer.parseInt(it.next())+1;
				}
				if(c<100){
					c = Integer.parseInt(it.next())+1;
				}
			}
			
			// 后五位随机数
			int  m = r.nextInt(100000);
			String m5 = String.valueOf(m);
			if( m!= 0){
				if(m<10000&&m>1000){
					m5 = m5+"1";
				}
				else if(m<1000&&m>100){
					m5 = m5+"01";
				}
				else if(m<100&&m>10){
					m5 = m5+"001";
				}
				else if(m<10&&m>1){
					m5 = m5+"0001";
				}
				else{
					m5 = String.valueOf(m);
				}
			}else{
			   m5 = "00001";
			}
			sets.add(String.valueOf(c));
			orgCodes.setId(String.valueOf(c));
			orgCodes.setCode(String.valueOf(c)+m5);
			newlist.add(orgCodes);
		}
		orgCodesMapper.addOrgCodes(newlist);
	}
}
