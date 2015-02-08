package com.ufufund.test.test3;



import com.ufufund.test.test3.LightTest.Light;

public class EnumTest {
	
	
	
public static void main(String[] args) {
		
		EnumTest s = new EnumTest();
		s.setAllLight(Light.GREEN);
		System.out.println(s.getAllLight());
//		Light[] allLight = Light.values();
//        for (Light aLight : allLight) {
//           System. out .println( " 当前灯 name ： " + aLight.name());
//           System. out .println( " 当前灯 ordinal ： " + aLight.ordinal());
//           System. out .println( " 当前灯： " + aLight);
//       }
	}

	
	
	public  Light allLight;
	
	
	
	

	public Light getAllLight() {
		return allLight;
	}


	public void setAllLight(Light allLight) {
		this.allLight = allLight;
	}



}
	
