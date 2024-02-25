package cosmetic.vo;

public class Lipstick extends Product {
    private String type;  // 립밤, 립글로스, 틴트
    private String color; // 빨강, 분홍, 오렌지 
    
    public Lipstick() {
        super();
    }
    public Lipstick(String productNo, String name, int price, String type, String color) {
        super(productNo, name, price);
        this.type = type;
        this.color = color;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    @Override
    public String toString() {
    	String t = (type.equals("1")? "립밤" : type.equals("2") ?  "립글로스" : "틴트");
    	String c = (color.equals("1")? "빨강" : color.equals("2") ?  "분홍" : "오렌지");
        return super.toString()+ ", 립스틱 타입=" + t + ", 색상=" + c  ;
    }
}
