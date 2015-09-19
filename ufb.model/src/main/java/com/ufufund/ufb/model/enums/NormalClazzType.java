package com.ufufund.ufb.model.enums;

import java.util.ArrayList;
import java.util.List;

import com.ufufund.ufb.model.db.ClazzType;

public enum NormalClazzType {
	TB("托班","1"),
	XB("小班","2"),
	ZB("中班","3"),
	DB("大班","4");
	
	private String name;
	private String id;
	
	private NormalClazzType(String name, String id){
		this.name = name;
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	public String getId(){
		return this.id;
	}
	
	public static List<ClazzType> getList(String orgid){
		List<ClazzType> list = new ArrayList<ClazzType>();
		
		ClazzType ct = new ClazzType();
		ct.setOrgid(orgid);
		ct.setId(NormalClazzType.TB.getId());
		ct.setName(NormalClazzType.TB.getName());
		list.add(ct);
		
		ct = new ClazzType();
		ct.setOrgid(orgid);
		ct.setId(NormalClazzType.XB.getId());
		ct.setName(NormalClazzType.XB.getName());
		list.add(ct);
		
		ct = new ClazzType();
		ct.setOrgid(orgid);
		ct.setId(NormalClazzType.ZB.getId());
		ct.setName(NormalClazzType.ZB.getName());
		list.add(ct);
		
		ct = new ClazzType();
		ct.setOrgid(orgid);
		ct.setId(NormalClazzType.DB.getId());
		ct.setName(NormalClazzType.DB.getName());
		list.add(ct);
		
		return list;
	}
}
