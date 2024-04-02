package com.miniproject.env;

import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Properties;

public class EnvProperties {
	static Properties prop = new Properties(); //  프로퍼티 생성
//	static Path envFilePath = Paths.get("env.properties");
	
	static {
		try {
			prop.load(EnvProperties.class.getResourceAsStream("env.properties")); //  EnvProperties 클래스파일이 있는 폴더에서 env.properties 파일 load
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	private static void loadProperties() throws IOException {
//        prop.load(EnvProperties.class.getResourceAsStream("env.properties"));
//    }
//	
//	private static void watchFileChanges() throws IOException {
//        WatchService watchService = FileSystems.getDefault().newWatchService();
//        envFilePath.getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
//
//        Thread watchThread = new Thread(() -> {
//            try {
//                while (true) {
//                    WatchKey key = watchService.take();
//                    for (WatchEvent<?> event : key.pollEvents()) {
//                        WatchEvent.Kind<?> kind = event.kind();
//                        if (kind == StandardWatchEventKinds.OVERFLOW) {
//                            continue;
//                        }
//                        if (event.context().toString().equals(envFilePath.getFileName().toString())) {
//                            loadProperties(); // 파일이 수정되면 프로퍼티 파일을 다시 로드합니다.
//                            System.out.println("env.properties 파일이 변경되었습니다. 프로퍼티를 다시 로드합니다.");
//                        }
//                    }
//                    boolean valid = key.reset();
//                    if (!valid) {
//                        break;
//                    }
//                }
//            } catch (InterruptedException | ClosedWatchServiceException | IOException e) {
//                e.printStackTrace();
//            }
//        });
//        watchThread.setDaemon(true);
//        watchThread.start();
//    }
	
	// DB정보
	public static String getDriverClass() {
		return prop.getProperty("DriverClass");
	}
	
	public static String getDataBaseConn() {
		return prop.getProperty("DataBaseConn");
	}
	
	public static String getName() {
		return prop.getProperty("Name");
	}
	
	public static String getPwd() {
		return prop.getProperty("Pwd");
	}
	
	// 쿼리
	public static String getSelectAllMember() {
		return prop.getProperty("SelectAllMember");
	}
	
	public static String getInsertMember() {
		return prop.getProperty("InsertMember");
	}
}
