package com.ufufund.common;


import java.lang.reflect.Field;



public class ObjectCommon {
	
	/**
	 * 将1个Bean 所有null 的属性置空
	 * 
	 * @param obj
	 * @param 
	 * @return obj
	 */
	public static Object convertObject(Object obj){
		ObjectCommon.convertObject(obj, obj.getClass());
		return obj;
	}

	private static void convertObject(Object obj, Class<?> s){
		Field[] fields = s.getDeclaredFields();
		// Method[] methods = obj.getClass().getDeclaredMethods();
		for (int i = 0, len = fields.length; i < len; i++) {
			// Returns the name of the field represented by this Field object.
			String varName = fields[i].getName();
			try {	
				if (!fields[i].getGenericType().toString().equals(  
                        "class java.lang.String")) {
				  continue;  
				}
				// Get the value of the accessible flag for this object.
				boolean accessFlag = fields[i].isAccessible();
				// Set the accessible flag for this object to the indicated
				// boolean value.
				fields[i].setAccessible(true);
				// Returns the value of the field represented by this Field, on
				// the specified object.
				// if (accessFlag) {
				Object o = fields[i].get(obj);
				if(o==null){
					fields[i].set(obj, "");
				}
				 System.out.println( varName + ":" + o);  
				//if(o instanceof String){				
				//}
				//}
				//恢复访问控制权限
				fields[i].setAccessible(accessFlag);
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			} catch (IllegalAccessException ex) {
				ex.printStackTrace();
			}
		}
		if(s.getSuperclass()!=null
		   && !s.getName().equals("java.lang.Object")){
			ObjectCommon.convertObject(obj,s.getSuperclass());
		}
	}
	
	
//	public static Object convertObject(Object obj ,String value){
//		return obj;
//	}
	
	
//	public static void getObjectValue(Object object) throws Exception {  
//        //我们项目的所有实体类都继承BaseDomain （所有实体基类：该类只是串行化一下）  
//        //不需要的自己去掉即可  
//        if (object != null && object instanceof BaseDomain) {//if (object!=null )  ----begin  
//            // 拿到该类  
//            Class<?> clz = object.getClass();  
//            // 获取实体类的所有属性，返回Field数组  
//            Field[] fields = clz.getDeclaredFields();  
//  
//            for (Field field : fields) {// --for() begin  
//                System.out.println(field.getGenericType());//打印该类的所有属性类型  
//  
//                // 如果类型是String  
//                if (field.getGenericType().toString().equals(  
//                        "class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名  
//                    // 拿到该属性的gettet方法  
//                    /** 
//                     * 这里需要说明一下：他是根据拼凑的字符来找你写的getter方法的 
//                     * 在Boolean值的时候是isXXX（默认使用ide生成getter的都是isXXX） 
//                     * 如果出现NoSuchMethod异常 就说明它找不到那个gettet方法 需要做个规范 
//                     */  
//                    Method m = (Method) object.getClass().getMethod(  
//                            "get" + getMethodName(field.getName()));  
//  
//                    String val = (String) m.invoke(object);// 调用getter方法获取属性值  
//                    if (val != null) {  
//                        System.out.println("String type:" + val);  
//                    }  
//  
//                }  
//  
//                // 如果类型是Integer  
//                if (field.getGenericType().toString().equals(  
//                        "class java.lang.Integer")) {  
//                    Method m = (Method) object.getClass().getMethod(  
//                            "get" + getMethodName(field.getName()));  
//                    Integer val = (Integer) m.invoke(object);  
//                    if (val != null) {  
//                        System.out.println("Integer type:" + val);  
//                    }  
//  
//                }  
//  
//                // 如果类型是Double  
//                if (field.getGenericType().toString().equals(  
//                        "class java.lang.Double")) {  
//                    Method m = (Method) object.getClass().getMethod(  
//                            "get" + getMethodName(field.getName()));  
//                    Double val = (Double) m.invoke(object);  
//                    if (val != null) {  
//                        System.out.println("Double type:" + val);  
//                    }  
//  
//                }  
//  
//                // 如果类型是Boolean 是封装类  
//                if (field.getGenericType().toString().equals(  
//                        "class java.lang.Boolean")) {  
//                    Method m = (Method) object.getClass().getMethod(  
//                            field.getName());  
//                    Boolean val = (Boolean) m.invoke(object);  
//                    if (val != null) {  
//                        System.out.println("Boolean type:" + val);  
//                    }  
//  
//                }  
//  
//                // 如果类型是boolean 基本数据类型不一样 这里有点说名如果定义名是 isXXX的 那就全都是isXXX的  
//                // 反射找不到getter的具体名  
//                if (field.getGenericType().toString().equals("boolean")) {  
//                    Method m = (Method) object.getClass().getMethod(  
//                            field.getName());  
//                    Boolean val = (Boolean) m.invoke(object);  
//                    if (val != null) {  
//                        System.out.println("boolean type:" + val);  
//                    }  
//  
//                }  
//                // 如果类型是Date  
//                if (field.getGenericType().toString().equals(  
//                        "class java.util.Date")) {  
//                    Method m = (Method) object.getClass().getMethod(  
//                            "get" + getMethodName(field.getName()));  
//                    Date val = (Date) m.invoke(object);  
//                    if (val != null) {  
//                        System.out.println("Date type:" + val);  
//                    }  
//  
//                }  
//                // 如果类型是Short  
//                if (field.getGenericType().toString().equals(  
//                        "class java.lang.Short")) {  
//                    Method m = (Method) object.getClass().getMethod(  
//                            "get" + getMethodName(field.getName()));  
//                    Short val = (Short) m.invoke(object);  
//                    if (val != null) {  
//                        System.out.println("Short type:" + val);  
//                    }  
//  
//                }  
//                // 如果还需要其他的类型请自己做扩展  
//  
//            }//for() --end  
//              
//        }//if (object!=null )  ----end  
//    }  
  

}
