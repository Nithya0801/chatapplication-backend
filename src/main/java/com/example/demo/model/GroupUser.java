package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "GroupUser")
public class GroupUser {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "groupId", updatable = false, nullable = false)
	private String groupId;
	private String groupName;
	
	@JsonBackReference
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "groupid_userid", joinColumns = @JoinColumn(name = "groupId", referencedColumnName = "groupId"), inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"))
	private Set<ChatUser> groupMember=new HashSet<>(0);

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Set<ChatUser> getGroupMember() {
		return groupMember;
	}

	public void setGroupMember(Set<ChatUser> groupMember) {
		this.groupMember = groupMember;
	}
	
	
	
}
