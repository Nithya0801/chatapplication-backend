package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ChatUser;
//import com.example.demo.model.ChatUser1;
import com.example.demo.repository.ChatUserRepository;
import com.example.demo.requestDto.ChatUserRequestDto;
import com.example.demo.service.IChatUser;

@Service
public class ChatUserImpl implements IChatUser{

	@Autowired
	ChatUserRepository userRepo;
	
	@Override
	public ChatUser createUser(ChatUserRequestDto dto) {
		ChatUser user=new ChatUser();
		user.setStatus(dto.getStatus());
		user.setUserName(dto.getUserName());
		ChatUser user1=userRepo.save(user);
//		ChatUser1 u=new ChatUser1();
//		u.setUserId(user1.getUserId());
//		u.setUserName(user1.getUserName());
//		u.setStatus(user.getStatus());
//		user
		
		return userRepo.save(user);
		
	}

	@Override
	public ChatUser getByUsername(String name) {
		// TODO Auto-generated method stub
		return userRepo.getUser(name);
	}

	@Override
	public List<ChatUser> getOnlineUser() {
		// TODO Auto-generated method stub
		return userRepo.getOnlineUser("online");
	}

	@Override
	public List<ChatUser> getAllUser() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

}
