package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.ChatUser;

public interface ChatUserRepository extends JpaRepository<ChatUser,String>{

	@Query("select c from ChatUser c where c.userName=?1")
	ChatUser getUser(String name);
	
	@Query("select c from ChatUser c where c.userId=?1")
	ChatUser getUserById(String userId);
	
	@Query("select c from ChatUser c where c.status=?1")
	List<ChatUser> getOnlineUser(String status);
}
