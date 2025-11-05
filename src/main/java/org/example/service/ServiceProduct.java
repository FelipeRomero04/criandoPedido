package org.example.service;

import org.example.entitys.Product;
import org.example.repository.RepoProduct;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ServiceProduct {
    private final Connection conn;
    private final RepoProduct repoProduct;

    public ServiceProduct(Connection conn){
        this.conn = conn;
        repoProduct = new RepoProduct(conn);
    }

    public void saveProduct(Product product) throws SQLException {
        //Aviso caso o produto ja exista no banco?
        repoProduct.createProduct(product);

    }

    public void setUpdateProduct(Product product) throws SQLException {
        repoProduct.updateProduct(product);
    }

    public void productDeleted(int id) throws SQLException{
        repoProduct.deleteProduct(id);
    }

    public List<Product> getListProducts(){
        return repoProduct.findAll();
    }

    public Product getClientById(int id) throws SQLException {
        return repoProduct.findById(id);
    }




}
