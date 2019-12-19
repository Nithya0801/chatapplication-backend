package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ChatDetails;
import com.example.demo.repository.ChatRepository;
import com.example.demo.requestDto.ChatDetailsRequestDto;
import com.example.demo.service.IChatDetails;

@Service
public class ChatDetailImpl implements IChatDetails{

	@Autowired
	ChatRepository chatRepo;
	
	@Override
	public ChatDetails createChat(ChatDetailsRequestDto dto) {
		// TODO Auto-generated method stub
		ChatDetails chat=new ChatDetails();
		chat.setFromUserId(dto.getFromUserId());
		chat.setMessage(dto.getMessage());
		chat.setMsgType(dto.getMsgType());
		if(dto.getToUserId() != null) {
			chat.setToUserId(dto.getToUserId());
			chat.setReceiver(dto.getReceiver());
		}
		chat.setSender(dto.getSender());
		chat.setDateTime(dto.getDateTime());
		if(dto.getGroupId() != null) {
			chat.setGroupId(dto.getGroupId());
			chat.setGroupName(dto.getGroupName());
			
		}
		return chatRepo.save(chat);
	}

	@Override
	public List<ChatDetails> getPublicChat() {
		// TODO Auto-generated method stub
		return chatRepo.getPublicChat("public"
				);
	}

	@Override
	public List<ChatDetails> getPrivateChat(String fromUserId, String toUserId) {
		// TODO Auto-generated method stub
		return chatRepo.getPrivateChat(fromUserId, toUserId);
	}

}
