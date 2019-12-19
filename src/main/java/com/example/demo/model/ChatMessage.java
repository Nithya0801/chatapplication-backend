package com.example.demo.model;
import java.util.UUID;
public class ChatMessage {
	private MessageType type;
    private String content;
    private String sender;
    private String toName;
    private String channelId;
    
//    public ChatMessage() {
//		this.channelId=UUID.randomUUID().toString();
//	}
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
    
    public String getContent() {
        return content;
    }
    
    public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
    
}
