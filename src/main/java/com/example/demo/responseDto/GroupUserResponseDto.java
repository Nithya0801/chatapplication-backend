package com.example.demo.responseDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.model.ChatUser;

public class GroupUserResponseDto {

		private String groupId;
		private String groupName;
		private Set<ChatUser> user=new HashSet<>();
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
		public Set<ChatUser> getUser() {
			return user;
		}
		public void setUser(Set<ChatUser> user) {
			this.user = user;
		}
		
		
}
