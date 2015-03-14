package com.ufufund.ufb.biz.manager.impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.DictManager;
import com.ufufund.ufb.common.cache.Cache;
import com.ufufund.ufb.common.cache.CacheManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.dao.DictMapper;
import com.ufufund.ufb.model.db.Dictionary;



/**
 * 字典表翻译通用缓存
 * @author gaoxin
 *
 */
@Service
public class DictManagerImpl implements DictManager {
	
	//private final Logger log = Logger.getLogger(DictManager.class);

	// 暂时使用内存缓存，支持开发
	//private Map<String,List<ParameterDO>> map = new HashMap<String,List<ParameterDO>>();
	@Autowired
	private DictMapper dictMapper;
	
	
	//private Map<String,ParameterDO> errorMap = new HashMap<String,ParameterDO>();
	
	//public DictManagerImpl(){
		//super.setExpire(ParameterConstant.CACHE_TIME);
	//}
	
	private void getParameterMap() {	
		List<Dictionary> dictionaryList  =  dictMapper.getDictionary();
		List<Dictionary> chParameterDOList = new ArrayList<Dictionary>();
		HashMap<String ,List<Dictionary>>  parameterMap = new HashMap<String ,List<Dictionary>>();
		String dictionaryType ="";
		if(dictionaryList!=null&&dictionaryList.size()>0){
			dictionaryType = dictionaryList.get(0).getPmky();			
			for(Dictionary dictionary : dictionaryList){	
				if(!dictionaryType.equals(dictionary.getPmky())){
						parameterMap.put(dictionaryType, chParameterDOList);
						chParameterDOList = new ArrayList<Dictionary>();
						dictionaryType = dictionary.getPmky();
				}
				chParameterDOList.add(dictionary);
			}
			parameterMap.put(dictionaryType, chParameterDOList);
		}
		Cache s = new Cache();
		s.setValue(parameterMap);
		CacheManager.putCache(Constant.CACHE$DICTIONARY, s);
	}
	
//所有销售机构 写入缓存
//	private List<ParameterDO>  getSeatNoList(){		
//		return parameterMapper.getSeatNo();
//	}
//		
	 /**
	 * 字典列表
	 * @since
	 * @param key
	 * @return List<ParameterDO>
	 * <br><b>作者： gaoxin</b>
	 * <br>创建时间：2014-7-21 上午9:18:16
	 */
	public List<Dictionary> getDictionaryByType(String type) {
		List<Dictionary> list = this.dictionaryByType(type);
		if (list.size() > 0) {
			return list;
		}
		this.getParameterMap();
		return this.dictionaryByType(type);
	}

	private List<Dictionary> dictionaryByType(String type) {
		List<Dictionary> list = new ArrayList<Dictionary>();
		Cache cache = CacheManager.getCacheInfo(Constant.CACHE$DICTIONARY);
		if (cache != null) {
			@SuppressWarnings("unchecked")
			HashMap<String, List<Dictionary>> parameterMap = (HashMap<String, List<Dictionary>>) cache.getValue();
			if (parameterMap != null) {
				list = parameterMap.get(type);
				if (list != null && list.size() > 0) {
					return list;
				}
			}
		}
		return list;
	}
	

	/**
	 * 字典翻译
	 * @since
	 * @param key
	 * @param value
	 * @return Dictionary
	 * 注意 ： 特殊单个翻译所用 ，列表数据翻译用 getDict(list,value)方法
	 * <br><b>作者： gaoxin</b>
	 * <br>创建时间：2014-7-21 上午9:18:16
	 */
	public Dictionary getDict(String type,String value){		
		Dictionary des = new Dictionary();
		if(StringUtils.isEmpty(value)||StringUtils.isEmpty(type)){
			return des;
		}
		List<Dictionary> parameterDOList = this.getDictionaryByType(type);
		for(Dictionary dictionary : parameterDOList){		
			if(value.equals(dictionary.getPmco())){				
				return dictionary;
			}
		}	
		return des;
	}
	
	
	/**
	 * 字典翻译
	 * @since
	 * @param key
	 * @param value
	 * @return Pmco
	 * <br><b>作者： gaoxin</b>
	 * <br>创建时间：2014-7-21 上午9:18:16
	 */
	public Dictionary getDict(List<Dictionary> parameterDOList,String value){		
		Dictionary des = new Dictionary();
		if(StringUtils.isEmpty(value)||parameterDOList.isEmpty()){
			return des;
		}
		for(Dictionary dictionary  : parameterDOList){		
			if(value.equals(dictionary.getPmco())){
				return dictionary;
			}
		}	
		return des;
	}

//	public Dictionary getErrorInfo(String errorCode){
//		if(errorMap.get(errorCode)==null){
//			 List<ParameterDO> s = this.getParameterByType("ERRORCODE");
//			for(ParameterDO parameterDO:s){
//				errorMap.put(parameterDO.getPmco(), parameterDO);
//			}
//			 
//		}
//		return errorMap.get(errorCode);
//	}
	
	
	

	
}
