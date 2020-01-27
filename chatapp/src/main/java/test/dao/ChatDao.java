package test.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import test.model.Message;

@Repository
public interface ChatDao {

	public List<String> userList(String id);
	
	public int checkRoom(HashMap<String, Object> map);
	
	public int takeRoomNum(HashMap<String, Object> map);
	
	public int makeRoom(String user);
	
	public int insertMessage(Message mes);
	
	public List<Message> takeMessage(int chatroom_id);
	
}
