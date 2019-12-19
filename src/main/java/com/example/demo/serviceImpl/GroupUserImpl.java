package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ChatUser;
import com.example.demo.model.GroupUser;
import com.example.demo.repository.GroupUserRepository;
import com.example.demo.requestDto.GroupUserRequestDto;
import com.example.demo.service.IGroupUser;

@Service
public class GroupUserImpl implements IGroupUser {

	@Autowired
	GroupUserRepository groupRepo;
	@Override
	public GroupUser createGroup(GroupUserRequestDto dto) {
		// TODO Auto-generated method stub
		GroupUser user=new GroupUser();
		user.setGroupName(dto.getGroupName());
//		for(ChatUser cu:dto.getUser()) {
//		user.getGroupMember().add(cu);
//		}
		GroupUser ss=groupRepo.saveAndFlush(user);
		for(ChatUser cu:dto.getUser()) {
//			user.getGroupMember().add(cu);
//			}
		ss.getGroupMember().add(cu);
		}
		System.out.println(">>>>>>>>>>>>>>>>>>"+user+""+ss);
		
		return groupRepo.saveAndFlush(ss);
	}
	@Override
	public List<GroupUser> listGroup() {
		// TODO Auto-generated method stub
		return groupRepo.findAll();
	}

}
