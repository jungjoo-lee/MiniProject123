package com.miniproject.menu;

import java.util.Date;
import java.util.Scanner;

import com.miniproject.dto.MemberDTO;
import com.miniproject.repositoryimpl.MemberRepositoryImpl;

public class Menu {
	static MenuMode menuMode;
	MemberRepositoryImpl memberRepositoryImpl = new MemberRepositoryImpl();
	boolean run;
	Scanner sc;
	
	public Menu() {
		menuMode = MenuMode.NOT_LOGIN_MENU;
	}
	
	public void mainMenu() {
		run = false;

		while (false == run) {
			menuDisplay();

			System.out.print("메뉴 선택 => ");
			sc = new Scanner(System.in);
			String menuNum = sc.nextLine();

			switch (menuMode) {
			case NOT_LOGIN_MENU -> { // 초기 메뉴
				switch (menuNum) {
				case "1" -> System.out.println("로그인 메뉴");
				case "2" -> registerMemberMenu(sc); // 회원가입 메뉴
				case "3" -> System.out.println("비밀번호찾기 메뉴");
				case "4" -> selectAllMemberMenu();
				case "Q", "q" -> System.out.println("종료 메뉴");
				}
			}
			case LOGIN_MENU -> { // 로그인후 메뉴
				switch (menuNum) {
				case "1" -> menuMode = MenuMode.NOT_LOGIN_MENU; // 로그아웃 메뉴
				case "2" -> System.out.println("회원정보 변경 메뉴");
				case "3" -> System.out.println("회원탈퇴 메뉴");
				case "Q", "q" -> System.out.println("종료 메뉴");
				}
			}
			}
		}
	}
	
	private void menuDisplay() {
		switch (menuMode) {
		case NOT_LOGIN_MENU:
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 비밀번호검색");
			System.out.println("4. 테스트");
			System.out.println("q. 프로그램 종료");
			break;
		case LOGIN_MENU:
			System.out.println("1. 로그아웃");
			System.out.println("2. 회원정보수정");
			System.out.println("3. 채팅");
			System.out.println("4. 회원탈퇴");
			System.out.println("q. 종료");
			break;
		default:
			break;
		}
	}
	
	private void registerMemberMenu(Scanner sc) {
		String uid;
		String pwd;
		String name;
		int age;
		String gender;

		System.out.println("\n2. 회원가입");
		System.out.print("아이디 : ");
		uid = sc.nextLine();
		System.out.print("비밀번호 : ");
		pwd = sc.nextLine();
		System.out.print("이름 : ");
		name = sc.nextLine();
		System.out.print("나이 : ");
		age = sc.nextInt();
		System.out.print("성별(M/F) : ");
		gender = sc.nextLine();
		
		MemberDTO memberDTO = new MemberDTO(uid, pwd, name, age, gender, new Date());
		memberRepositoryImpl.insertMember(memberDTO);
		memberRepositoryImpl.insertMember(new MemberDTO(uid, pwd, name, age, gender, new Date())); // 위아래 아무거나
	}
	
	private void selectAllMemberMenu() {
		memberRepositoryImpl.selectAllMember();
	}
}
