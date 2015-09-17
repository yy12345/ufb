package com.ufufund.ufb.model.db;

import java.util.List;

import lombok.Data;

/**
 * 班级类型表
 * @author ayis
 * 2015年9月11日
 */
@Data
public class ClazzType {

	private String id;
	private String orgid;
	private String name;
	private String createtime;
	private List<Clazz> clazzList;
}
