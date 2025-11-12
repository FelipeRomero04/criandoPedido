package org.example.service;

import org.example.entitys.Order;
import org.example.entitys.Order_item;
import org.example.repository.RepoOrder;

import java.sql.*;



public class ServiceOrders {
    private final Connection conn;
    private final RepoOrder repoOrder;

    public ServiceOrders(Connection conn) {
        this.conn = conn;
        this.repoOrder = new RepoOrder(conn);
    }

    public void orderTransaction(int client_id, Order_item orderItem) throws SQLException {
        try {
            conn.setAutoCommit(false);

            Order order = new Order(client_id);
            int order_id = repoOrder.saveOrder(order);

            orderItem.setOrder_id(order_id);

            repoOrder.placingOrder(orderItem);
            repoOrder.update_TotalValue(orderItem);

            conn.commit();
        } catch (NullPointerException e) {
            conn.rollback(); // a conexão precisa continuar estabelecida para dar Rollback
            throw e;

        }finally {
            if (conn != null) conn.close();
        }

    }

}
