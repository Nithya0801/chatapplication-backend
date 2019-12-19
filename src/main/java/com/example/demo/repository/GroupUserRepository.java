package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.GroupUser;

public interface GroupUserRepository extends JpaRepository<GroupUser, String> {

	@Query(value="select groupid from groupid_userid  where userid=?1",nativeQuery = true)
	List<String> groups(String userId);
	
	
}
