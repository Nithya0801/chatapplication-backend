package com.example.demo.requestDto;

import java.util.Set;

import com.example.demo.model.ChatUser;

public class GroupUserRequestDto {

	private String groupName;
	private Set<ChatUser> user;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Set<ChatUser> getUser() {
		return user;
	}
	public void setUser(Set<ChatUser> user) {
		this.user = user;
	}
	
	
}
