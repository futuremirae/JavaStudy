package cosmetic.ui;

import java.util.List;
import java.util.Scanner;

import cosmetic.service.CosmeticService;
import cosmetic.service.CosmeticServiceImpl;
import cosmetic.vo.Foundation;
import cosmetic.vo.Lipstick;
import cosmetic.vo.Product;

public class CosmeticUI {
	Scanner keyin = new Scanner(System.in);

	CosmeticService service = new CosmeticServiceImpl();

	public CosmeticUI() {
		String choice;
		while(true) {
			mainMenu();
			choice = keyin.next();

			switch(choice) {
			case "1": //등록
				makeProduct();
				break;
			case "2": //수정
				updateProduct();
				break;
			case "3": //삭제
				deleteProduct();
				break;
			case "4": // 출력
				searchProduct();
				break;
			case "5":
				productPrint();
				break;
			case "0":
				System.out.println("## 프로그램을 종료합니다.");
				return;
			}
		}
	}

	/**
	 * 메인 메뉴
	 */
	public void mainMenu() {
		System.out.println("===[ 화장품 관리 ]===");
		System.out.println(" 1. 상품 등록");
		System.out.println(" 2. 상품 수정");
		System.out.println(" 3. 상품 삭제");
		System.out.println(" 4. 상품 출력");
		System.out.println(" 5. 전체 상품 출력");
		System.out.println(" 0. 종 료");
		System.out.println("===============");
		System.out.print  ("  > 선택: ");
	}

	// 서브메뉴
	public String subMenu() { 
		String type;

		System.out.print("1. 립스틱 / 2. 파운데이션 : ");

		type = keyin.next();
		if(!(type.equals("1") || type.equals("2"))) {
			System.out.println("## 다시 선택해 주세요");
			return null;
		}
		return type;
	}

	// 상품 등록
	public void makeProduct() { 
		Product product = null;

		String productNo;
		String type;

		System.out.println("\n<< 상품 등록 >> ");
		type = subMenu();

		System.out.print("> 상품번호: "); productNo = keyin.next();

		Product p = service.selectOne(productNo);
		if(p != null) {
			System.out.println("\n## 이미 등록된 상품입니다.");
			return;
		}

		p = inputProduct();
		p.setProductNo(productNo);

		switch(type) {
		case "1" : 
			product = inputLipstick(p);
			break;

		case "2" : 
			product = inputFoundation(p);
			break;
		default : 
			System.out.println("## 다시 선택해주세요");
			return;
		}

		service.insert(product);
		System.out.println("\n## 상품이 등록되었습니다.\n");
	}

	// 상품 정보 입력
	public Product inputProduct() {
		String name;
		int price;

		System.out.print("> 상품명: ");   name = keyin.next();
		System.out.print("> 상품가격: "); price = keyin.nextInt();

		Product product = new Product(null, name, price);

		return product;
	}

	// 파운데이션 정보 입력
	public Foundation inputFoundation(Product product) {
		String texture = null;

		System.out.print("> 파운데이션 제형: (1. 크림 / 2. 스틱)");
		texture = keyin.next();

		return new Foundation(product.getProductNo(), product.getName(), product.getPrice(), texture);
	}

	// 립스틱 정보 입력
	public Lipstick inputLipstick(Product product) {
		String type = null;
		String color = null;

		System.out.print("> 립스틱 타입: (1. 립밤 / 2. 립글로스 / 3. 틴트) : ");
		type = keyin.next();

		System.out.print("> 립스틱 색상: (1. 빨강 / 2. 분홍 / 3. 오렌지) : ");
		color = keyin.next();

		return new Lipstick(product.getProductNo(), product.getName(), product.getPrice(), type, color);
	}

	// 하나의 상품을 출력
	public void searchProduct() {
		System.out.println("\n<< 상품 출력 >>");

		String productNo;
		System.out.print("> 상품번호: "); 
		productNo = keyin.next();

		Product product = service.selectOne(productNo);

		if(product == null) {
			System.out.println("## 등록된 상품이 없습니다.");
			return;
		}

		System.out.println(product);
	}

	// 전체 상품 출력
	public void productPrint() {
		System.out.println("\n<< 전체 상품 출력 >>");
		List<Product> list = service.selectAll();

		int size = service.getCount();

		if(size == 0) {
			System.out.println("## 등록된 상품이 없습니다.\n");
			return;
		}

		for(int i=0; i<list.size(); ++i)
			System.out.println(list.get(i));
		System.out.println();
	}

	// 상품 정보 수정
	public void updateProduct() { 
		System.out.println("\n<< 상품 정보 수정 >>");

		System.out.print("## 수정할 상품의 번호: ");
		String productNo = keyin.next();
		Product product = null; 

		product = service.selectOne(productNo);

		if(product == null) {
			System.out.println("## 상품이 존재하지 않습니다.\n");
			return;
		}

		System.out.println(product);

		Product p = inputProduct();
		p.setProductNo(productNo);

		if(product instanceof Lipstick)
			product = inputLipstick(p);
		else
			product = inputFoundation(p);

		System.out.println(product);
		service.update(product);
		
		System.out.println("## 수정이 완료되었습니다.\n");
	}

	// 상품 삭제
	public void deleteProduct() { 
		System.out.println("\n<< 상품 삭제 >>");

		System.out.print("## 삭제할 상품의 번호: ");
		String productNo = keyin.next();
		Product product = null; 
		String confirm = null;

		product = service.selectOne(productNo);

		if(product == null) {
			System.out.println("## 상품이 존재하지 않습니다.");
			return;
		}

		System.out.println(product);

		System.out.println("## 상품을 삭제하시겠습니까? (y/n) ");
		confirm = keyin.next();
		
		if(confirm.equals("Y") || confirm.equals("y")) {
			service.delete(productNo);
			System.out.println("## 상품이 삭제되었습니다.\n");
			return ;
		}
		System.out.println("## 삭제작업이 취소되었습니다.\n");

	}
}
