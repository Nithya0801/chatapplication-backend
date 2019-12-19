package com.example.demo.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ChatUser")
public class ChatUser {
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name="userId" ,updatable = false, nullable = false)
	private String userId;
	private String userName;
	private String status;
	
	
//	@ManyToMany(mappedBy = "groupMembers")
//    private Set<GroupChatDetails> groups=new HashSet<>(0);
//	@JsonIgnore
//	private Set<GroupChatDetails> groups=new HashSet<>(0);
	
	
//	public String getGroupName() {
//		return groupName;
//	}
//	public void setGroupName(String groupName) {
//		this.groupName = groupName;
//	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
//	public String getGroupId() {
//		return groupId;
//	}
//	public void setGroupId(String groupId) {
//		this.groupId = groupId;
//	}
	
	
}
