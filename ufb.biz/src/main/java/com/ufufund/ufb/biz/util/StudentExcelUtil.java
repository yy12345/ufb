package com.ufufund.ufb.biz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.db.Clazz;
import com.ufufund.ufb.model.db.Student;

public class StudentExcelUtil {
	// excel文件目录
	private static String documentDir = "E:/workspace/ufufund/excel/";
	// 班级最大学生数量
	public static int max_num = 50;
	// protect密码
	private static String password = "123";
	// 表单tab项颜色 
	private static Map<String, IndexedColors> colorMap = new HashMap<String, IndexedColors>();
	static{
		colorMap.put("01", IndexedColors.RED);
		colorMap.put("02", IndexedColors.YELLOW);
		colorMap.put("03", IndexedColors.GREEN);
		colorMap.put("04", IndexedColors.BLUE);
		colorMap.put("05", IndexedColors.VIOLET);
	}
	
	
	/**
	 * 从标准模板创建机构班级excel模板
	 * @param orgname
	 * @param clazzList
	 * @param templateName
	 * @throws IOException
	 */
	public static void createFromTemplate(String orgname, List<Clazz> clazzList, String templateName)
			throws IOException {
		
		XSSFWorkbook workBook = new XSSFWorkbook(new FileInputStream(new File(documentDir+templateName)));
		XSSFSheet sheet = null;
		for(int i=0; i < clazzList.size(); i++){
			Clazz clazz = clazzList.get(i);
			if(i == 0){
				sheet = workBook.getSheetAt(0);
			}else{
				sheet = workBook.cloneSheet(0);
			}
			workBook.setSheetName(i, clazz.getName());
			sheet.getRow(max_num+3).getCell(0).setCellValue(clazz.getCid());
			sheet.setTabColor(colorMap.get(clazz.getTypeid()).getIndex());
		}
		
		workBook.lockStructure();
		workBook.setWorkbookPassword(password, HashAlgorithm.sha1);
		
		// 文档保存到输出流
		FileOutputStream os = new FileOutputStream(documentDir+orgname+"-学生档案.xlsx");
		workBook.write(os);
		os.close();
		workBook.close();
		System.out.println("创建excel成功："+orgname+"-学生档案.xls");
	}
	
	
	public static Map<String, List<Student>> readFromExcel(Workbook workBook)
			throws IOException {
		Map<String, List<Student>> resultMap = new HashMap<String, List<Student>>();
		
		int n = workBook.getNumberOfSheets();
		for(int i = 0; i < n; i++){
			Sheet sheet = workBook.getSheetAt(i);
			// 逐个解析班级对象
			String clazzId = sheet.getRow(0).getCell(4).getStringCellValue();
			if(!StringUtils.isBlank(clazzId)){
				resultMap.put(clazzId, parseClazz(sheet));
			}
		}
		workBook.close();
		return resultMap;
	}
	
	private static List<Student> parseClazz(Sheet sheet){
		List<Student> stuList = new ArrayList<Student>();
		
		for(int i = 3; i < max_num+3; i++){
			Row r = sheet.getRow(i);
			// 逐个解析学生对象
			Student s = parseStudent(r);
			if(s != null && !StringUtils.isBlank(s.getName())){
				stuList.add(s);
			}
		}
		return stuList;
	}
	
	private static Student parseStudent(Row r){
		
		Student s = new Student();
		
		// 记录为null或者学生姓名为空值，则认为无效数据
		if(r == null || r.getCell(1) == null || StringUtils.isBlank(r.getCell(1).getStringCellValue())){
			return null;
		}
		s.setSno(r.getCell(0)==null?null:r.getCell(0).getStringCellValue());
		s.setName(r.getCell(1)==null?null:r.getCell(1).getStringCellValue());
		s.setSex(r.getCell(2)==null?null:getSexValue(r.getCell(2).getStringCellValue()));
		s.setBirthday(r.getCell(3)==null?null:getBirthdayValue(r.getCell(3).getDateCellValue()));
		s.setF_type(r.getCell(15)==null?null:r.getCell(15).getStringCellValue());
		s.setP1_relation(r.getCell(5)==null?null:r.getCell(5).getStringCellValue());
		s.setP1_name(r.getCell(6)==null?null:r.getCell(6).getStringCellValue());
		s.setP1_mobile(r.getCell(7)==null?null:r.getCell(7).getStringCellValue());
		s.setP1_mail(r.getCell(8)==null?null:r.getCell(8).getStringCellValue());
		
		return s;
	}
	
	private static String getSexValue(String sex){
		if(StringUtils.isBlank(sex)){
			return null;
		}else if("男".equals(sex)){
			return "0";
		}else {
			return "1";
		}
	}
	
	private static String getBirthdayValue(Date birthday){
		if(birthday == null){
			return null;
		}else{
			return DateUtil.format(birthday, DateUtil.DATE_PATTERN_1);
		}
	}
	
	
	public static void main(String args[]){
		
		String orgname = "孙桥幼儿园";
		List<Clazz> clazzList = new ArrayList<Clazz>();
		Clazz c11 = new Clazz();
		c11.setCid("00011");
		c11.setName("大一班");
		c11.setTypeid("01");
		Clazz c12 = new Clazz();
		c12.setCid("00012");
		c12.setName("大二班");
		c12.setTypeid("01");
		Clazz c2 = new Clazz();
		c2.setCid("00021");
		c2.setName("中一班");
		c2.setTypeid("02");
		Clazz c3 = new Clazz();
		c3.setCid("00031");
		c3.setName("小一班");
		c3.setTypeid("03");
		Clazz c4 = new Clazz();
		c4.setCid("00041");
		c4.setName("托一班");
		c4.setTypeid("04");
		Clazz c5 = new Clazz();
		c5.setCid("00051");
		c5.setName("兴趣班");
		c5.setTypeid("05");
		clazzList.add(c11);
		clazzList.add(c12);
		clazzList.add(c2);
		clazzList.add(c3);
		clazzList.add(c4);
		clazzList.add(c5);
		
		try {
			createFromTemplate(orgname, clazzList, "学生档案摸版.xlsx");
			
//			String templateName = "学生档案摸版.xlsx";
//			Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(documentDir+templateName)));
//			Map<String, List<Student>> map = readFromExcel(workbook);
//			System.out.println(map);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
