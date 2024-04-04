package com.miniproject.producerconsumer;

import com.miniproject.dto.MessageDTO;
import com.miniproject.repositoryimpl.MessageRepositoryImpl;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<MessageDTO> queue;
    MessageRepositoryImpl messageRepository = new MessageRepositoryImpl();

    public Consumer(BlockingQueue<MessageDTO> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while(true) {
                messageRepository.saveMessage(queue.poll());
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
