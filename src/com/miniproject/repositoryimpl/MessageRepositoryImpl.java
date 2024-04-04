package com.miniproject.repositoryimpl;

import com.miniproject.dto.MessageDTO;
import com.miniproject.repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;

public class MessageRepositoryImpl implements MessageRepository {
    List<MessageDTO> messageList = new ArrayList<>();

    public MessageRepositoryImpl() {

    }

    @Override
    public void saveMessage(MessageDTO messageDTO) {

    }

    @Override
    public void loadMessage() {

    }
}
