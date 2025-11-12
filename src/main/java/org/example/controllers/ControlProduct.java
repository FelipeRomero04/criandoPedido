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

    public void productRegister(){
        try{
            Product product = viewProduct.productData();
            serviceProduct.saveProduct(product);
        }catch (SQLException e){
            throw new RuntimeException("Erro ao registrar produto. Verifique ControlProduct.");
        }
    }

    public void productUpdate(){
        try{
            Product productUpdate = getOldProduct();
            productUpdate = viewProduct.productUpdate(productUpdate);
            serviceProduct.setUpdateProduct(productUpdate);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException("Nenhum produto foi atualizado.");
        }
    }

    private Product getOldProduct() throws SQLException{
        List<Product> products = serviceProduct.getListProducts();
        int product_id = viewProduct.idProductChoose(products);
        return serviceProduct.getClientById(product_id);
    }

    public void productDelete() {
        try{
            List<Product> products = serviceProduct.getListProducts();
            int idDeleted = viewProduct.idProductChoose(products);
            serviceProduct.productDeleted(idDeleted);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException("Nenhum produto foi deletado.");
        }
    }
}
