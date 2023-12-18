package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import dao.ItemDAO;
import dao.UserDAO;

public class FileManager {
	ItemDAO itemDAO = ItemDAO.getInstance();
	UserDAO userDAO = UserDAO.getInstance();
	private static String CUR_PATH = System.getProperty("user.dir") + "\\src\\" + new FileManager().getClass().getPackageName()+"\\";
	
	private static FileManager instance = new FileManager();
	public static FileManager getInstance() {
		return instance;
	}
	
	// cart 파일 저장하기
	public static void saveCartFile(ItemDAO itemDAO) {
		String fileName = "cart.txt";
		try(FileWriter fw = new FileWriter(CUR_PATH+fileName)){
			String data = itemDAO.cartListToData();
			fw.write(data);
			System.out.println(fileName + " 저장 성공");
		} catch (IOException e) {
			System.out.println("파일 저장 실패");
			e.printStackTrace();
		}
	}
	
	// user 파일 저장하기
	public static void saveUserFile(UserDAO userDAO) {
		String fileName = "user.txt";
		try(FileWriter fw = new FileWriter(CUR_PATH+fileName)){
			String data = userDAO.userListToData();
			fw.write(data);
			System.out.println(fileName + " 저장 성공");
		} catch (IOException e) {
			System.out.println("파일 저장 실패");
			e.printStackTrace();
		}
	}
	
	// item 파일 저장하기
	public static void saveItemFile(ItemDAO itemDAO) {
		String fileName = "item.txt";
		try(FileWriter fw = new FileWriter(CUR_PATH+fileName)){
			String data = itemDAO.itemListToData();
			fw.write(data);
			System.out.println(fileName + " 저장 성공");
		} catch (IOException e) {
			System.out.println("파일 저장 실패");
			e.printStackTrace();
		}
	}
	
	
	// 유저 파일 불러오기
	public String userDataRoad() {
		String roadUserData = roadData("user.txt");
		return roadUserData;
	}
	
	// 아이템 파일 불러오기
	public String itemDataRoad() {
		String roadItemData = roadData("item.txt");
		return roadItemData;
	}
	
	// 카트 파일 불러오기
	public String cartDataRoad() {
		String roadCartData = roadData("cart.txt");
		return roadCartData;
	}
	
	
	// 파일 로드 틀
	private String roadData(String fileName) {
		try(FileReader fr = new FileReader(CUR_PATH+fileName);
			BufferedReader br = new BufferedReader(fr)){
			String data = "";
			while(true) {
				String line = br.readLine();
				if(line == null) break;
				data += line+"\n";
			}
			return data;
		} catch (IOException e) {
			System.out.println("파일 로드 실패");
			e.printStackTrace();
		}
		return null;
	}
	
	
}
