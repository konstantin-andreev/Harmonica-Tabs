package org.harmonicatabs.service;

import org.harmonicatabs.model.dtos.SentMessageDTO;

public interface MessageService {
    boolean addMessage(SentMessageDTO sentMessageDTO);
}
