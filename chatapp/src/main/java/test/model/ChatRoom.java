package test.model;

import lombok.Data;

@Data
public class ChatRoom {
	int chatroom_id;
	
	String user_id;
	String tutor_id;
	
	String class_id;
}
