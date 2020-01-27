package test.service;

import test.dao.LoginDao;
import test.model.Member;

public class LoginService {

	LoginDao loginDao;
	
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao=loginDao;
	}

	public boolean loginCheck(Member member) {
		String password = loginDao.login(member.getId());
		if(password==null) {
			return false;
		}
		
		if(password.equals(member.getPassword())) {
			return true;
		}else {
			
			return false;
		}
	}
	
	public String signUp(Member member) {
		int id = loginDao.idCheck(member.getId());
		
		if(id!=0) {
			return "false";
		}else {
			loginDao.insertMember(member);
			return "success";
		}
		
	}
}
