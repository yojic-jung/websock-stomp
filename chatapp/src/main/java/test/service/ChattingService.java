package test.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import test.dao.ChatDao;
import test.model.Message;

public class ChattingService {
	ChatDao chatDao;
	
	public void setChatDao(ChatDao chatDao) {
		this.chatDao=chatDao;
	}
	
	public List<String> userList(String id){
		List<String> user = chatDao.userList(id);
		return user;
	}
	
	public int checkRoom(String sender, String receiver) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("sender", sender);
		map.put("receiver", receiver);
		int a = chatDao.checkRoom(map);
		
		if(a==0) {
			return a;
		}else {
			int b = chatDao.takeRoomNum(map);
			return b;
		}
	}
	
	@Transactional(rollbackFor= {Exception.class})
	public int makeRoom(String sender, String receiver) {
		chatDao.makeRoom(sender+","+receiver);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("sender", sender);
		map.put("receiver", receiver);
		int a = chatDao.takeRoomNum(map);
		
		return a;
	}
	
	public int insertMessage(Message mes) {
		return chatDao.insertMessage(mes);
	}
	
	public List<Message> takeMessage( int chatroom_id) {
		List<Message> mesList = chatDao.takeMessage(chatroom_id);
		return mesList;
	}
	
}
