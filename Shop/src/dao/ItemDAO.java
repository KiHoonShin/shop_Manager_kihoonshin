package dao;

import java.util.ArrayList;

import Controller.ShopController;
import Utils.FileManager;
import vo.Cart;
import vo.Item;
import vo.User;

public class ItemDAO {
	ArrayList<Item> itemList;
	ShopController shop = new ShopController();
	FileManager fileManager = FileManager.getInstance();
	ArrayList<Cart> cartList = new ArrayList<Cart>();
	int cnt;
	
	public ItemDAO() {
		itemList = new ArrayList<Item>();
		roadItemFile();
		roadCartFile();
	}
	
	public static ItemDAO instance = new ItemDAO();
	public static ItemDAO getInstance() {
		return instance;
	}
	
	// 아이템 파일 불러오기
	private void roadItemFile() {
		String data = fileManager.itemDataRoad();
		String[] temp = data.split("\n");
		for(int i = 0; i < temp.length; i++) {
			String[] info = temp[i].split("/");
			itemList.add(new Item(info[0], info[1], Integer.parseInt(info[2])));
		}
		cnt = itemList.size();
	}
	
	// 장바구니 파일 불러오기
	private void roadCartFile() {
		int cnt = 0;
		String data = fileManager.cartDataRoad();
		String[] temp = data.split("\n");
		cnt = temp.length;
		if(cnt == 0) return;
		for(int i = 0; i < temp.length; i++) {
			String[] info = temp[i].split("/");
			cartList.add(new Cart(info[0], info[1]));
		}
	}
	
	
	public void plusCategory() {
		System.out.println("[ 카테고리 추가 ]");
		String category = shop.getStr("추가할 카테고리");
//		Item ctCheck = CategoryCheck(category);
//		if(ctCheck != null) {
//			System.out.println("이미 등록된 카테고리입니다.");
//			return;
//		}
		String name = shop.getStr("추가할 품목");
		Item nameCheck = itemCheck(name);
		if(nameCheck != null) {
			System.out.println("이미 등록된 품목입니다.");
			return;
		}
		int price = shop.getInt("가격", 1000, 100000);
		itemList.add(new Item(category, name, price));
		System.out.println(itemList.get(cnt));
		System.out.println(" [ 품목 및 카테고리 추가 완료 ]");
		System.out.println();
		cnt +=1;
	}
	
//	// 카테고리 중복 체크
//	private Item CategoryCheck(String category) {
//		if(itemList == null) return null;
//		for(Item it : itemList) {
//			if(it.getCategory().equals(category)) {
//				return it;
//			}
//		}
//		return null;
//	}
	
	// 품목 중복 체크
	private Item itemCheck(String name) {
		if(itemList == null) return null;
		for(Item it : itemList) {
			if(it.getName().equals(name)) {
				return it;
			}
		}
		return null;
	}
	
	public void shopping(String log) {
		while(true) {
			printItem(log);
			int num = shop.getInt("아이템 번호 (0.뒤로) ", 0, cnt) - 1;
			if(num == -1) {
				System.out.println(" [ 뒤로 ] ");
				return;
			}
			//int cnt = 0;
			cartList.add(new Cart(log, itemList.get(num).getName()));
			System.out.print(itemList.get(num).getName());
			System.out.print(" [장바구니 추가 완료] \n");
			//cnt += 1;
		} //while
	}
	
//	private int cartIdx() {
//		for(int i = 0; i < cartList.size(); i++) {
//			if(cartList.get(i))
//		}
//	}
	
	
	private void printItem(String log) {
		System.out.printf("[ %s 님 쇼핑 ] %n" , log);
		int idx = 1;
		for(Item it : itemList) {
			System.out.print("("+(idx++)+") ");
			System.out.print(it);
			System.out.println();
		}
	}
	
	public void deleteItem() {
		if(cnt == 0) {
			System.out.println("품목이 비었습니다.");
			return;
		}
		String item = shop.getStr("삭제할 품목");
		Item name = itemCheck(item);
		if(name == null) {
			System.out.println("삭제할 품목이 없습니다.");
			return;
		}
		int delIdx = findIdx(item);
		for(int i = 0; i < itemList.size(); i++) {
			if(delIdx ==i) {
				itemList.remove(delIdx);
			}
		}
		System.out.println(item + " 품목 삭제 완료");
		cnt -=1;
		//int sel = shop.getInt("삭제할 아이템 번호", 1, cnt) -1;
	}
	
