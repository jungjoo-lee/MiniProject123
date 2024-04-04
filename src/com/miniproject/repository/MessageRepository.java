package com.miniproject.repository;

import com.miniproject.dto.MessageDTO;

public interface MessageRepository {
    void saveMessage(MessageDTO messageDTO);
    void loadMessage();
}
