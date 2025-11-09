package org.example.repository;

import org.example.entitys.Order;
import org.example.entitys.Order_item;
import org.example.entitys.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RepoOrder {
    private final Connection conn;
    private final RepoProduct repoProduct;

    public RepoOrder(Connection conn) {
        this.conn = conn;
        this.repoProduct = new RepoProduct(conn);
    }

    public int saveOrder(Order order) throws SQLException {
        String query = "INSERT INTO orders(client_id, date, total_value) VALUES (?, ?, ?);";
        int order_id;
        try(PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, order.getClient_id());
            stmt.setTimestamp(2, toTimeStamp(order.getDate()));
            stmt.setDouble(3, order.getTotal_value());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            order_id = rs.getInt(1);

        }catch (SQLException e){
            throw e;
        }
        return order_id;
    }

    public void placingOrder(Order_item orderItem) throws SQLException {
        String insertQuery = "INSERT INTO order_item(order_id, product_id, quantity) VALUES (?,?,?);";

        try (PreparedStatement stmtInsrt = conn.prepareStatement(insertQuery)) {
            for (int i = 0; i < orderItem.getProducts_id().size(); i++) {
                stmtInsrt.setInt(1, orderItem.getOrder_id());
                stmtInsrt.setInt(2, orderItem.getProducts_id().get(i));
                stmtInsrt.setDouble(3, orderItem.getQuantities().get(i));

                stmtInsrt.addBatch(); //guarda em lotes
            }
            stmtInsrt.executeBatch(); //executa tudo

            System.out.println("FEITO");
        } catch (SQLException e) {
            throw e;
        }
    }

    public void update_TotalValue(Order_item orderItem) throws SQLException { //mudar o nome
        String uptQuery = "UPDATE orders SET total_value = ? WHERE id = ?;";

        double total = 0;
        try(PreparedStatement stmtUpd = conn.prepareStatement(uptQuery)){

            for (int i = 0; i < orderItem.getProducts_id().size(); i++) {
                Product product = repoProduct.findById(orderItem.getProducts_id().get(i));
                total += product.getPrice() * orderItem.getQuantities().get(i);
                repoProduct.decrementStock(product.getId(), orderItem.getQuantities().get(i));
            }

            System.out.println(total);
            stmtUpd.setDouble(1, total);
            stmtUpd.setInt(2, orderItem.getOrder_id());
            stmtUpd.executeUpdate();

        }catch(SQLException e){
            throw e;
        }
    }


    public void viewOrder(){
        String query = "SELECT * FROM finalizePurchase";
    }

    private Timestamp toTimeStamp(LocalDateTime date){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        return Timestamp.valueOf(date.format(format));

    }//tirar isso daqui

}
