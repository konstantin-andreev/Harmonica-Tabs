package org.harmonicatabs.service.impl;

import org.harmonicatabs.model.dtos.SentMessageDTO;
import org.harmonicatabs.model.entity.Message;
import org.harmonicatabs.model.entity.UserEntity;
import org.harmonicatabs.repository.MessageRepository;
import org.harmonicatabs.repository.UserRepository;
import org.harmonicatabs.service.MessageService;
import org.harmonicatabs.service.session.UserHelperService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private final UserRepository userRepository;
    private final UserHelperService userHelperService;
    private final MessageRepository messageRepository;

    public MessageServiceImpl(UserRepository userRepository, UserHelperService userHelperService, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.userHelperService = userHelperService;
        this.messageRepository = messageRepository;
    }

    @Override
    public boolean addMessage(SentMessageDTO sentMessageDTO) {

        if(sentMessageDTO.getReceiverUsername().isBlank()) return false;
        Optional<UserEntity> optional = this.userRepository.findByUsername(sentMessageDTO.getReceiverUsername());
        if(optional.isEmpty()) return false;
        UserEntity receiver = optional.get();
        UserEntity sender = this.userHelperService.getUser();

        Message message = new Message();
        message.setContent(sentMessageDTO.getContent());
        message.setReceiver(receiver);
        message.setSender(sender);

        receiver.getReceivedMessages().add(message);
        sender.getSentMessages().add(message);

        this.messageRepository.saveAndFlush(message);

        return true;
    }
}
