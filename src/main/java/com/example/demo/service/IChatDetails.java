package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ChatDetails;
import com.example.demo.requestDto.ChatDetailsRequestDto;

public interface IChatDetails {

	ChatDetails createChat(ChatDetailsRequestDto dto);
	List<ChatDetails> getPublicChat();
	List<ChatDetails> getPrivateChat(String fromUserId,String toUserId);
	
}
