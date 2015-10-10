package com.ufufund.ufb.web.controller;

public class UserController {
	
	private boolean login(User user){
		// 验证用户
		if(checkPassword(user)){
			// 成功
		}else{
			// 失败
		}
		return true;
	}
	
	private boolean logout(User user){
		return true;
	}
	
	private boolean checkPassword(User user){
		if("123".equals(user.getPassword()) 
				&&"13900000000".equals(user.getPhone())){
			return true;
		}else{
			return false;
		}
	}

}
