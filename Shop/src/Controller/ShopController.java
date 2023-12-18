package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Utils.InputManager;
import dao.ItemDAO;
import dao.UserDAO;
import vo.User;

public class ShopController {
	Scanner sc = new Scanner(System.in);
	UserDAO userDAO = UserDAO.getInstance();
	ItemDAO itemDAO = ItemDAO.getInstance();
	InputManager inputManager = InputManager.getInstance();
	String log;
//	private ShopController() {
//		//sc= new Scanner(System.in);
//		//userDAO.getInstance();
//	}
//	public static ShopController instance = new ShopController();
//	public static ShopController getInstance() {
//		return instance;
//	}
	// 	System.out.println("[1.가입] [2.탈퇴] [3.로그인] [4.로그아웃]" + "\n[100.관리자] [0.종료] ");  

	// 	System.out.println("[1.쇼핑] [2.장바구니목록] [0.뒤로가기]");

	// 	System.out.println("[1.내 장바구니] [2.삭제] [3.구입] [0.뒤로가기]");
// 	System.out.println("[1.아이템관리] [2.카테고리관리] [3.장바구니관리] [4.유저관리] [0.뒤로가기] ");

	public void run() {
		printMenu();
	}
	
	public int getInt(String msg, int start, int end) {
		while(true) {
			System.out.printf("%s 를 입력하세요 >> %n", msg);
			try {
				int sel = sc.nextInt();
				sc.nextLine();
				if (sel < start || sel > end) {
					System.out.println("범위 오류");
					continue;
				}
				return sel;
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("숫자만 입력하세요");
			}
		} //while
	}

	public String getStr(String msg) {
		System.out.printf("%s 를 입력하세요 >> %n" , msg);
		String input = sc.next();
		return input;
	}


	private void printMenu() {
		while(true) {
			if(log != null) System.out.println("["+log+"]"+" 님 로그인 중");
			System.out.println("[1.가입] [2.탈퇴] [3.로그인] [4.로그아웃]" + "\n[100.관리자] [0.종료] ");
			System.out.print("메뉴 선택 >> ");
			int sel = sc.nextInt();
			if(sel == 100) {
				// 관리자 모드
				inputManager.printManager(itemDAO);
			} else if (sel < 0 || sel > 4) {
				System.out.println("범위 오류");
				continue;
			}
			else if(sel == 1) {
				userDAO.plusUser();
			} else if(sel == 2) {
				userDAO.deleteUser(itemDAO);
			} else if (sel == 3) {
				log = userDAO.logIn();
				if(log == null) {
					continue;
				}
				while(true) {
				System.out.println("["+log+"]" +" 님 로그인 중");
				System.out.println("[1.쇼핑] [2.장바구니목록] [0.뒤로가기]");
				int sel3 = getInt("메뉴", 0, 2);
				if(sel3 == 1) {
					// 쇼핑
					itemDAO.shopping(log);
				} else if(sel3 == 2){
					// 장바구니 목록
					inputManager.cartMenu(itemDAO, log);
				} else {
					System.out.println("[ 뒤로 ]");
					break;
				}
				} //while
			} else if(sel == 4) {
				if(log == null) {
					System.out.println("현재 로그아웃 중입니다.");
					continue;
				}
				System.out.println("로그아웃 합니다.");
				log = null;
			}
			else { 
				System.out.println("[종료합니다]");
				return;
			}
		} //while
	}

}
