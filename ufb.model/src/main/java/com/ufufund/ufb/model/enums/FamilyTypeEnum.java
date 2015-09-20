package com.ufufund.ufb.model.enums;

public enum FamilyTypeEnum {
	FT_1("1","业主"),
	FT_2("2","非业主"),
	FT_3("3","关系单位"),
	FT_4("4","本园职工"),
	FT_5("5","助养孤儿"),
	FT_6("6","特困家庭"),
	FT_7("7", "其它");
	
	private String key;
	private String value;
	
	private FamilyTypeEnum(String key, String value){
		this.key = key;
		this.value = value;
	}
	
	public String getKey(){
		return key;
	}
	public String getValue(){
		return value;
	}
	
	public static String getKeyByValue(String value){
		if(value == null){
			return null;
		}else if(FamilyTypeEnum.FT_1.value.equals(value)){
			return FamilyTypeEnum.FT_1.key;
		}else if(FamilyTypeEnum.FT_2.value.equals(value)){
			return FamilyTypeEnum.FT_2.key;
		}else if(FamilyTypeEnum.FT_3.value.equals(value)){
			return FamilyTypeEnum.FT_3.key;
		}else if(FamilyTypeEnum.FT_4.value.equals(value)){
			return FamilyTypeEnum.FT_4.key;
		}else if(FamilyTypeEnum.FT_5.value.equals(value)){
			return FamilyTypeEnum.FT_5.key;
		}else if(FamilyTypeEnum.FT_6.value.equals(value)){
			return FamilyTypeEnum.FT_6.key;
		}else if(FamilyTypeEnum.FT_7.value.equals(value)){
			return FamilyTypeEnum.FT_7.key;
		}else{
			return null;
		}
	}
}
