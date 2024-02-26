package library.vo;

import java.util.ArrayList;

public class Customer {

	public Customer(String name, String id, ArrayList<Book> bookList) {
		super();
		this.name = name;
		this.id = id;
		this.bookList = bookList;
	}

	public Customer() {
		super();
	}
	
	// 회원 이름
	private String name; 
	
	// 회원 아이디 
	private String id;
	
	// 대출중인 책 리스트 
	private ArrayList<Book> bookList = new ArrayList<>();

	// 세터 겟터 
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public ArrayList<Book> getBookList() {
		return bookList;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setBookList(ArrayList<Book> bookList) {
		this.bookList = bookList;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", id=" + id + ", bookList=" + bookList + "]";
	} 
	
	
	
	
	
	

}
