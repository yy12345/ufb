package com.ufufund.test.test1;




import java.util.Set;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlAccessorOrder;
 
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "shop", propOrder = { "name", "number", "describer", "address","orders" })
@XmlRootElement(name = "CHMart")
public class Shop {
 
    @XmlAttribute
    private String name;
 
    // @XmlElement
    private String number;
 
    @XmlElement
    private String describer;
 
    @XmlElementWrapper(name = "orders")
    @XmlElement(name = "order")
    private Set<Order> orders;
 
    @XmlElement
    private Address address;
 
    public Shop() {
    }
 
    public Shop(String name, String number, String describer, Address address) {
        this.name = name;
        this.number = number;
        this.describer = describer;
        this.address = address;
    }

    
    
    
	public String getName() {
		return name;
	}

	
	
	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescriber() {
		return describer;
	}

	public void setDescriber(String describer) {
		this.describer = describer;
	}

	public Set<Order> getOrder() {
		return orders;
	}

	public void setOrder(Set<Order> orders) {
		this.orders = orders;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

    
    
}

 