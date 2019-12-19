package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.GroupChat;

public interface GroupChatRepository extends JpaRepository<GroupChat, String> {

	@Query("select c from GroupChat c where groupId=?1")
	List<GroupChat> getChat(String groupId);
}
