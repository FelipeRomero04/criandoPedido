package org.example.service;

import org.example.entitys.Order;
import org.example.repository.RepoOrder;

import java.sql.*;
import java.util.List;


public class ServiceOrders {
    private final Connection conn;
    private final RepoOrder repoOrder;

    public ServiceOrders(Connection conn) {
        this.conn = conn;
        this.repoOrder = new RepoOrder(conn);
    }

    public void orderTransaction(int client_id, List<Integer> id_products, List<Integer> list_quantity) throws SQLException {
        try {
            conn.setAutoCommit(false);

            Order order = new Order(client_id);
            int order_id = repoOrder.saveOrder(order);

            repoOrder.placingOrder(order_id, id_products, list_quantity);
            repoOrder.update_TotalValue(order_id, id_products, list_quantity);

            conn.commit();
        } catch (NullPointerException e) {
            if (conn != null) conn.rollback();
            e.printStackTrace();
        }
        if (conn != null) conn.close();
    }

}
