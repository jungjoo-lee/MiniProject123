package com.miniproject.producerconsumer;

import com.miniproject.dto.MessageDTO;
import lombok.Setter;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

@Setter
public class Producer implements Runnable {
    private final BlockingQueue<MessageDTO> queue;
    private String message;

    public Producer(BlockingQueue<MessageDTO> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                MessageDTO messageDTO = MessageDTO.builder().message("").sendTime(new Date()).build();
                queue.put(messageDTO);
            }

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
