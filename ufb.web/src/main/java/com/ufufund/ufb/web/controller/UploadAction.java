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
	
	private String upload_path = "/Users/goodrich/Downloads/";
	
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
	        		 marshallResult(multipartFile,request,resp);
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
		
		try{
//			String path = request.getSession().getServletContext().getRealPath("/upload")+changeDateToPath(); 
			String path = upload_path+changeDateToPath(); 
//			String fileName = file.getOriginalFilename();  
	        String fileName = new Date().getTime()+".jpg";  
	        LOG.info("path==="+path);  
	        File targetFile = new File(path);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
	        
	        targetFile = new File(path+"/"+fileName);
	        
	        os = new FileOutputStream(targetFile);
	        is = file.getInputStream();
	        
	        //保存
	        byte [] bytes = new byte[1024];
	        int len = 0;
	        while((len = is.read(bytes)) > -1){
	        	 os.write(bytes,0,len);
	        	 os.flush();
	        }
	        
	        resp.put("data", "/upload"+changeDateToPath()+fileName);
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
		return "test";
	}
	
	/**
	 * 将日期转换成路径
	 * @return
	 */
	private String changeDateToPath(){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyymmdd24hhmmss");
		String nowDate = sdf.format(new Date());
		
		if(nowDate != null){
			return "/"+nowDate.replace("-", "/")+"/";
		}
		
		return null;
	}
	
}
