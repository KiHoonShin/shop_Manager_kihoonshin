package dao;

import java.util.ArrayList;

import Controller.ShopController;
import vo.User;

public class UserDAO {
	ArrayList<User> userList;
	ShopController shop = new ShopController();
	ItemDAO itemDAO = new ItemDAO();
	int cnt;
	public UserDAO(){
			userList = new ArrayList<User>();

		}

	public static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	}

	public void plusUser() {
		System.out.println("[ 회원 가입 ]");
		String id = shop.getStr("id");
		User u = checkId(id);
		if(u != null) {
			System.out.println("이미 가입된 id입니다.");
			return;
		}
		String pw = shop.getStr("pw");
		String name = shop.getStr("이름");

		userList.add(new User(id,pw,name));
		System.out.println(userList.get(cnt));
		System.out.println("[회원가입 성공 !]");
		System.out.println();
		cnt += 1;
	}

	public String logIn() {
//		if(!hasUser()) {
//			System.out.println("가입된 회원이 아직 없습니다.");
//			return null;
//		}
		System.out.println("[ 로그인 ]");
		String id = shop.getStr("id");
		User u = checkId(id);
		if(u == null) {
			System.out.println("가입된 id가 없습니다.");
			return null;
		}
		String pw = shop.getStr("pw");
		if(!u.getPw().equals(pw)) {
			System.out.println("비밀번호가 틀렸습니다.");
			return null;
		}
		System.out.println("[로그인 성공]");
		return u.getName();
	}

	//id 중복 체크
	private User checkId(String id) {
		for(User u : userList) {
			if(u.getId().equals(id)) {
				return u;
			}
		}
		return null;
	}
	
//	private boolean hasUser() {
//		for(User u : userList) {
//			if(u.getId() == null) {
//				return false;
//			}
//		}
//		return true;
//	}
	
	// 탈퇴
	public void deleteUser(ItemDAO itemDAO) {
		System.out.println("탈퇴하려면 로그인을 해주세요." + " \n [1] 로그인 [0] 뒤로");
		int sel = shop.getInt("메뉴", 0, 1);
		if(sel == 1) {
			String id = logIn();
			if(id == null) return;
			int delIdx = isUser(id);
			
			for(int i = 0; i < userList.size(); i++) {
				if(delIdx == i) {
					userList.remove(delIdx);
				}
			}
			itemDAO.deleteUserInCart(id);
			System.out.println(id + " 회원 탈퇴 완료");
			cnt -=1;
		} else {
			return;
		}
	}
	
	// 유저 idx찾기
	private int isUser(String id) {
		if(userList == null) return -1;
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}
	
}
