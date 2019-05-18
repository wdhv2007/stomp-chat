package com.damdev.stompchat;

import lombok.Getter;
import lombok.Setter;

/**
 * Author : zenic
 * Created : 2019-05-18
 */
@Getter
@Setter
public class ChatMessage {

  private MessageType type;
  private String content;
  private String sender;

  public enum MessageType {
    CHAT,
    JOIN,
    LEAVE
  }
}
