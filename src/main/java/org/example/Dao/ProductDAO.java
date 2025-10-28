package org.example.Dao;

import org.example.entitys.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO {
    private final Connection conn;

    public ProductDAO(Connection conn){
        this.conn = conn;
    }

    public void saveProduct(String name, Double price, Integer quantity) throws SQLException  {
        String query = "INSERT INTO product(name, price, quantity) VALUES (?, ?, ?)";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, quantity);

            stmt.executeUpdate();
            System.out.println("Produto inserido com sucesso.");
        }catch (SQLException e){
            //System.out.println("FALHA ao salvar produto no banco de dados." );
            throw e;
        }

    }


    public List<Product> selectProduct(){
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
        }catch (SQLException e){
            e.printStackTrace();
        }

        return products;
    }

    public void updateProduct(Product product){
        String query = "UPDATE product SET name = ?, price = ?, quantity = ? WHERE id = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setInt(4, product.getId());

            stmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("FALHA ao atualizar produto. Verifique sua query.");
        }
        System.out.println("Produto Atualizado com sucesso");
    }

    public void deleteProduct(int id){
        String query = "DELETE FROM product WHERE id = ?;";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Produto Deletado com sucesso.");
        }catch (SQLException e){
            throw new RuntimeException("FALHA: O produto definido não foi encontrado. Verifique query/conexão.");
        }

    }

}
