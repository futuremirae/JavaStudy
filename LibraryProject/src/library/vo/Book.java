package library.vo;

public class Book {
	
	public Book() {
		
	}
	public Book(String bookid, String bookTitle, String bookAuthor, boolean bookAvailable) {
		super();
		this.bookID = bookid;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookAvailable = bookAvailable;
	}
	
	
	// 책 아이디 
	private String bookID;
	// 책 제목 
	private String bookTitle;
	// 책 저자 
	private String bookAuthor;
	// 대출 가능 여부 
	private boolean bookAvailable;
	
	public String getBookID() {
		return bookID;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public boolean isBookAvailable() {
		return bookAvailable;
	}
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public void setBookAvailable(boolean bookAvailable) {
		this.bookAvailable = bookAvailable;
	}
	@Override
	public String toString() {
		return String.format("도서 아이디 : %s%n도서명 : %s%n저자 : %s%n", bookID,bookTitle,bookAuthor) ;
	}
	
	//String.format("MyClass{value1=%d, value2='%s'}", value1, value2);
}
	
	

	


