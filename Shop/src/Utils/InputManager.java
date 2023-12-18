package Utils;

import Controller.ShopController;
import dao.ItemDAO;
import dao.UserDAO;

public class InputManager {
	ItemDAO itemDAO = ItemDAO.getInstance();
	ShopController shop = new ShopController();
	UserDAO userDAO = UserDAO.getInstance();
	//FileManager fileManager = FileManager.getInstance();
	public InputManager(){
		
	}
	private static InputManager instance = new InputManager();
	public static InputManager getInstance() {
		return instance;
	}
	
//	public void newCategory(ItemDAO itemDAO) {
//		itemDAO.plusCategory();
//	}
	
	public void printManager(ItemDAO itemDAO , UserDAO userDAO) {
		while(true) {
			System.out.println("[1.아이템관리] [2.카테고리관리] [3.장바구니관리] [4.유저관리] [0.뒤로가기] ");
			int sel = shop.getInt("메뉴", 0, 4);
			if (sel == 1) {
				itemMenu(itemDAO);
			} else if (sel == 2) {

			} else if (sel == 3) {
				cartMenu(itemDAO);
			} else if(sel == 4) {
				userMenu(userDAO);
			} else {
				System.out.println(" [ 뒤로 ] ");
				return;
			}
		}//while
	}
	
	// 유저 관리
	private void userMenu(UserDAO userDAO) {
		while(true) {
			System.out.println("[1] 전체 유저 목록 [2] 유저 파일 저장 [0] 뒤로가기");
			int sel = shop.getInt("메뉴", 0, 2);
			if (sel == 1) {
				userDAO.printUser();
			} else if (sel == 2) {
				FileManager.getInstance().saveUserFile(userDAO);
			} else {
				System.out.println(" [ 뒤로 ]");
				return;
			}
		} //while
	}
	
	
	//장바구니 관리
	private void cartMenu(ItemDAO itemDAO) {
		while(true) {
			System.out.println("[1] 전체 장바구니 목록 [2] 장바구니 파일 저장 [0] 뒤로가기");
			int sel = shop.getInt("메뉴", 0, 2);
			if (sel == 1) {
				itemDAO.printCart();
			} else if (sel == 2) {
				FileManager.getInstance().saveCartFile(itemDAO);
			} else {
				System.out.println(" [ 뒤로 ]");
				return;
			}
		} //while
	}
	
	
	private void itemMenu(ItemDAO itemDAO) {
		while(true) {
			System.out.println("[1] 아이템 추가 [2] 아이템 삭제 [3] 아이템 저장하기 [4] 전체 아이템 목록 [0] 뒤로가기");
			int sel = shop.getInt("메뉴", 0, 4);
			if (sel == 1) {
				itemDAO.plusCategory();
			} else if (sel == 2) {
				itemDAO.deleteItem();
			} else if(sel == 3) {
				FileManager.getInstance().saveItemFile(itemDAO);
			} else if(sel == 4) {
				itemDAO.printItem();
			}
			else {
				System.out.println(" [ 뒤로 ]");
				return;
			}
		}//while
	}
	
//	public void removeItem(ItemDAO itemDAO) {
//		itemDAO.deleteItem();
//	}
	
	public void printCartList(ItemDAO itemDAO) {
		itemDAO.printCart();
	}
	
	//장바구니 목록
	public void cartMenu(ItemDAO itemDAO , String log) {
		while(true) {
			System.out.println("[1.내 장바구니] [2.삭제] [3.구입] [0.뒤로가기]");
			int sel = shop.getInt("메뉴", 0, 3);
			if (sel == 1) {
				printMyCart(itemDAO, log);
			} else if (sel == 2) {
				itemDAO.removeMyCart(log);
			} else if (sel == 3) {

			} else {
				System.out.println(" [ 뒤로 ]");
				return;
			}
		} //while
	}
	
	private void printMyCart(ItemDAO itemDAO , String log) {
		itemDAO.printCart(log);
	}
	
	
}
