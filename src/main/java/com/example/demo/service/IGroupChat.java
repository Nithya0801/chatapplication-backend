package com.example.demo.service;

import com.example.demo.model.GroupChat;
import com.example.demo.requestDto.GroupChatRequestDto;

public interface IGroupChat {

	GroupChat createChat(GroupChatRequestDto dto);
}
