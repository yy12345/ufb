package com.ufufund.ufb.biz.manager;

import java.util.List;

import com.ufufund.ufb.model.action.cust.AddAutotradeAction;
import com.ufufund.ufb.model.action.cust.ChangeAutoStateAction;
import com.ufufund.ufb.model.action.cust.ModifyAutotradeAction;
import com.ufufund.ufb.model.db.Autotrade;

public interface AutotradeManager {

	/**
	 * 新建自动交易
	 * 
	 * @param AddAutotradeAction
	 *            action
	 * @return
	 */
	public void addAutotrade(AddAutotradeAction action);

	/**
	 * 修改自动交易
	 * 
	 * @param ModifyAutotradeAction
	 *            action
	 * @return
	 */
	public void modifyAutotrade(ModifyAutotradeAction action);

	/**
	 * 暂停 ，终止，恢复 智能交易
	 * 
	 * @param state
	 *            P-暂停 ,C 终止 ,N 恢复
	 * @return
	 */
	public void changestatus(ChangeAutoStateAction action);

	/**
	 * 获取下一扣款日
	 * 
	 * @param String
	 *            cycle MM=每月；WW=每周;DD 每隔多少天；
	 * @param String
	 *            dat 扣款日
	 * @return
	 */
	public String getNextdate(String cycle, String dat);

	/**
	 * 获取客户的智能交易
	 * 
	 * @param String
	 *            custno
	 * @return
	 */
	public List<Autotrade> getAutotradeList(String custno);
	
	/**
	 * 获取客户的智能交易
	 * 
	 * @param String
	 *            custno
	 * @return
	 */
	public List<Autotrade> getAutotradeCList(String custno);

	/**
	 * 获取客户的智能交易
	 * 
	 * @param String
	 *            autoid
	 * @return
	 */
	public Autotrade getAutotrade(String autoid);
	
	/**
	 * 获取取现信息
	 * @param String
	 * @return 
	 */
	public List<Autotrade> getCashtradeList(String custno,String autoid);
	
	/**
	 * 删除自动充值业务
	 * @param String
	 * @return 
	 */
	public void deleteAutotrade(String custno,String frombankserialid,String autoid);
	
	/**
	 * 暂停自动缴费交易
	 * 
	 * @param state
	 *            P-暂停 
	 * @return
	 */
	public void changeAutoPaystatus(ChangeAutoStateAction action);

}
