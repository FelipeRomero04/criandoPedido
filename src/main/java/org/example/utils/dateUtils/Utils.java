package org.example.utils.dateUtils;

import org.example.Dao.ProductDao;
import org.example.entitys.Product;

import java.util.List;

public class Utils {
    private static final ProductDao productDAO = new ProductDao();


    public static Product productById(int id){
        List<Product> product = productDAO.selectProduct();
        return product.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
}
