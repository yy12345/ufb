package com.ufufund.common;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.daointerface.ParameterMapper;
import com.ufufund.dataobject.ParameterDO;
import com.ufufund.ufb.common.cache.Cache;
import com.ufufund.ufb.common.cache.CacheManager;



/**
 * 字典表翻译通用缓存
 * @author gaoxin
 *
 */
@Service
public class DictManager {// extends AbstractCacheInter<ParameterDO>{
	
	//private final Logger log = Logger.getLogger(DictManager.class);

	// 暂时使用内存缓存，支持开发
	//private Map<String,List<ParameterDO>> map = new HashMap<String,List<ParameterDO>>();
	@Autowired
	private ParameterMapper parameterMapper;
	
	
	private Map<String,ParameterDO> errorMap = new HashMap<String,ParameterDO>();
	
	public DictManager(){
		//super.setExpire(ParameterConstant.CACHE_TIME);
	}
	
	public void getParameterMap() {	
		List<ParameterDO> parameterDOList  =  parameterMapper.getParameter();
		List<ParameterDO> chParameterDOList = new ArrayList<ParameterDO>();
		HashMap<String ,List<ParameterDO>>  parameterMap = new HashMap<String ,List<ParameterDO>>();
		String parameterType ="";
		if(parameterDOList!=null&&parameterDOList.size()>0){
			parameterType =  parameterDOList.get(0).getPmky();			
			for(ParameterDO parameterDO : parameterDOList){	
				if(!parameterType.equals(parameterDO.getPmky())){
						parameterMap.put(parameterType, chParameterDOList);
						chParameterDOList = new ArrayList<ParameterDO>();
						parameterType = parameterDO.getPmky();
				}
				chParameterDOList.add(parameterDO);
			}
			parameterMap.put(parameterType, chParameterDOList);
		}
		//追加销售机构写入缓存
		//parameterMap.put(ParameterConstant.PAR_SEATNO, this.getSeatNoList());
		Cache s = new Cache();
		s.setValue(parameterMap);
		CacheManager.putCache("PARAMETER", s);
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
	public List<ParameterDO> getParameterByType(String type){
		HashMap<String ,List<ParameterDO>>  parameterMap =  (HashMap<String, List<ParameterDO>>) CacheManager.getCacheInfo("getCacheInfo").getValue();
		List<ParameterDO> list = parameterMap.get(type);
		if(list != null){
			return list;
		}
		this.getParameterMap();
		return ((HashMap<String, List<ParameterDO>>) CacheManager.getCacheInfo("getCacheInfo").getValue()).get(type);
	}

	/**
	 * 字典翻译
	 * @since
	 * @param key
	 * @param value
	 * @return Pmco
	 * 注意 ： 特殊单个翻译所用 ，列表数据翻译用 getDict(list,value)方法
	 * <br><b>作者： gaoxin</b>
	 * <br>创建时间：2014-7-21 上午9:18:16
	 */
	public String getDict(String type,String value){		
		if(StringUtils.isEmpty(value)||StringUtils.isEmpty(type)){
			return "";
		}
		List<ParameterDO> parameterDOList = this.getParameterByType(type);
		for(ParameterDO parameterDO : parameterDOList){		
			if(value.equals(parameterDO.getPmco())){
				return parameterDO.getPmnm();
			}
		}	
		return "";
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
	public String getDict(List<ParameterDO> parameterDOList,String value){		
		if(StringUtils.isEmpty(value)||parameterDOList.isEmpty()){
			return "";
		}
		for(ParameterDO parameterDO : parameterDOList){		
			if(value.equals(parameterDO.getPmco())){
				return parameterDO.getPmnm();
			}
		}	
		return "";
	}
	
	
	public ParameterDO getErrorInfo(String errorCode){
		if(errorMap.get(errorCode)==null){
			 List<ParameterDO> s = this.getParameterByType("ERRORCODE");
			for(ParameterDO parameterDO:s){
				errorMap.put(parameterDO.getPmco(), parameterDO);
			}
			 
		}
		return errorMap.get(errorCode);
	}
	
	
	

	
}
