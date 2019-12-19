package com.example.demo.service;

import java.util.List;

import com.example.demo.model.GroupUser;
import com.example.demo.repository.GroupUserRepository;
import com.example.demo.requestDto.GroupUserRequestDto;

public interface IGroupUser {
	GroupUser createGroup(GroupUserRequestDto dto);
	List<GroupUser> listGroup();
}
