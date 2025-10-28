package org.example.utils.dateUtils;

import org.example.Dao.ClientDAO;
import org.example.Dao.ProductDAO;
import org.example.entitys.Client;
import org.example.entitys.Product;

import java.sql.Connection;
import java.util.List;

import static org.example.utils.connectionFactory.Connectivity.connectionDb;

public class Utils {
    private static final Connection conn = connectionDb();
    private static final ProductDAO productDAO = new ProductDAO(conn);
    private static final ClientDAO clientDAO = new ClientDAO(conn);

    public static Product productById(int id){
        List<Product> product = productDAO.selectProduct();
        return product.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public static void printTableProducts(List<Product> products){
        if(products.isEmpty()){
            System.out.println("O banco de Produtos está vazio.");
            return;
        }
        products.forEach(prod -> {
            System.out.println(prod);
            System.out.println("=".repeat(50)+"\n");
        });
    }

    public static Client clientById(int id){
        List<Client> client = Utils.clientDAO.selectClients();
        return client.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public static void printTableClients(List<Client> client){
        if(client.isEmpty()){
            System.out.println("O banco de Cliente está vazio.");
            return;
        }

        client.forEach(cl -> {
            System.out.println(cl);
            System.out.println("=".repeat(50));
        });
    }




}
