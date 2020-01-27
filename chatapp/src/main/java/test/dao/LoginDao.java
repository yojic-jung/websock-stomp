package test.dao;

import test.model.Member;

public interface LoginDao {

	public String login(String id);
	
	public int idCheck(String id);
	
	public void insertMember(Member member);
}
