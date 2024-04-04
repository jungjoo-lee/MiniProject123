package com.miniproject.main;

import com.miniproject.dto.MessageDTO;
import com.miniproject.env.EnvProperties;
import com.miniproject.menu.Menu;
import com.miniproject.producerconsumer.Consumer;
import com.miniproject.producerconsumer.Producer;
import com.miniproject.watchservice.ProjectWatchService;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    private static final int QUEUE_CAPACITY = 10; // 큐의 최대 크기

    public static void main(String[] args) {
        BlockingQueue<MessageDTO> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

        try {
            // 왓치서비스 스레드 생성 및 시작
            Thread watchThread = new Thread(new ProjectWatchService(EnvProperties.getWatchServicePath()));
            watchThread.setDaemon(true);
            watchThread.start();

            // 생산자 스레드 생성 및 시작
            Thread producerThread = new Thread(new Producer(queue));
            producerThread.start();

            // 소비자 스레드 생성 및 시작
            Thread consumerThread = new Thread(new Consumer(queue));
            consumerThread.start();

            new Menu().mainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
