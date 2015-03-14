package com.ufufund.ufb.biz.manager;

import java.util.HashMap;
import java.util.List;

import com.ufufund.ufb.model.db.Dictionary;

/**
 * 字典表翻译通用缓存
 * 
 * @author gaoxin
 *
 */
public interface DictManager {// extends AbstractCacheInter<ParameterDO>{

	/**
	 * 字典列表
	 * 
	 * @since
	 * @param key
	 * @return HashMap<String,Dictionary> <br>
	 *         <b>作者： gaoxin</b> <br>
	 *         创建时间：2014-7-21 上午9:18:16
	 */
	public HashMap<String,Dictionary> getDictionaryByType(String type);

	/**
	 * 字典翻译
	 * 
	 * @since
	 * @param key
	 * @param value
	 * @return Dictionary 注意 ： 特殊单个翻译所用 ，列表数据翻译用 getDict(list,value)方法 <br>
	 *         <b>作者： gaoxin</b> <br>
	 *         创建时间：2014-7-21 上午9:18:16
	 */
	public Dictionary getDict(String type, String value);

//	/**
//	 * 字典翻译
//	 * 
//	 * @since
//	 * @param key
//	 * @param value
//	 * @return Pmco <br>
//	 *         <b>作者： gaoxin</b> <br>
//	 *         创建时间：2014-7-21 上午9:18:16
//	 */
//	public Dictionary getDict(HashMap<String,Dictionary> parameterMap, String value);

}