	// 인덱스 찾기
	private int findIdx(String item) {
		if(itemList == null) return -1;
		for(int i = 0; i < cnt; i++) {
			if(itemList.get(i).getName().equals(item)) {
				return i;
			}
		}
		return -1;
	}
	
	// 전체 장바구니 품목 출력
	public void printCart() {
		int idx = 1;
		for(Cart c : cartList) {
			System.out.printf("%d ) ",idx++);
			System.out.print(c);
			System.out.println();
			System.out.println("---------------------");
		}
	}
	// 전체 아이템 품목 출력
	public void printItem() {
		int idx = 1;
		for(Item i : itemList) {
			System.out.printf("%d ) ",idx++);
			System.out.print(i);
			System.out.println();
			System.out.println("---------------------");
		}
	}
	
	// id별 장바구니 품목 출력
	public void printCart(String log) {
		int[] itemCnt = new int[itemList.size()];
		int total = 0;
		int count = 0; // 총 개수
		for(int i = 0; i < cartList.size(); i++) {
			int cnt = 0;
			int price = 0;
			if(cartList.get(i).getUserId().equals(log)) { //  [] 님의 장바구니 내용
				
				for(int k = 0; k < itemList.size(); k ++) {
					if(cartList.get(i).getItemName().equals(itemList.get(k).getName())) {
						itemCnt[k] +=1 ;
						cnt +=1;
						price = itemList.get(k).getPrice();
						total += itemList.get(k).getPrice();
						count +=1;
					}
				} // k
			}
		} //i
				int idx = 1;
				System.out.printf("===== %s 님의 장바구니 내용 =====%n" , log);
				for(int i = 0; i < itemCnt.length; i++) {
					if(itemCnt[i] > 0) {
						System.out.println((idx++)+")   " +itemList.get(i).getName() +"\t" + itemList.get(i).getPrice()+"원" + "\t" + itemCnt[i]+"개");
					}
				}
				System.out.println("============================");
				System.out.printf("총 개수 : %d개    총 금액 : %d원 %n" , count , total);
				System.out.println("============================");
//		for(Cart c : cartList) {
//			if(c.getUserId().equals(log)) {
//				System.out.println(c);
//			}
//		}
	}
	
	// 장바구니 삭제
	public void removeMyCart(String log) {
		if(!isCart(log)) return;
		String item = shop.getStr("삭제할 품목");
		int delIdx = removeIdx(item , log);
		for(int i = 0; i < cartList.size(); i++) {
			if(i == delIdx) {
				cartList.remove(delIdx);
			}
		}
		System.out.println(item + " 품목 1개 삭제 완료");
	}
	
	
	// 카트리스트에서 장바구니가 있는지 찾기
	private boolean isCart(String log) {
		if(cartList == null) return false;
		for(Cart c : cartList) {
			if(c.getUserId().equals(log)) {
				return true;
			}
		}
		return false;
	}
	
	// 카트리스트에서 item idx 찾기
	private int removeIdx(String item , String log) {
		if (cartList == null)
			return -1;
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getUserId().equals(log)) {
				for (int k = 0; k < cartList.size(); k++) {
					if (cartList.get(k).getItemName().equals(item)) {
						return k;
					}
				} // k
			}
		} // i
		return -1;
	}
	
	// 카트리스트에서 탈퇴한 id 지우기
	public void deleteUserInCart(String id) {
		for(int i = 0; i < cartList.size(); i++) {
			if(cartList.get(i).getUserId().equals(id)) {
				cartList.remove(i);
			}
		}
	}
	
	// 카트리스트 데이터로 변환
	public String cartListToData() {
		String data = "";
		for(Cart c : cartList) {
			data += c.getUserId()+"/"+c.getItemName()+"\n";
		}
		return data;
	}
	
	// 아이템리스트 데이터로 변환
	public String itemListToData() {
		String data = "";
		for(Item i : itemList) {
			data += i.getCategory()+"/"+i.getName()+"/"+i.getPrice()+"\n";
		}
		return data;
	}
	
	
	
}
