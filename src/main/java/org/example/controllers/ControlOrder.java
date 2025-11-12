package org.example.controllers;

import org.example.entitys.Client;
import org.example.entitys.Order_item;
import org.example.entitys.Product;
import org.example.service.ServiceOrders;
import org.example.service.ServiceProduct;
import org.example.view.ViewOrder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ControlOrder {
    private final ServiceOrders serviceOrders;
    private final ServiceProduct serviceProduct;
    private final ViewOrder viewOrder = new ViewOrder();

    public ControlOrder(Connection conn){
        serviceOrders = new ServiceOrders(conn);
        serviceProduct = new ServiceProduct(conn);
    }

    public Order_item assembleCart(Client client) {
        try{
            List<Product> products = serviceProduct.getListProducts();
            return viewOrder.createCart(client, products);
        }
        catch (SQLException e){
            throw new RuntimeException("ERRO ao montar carrinho. Tente Novamente.");
        }
    }

    public void startedTransaction(Client client){
        try{
            if(client.getId() == null){
                throw new NullPointerException("É preciso estar logado para fazer um pedido.");
            }
            Order_item order = assembleCart(client);

            if(order.getProducts_id().isEmpty()){
                throw new RuntimeException("O carrinho está vazio. Retornando ao Menu...");
            }
            serviceOrders.orderTransaction(client.getId(), order);

        }catch (SQLException e){
            throw new RuntimeException("ERRO na transação: Não foi possível realizar o Pedido.");
        }
    }
}


