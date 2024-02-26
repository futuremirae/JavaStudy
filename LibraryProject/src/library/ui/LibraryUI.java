package library.ui;

import java.util.ArrayList;
import java.util.Scanner;

import library.service.LibraryServiceImpl;
import library.vo.Book;
import library.vo.Customer;



public class LibraryUI {
	Scanner input = new Scanner(System.  in); 
	LibraryServiceImpl service = new LibraryServiceImpl() ; 

	public LibraryUI() {
		int menuNum = -1;
		
		try {
			while(true) {
				mainMenu();
				 menuNum = input.nextInt();
				
				switch(menuNum) {
				
				case 1: add();break; 
				case 2: search();break; 
				case 3: update();break; 
				case 4: delete();break; 
				case 5: borrow();break;
				case 6: returning();break; 
				case 7: booksearch(); break; 
				case 8: manager();break; 
				case 0: System.out.println("이용해주셔서 감사합니다.");return;
				
				}
			}
			
		}catch(Exception e){
			System.out.println("[ 오류 ] 입력형식이 올바르지 않습니다.");
			
			new LibraryUI(); 
		}
		
	}

	/**
	 * 메인 메뉴
	 */
	public void mainMenu() {
		System.out.println("====[ 디마 도서관에 오신걸 환영니다 ]====");
		System.out.println(" 1. 회원 가입");
		System.out.println(" 2. 회원 정보 조회"); 
		System.out.println(" 3. 회원 정보 수정"); // 회원 정보,수정, 회원 탈퇴
		System.out.println(" 4. 회원 탈퇴"); // 회원 정보,수정, 회원 탈퇴
		System.out.println(" 5. 대출");
		System.out.println(" 6. 반납");
		System.out.println(" 7. 책 정보 조회");
		System.out.println(" 8. 관리자 모드"); // 도서 등록 
		System.out.println(" 0. 프로그램 종료");
		System.out.println("========================================");
		System.out.print  ("  > 선택: ");
	}
	
	public void add() {
		String id;
		System.out.println("[ 회원 가입 ]");
	
		System.out.print("회원 이름 > ");
		String name = input.next(); 
		while (true) {
		System.out.print("회원 아이디 > ");
		id = input.next();
		if (service.searchIndex(id) != -1) {
		System.out.println("이미 존재하는 아이디입니다.");
		System.out.println("다시 입력해주세요!");
	
		}
		else break;
		}
		ArrayList<Book> bookList = new ArrayList<>();
		Customer customer = new Customer(name,id,bookList);
		service.insert(customer); 
		System.out.println("가입이 완료되었습니다.");
		return;
	}
	
	public void search() {
		String id ;
		int menuNum;
		System.out.println(" [조 회] ");
		System.out.println("1. 전체 사용자 조회 ");
		System.out.println("2. 사용자 조회 ");
		menuNum = input.nextInt();
		
		if (menuNum == 1) {
			System.out.println("[ 전체 사용자 조회 ]");
			//if (service.)
		} 
		System.out.println("[ 사용자 조회 ]");
		System.out.println("사용자의 아이디를 입력하세요 > ");
		id = input.next(); 
	
		if (service.searchCustomer(id) == null) {
			System.out.println("일치하는 아이디가 없습니다.");
			System.out.println("첫 페이지로 돌아갑니다.");
			System.out.println();
			return;
			}
	  
	   
	   Customer customer = service.searchCustomer(id);
	   
	   System.out.println("회원 이름 : " + customer.getName());
	   System.out.println("회원 아이디 : " + customer.getId());
	   System.out.println("[ 대출한 책 목록 ]");
	   if (customer.getBookList().size() == 0)
		   System.out.println("현재 대출하고 있는 책이 없습니다.");
	   else {
		   int count = 0;
		   for (int i = 0 ; i < customer.getBookList().size(); i++){
			   System.out.println(customer.getBookList().get(i));
			   count += 1;
		   }
		   System.out.printf("총 %d권의 책을 대출중입니다.", count);
		   System.out.println();
	   }
	   return;
		
	}
	
	public void update() {
		System.out.println("[ 회원 정보 수정 ");
		String id ;
		
		
		System.out.println("사용자의 아이디를 입력하세요 > ");
		id = input.next(); 
		Customer customer = service.searchCustomer(id);
		if (customer == null) {
			System.out.println("일치하는 아이디가 없습니다.");
			System.out.println("첫 페이지로 돌아갑니다.");
			System.out.println();
			return;
			}
		
		System.out.println("수정할 이름을 입력하세요 > ");
		String name = input.next();
		customer.setName(name);
		if(service.update(customer))
			System.out.println("정보수정이 완료되었습니다.");
		 return;
		
		
		
	}
	public void delete() {
		
		System.out.println("[ 회원 탈퇴 ]");
		System.out.print("삭제하실 분의 아이디 입력하세요> ");
		String id  = input.next(); 
		if (service.searchCustomer(id) == null) {
			System.out.println("일치하는 아이디가 없습니다.");
			System.out.println("첫 페이지로 돌아갑니다.");
			System.out.println();
			return;
			}
	    String confirm;
	    System.out.println("> 정말 탈퇴를 하실거예요? (y/n) ");
	    confirm = input.next();
		if(!(confirm.equals("Y") || confirm.equals("y"))) {
			System.out.println("## 탈퇴가 취소되었습니다!\n");
			return; 
		}
		if (service.delete(id))
			System.out.println("## 탈퇴 되었습니다!");
		 return;

	}
	
