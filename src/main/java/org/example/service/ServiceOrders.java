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

    public void orderTransaction() throws SQLException {
        try {
            conn.setAutoCommit(false);

            Order order = new Order(2, "2025-05-10 17:40:20", 0.0);

            int order_id = repoOrder.saveOrder(order);
            repoOrder.placingOrder(order_id, List.of(4, 5, 6), List.of(2, 3, 7));
            // passar listas como parâmetros

            conn.commit();
        } catch (NullPointerException e) {
            if (conn != null) conn.rollback();
            e.printStackTrace();
        }
        if (conn != null) conn.close();
    }

}
