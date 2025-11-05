package org.example.controllers;

import org.example.entitys.Product;
import org.example.service.ServiceProduct;
import org.example.view.ViewProduct;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ControlProduct {
    private final ViewProduct viewProduct;
    private final ServiceProduct serviceProduct;

    public ControlProduct(Connection conn){
        viewProduct = new ViewProduct();
        serviceProduct = new ServiceProduct(conn);
    }

    public void productRegister() throws SQLException{
        Product product = viewProduct.productData();
        serviceProduct.saveProduct(product);
    }

    public void productUpdate() throws SQLException {
        List<Product> products = serviceProduct.getListProducts();
        int product_id = viewProduct.idProductChoose(products);
        Product productUpdate = serviceProduct.getClientById(product_id);
        productUpdate = viewProduct.productUpdate(productUpdate);
        serviceProduct.setUpdateProduct(productUpdate);
    }

    public void productDelete() throws SQLException{
        List<Product> products = serviceProduct.getListProducts();
        int idDeleted = viewProduct.idProductChoose(products);
        serviceProduct.productDeleted(idDeleted);
    }
}
