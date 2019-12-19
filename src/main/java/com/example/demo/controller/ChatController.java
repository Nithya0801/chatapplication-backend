package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ChatDetails;
import com.example.demo.model.ChatMessage;
import com.example.demo.model.ChatUser;
import com.example.demo.model.GroupChat;
import com.example.demo.model.GroupUser;
//import com.example.demo.model.GroupChatDetails;
import com.example.demo.repository.ChatUserRepository;
import com.example.demo.repository.GroupChatRepository;
import com.example.demo.repository.GroupUserRepository;
import com.example.demo.requestDto.ChatDetailsRequestDto;
import com.example.demo.requestDto.ChatUserRequestDto;
import com.example.demo.requestDto.GroupChatRequestDto;
import com.example.demo.requestDto.GroupUserRequestDto;
import com.example.demo.responseDto.GroupChatResponseDto;
import com.example.demo.responseDto.GroupUserResponseDto;
//import com.example.demo.requestDto.GroupChatRequestDto;
//import com.example.demo.requestDto.GroupChatUserRequestDto;
import com.example.demo.service.IChatDetails;
import com.example.demo.service.IChatUser;
import com.example.demo.service.IGroupChat;
import com.example.demo.service.IGroupUser;
//import com.example.demo.service.IGroupChatDetails;
import com.example.demo.model.ChatMessage.MessageType;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Controller
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ChatController {

	 static ChatMessage one=new ChatMessage();
	 @Autowired
	 private 	SimpMessagingTemplate simpMessagingTemplate;
	 @Autowired
	 IChatUser userDetails;
	 
	 @Autowired
	 IGroupUser groupDetails;
	 
	 @Autowired
	 GroupChatRepository groupChatRepo;
	 
	 @Autowired
	 IGroupChat groupChat;
	 
	 @Autowired
	 GroupUserRepository groupRepo;
	 
	 @Autowired
	 ChatUserRepository userRepo;
	 
	 @Autowired
	 IChatDetails chatDb;
//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
//    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
//    	System.out.println("------------------------------------"+chatMessage.getContent());
//        return chatMessage;
//    }

    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/{sender}")  
//     @SendTo("/topic/public")  
//    public ChatMessage addUser(@Payload ChatMessage chatMessage, 
//                               SimpMessageHeaderAccessor headerAccessor) {
    public ResponseEntity<ChatUser> addUser(@Payload ChatUserRequestDto chatUser, 
                SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
    	ChatUser existingUser=userDetails.getByUsername(chatUser.getUserName());
    	if(existingUser == null) {
    	ChatUser user=userDetails.createUser(chatUser);
        headerAccessor.getSessionAttributes().put("userId", user.getUserId());
               
        System.out.println("--------Second one----------------------------"+user.getUserId()+user.getUserName()+user.getStatus());
        simpMessagingTemplate.convertAndSend("/topic/public", user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    	}
    	else {
    		 headerAccessor.getSessionAttributes().put("userId", existingUser.getUserId());
    		existingUser.setStatus("online");
    		userRepo.saveAndFlush(existingUser);
    		 simpMessagingTemplate.convertAndSend("/topic/public", existingUser);
    		 return ResponseEntity.status(HttpStatus.OK).body(existingUser);
    	}
    }
//    
    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/{sender}")
//    public ChatMessage sendPrivateMessage(@Payload ChatMessage chatMessage) {
    public ResponseEntity<ChatDetails> sendPrivateMessage(@Payload ChatDetailsRequestDto chatMessage) {
    	System.out.println("................"+chatMessage.getFromUserId()+" "+chatMessage.getMessage()+" "+chatMessage.getToUserId());
    	if(chatMessage.getToUserId() != "") {
    		System.out.println("................");
    		ChatDetails chat=chatDb.createChat(chatMessage);
    		System.out.println(".....bbbb...........");
//    	simpMessagingTemplate.convertAndSend("/topic/"+chat.getToUserId(),chat);
//    	System.out.println(".....cccc...........");
//    	simpMessagingTemplate.convertAndSend("/topic/"+chat.getFromUserId(), chat);
    	simpMessagingTemplate.convertAndSend("/queue/"+chat.getToUserId(), chat);
    	simpMessagingTemplate.convertAndSend("/queue/"+chat.getFromUserId(), chat);
//    	simpMessagingTemplate.convertAndSendToUser(chat.getToUserId(), "/queue", chat);
    	return ResponseEntity.status(HttpStatus.OK).body(chat);
    	}
    	else {
    		ChatDetails chat=chatDb.createChat(chatMessage);
//    		simpMessagingTemplate.convertAndSend("/topic/public", chat);
    		simpMessagingTemplate.convertAndSend("/topic/public", chat);
    		return ResponseEntity.status(HttpStatus.OK).body(chat);
    	}
//     return ResponseEntity.status(HttpStatus.OK).body(chat);;
    }
//
//    @MessageMapping("/send/message")
//    public Map<String, String> useSocketCommunication(@Payload ChatMessage chatMessage,String message){
//    	System.out.println("..called");
//        ObjectMapper mapper = new ObjectMapper();
//        Map<String, String> messageConverted = null;
//        
//        try {
//            messageConverted = mapper.readValue(message, Map.class);
//            System.out.println("../////"+messageConverted.get("sender"));
//            one.setSender(messageConverted.get("sender"));
//            one.setContent(messageConverted.get("content"));
//            one.setChannelId(messageConverted.get("channelId"));
//            one.setToName(messageConverted.get("toName"));
//            String type=messageConverted.get("type");
//            one.setType(MessageType.valueOf(type));
//        } catch (IOException e) {
//            messageConverted = null;
//        }
//        if(messageConverted!=null){
//        	 System.out.println("../////"+messageConverted.containsKey("toName")+" "+messageConverted.get("toName") );
//            if(messageConverted.containsKey("toName") && messageConverted.get("toName")!=null && !messageConverted.get("toName").equals("")){
//                simpMessagingTemplate.convertAndSend("/topic/"+messageConverted.get("toName"),messageConverted);
//                simpMessagingTemplate.convertAndSend("/topic/"+messageConverted.get("sender"),message);
//            }else{
//                simpMessagingTemplate.convertAndSend("/topic/public",one);
//            }
//        }
//        return messageConverted;
//    }
    
    
    
    @MessageMapping("/chat.groupUser")
    public GroupUser createGroup(@Payload GroupUserRequestDto groupUser,SimpMessageHeaderAccessor headerAccessor) {
    	System.out.println(":::::::::::::::::::"+groupUser.getGroupName());
    	headerAccessor.getSessionAttributes().put("groupName",groupUser.getGroupName());
    	
    	GroupUser gChat=groupDetails.createGroup(groupUser);
//    	GroupUser user=	groupRepo.findById(gchat).get();
		 GroupUserResponseDto rdto=new GroupUserResponseDto();
		rdto.setGroupId(gChat.getGroupId());
		rdto.setGroupName(gChat.getGroupName());
		rdto.getUser().addAll(gChat.getGroupMember());
    	 simpMessagingTemplate.convertAndSend("/topic/"+gChat.getGroupId(), rdto);
    	 
    	return gChat;
    }
//    
    @MessageMapping("/chat.sendGroupMessage")
    public ResponseEntity<?> sendGroupMessage(@Payload GroupChatRequestDto chatMessage) {
    	
    	GroupChat chat=groupChat.createChat(chatMessage);
    	GroupUser user=groupRepo.findById(chat.getGroupId()).get();
    	GroupChatResponseDto dto=new GroupChatResponseDto();
    	dto.setGroupId(chat.getGroupId());
    	dto.setMessage(chat.getMessage());
    	dto.setSender(chat.getSenderName());
    	dto.setSenderId(chat.getSenderId());
    	dto.setUsers(user.getGroupMember());
    	System.out.println("??????????????????????????"+user.getGroupMember());
//    	dto.getUsers().addAll(user.getGroupMember());
    	for(ChatUser cu:user.getGroupMember()) {
    		dto.getUsers().add(cu);
    	}
    	if(dto.getUsers().size()!=0)
    	simpMessagingTemplate.convertAndSend("/topic/"+chat.getGroupId(), dto);
    	return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    
    
    @GetMapping("/list/onlineUser")
    public ResponseEntity<List<ChatUser>> getOnlineUsers(){
		return ResponseEntity.status(HttpStatus.OK).body(userDetails.getOnlineUser());
    	
    }
    
    @GetMapping("/getStatus/{name}")
    public ResponseEntity<ChatUser> getOnlineUsers(@PathVariable String name){
    	ChatUser user=userDetails.getByUsername(name);
    	if(user != null)
		return ResponseEntity.status(HttpStatus.OK).body(userDetails.getByUsername(name));
    	else
    		return ResponseEntity.status(HttpStatus.OK).body(null);	
    	
    }
    
    @GetMapping("/getChat")
    public ResponseEntity<?> getChat(){
    	return ResponseEntity.status(HttpStatus.OK).body(chatDb.getPublicChat());
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
    	return ResponseEntity.status(HttpStatus.OK).body(userDetails.getAllUser());
    }
    
    
    @GetMapping("/getPrivateChat/{fromId}/{toId}")
    public ResponseEntity<?> getPrivateChat(@PathVariable String fromId,@PathVariable String toId){
    	return ResponseEntity.status(HttpStatus.OK).body(chatDb.getPrivateChat(fromId, toId));
    }
    
    @GetMapping("/listGroup/{userId}")
    public ResponseEntity<?> listGroup(@PathVariable String userId){
    	List<String> groupIds=groupRepo.groups(userId);
    	List<GroupUser> groupUsers=new ArrayList<GroupUser>();
    	List<GroupUserResponseDto> dto=new ArrayList<GroupUserResponseDto>();
    	for(String gId:groupIds) {
    	GroupUser user=	groupRepo.findById(gId).get();
    	 GroupUserResponseDto rdto=new GroupUserResponseDto();
    	rdto.setGroupId(user.getGroupId());
    	rdto.setGroupName(user.getGroupName());
    	rdto.getUser().addAll(user.getGroupMember());
    	dto.add(rdto);
    		groupUsers.add(user);
    	
    	}
    	return ResponseEntity.status(HttpStatus.OK).body(dto);
//    	for(GroupUser gu:list) {
//    		for(ChatUser cu : gu) {
//    			
//    		}
//    	}
    }
    
    @GetMapping("/listChat/{groupId}")
    public ResponseEntity<?> listChat(@PathVariable String groupId){
    	List<GroupChat> chats=groupChatRepo.getChat(groupId);
    	List<GroupChatResponseDto> groupDto=new ArrayList<GroupChatResponseDto>();
    	for(GroupChat chat:chats) {
    	GroupChatResponseDto dto=new GroupChatResponseDto();
    	dto.setGroupId(chat.getGroupId());
    	dto.setMessage(chat.getMessage());
    	dto.setSender(chat.getSenderName());
    	dto.setSenderId(chat.getSenderId());
    	groupDto.add(dto);
    	
    	}
    	return ResponseEntity.status(HttpStatus.OK).body(groupDto);
    }
}
