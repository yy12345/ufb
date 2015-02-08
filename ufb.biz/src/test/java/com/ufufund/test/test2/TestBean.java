package com.ufufund.test.test2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlElement.DEFAULT;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.ufufund.test.test1.DateAdapter;
import com.ufufund.test.test3.LightTest;
import com.ufufund.test.test3.LightTest.Light;


@XmlRootElement(name = "TestBean")
public class TestBean extends TestParent {
	
	
	private String aname;
	
	private String description;
	
	
	private  Light allLight = Light.YELLOW;
	
	private List<ListBean> listBean = new ArrayList<ListBean>();
	 

	
	@XmlElement(name = "Aname")
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	public List<ListBean> getListBean() {
		return listBean;
	}
	public void setListBean(List<ListBean> listBean) {
		this.listBean = listBean;
	}
	
	//@XmlElement(type = Light.class)
	//@XmlEnum
	//@XmlEnumValue(value = Light.YELLOW)
	public Light getAllLight() {
		return allLight;
	}
	public void setAllLight(Light allLight) {
		this.allLight = allLight;
	}

	
	
	
	
	
}
