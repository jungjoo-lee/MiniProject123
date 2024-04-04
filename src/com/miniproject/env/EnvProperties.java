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

	static {
		try {
			prop.load(EnvProperties.class.getResourceAsStream("env.properties")); //  EnvProperties 클래스파일이 있는 폴더에서 env.properties 파일 load
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("properties 파일 없습니다.");
		}
	}

	public static void loadProperties() throws IOException {
		try {
			prop.load(EnvProperties.class.getResourceAsStream("env.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("properties 파일 없습니다.");
		}
    }

	// WatchServicePath
	public static String getWatchServicePath() {
		return prop.getProperty("WatchService");
	}
	
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
