package cosmetic.service;

import java.util.List;

import cosmetic.vo.Product;

public interface CosmeticService {
    public boolean insert(Product product);
    public boolean update(Product product);
    public boolean delete(String productNo);
    public Product selectOne(String productNo);
    public List<Product> selectAll();
    public int getCount();
    public int searchIndex(String productNo);
}
