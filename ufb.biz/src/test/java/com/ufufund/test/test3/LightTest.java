package com.ufufund.test.test3;



import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;



public class LightTest {

	// 1. 定义枚举类型
	@XmlEnum
	public enum Light {
		// 利用构造函数传参
		@XmlEnumValue("01")
		RED("01"), 
		@XmlEnumValue("03")
		GREEN("03"),
		@XmlEnumValue("02")
		YELLOW("02");

		// 定义私有变量
		private String nCode;

		// 构造函数，枚举类型只能为私有
		private Light(String _nCode) {
			this.nCode = _nCode;
		}

		@Override
		public String toString() {
			return this.nCode;
		}
	}
	
	
}
