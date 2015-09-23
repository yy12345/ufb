package com.ufufund.ufb.model.db;

import lombok.Data;

/**
 * 班级表
 * @author ayis
 * 2015年9月11日
 */
@Data
public class Clazz {

	private String cid;
	private String orgid;
	private String typeid;
	private String name;
	private String cno;
	private String createtime;
	// 非持久化属性
	private int size;   // 班级学生数量
}
