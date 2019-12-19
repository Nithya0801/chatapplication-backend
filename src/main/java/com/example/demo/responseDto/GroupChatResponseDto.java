package com.example.demo.responseDto;

import java.util.List;
import java.util.Set;

import com.example.demo.model.ChatUser;

public class GroupChatResponseDto {
	
	private String groupId;
	private String message;
	private String sender;
	private String senderId;
	private Set<ChatUser> users;
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public Set<ChatUser> getUsers() {
		return users;
	}
	public void setUsers(Set<ChatUser> set) {
		this.users = set;
	}
	
	

}
