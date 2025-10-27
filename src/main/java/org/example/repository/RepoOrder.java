package org.example.repository;

import org.example.entitys.Order;
import org.example.entitys.Product;

import java.sql.*;
import java.util.List;

import static org.example.utils.dateUtils.Utils.productById;

public class RepoOrder {

    private final Connection conn;

    public RepoOrder(Connection conn) {
        this.conn = conn;
    }

    public int saveOrder(Order order) throws SQLException {
        String query = "INSERT INTO orders(client_id, date, total_value) VALUES (?, ?, ?);";
        int order_id;
        try(PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){

            stmt.setInt(1, order.getClient_id());
            stmt.setTimestamp(2, Timestamp.valueOf(order.getDate()));
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


    public void placingOrder(int order_id, List<Integer> id_products, List<Integer> quantitys) throws SQLException {
        String insertQuery = "INSERT INTO order_item(order_id, product_id, quantity) VALUES (?,?,?);";

        try (PreparedStatement stmtInsrt = conn.prepareStatement(insertQuery)) {
            for (int i = 0; i < id_products.size(); i++) {
                stmtInsrt.setInt(1, order_id);
                stmtInsrt.setInt(2, id_products.get(i));
                stmtInsrt.setDouble(3, quantitys.get(i));

                stmtInsrt.addBatch(); //guarda em lotes
            }
            stmtInsrt.executeBatch(); //executa tudo

            calc_total(order_id, id_products, quantitys);

            System.out.println("FEITO");
        } catch (SQLException e) {
            throw e;
        }
    }

    public void calc_total(int order_id ,List<Integer> id_items, List<Integer> quant) throws SQLException {
        String uptQuery = "UPDATE orders SET total_value = ? WHERE id = ?;";
        double total = 0;

        try(PreparedStatement stmtUpd = conn.prepareStatement(uptQuery)){

            for (int i = 0; i < id_items.size(); i++) {
                Product product = productById(id_items.get(i));
                total += product.getPrice() * quant.get(i);
            }

            stmtUpd.setDouble(1, total);
            stmtUpd.setInt(2, order_id);
            stmtUpd.executeUpdate();

        }catch(SQLException e){
            throw e;
        }
    }

}
