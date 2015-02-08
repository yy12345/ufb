package com.ufufund.test.test1;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
 
@XmlType(propOrder={"state","province","city","street","zip"})
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class Address {
 
    @XmlAttribute
    private String state;
     
    @XmlElement
    private String province;
     
    @XmlElement
    private String city;
 
    @XmlElement
    private String street;
     
    @XmlElement
    private String zip;
 
    public Address() {
        super();
    }
 
    public Address(String state, String province, String city, String street,
            String zip) {
        super();
        this.state = state;
        this.province = province;
        this.city = city;
        this.street = street;
        this.zip = zip;
    }

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
    
}