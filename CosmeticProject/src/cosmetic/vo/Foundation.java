package cosmetic.vo;

public class Foundation extends Product {
    private String texture; // 크림, 스틱

    public Foundation() {
        super();
    }

    public Foundation(String productNo, String name, int price, String texture) {
        super(productNo, name, price); 
        this.texture = texture;
    }

    public String getTexture() {
        return texture;
    }
    public void setTexture(String texture) {
        this.texture = texture;
    }
    @Override
    public String toString() {
        return super.toString() + ", 파운데이션 제형 = " + (texture.equals("1") ? "크림" : "스틱") ;
    }
}
