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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import com.ufufund.ufb.model.db.BankCardWithTradeAcco;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.model.db.PicInfo;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.web.util.UserHelper;

@Controller
public class UploadAction {
	private static final Logger LOG = LoggerFactory.getLogger(UploadAction.class);
	
	private String UPLOADPATH = "src/main/webapp/images/";
	
	@Autowired
	private BankCardManager bankCardManager;
	
	@RequestMapping(value="upload/upload")
	public @ResponseBody String uploadPic(
									HttpServletRequest request,
									HttpServletResponse response) throws Exception {
		String imgtype = request.getParameter("imgtype");
		String file = null;
		Map<String, String> resp = new HashMap<String, String>();

		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	        MultiValueMap<String, MultipartFile> map = multipartRequest.getMultiFileMap();  
	        
	        Iterator<String> iter = map.keySet().iterator(); 
	        while(iter.hasNext()) {  
	        	 String str = (String) iter.next(); 
	        	 List<MultipartFile> fileList =  map.get(str);  
	        	 for(MultipartFile multipartFile : fileList) {  
	        		 file = marshallResult(s_custinfo.getCustno(),multipartFile,request,resp);
	        	 }
	        	 LOG.info("file name===="+file);
	        }
		}catch(Exception e) {
			e.printStackTrace();
			LOG.error("msg[{}],\nparams[{}]", e.getMessage(), null);
			resp.put("code", "9999");
			resp.put("error", e.getMessage());
		}
		
		//return JSONUtil.toJSONString(resp);
		return file;
	}
	
	@RequestMapping(value="upload/update")
	public @ResponseBody Map<String,String> updatePicInfo(HttpServletRequest request) {
		Map<String, String> resp = new HashMap<String, String>();
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			
			String imgtype = request.getParameter("imgtype");
			String imgpath = request.getParameter("imgpath");
			System.out.println(imgtype + "," + imgpath);
			PicInfo picInfo = new PicInfo();
			picInfo.setCustno(s_custinfo.getCustno());
			
			if(imgtype.indexOf("01")>0){
				picInfo.setImgpath1(imgpath);
			}
			if(imgtype.indexOf("02")>0){
				picInfo.setImgpath2(imgpath);
			}
			if(imgtype.indexOf("03")>0){
				picInfo.setImgpath3(imgpath);
			}
			if(imgtype.indexOf("04")>0){
				picInfo.setImgpath4(imgpath);
			}
			if(imgtype.indexOf("05")>0){
				picInfo.setImgpath5(imgpath);
			}
			if(imgtype.indexOf("06")>0){
				picInfo.setImgpath6(imgpath);
			}
			if(imgtype.indexOf("07")>0){
				picInfo.setImgpath7(imgpath);
			}
			if(imgtype.indexOf("08")>0){
				picInfo.setImgpath8(imgpath);
			}
			if(imgtype.indexOf("09")>0){
				picInfo.setImgpath9(imgpath);
			}
			bankCardManager.updatePicInfo(picInfo);
			
		}catch(Exception e) {
			e.printStackTrace();
			//logger.error("msg[{}]", e.getMessage());
			//map.put("code", String.valueOf(TxException.SAVE_CHEQUE_FAIL));
			//map.put("error", e.getMessage());
		}
		
		resp.put("code", "200");
		return resp;
	}
	
	private String marshallResult(String s_custinfo, MultipartFile file,HttpServletRequest request,Map<String, String> resp) throws Exception{
		
		OutputStream os = null;
		InputStream is = null;
		String floder = null;
		String fileName = s_custinfo + '_' + new Date().getTime()+".jpg"; // 1437881637490.jpg
		try{
			floder = changeDateToPath(); // /20150726/
	        
	        File targetFloder = new File(UPLOADPATH + floder);  // src/main/webapp/images/20150726/
	        if(!targetFloder.exists()){  
	        	targetFloder.mkdirs();  
	        }  
	        
	        LOG.info("path==="+UPLOADPATH + floder + fileName);  
	        File targetFile = new File(UPLOADPATH + floder + fileName); // src/main/webapp/images/20150726/1437881637490.jpg
	        os = new FileOutputStream(targetFile);
	        is = file.getInputStream();
	        //保存
	        byte [] bytes = new byte[1024];
	        int len = 0;
	        while((len = is.read(bytes)) > -1){
	        	 os.write(bytes,0,len);
	        	 os.flush();
	        }
	        
	        resp.put("data", floder + fileName); // 20150726/1437881637490.jpg
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
		return floder + fileName;
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
			// return "/"+dir1+"/"+dir2+"/";
			return "/"+dir1+"/";
		}
		
		return null;
	}
	
}
