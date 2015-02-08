package com.ufufund.test.test2;







import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "item")
public class ListBean {

	private String naa;
	private String nbb;
	
	public String getNaa() {
		return naa;
	}
	public void setNaa(String naa) {
		this.naa = naa;
	}
	public String getNbb() {
		return nbb;
	}
	public void setNbb(String nbb) {
		this.nbb = nbb;
	}
	
	
}
