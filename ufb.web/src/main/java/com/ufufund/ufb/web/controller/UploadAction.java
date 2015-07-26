package com.ufufund.ufb.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
//import com.ufufund.ufb.model.db.BankCardWithTradeAcco;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class UploadAction {
	private static final Logger LOG = LoggerFactory.getLogger(UploadAction.class);
	
	private String upload_path = "src/main/webapp/images/";
	
	@RequestMapping(value="upload/upload")
	public @ResponseBody String uploadPic(
									HttpServletRequest request,
									HttpServletResponse response) throws Exception {
		Map<String, String> resp = new HashMap<String, String>();

		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	        MultiValueMap<String, MultipartFile> map = multipartRequest.getMultiFileMap();  
	        
	        Iterator<String> iter = map.keySet().iterator(); 
	        while(iter.hasNext()) {  
	        	 String str = (String) iter.next(); 
	        	 List<MultipartFile> fileList =  map.get(str);  
	        	 for(MultipartFile multipartFile : fileList) {  
	        		 return marshallResult(multipartFile,request,resp);
	        	 }
	        	 LOG.info("file name===="+str);
	        }
		}catch(Exception e) {
			e.printStackTrace();
			LOG.error("msg[{}],\nparams[{}]", e.getMessage(), null);
			resp.put("code", "9999");
			resp.put("error", e.getMessage());
		}
		
		//return JSONUtil.toJSONString(resp);
		return "test";
	}
	
	private String marshallResult(MultipartFile file,HttpServletRequest request,Map<String, String> resp) throws Exception{
		
		OutputStream os = null;
		InputStream is = null;
		String pathfilename = null;
		String changeDateToPath = null;
		
		try{
//			String path = request.getSession().getServletContext().getRealPath("/upload")+changeDateToPath(); 
			changeDateToPath = changeDateToPath();
			String path = upload_path+changeDateToPath; //Users/goodrich/Downloads/20150726/120001/
			
//			String fileName = file.getOriginalFilename();  
	        String fileName = new Date().getTime()+".jpg"; // 1437881637490.jpg
	        changeDateToPath = changeDateToPath + fileName;
	        File targetFile = new File(path);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
	        
	        pathfilename = path+"/"+fileName;
	        LOG.info("path==="+pathfilename);  
	        targetFile = new File(pathfilename);
	        
	        os = new FileOutputStream(targetFile);
	        is = file.getInputStream();
	        
	        //保存
	        byte [] bytes = new byte[1024];
	        int len = 0;
	        while((len = is.read(bytes)) > -1){
	        	 os.write(bytes,0,len);
	        	 os.flush();
	        }
	        
	        //resp.put("data", "/upload"+changeDateToPath()+fileName);
	        resp.put("data",changeDateToPath);
		}catch(Exception e){
			LOG.error("upload pic find exception", e);
			throw e;
		}finally{
			if(os != null){
				os.close();
			}if(is != null){
				is.close();
			}
		}
		
		//return JSONUtil.toJSONString(resp);
		return changeDateToPath;
	}
	
	/**
	 * 将日期转换成路径
	 * @return
	 */
	private String changeDateToPath(){
		Date date = new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
		String dir1 = sdf.format(date);
		sdf= new SimpleDateFormat("HHmmss");
		String dir2 = sdf.format(date);
		
		if(dir1 != null && dir2 != null){
			//return "/"+nowDate.replace("-", "/")+"/";
			return "/"+dir1+"/"+dir2+"/";
		}
		
		return null;
	}
	
}
