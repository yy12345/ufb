package com.ufufund.ufb.common.utils;



import java.lang.reflect.Field;





/**
 * 通用基金校验类
 * @author gaoxin
 *
 */

public class SysoutCommon {
	
	
	public static String JOB_STATUS = "N";
	
//	private static final Logger logger = Logger.getLogger(ValidatorMgr.class);
//	
//	
//	/*
//	 * 返回1个数组写入校验对象的元素 
//	 * ValidatorMgr 会自动判断这些元素是否为空
//	 * 如 ：String = new String{"A","B"}
//	 * 系统会自动判断 T对象的getA();getB()是否为空，
//	 * 如果不需要该判断则返回null
//	 */
//	public static ResultVO ValidateIsempty(Object vo,String[] element) throws Exception {
//		ResultVO reVO = new ResultVO();
//		if(vo==null){
//			reVO.setResultVO(ParameterConstant.ERROR_OBJECT_EMPTY,"校验对象为空");
//		}		
//		//没有需要判断的必备字段	
//		if(element==null){
//			return reVO;
//		}
//		//获得需要判断的字段
//		List<String> checkList = Arrays.asList(element);
//		if(checkList.size() == 0){
//			return reVO;
//		}
//		Field [] fields = vo.getClass().getDeclaredFields();					
//		for(Field f:fields){
//			f.setAccessible(true);
//		}
//		for(Field f:fields){
//			String field = f.toString().substring(f.toString().lastIndexOf(".")+1);			//取出属性名称
//			System.out.println("开始校验字段 ： " + field);
//			if(checkList.contains(field)){
//				try {
//					//判断字段为空 返回错误信息
//					if("".equals(f.get(vo))||f.get(vo)==null){						
//						reVO.setResultVO(ParameterConstant.ERROR_FIELD_EMPTY,field);
//						break;
//					}else{
//						//如果字段不会空   该字段为BigDecimal 类型 ，自动判断金额校验
////						if("BigDecimal".equals(f.getType().getSimpleName())){
////							if(!ValidatorTool.checkMoney(f.get(vo).toString())){
////								reVO.setIsContinue(false);
////								reVO.setErrorResult(ParameterConstant.ERROR_MONEY_FORMAT,field,null);
////								break;
////							}	
////						}
//						continue;
//					}
//				} catch (IllegalArgumentException e) {				
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					e.printStackTrace();
//				}
//			}else{
//			    continue;	
//			}	
//		}
//		return reVO;
//	}	
//	
//	
//	
//	/***
//	 * 获取方法的方法名和内名,用于日志，可以扩展
//	 * @since
//	 * @param Object,客户号，功能解释
//	 * @return
//	 * <br><b>作者：gaoxin</b>
//	 * <br>创建时间：2014-07-22 上午10:00:03
//	 */
//	public static String SystemClassMethod(Object obj,BaseModel baseModel) {
//		StringBuffer relog = new StringBuffer();
//		String res = baseModel.getLoggerId();
//		try {	
//			relog.append("\r\n"); 
//			relog.append(baseModel.getCustNo()); //客户号
//			relog.append("|");
//			relog.append(obj.getClass().getName());//客户使用类名
//			relog.append("|");
//			relog.append(new Throwable().getStackTrace()[1].getMethodName());//客户使用方法名	
//			relog.append("|");
//			relog.append(res);
//			relog.append("|");
//			//System.out.println(relog.toString());	
//			logger.info(relog.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return relog.toString();
//	}
//	
	/***
	 * 获取一个对象和属性和属性值 并输出 ,用于测试，可以扩展，上线注释实体
	 * @since
	 * @param Object
	 * @return
	 * <br><b>作者：gaoxin</b>
	 * <br>创建时间：2014-07-22 上午10:00:03
	 */
	
	public static void  SystemObject(Object obj) {
		try {	
			//输出p1的所有属性
			StringBuffer relog = new StringBuffer();
			relog.append("===============begin===================\r\n");
			if(obj==null||obj.getClass()==null||obj.getClass().getDeclaredFields()==null){
				relog.append("-SystemObject--Object is null  \r\n");
			}else{
				Field [] fields = obj.getClass().getDeclaredFields();					
				for(Field f:fields){
					f.setAccessible(true);
				}
				String objName = obj.getClass().getSimpleName();
				for(Field f:fields){
					String field = f.toString().substring(f.toString().lastIndexOf(".")+1);			//取出属性名称
					relog.append(objName+":"+field+" --> "+f.get(obj)+"\r\n");
				}
			}
//			if(obj instanceof com.htffund.etrade.fts.model.BaseModel == true){				
//				Field [] fields = obj.getClass().getSuperclass().getDeclaredFields();					
//				for(Field f:fields){
//					f.setAccessible(true);
//				}
//							String objName = obj.getClass().getSimpleName();
//				for(Field f:fields){
//					String field = f.toString().substring(f.toString().lastIndexOf(".")+1);			//取出属性名称
//					relog.append(objName+":"+field+" --> "+f.get(obj)+"\r\n");
//				}
//			}
			relog.append("================end====================\r\n");
			System.out.println(relog.toString());	
			//logger.info(relog.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
//	/***
//	 * 获取方法的方法名和内名,用于日志，可以扩展
//	 * @since
//	 * @param Object,客户号，功能解释
//	 * @return
//	 * <br><b>作者：gaoxin</b>
//	 * <br>创建时间：2014-07-22 上午10:00:03
//	 */
//	public static String  SystemClassMethod(Object obj,String custno) {
//		StringBuffer relog = new StringBuffer();
//		String res = UUID.randomUUID().toString();
//		try {	
//			relog.append("\r\n"); 
//			relog.append(custno); //客户号
//			relog.append("|");
//			relog.append(obj.getClass().getName());//客户使用类名
//			relog.append("|");
//			relog.append(new Throwable().getStackTrace()[1].getMethodName());//客户使用方法名	
//			relog.append("|");
//			relog.append(res);
//			relog.append("|");
//			//System.out.println(relog.toString());	
//			logger.info(relog.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return relog.toString();
//	}
	
	/*
	 * 
	 * 通过类名称调用该类的main方法、静态方法、无参的成员方法，有参数的成员方法。
	 */
	/*
	public static void main(String[] args) {
		String className = "com.evan.exercise.Test";         
		Class clazz = Class.forName(className);      
		ValidatorTool s = new ValidatorTool();
		clazz = s.getClass();   
		Method mainMethod = clazz.getMethod("main",String[].class);
		Method variableMethod = clazz.getMethod("test");
		Method staticMethod = clazz.getMethod("staticTest");
		Method paraMethod = clazz.getMethod("parameterTest", String.class,int.class);
		//通过反射调用main方法        
		mainMethod.invoke(null, (Object) new String[] { "evan" });
		//通过反射调用无参的成员方法 
		variableMethod.invoke(Class.forName(className).newInstance());
		//通过反射调用静态方法         
		staticMethod.invoke(clazz.newInstance());
		//通过反射调用有参的成员方法
		paraMethod.invoke(clazz.newInstance(),(Object)new String("evan"),(Object)520); 
	}
	*/
	
	
	
}

