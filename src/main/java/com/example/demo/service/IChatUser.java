package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ChatUser;
import com.example.demo.requestDto.ChatUserRequestDto;

public interface IChatUser {

	ChatUser createUser(ChatUserRequestDto dto);
	ChatUser getByUsername(String name);
	List<ChatUser> getOnlineUser();
	List<ChatUser> getAllUser();
}
