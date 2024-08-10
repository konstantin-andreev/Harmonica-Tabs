package org.harmonicatabs.controller;

import org.harmonicatabs.model.dtos.SentMessageDTO;
import org.harmonicatabs.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/user")
    public ResponseEntity<String> message(@RequestBody SentMessageDTO sentMessageDTO){

        boolean success = this.messageService.addMessage(sentMessageDTO);

        return ResponseEntity.ok("Message sent!");
    }
}
