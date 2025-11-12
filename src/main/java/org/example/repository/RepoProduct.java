package org.example.repository;

import org.example.entitys.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepoProduct {
    private final Connection conn;

    public RepoProduct(Connection conn){
        this.conn = conn;
    }

    public void createProduct(Product product) throws SQLException  {
        String query = "INSERT INTO product(name, price, stock) VALUES (?, ?, ?)";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getStock());

            stmt.executeUpdate();
            System.out.println("Produto inserido com sucesso.");
        }
    }

    public List<Product> findAll() throws SQLException{
        String query = "SELECT * FROM product ORDER by id";
        List<Product> products = new ArrayList<>();
        try(PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                ));
            }
        }

        return products;
    }

    public Product findById(int id) throws SQLException{
        String query = "SELECT * FROM product WHERE id = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return new Product(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("stock"));
                }
            }
        }
        throw new SQLException("Nenhum produto com esse ID foi encontrado");

    }


    public void updateProduct(Product product) throws SQLException{
        String query = "UPDATE product SET name = ?, price = ?, stock = ? WHERE id = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getStock());
            stmt.setInt(4, product.getId());

            stmt.executeUpdate();
        }
    }

    public void deleteProduct(int id) throws SQLException{
        String query = "DELETE FROM product WHERE id = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void decrementStock(int id, int valueDecrement) throws SQLException{
        String query = "UPDATE product SET stock = stock - ? WHERE id = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(query)){
            Product product = findById(id);
            if(valueDecrement > product.getStock()){
                throw new IllegalArgumentException("Não temos "+ product.getName()+ " suficiente no estoque para realizar a compra.");
            }

            stmt.setInt(1, valueDecrement);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        }
    }

}
