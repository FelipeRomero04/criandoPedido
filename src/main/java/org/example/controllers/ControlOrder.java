package org.example.controllers;

import org.example.Dao.ClientDAO;
import org.example.Dao.ProductDAO;
import org.example.entitys.Client;
import org.example.entitys.Product;
import org.example.repository.RepoClient;
import org.example.repository.RepoProduct;
import org.example.service.ServiceOrders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.utils.dateUtils.Utils.*;
import static org.example.validations.ProductValidation.quantityProductValid;

public class ControlOrder {
    private final Scanner input = new Scanner(System.in);
    private final ServiceOrders serviceOrder;
    private final RepoClient repoClient;
    private final RepoProduct repoProduct;
    private Connection conn;
    private ProductDAO productDAO;
    private ClientDAO clientDAO;

    private final List<Integer> id_products = new ArrayList<>();
    private final List<Integer> quant = new ArrayList<>();

    public ControlOrder(Connection conn){
        serviceOrder = new ServiceOrders(conn);
        repoClient = new RepoClient(conn);
        repoProduct = new RepoProduct(conn);

    }
    
    public void addCart() throws SQLException {
        List<Client> clients = clientDAO.selectClients();
        printTableClients(clients);

        System.out.print("Selecione o cliente: ");
        int client_id = Integer.parseInt(input.nextLine().trim());
        if(!repoClient.isExist(client_id)){
            return;
        }

        List<Product> products = productDAO.selectProduct();
        printTableProducts(products);
        System.out.println("=".repeat(50));

        while(true){
            try{
                System.out.print("Selecione o Id dos Produtos que deseja: ");
                String id_product = input.nextLine().trim();
                System.out.println("\n[ENTER P/SAIR]");

                if (id_product.isBlank() || !Character.isDigit(id_product.charAt(0))) {
                    System.out.println("Retornando ao Menu...");
                    break;
                }
                if(!repoProduct.isExist((Integer.parseInt(id_product)))){
                    return;
                }

                System.out.print("Quantidade: ");
                int quantity = quantityProductValid(input.nextLine());
                if (quantity == 0) {
                    System.out.println("Quantidade não foi inserida corretamente.");
                    continue;
                }

                id_products.add(Integer.parseInt(id_product));
                quant.add(quantity);

            }catch (NumberFormatException e){
                System.err.println("Campo inserido com valor inválido. Insira novamente...");
                continue;
            }

            //serviceOrder.orderTransaction(client_id, id_products,quant);
            // Passar para um objeto não chamar aqui

        }

    }

}
