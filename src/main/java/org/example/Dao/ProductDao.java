package org.example.Dao;

import org.example.entitys.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.connectionFactory.Connectivity.connectionDb;

public class ProductDao {

    public List<Product> selectProduct(){
        String query = "SELECT * FROM product ORDER by id";
        List<Product> products = new ArrayList<>();
        try(Connection conn = connectionDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return products;
    }

}
