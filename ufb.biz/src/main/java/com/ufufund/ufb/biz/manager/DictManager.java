package com.ufufund.ufb.biz.manager;

import java.util.HashMap;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.util.UfbContextUtil;
import com.ufufund.ufb.common.cache.Cache;
import com.ufufund.ufb.common.cache.CacheManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.dao.DictMapper;
import com.ufufund.ufb.model.db.Dictionary;

/**
 * 字典表翻译通用缓存
 * 
 * @author gaoxin
 *
 */
@Service
public class DictManager {

	private static void getParameterMap() {
		ApplicationContext context = UfbContextUtil.getContext();
		DictMapper dictMapper = (DictMapper) context.getBean("dictMapper");
		List<Dictionary> dictionaryList = dictMapper.getDictionary();
		HashMap<String, Dictionary> chParameterDOMap = new HashMap<String, Dictionary>();
		HashMap<String, HashMap<String, Dictionary>> parameterMap = new HashMap<String, HashMap<String, Dictionary>>();
		String dictionaryType = "";
		if (dictionaryList != null && dictionaryList.size() > 0) {
			dictionaryType = dictionaryList.get(0).getPmky();
			for (Dictionary dictionary : dictionaryList) {
				if (!dictionaryType.equals(dictionary.getPmky())) {
					parameterMap.put(dictionaryType, chParameterDOMap);
					chParameterDOMap = new HashMap<String, Dictionary>();
					dictionaryType = dictionary.getPmky();
				}
				chParameterDOMap.put(dictionary.getPmco(), dictionary);
			}
			parameterMap.put(dictionaryType, chParameterDOMap);
		}
		Cache s = new Cache();
		s.setValue(parameterMap);
		CacheManager.putCache(Constant.Cache.CACHE$DICTIONARY, s);
	}

	private static HashMap<String, Dictionary> dictionaryByType(String type) {
		HashMap<String, Dictionary> map = new HashMap<String, Dictionary>();
		Cache cache = CacheManager.getCacheInfo(Constant.Cache.CACHE$DICTIONARY);
		if (cache != null) {
			@SuppressWarnings("unchecked")
			HashMap<String, HashMap<String, Dictionary>> parameterMap = (HashMap<String, HashMap<String, Dictionary>>) cache.getValue();
			if (parameterMap != null) {
				map = parameterMap.get(type);
				if (map != null && map.size() > 0) {
					return map;
				}
			}
		}
		return map;
	}

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
	public static Dictionary getDict(String type, String value) {
		Dictionary des = new Dictionary();
		if (StringUtils.isEmpty(type) || StringUtils.isEmpty(type)) {
			return des;
		}
		if (StringUtils.isEmpty(value) || StringUtils.isEmpty(type)) {
			return des;
		}
		HashMap<String, Dictionary> parameterDOMap = getDictionaryByType(type);
		des = parameterDOMap.get(value);
		return des;
	}

	/**
	 * 字典列表
	 * 
	 * @since
	 * @param key
	 * @return HashMap<String,Dictionary> <br>
	 *         <b>作者： gaoxin</b> <br>
	 *         创建时间：2014-7-21 上午9:18:16
	 */
	public static HashMap<String, Dictionary> getDictionaryByType(String type) {
		HashMap<String, Dictionary> map = dictionaryByType(type);
		if (!map.isEmpty() && map.size() > 0) {
			return map;
		}
		getParameterMap();
		return dictionaryByType(type);
	}

}
