package com.ufufund.ufb.common.dao;


/**
 * @name	:	BaseMapper.java
 * @todo	:	TODO
 * @author	:	user
 * @date  	:	2011-2-28
 * @version	:	1.0
 * @copyright:	opensource
 */
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

/**
 * @author user
 * 
 */
@SuppressWarnings("rawtypes")
public interface BaseDao {

	// 插入一个对象
	public int insert(Object object);

	// 更新一个对象
	public int update(Object object);

	// 删除一个对象
	public int delete(Object object);

	// 删除对象
	public int deleteAll(String ids);

	// 选择对象
	public Object getById(String id);

	// 选择对象
	public Object getByKey(Object key);

	// 选择对象
	public Object getByMap(Map map);

	// 统计总数
	public int getTotal(Object object);

	// 普通查找
	public List select(Object object);

	// 普通查找
	public List selectById(Object object);

	// 分页查找
	public List select(Object object, RowBounds rowBounds);

	public Object getLookupByKey(Object key);

	// 普通查找
	public List lookup(String lookupStr);

	// 分页查找
	public List lookup(String lookupStr, RowBounds rowBounds);

	// 统计总数
	public int getTotalWithMap(Map conditionMap);

	public List selectWithMap(Map conditionMap);

	public List selectWithMap(Map map, RowBounds rowBounds);

}
