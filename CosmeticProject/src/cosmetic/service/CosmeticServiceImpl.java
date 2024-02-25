package cosmetic.service;

import java.util.ArrayList;
import java.util.List;

import cosmetic.vo.Foundation;
import cosmetic.vo.Lipstick;
import cosmetic.vo.Product;

public class CosmeticServiceImpl implements CosmeticService {
    List<Product> list = new ArrayList<>();
    
    @Override
    public boolean insert(Product product) {
    	list.add(product);
        
        return true;
    }

    @Override
    public boolean update(Product product) {
        int index = searchIndex(product.getProductNo());
        
        if(product instanceof Lipstick) {
        	Lipstick lipstick = (Lipstick) product;
        	Lipstick temp = (Lipstick) list.get(index);
        	
        	temp.setName(lipstick.getName());
        	temp.setPrice(lipstick.getPrice());
        	temp.setType(lipstick.getType());
        	temp.setColor(lipstick.getColor());
        	
        	list.add(index, temp);
        	
        } else if(product instanceof Foundation) {
        	Foundation foundation = (Foundation) product;
        	Foundation temp = (Foundation) list.get(index);
        	
        	temp.setName(foundation.getName());
        	temp.setPrice(foundation.getPrice());
        	temp.setTexture(foundation.getTexture());
        	
        	list.add(index, temp);
        }
    	
        return true;
    }

    @Override
    public boolean delete(String productNo) {
    	int index = searchIndex(productNo);
    	
    	list.remove(index);
        return true;
    }

    @Override
    public Product selectOne(String productNo) {
        int index = searchIndex(productNo);
        
        if(index == -1) 
        	return null;
       
        return list.get(index);
    }

    @Override
    public List<Product> selectAll() {
        return list;
    }

    @Override
    public int getCount() {
        
        return list.size();
    }

    @Override
    public int searchIndex(String productNo) {
        int index = -1;
        for(int i=0; i<list.size(); ++i) {
            if(list.get(i).getProductNo().equals(productNo)) { 
                index = i;
                break;
            }
        }
            
        return index;
    }
}
