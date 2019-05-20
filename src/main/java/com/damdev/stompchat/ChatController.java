package com.damdev.stompchat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Author : zenic
 * Created : 2019-05-18
 */
@Slf4j
@Controller
public class ChatController {

  @MessageMapping("/chat.sendMessage/{room}")
  @SendTo("/topic/public/{room}")
  public ChatMessage sendMessage(
          @Payload ChatMessage chatMessage,
          SimpMessageHeaderAccessor headerAccessor,
          @DestinationVariable("room") String room) {
    log.info(headerAccessor + "");
    return chatMessage;
  }

  @MessageMapping("/chat.addUser/{room}")
  @SendTo("/topic/public/{room}")
  public ChatMessage addUser(
          @Payload ChatMessage chatMessage,
          SimpMessageHeaderAccessor headerAccessor,
          @DestinationVariable("room") String room) {
    // Add username in web socket session
    headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
    headerAccessor.getSessionAttributes().put("room", room);
    return chatMessage;
  }

  @GetMapping("/")
  public String index() {
    return "index";
  }
}
