package com.ufufund.daointerface;



import java.util.List;

import com.ufufund.common.BaseDao;
import com.ufufund.dataobject.ParameterDO;



public interface ParameterMapper extends BaseDao{
    
	
	/**
	 * 所有参数表数据
	 * @return
	 */
	public List<ParameterDO> getParameter();
	
//	/**
//	 * 查询基金购买笔数限制
//	 * @return
//	 */
//	public int getPurLimitCount();
//	
//	
//	/**
//	 * 所有销售机构 写入缓存
//	 * @return
//	 */
//	public List<ParameterDO> getSeatNo();
}
