package com.ufufund.ufb.biz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ufufund.ufb.common.exception.SysException;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.CompressUtil;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.common.utils.SequenceUtil;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.db.Clazz;
import com.ufufund.ufb.model.db.Student;
import com.ufufund.ufb.model.enums.FamilyTypeEnum;
import com.ufufund.ufb.model.enums.SexEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentExcelUtil {
	// 配置文件路径
	private static final String CONFIG_FILE = "config/spring-ufb-all.properties";
	// 表单tab项颜色 
	private static final Map<String, IndexedColors> colorMap = new HashMap<String, IndexedColors>();
		
	/** 配置变量 **/
	private static String excel_template_file;  // 系统模板文件
	private static String excel_download_dir;	// 下载文件目录
	public static int max_num;  			// 班级最大学生数量
	private static String password; 		// protect密码
	
	
	/** 初始化配置 **/
	static{
		colorMap.put("4", IndexedColors.RED);
		colorMap.put("3", IndexedColors.YELLOW);
		colorMap.put("2", IndexedColors.GREEN);
		colorMap.put("1", IndexedColors.BLUE);
		colorMap.put("other", IndexedColors.VIOLET);
		
		InputStream in = StudentExcelUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
		Properties p = new Properties();
		try {
			p.load(new InputStreamReader(in));
			
			excel_template_file = p.getProperty("ufb.school.excel_template_file");
			excel_download_dir = p.getProperty("ufb.school.excel_download_dir");
			max_num = Integer.parseInt(p.getProperty("ufb.school.excel_template_num"));
			password = p.getProperty("ufb.school.excel_template_pwd");
			
			if(StringUtils.isBlank(excel_template_file)  
					|| StringUtils.isBlank(excel_download_dir) 
					|| max_num == 0
					|| StringUtils.isBlank(password) ){
				throw new SysException("excel模板参数为空！");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			System.exit(0);
		}
	}
	
	/**
	 * 压缩包目录名
	 * @param orgname
	 * @return
	 */
	public static String getDirName(String orgname){
		return orgname+"-学生档案" + File.separator;
	}
	
	/**
	 * 指定生成模板的文件名规范
	 * @param orgname
	 * @param gradeName
	 * @param cname
	 * @return
	 */
	public static String getTemplateName(String orgname, String gradeName, String cname){
		return orgname+"-"+gradeName+"-"+cname+".xlsx";
	}
	
	/**
	 * 确保为空文件夹
	 * @param dir
	 */
	public static void ensureDirEmpty(String dir){
		File directory = new File(excel_download_dir+dir);
		if(!directory.exists()){
			directory.mkdirs();
		}else{
			File[] files = directory.listFiles();
			for(File f : files){
				f.delete();
			}
		}
	}
	
	/**
	 * 打包生成的excel文件
	 * @param relativeDir
	 */
	public static String zipExcels(String archive, String sourceDir){
		String archive_temp = excel_download_dir + archive + ".zip";
		String sourceDir_temp = excel_download_dir + sourceDir;
		CompressUtil.zip(archive_temp, sourceDir_temp);
		return archive + ".zip";
	}
	
	/**
	 * 从标准模板创建机构班级excel模板
	 * @param orgname
	 * @param clazzList
	 * @param templateName
	 * @throws IOException
	 */
	public static void createFromTemplate(Clazz clazz, String savePath){
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(new FileInputStream(new File(excel_template_file)));
			XSSFSheet sheet = workBook.getSheetAt(0);
			// 班级名、及id数据填充
			workBook.setSheetName(0, clazz.getName());
			sheet.getRow(max_num+3).getCell(0).setCellValue(clazz.getCid());
			// 第一列班级名数据填充
			for(int j = 2; j < max_num+2; j++){
				sheet.getRow(j).getCell(0).setCellValue(clazz.getName());
			}
			// 表单tab页颜色
//			String colorKey = "other";
//			if(clazz.getTypeid().length() == 1){
//				colorKey = clazz.getTypeid();
//			}
//			sheet.setTabColor(colorMap.get(colorKey).getIndex());
			
			workBook.lockStructure();
			workBook.setWorkbookPassword(password, HashAlgorithm.sha1);
			
			// 文档保存到输出流
			FileOutputStream os = new FileOutputStream(excel_download_dir+savePath);
			workBook.write(os);
			os.close();
			workBook.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new SysException("生成excel错误：filename="+excel_download_dir+savePath+","+e.getMessage());
		}
		log.info("生成excel成功："+excel_download_dir+savePath);
	}
	
	
	/**
	 * 读取excel的学生档案数据
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static Map<String, List<Student>> readFromExcel(String filePath) {
		Map<String, List<Student>> resultMap = new HashMap<String, List<Student>>();
		try {
			Workbook workBook = new XSSFWorkbook(new FileInputStream(new File(filePath)));
			int n = workBook.getNumberOfSheets();
			for(int i = 0; i < n; i++){
				Sheet sheet = workBook.getSheetAt(i);
				// 逐个解析班级对象
				String clazzId = sheet.getRow(max_num+3).getCell(0).getStringCellValue();
				if(!StringUtils.isBlank(clazzId)){
					resultMap.put(clazzId, parseClazz(sheet));
				}
			}
			workBook.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new SysException("读取excel错误：filePath="+filePath+","+e.getMessage());
		}
		log.info("读取excel成功：clazz_num="+resultMap.size()+",filePath="+filePath);
		return resultMap;
	}
	
	private static List<Student> parseClazz(Sheet sheet){
		List<Student> stuList = new ArrayList<Student>();
		
		for(int i = 2; i < max_num+2; i++){
			Row r = sheet.getRow(i);
			// 逐个解析学生对象
			Student s = parseStudent(sheet, r);
			if(s != null){
				stuList.add(s);
			}
		}
		return stuList;
	}
	
	private static Student parseStudent(Sheet sheet, Row r){
		// 检测[姓名]字段，若其无数据，则认为无效记录
		if(r == null || r.getCell(2) == null 
				|| StringUtils.isBlank(r.getCell(2).getStringCellValue())){
			return null;
		}
		// 读取数据
		String clazzName = sheet.getSheetName();
		Student s = new Student();
		s.setSid(SequenceUtil.getSerial());
		s.setCid(sheet.getRow(max_num+3).getCell(0).getStringCellValue());
		s.setCname(clazzName);
		s.setSno(r.getCell(1)==null?null:r.getCell(1).getStringCellValue());
		s.setName(r.getCell(2)==null?null:r.getCell(2).getStringCellValue());
		s.setSex(r.getCell(3)==null?null:getSexValue(r.getCell(3).getStringCellValue()));
		s.setBirthday(r.getCell(4)==null?null:getBirthdayValue(r.getCell(4).getDateCellValue()));
		s.setF_type(r.getCell(5)==null?null:getFamilyType(r.getCell(5).getStringCellValue()));
		s.setP1_name(r.getCell(6)==null?null:r.getCell(6).getStringCellValue());
		s.setP1_mobile(r.getCell(7)==null?null:r.getCell(7).getStringCellValue());
		s.setP1_mail(r.getCell(8)==null?null:r.getCell(8).getStringCellValue());
		
		// 检验数据
		if(!StringUtils.isBlank(s.getSno()) && s.getSno().length() > 12){
			throw new UserException(clazzName+", 第"+(r.getRowNum()+1)+"行:[学号]超过12个字符！");
		}
		if(s.getName().length() > 20){
			throw new UserException(clazzName+", 第"+(r.getRowNum()+1)+"行:[姓名]超过20个字！");
		}
		if(StringUtils.isBlank(s.getSex())){
			throw new UserException(clazzName+", 第"+(r.getRowNum()+1)+"行:[性别]为空！");
		}
		if(StringUtils.isBlank(s.getBirthday())){
			throw new UserException(clazzName+", 第"+(r.getRowNum()+1)+"行:[生日]为空！");
		}
		if(StringUtils.isBlank(s.getF_type())){
			throw new UserException(clazzName+", 第"+(r.getRowNum()+1)+"行:[家庭类型]为空！");
		}
		if(StringUtils.isBlank(s.getP1_name())){
			throw new UserException(clazzName+", 第"+(r.getRowNum()+1)+"行:[缴费家长]为空！");
		}
		if(s.getP1_name().length() > 20){
			throw new UserException(clazzName+", 第"+(r.getRowNum()+1)+"行:[缴费家长]超过20个字！");
		}
		if(!RegexUtil.isMobile(s.getP1_mobile())){
			throw new UserException(clazzName+", 第"+(r.getRowNum()+1)+"行:[家长手机]有误！");
		}
		if(!StringUtils.isBlank(s.getP1_mail()) && s.getP1_mail().indexOf('@') == -1){
			throw new UserException(clazzName+", 第"+(r.getRowNum()+1)+"行:[家长邮箱]有误！");
		}
		if(s.getP1_mail().length() > 30){
			throw new UserException(clazzName+", 第"+(r.getRowNum()+1)+"行:[家长邮箱]超过30个字符！");
		}
		
		return s;
	}
	
	private static String getSexValue(String sex){
		if(StringUtils.isBlank(sex)){
			return null;
		}else if(SexEnum.Man.getValue().equals(sex)){
			return SexEnum.Man.getKey();
		}else if(SexEnum.Female.getValue().equals(sex)){
			return SexEnum.Female.getKey();
		}else{
			return null;
		}
	}
	
	private static String getBirthdayValue(Date birthday){
		if(birthday == null){
			return null;
		}else{
			return DateUtil.format(birthday, DateUtil.DATE_PATTERN_2);
		}
	}
	
	private static String getFamilyType(String value){
		return FamilyTypeEnum.getKeyByValue(value);
	}
	
	
	public static void main(String args[]){
		
		String dir = "E:/opt/logs/孙桥幼儿园";
		
		File tempDir = new File(dir);
		if(!tempDir.exists()){
			tempDir.mkdirs();
		}else{
			File[] files = tempDir.listFiles();
			for(File f : files){
				f.delete();
			}
			System.out.println(tempDir.exists());
			tempDir.mkdirs();
		}
		
		System.out.println(tempDir.exists());
	}
}
