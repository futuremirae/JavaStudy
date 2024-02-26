package library.service;

import library.vo.Book;
import library.vo.Customer;

public interface LibraryService {
	
	/**
	 * 전달 받은customer 객체를 리스트에 저장 
	 * @param customer
	 * @return true/ false 
	 */
	public boolean insert(Customer customer); 
	
	/**
	 * 회원 정보 조회 
	 * @param id
	 * @return customor 반환  
	 */
	public Customer searchCustomer(String id); 
	
	/**
	 * 회원 정보 수정 
	 * @param customer
	 * @return true/ false
	 */
	
	public boolean update(Customer customer);
	
	/**
	 * 회원 삭제 
	 * @param id
	 * @return true / false 
	 * 
	 */
	public boolean delete(String id) ;
	
	/**
	 * 회원 아이디 조회 
	 * @param id
	 * @return 인덱스 반환 
	 */
	public int searchIndex(String id) ;
	
	/**
	 * 책 id 조회 
	 * @param bookid
	 * @return 책 인덱스 반환 
	 */
	
	public int searchBookIndex(String bookid);
	
	/**
	 *  도서추가 
	 * @param book
	 * @return true/false
	 */
	public boolean insertBook(Book book);
	
	/**
	 * 책 정보 조회 
	 * @param bookTitle
	 * @return book 객체 반환 
	 */
	public Book searchBook(String bookTitle);
	
	/**
	 * 책 대출 
	 * @param bookid
	 * @return true/false
	 */
	public boolean borrowBook(String bookid,  String id); 
	
	/**
	 * 책 반납 
	 * @param bookid
	 * @return true/false 
	 */
	public boolean returnBook(String bookid, String id); 
	
	/**
	 * 책 정보 수정 
	 * @param book
	 * @return
	 */
	public boolean updatebook(Book book);
	
	/**
	 * 책 삭제 
	 * @param id
	 * @return
	 */
	public boolean bookdelete(String id) ;

}
