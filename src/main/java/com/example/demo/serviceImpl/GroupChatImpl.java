package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.GroupChat;
import com.example.demo.repository.GroupChatRepository;
import com.example.demo.requestDto.GroupChatRequestDto;
import com.example.demo.service.IGroupChat;

@Service
public class GroupChatImpl implements IGroupChat{

	@Autowired
	GroupChatRepository groupChatRepo;
	@Override
	public GroupChat createChat(GroupChatRequestDto dto) {
		GroupChat chat=new GroupChat();
		chat.setGroupId(dto.getGroupId());
		chat.setMessage(dto.getMessage());
		chat.setSenderId(dto.getSenderId());
		chat.setSenderName(dto.getSender());
		
		return groupChatRepo.save(chat);
	}

}
