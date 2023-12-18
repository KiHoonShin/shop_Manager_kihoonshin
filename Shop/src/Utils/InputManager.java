package Utils;

import Controller.ShopController;
import dao.ItemDAO;
import dao.UserDAO;

public class InputManager {
	ItemDAO itemDAO = ItemDAO.getInstance();
	ShopController shop = new ShopController();
	UserDAO userDAO = UserDAO.getInstance();
	public InputManager(){
		
	}
	public static InputManager instance = new InputManager();
	public static InputManager getInstance() {
		return instance;
	}
	
//	public void newCategory(ItemDAO itemDAO) {
//		itemDAO.plusCategory();
//	}
	
	public void printManager(ItemDAO itemDAO) {
		while(true) {
			System.out.println("[1.아이템관리] [2.카테고리관리] [3.장바구니관리] [4.유저관리] [0.뒤로가기] ");
			int sel = shop.getInt("메뉴", 0, 4);
			if (sel == 1) {
				itemMenu(itemDAO);

			} else if (sel == 2) {

			} else if (sel == 3) {
				itemDAO.printCart();

			} else if(sel == 4) {
				
			} else {
				System.out.println(" [ 뒤로 ] ");
				return;
			}
		}//while
	}
	
	private void itemMenu(ItemDAO itemDAO) {
		while(true) {
			System.out.println("[1] 아이템 추가 [2] 아이템 삭제 [0] 뒤로가기");
			int sel = shop.getInt("메뉴", 0, 2);
			if (sel == 1) {
				itemDAO.plusCategory();
			} else if (sel == 2) {
				itemDAO.deleteItem();
			} else {
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
