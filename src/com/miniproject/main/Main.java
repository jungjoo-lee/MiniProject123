package com.miniproject.main;

import com.miniproject.env.EnvProperties;
import com.miniproject.menu.Menu;
import com.miniproject.watchservice.ProjectWatchService;

public class Main {
    public static void main(String[] args) {
        try {
            Thread watchThread = new Thread(new ProjectWatchService(EnvProperties.getWatchServicePath()));
            watchThread.setDaemon(true);
            watchThread.start();

            new Menu().mainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
