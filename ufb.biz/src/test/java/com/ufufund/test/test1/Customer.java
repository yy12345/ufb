package com.ufufund.test.test1;

import java.util.Set;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
 
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Customer {
 
    @XmlAttribute
    private String name;
 
    private String gender;
 
    private String phoneNo;
 
    private Address address;
 
    private Set<Order> orders;
 
    public Customer() {
    }
 
    public Customer(String name, String gender, String phoneNo, Address address) {
        this.name = name;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.address = address;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
    
    
}