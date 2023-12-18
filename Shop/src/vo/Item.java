package vo;

import java.util.ArrayList;

public class Item {
	private String name;
	private int price;
	private String category; // 카테고리 // 육류 , 과자 , 어류 , 과일 등등
	
	public Item(String category, String name, int price) {
		super();
		this.category = category;
		this.price = price;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return category +"\t" +name+"\t"+price+"원";
	}
}
