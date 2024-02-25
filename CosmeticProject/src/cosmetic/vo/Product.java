package cosmetic.vo;

public class Product {
    private String productNo;
    private String name;
    private int price;
    
    public Product() {
        super();
    }
    public Product(String productNo, String name, int price) {
        super();
        this.productNo = productNo;
        this.name = name;
        this.price = price;
    }

    public String getProductNo() {
        return productNo;
    }
    public void setProductNo(String productNo) {
        this.productNo = productNo;
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
    @Override
    public String toString() {
        return "상품 번호=" + productNo + ", 상품명=" + name + ", 가격=" + price ;
    }
}
