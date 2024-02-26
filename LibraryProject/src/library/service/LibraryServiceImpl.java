package library.service;




import java.util.ArrayList;

import library.vo.Book;
import library.vo.Customer;

public class LibraryServiceImpl implements LibraryService {
	
	//private ArrayList<Player> playerList;// = new ArrayList<>();

//	public LibraryServiceImpl() {
//		// TODO Auto-generated constructor stub
//	}
	private ArrayList<Customer> customerList = new ArrayList<>();
	private ArrayList<Book> bookList = new ArrayList<>();

	@Override
	public boolean insert(Customer customer) {
		customerList.add(customer);
		return false;
	}

	@Override
	public Customer searchCustomer(String id) {
		
		int index = searchIndex(id);
		if(index != -1) 
			return customerList.get(index);
		return null;
	
		
	}

	@Override
	public boolean update(Customer customer) {
		int index = searchIndex(customer.getId());
		customerList.get(index).setName(customer.getName());

		return true;
	}

	@Override
	public boolean delete(String id) {
		int index = searchIndex(id); 
		customerList.remove(index);
		return true;
	}

	@Override
	public int searchIndex(String id) {
		for (int i = 0; i < customerList.size() ; i++) {
			if (customerList.get(i).getId().equals(id) ) 
				return i;
		}
		return -1;
	}

	@Override
	public int searchBookIndex(String bookid) {
		for (int i = 0; i < bookList.size() ; i++) {
			if (bookList.get(i).getBookID().equals(bookid) ) 
				return i;
		}
		return -1;
	
	}

	@Override
	public boolean insertBook(Book book) {
		bookList.add(book);
		return true;
	}

	@Override
	public Book searchBook(String bookid) {
		int index = searchBookIndex(bookid);
		return bookList.get(index);
		
		
	}

	@Override
	public boolean borrowBook(String bookid, String id) {
		int index = searchIndex(id);
		int bookindex = searchBookIndex(bookid);
		bookList.get(bookindex).setBookAvailable(false); // 책의 상태를 대출불가로 만들기 
		ArrayList<Book> books = customerList.get(index).getBookList(); // 사용자가대출 중인 책들 어레이 
		books.add(searchBook(bookid)); // 사용자의 대출리스트에 추가하기 
		customerList.get(index).setBookList(books); // 도서리스트 추가한거 다시 넣기 
		
		return true;
	}

	@Override
	public boolean returnBook(String bookid, String id) {
		int index = searchIndex(id);
		int bookindex = searchBookIndex(bookid);
		bookList.get(bookindex).setBookAvailable(true);
		ArrayList<Book> books = customerList.get(index).getBookList();
		books.remove(searchBook(bookid));
		customerList.get(index).setBookList(books);
		
		return true;
	}
	
	@Override
	public boolean updatebook(Book book) {
		int index = searchBookIndex(book.getBookID()); 
		
		bookList.get(index).setBookAuthor(book.getBookAuthor());
		bookList.get(index).setBookTitle(book.getBookTitle());
		return true;
	}
	@Override
	public boolean bookdelete(String bookid) {
		int index = searchBookIndex(bookid);
		bookList.remove(index);
		return true;
	}
	

}
