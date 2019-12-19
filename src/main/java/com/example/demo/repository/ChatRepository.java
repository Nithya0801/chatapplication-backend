package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.ChatDetails;

public interface ChatRepository extends JpaRepository<ChatDetails, String>{

	@Query("select c from ChatDetails c where c.msgType=?1")
	List<ChatDetails> getPublicChat(String status);
	
	@Query("select c from ChatDetails c where (c.fromUserId=?1 and c.toUserId=?2) or (c.fromUserId=?2 and c.toUserId=?1) ")
	List<ChatDetails> getPrivateChat(String fromUserId,String toUserId);
}