	public void borrow() {
		System.out.println("[ 책 대출 ]");
	
		String id ;
		String bookid;
		System.out.println("사용자의 아이디를 입력하세요 > ");
		id = input.next(); 
		Customer customer = service.searchCustomer(id);
		if (customer == null) {
			System.out.println("일치하는 아이디가 없습니다.");
			System.out.println("첫 페이지로 돌아갑니다.");
			System.out.println();
			return;
			}
		while(true) {
			System.out.println("대출하실 책의 아이디를 입력하세요 > ");
			bookid = input.next(); 
			if (service.searchBookIndex(bookid) == -1) {
				System.out.println("책 정보가 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
			}
			else break;
		}
		
		if (service.borrowBook(bookid,id))
			System.out.println("대출이 완료되었습니다. ");
		 return;
	}

	
	public void returning() {
		System.out.println("[ 책 반납 ]");
		String id ;
		String bookid;
		System.out.println("사용자의 아이디를 입력하세요 > ");
		id = input.next(); 
		Customer customer = service.searchCustomer(id);
		if (customer == null) {
			System.out.println("일치하는 아이디가 없습니다.");
			System.out.println("첫 페이지로 돌아갑니다.");
			System.out.println();
			return;
			}
		if (customer.getBookList().size() ==0) {
			System.out.println("현재 대출중인 도서가 없습니다. ");
			return; 
		}
		System.out.println("반납하실 책의 아이디를 입력하세요 > ");
		bookid = input.next(); 
		if (service.returnBook(bookid,id))
			System.out.println("반납이 완료되었습니다.");
		else {
			System.out.println("대출하신 도서가 아닙니다. .");
			System.out.println("다시 입력해주세요.");
		}
		return; 
			
		
		
		
	}
	public void booksearch() {
		String bookid;
		System.out.println("[ 책 조회 ]");
		
		while (true) {
			System.out.println("도서 아이디 입력 > ");
			bookid = input.next(); 
			if (service.searchBookIndex(bookid) == -1) {
				System.out.println("존재하지않는 책입니다. ");
				System.out.println("다시 입력해주세요");
			}
			else break; 
		}
		Book book = service.searchBook(bookid);
		System.out.println("도서 아이디: "+book.getBookID());
		System.out.println("도서명: "+book.getBookTitle());
		System.out.println("저자: "+book.getBookAuthor());	
		boolean yes = book.isBookAvailable();
		if (yes)
			System.out.println("대출가능여부: 가능");
		else System.out.println("대출가능여부: 불가능");
		
		return; 
		
		
	}
	
	public void manager() {
		String pw  = "0000";
		String password ; 
		System.out.println("[ 관리자 모드 ]");
		System.out.println("비밀번호를 입력하세요 > ");
		password = input.next(); 
		if (!(pw.equals(password))){
			System.out.println("비밀번호를 틀렸습니다.");
			System.out.println("처음 페이지로 돌아갑니다. ");
		}
		
		int menuNum = -1;
		while(true) {
			managerMenu(); 
			 menuNum = input.nextInt();
			 switch (menuNum) {
			 
			 case 1: addBook();break;
			 case 2: updateBook();break;
			 case 3 :deleteBook(); break;
			 case 0 : System.out.println("첫 페이지로 돌아갑니다. ");return;
			 default : System.out.println("잘못입력하셨습니다. "); return; 
			 
			 }	 
		}	
	}
	
	public void managerMenu() {
		System.out.println("[ 도서 등록 ]");
		System.out.println("1. 도서 등록");
		System.out.println("2. 도서 정보 수정 ");
		System.out.println("3. 도서 삭제");
		System.out.println("0. 종료");
		System.out.println("========================================");
		System.out.print  ("  > 선택: ");
	}
	
	public void addBook() {
		
		String id ; 
		System.out.println("도서명 입력 > ");
		String bookTitle = input.next(); 
		while (true) {
			System.out.println("도서 아이디 입력 > ");
			id = input.next(); 
			if (service.searchBookIndex(id) != -1) {
				System.out.println("이미 존재하는 아이디입니다. ");
				System.out.println("다시 입력해주세요");
			}
			else break; 
		}
		
		System.out.println("책 저자 입력 > ");
		String author = input.next(); 
		Book book = new Book(id, bookTitle, author, true);
		if (service.insertBook(book))
			System.out.println("도서 등록이 완료되었습니다. ");
		return; 
	}
	public void updateBook() {
		String bookid ; 
		System.out.println("[ 도서 정보 수정 ]");
		while (true) {
			System.out.println("도서 아이디 입력 > ");
			bookid = input.next(); 
			if (service.searchBookIndex(bookid) == -1) {
				System.out.println("존재하지 않는 책입니다. ");
				System.out.println("다시 입력해주세요");
			}
			else break; 
		}
		Book book = service.searchBook(bookid); 
		System.out.println("수정하려는 도서명 > ");
		String bookTitle = input.next();
		System.out.println("수정하려는 저자이름 > ");
		String author = input.next(); 
		book.setBookTitle(bookTitle);
		book.setBookAuthor(author);
		if (service.updatebook(book))
			System.out.println("도서 수정이 완료되었습니다. ");
		return;
	}
	
	public void deleteBook() {
		String bookid ; 
		System.out.println("[ 도서 삭제 ]");
		while (true) {
			System.out.println("도서 아이디 입력 > ");
			bookid = input.next(); 
			if (service.searchBookIndex(bookid) == -1) {
				System.out.println("존재하지 않는 책입니다. ");
			}
			else break; 
		}
		
		Book book = service.searchBook(bookid);
		if (!book.isBookAvailable()) {
			System.out.println("현재 대출중인 도서이므로 삭제가 불가능합니다.");
			return;
		}
		
		if (service.bookdelete(bookid))
			System.out.println("도서가 삭제되었습니다. ");
		return; 
		
	}
	
	
	
	
	

}


