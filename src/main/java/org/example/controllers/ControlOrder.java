package org.example.controllers;

import org.example.entitys.Client;
import org.example.entitys.Order_item;
import org.example.entitys.Product;
import org.example.service.ServiceClient;
import org.example.service.ServiceOrders;
import org.example.service.ServiceProduct;
import org.example.view.ViewClient;
import org.example.view.ViewOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ControlOrder {
    private final ServiceOrders serviceOrders;
    private final ServiceClient serviceClient;
    private final ServiceProduct serviceProduct;
    private final ViewClient viewClient = new ViewClient();
    private final ViewOrder viewOrder = new ViewOrder();

    public ControlOrder(Connection conn){
        serviceOrders = new ServiceOrders(conn);
        serviceClient = new ServiceClient(conn);
        serviceProduct = new ServiceProduct(conn);
    }

    public int clientIdOrder(){
        List<Client> clients = serviceClient.getListClients();
        return viewClient.idClientChoose(clients);
    } //client_id -> orders

    public Order_item assembleCart(Client client){
        List<Product> products = serviceProduct.getListProducts();
        return viewOrder.createCart(client,products); //2 lists order_item
    }

    public void startedTransaction(Client client){
        try{
            Order_item order = assembleCart(client);
            serviceOrders.orderTransaction(client.getId(), order);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}


