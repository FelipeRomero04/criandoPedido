package org.example.service;

import org.example.entitys.Order;
import org.example.entitys.Order_item;
import org.example.entitys.Product;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.example.utils.connectionFactory.Connectivity.connectionDb;
import static org.example.utils.dateUtils.Utils.productById;

public class ServiceOrder {
    Scanner input = new Scanner(System.in);

    public void createOrder(int client_id, String date, double total_value) throws SQLException {
        String query = "INSERT INTO orders(client_id, date, total_value) VALUES (?, ?, ?);";
        Connection conn = connectionDb();
        try(PreparedStatement stmt = conn.prepareStatement(query)){

            conn.setAutoCommit(false);

            stmt.setInt(1, client_id);
            stmt.setTimestamp(2, Timestamp.valueOf(date));
            stmt.setDouble(3, total_value); //valor base

            stmt.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            conn.rollback();
            throw e;
        }finally {
            conn.close();
        }
    }


    public void fazendoPedido(List<Integer> id_products, List<Integer> quantitys) throws SQLException{

        //Select -> retornar lista de produtos
        //Objetivo e retonar um order_item

        String query = "SELECT id FROM orders ORDER BY id DESC;";
        String updateQuery = "UPDATE orders SET total_value = ? WHERE id = ?;";
        String insertQuery = "INSERT INTO order_item(order_id, product_id, quantity) VALUES (?,?,?);";
        Connection conn = connectionDb();

        try(PreparedStatement stmt = conn.prepareStatement(query);
            PreparedStatement stmtUpd = conn.prepareStatement(updateQuery);
            PreparedStatement stmtInsrt = conn.prepareStatement(insertQuery);
            ResultSet rs = stmt.executeQuery()){
            conn.setAutoCommit(false);

            //pegar id de order
            int order = 0;
            if (rs.next()) {
                order = rs.getInt("id");
            }

            for (int i = 0; i < id_products.size(); i++) {
                stmtInsrt.setInt(1, order);
                stmtInsrt.setInt(2, id_products.get(i));
                stmtInsrt.setDouble(3, quantitys.get(i));

                stmtInsrt.addBatch();
            } //faz a inserçao dos pedidos, enquanto acrescenta valor total
            stmtInsrt.executeBatch();

            double price = 0;
            for (int i = 0; i < id_products.size(); i++) {
                Product product = productById(id_products.get(i));
                price += product.getPrice() * quantitys.get(i);
            }

            stmtUpd.setDouble(1, price);
            stmtUpd.setInt(2, order);
            //atualizar total de orders
            stmtUpd.executeUpdate();
            System.out.println(order);
            System.out.println(price);


            conn.commit();

            System.out.println("FEITO");
        }catch (SQLException e){
            conn.rollback();
            throw e;
        }finally {
            conn.close();
        }




//        //bota no loop
//        Map<Integer, Integer> hashOrder = new LinkedHashMap<>();
//        while(true){
//            int id = input.nextInt(); // -> id do produto
//            if(id == 0){
//                break;
//            }
//            hashOrder.merge(id, 1, Integer::sum); //Adiciona valor 1, se ja existir soma
//        }
//         return new Order_item(id_client, hashOrder);
//
//         //hashOrder.get(id) == null







    }
}



//Select com inner
//Insert apos pegar os dados retornados