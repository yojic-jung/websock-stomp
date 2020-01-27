package test.model;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class Message {
	int message_id;
	int chatroom_id;
	
	String message_sender;
	String message_receiver;
	String message_content;
	
	public void setMessage_id(int message_id) {
		this.message_id= message_id;
	}
	
	public void setMessage_sender(String message_sender) {
		this.message_sender=message_sender;
	}
	public void setMessage_content(String message_content) {
		this.message_content=message_content;
	}
	public void setMessage_receiver(String message_receiver) {
		this.message_receiver=message_receiver;
	}
	public void setChatroom_id(int chatroom_id) {
		this.chatroom_id=chatroom_id;
	}
	
	
	public String getMessage_sender() {
		return message_sender;
	}
	public String getMessage_content() {
		return message_content;
	}
	public String getMessage_receiver() {
		return message_receiver;
	}
	public int getChatroom_id() {
		return chatroom_id;
	}
	public int getMessage_id() {
		return message_id;
	}
	
		
	public static Message convertMessage(String source) {
		Message message = new Message();
		Gson gson = new Gson();
		message = gson.fromJson(source,  Message.class);
		return message;
	}
}
