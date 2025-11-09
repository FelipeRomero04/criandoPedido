package org.example.view;

import org.example.entitys.Client;
import org.example.entitys.Order_item;
import org.example.entitys.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewOrder {
    private final Scanner input = new Scanner(System.in);
    private final List<Integer> id_products = new ArrayList<>();
    private final List<Integer> quantities = new ArrayList<>();

    public Order_item createCart(List<Product> products){
        id_products.clear();
        quantities.clear();

        while(true){
            try{
                products.forEach(System.out::println);
                System.out.println("Qual produto deseja(id): ");
                int id_product = Integer.parseInt(input.nextLine());
                id_products.add(id_product);

                System.out.println("Quantidade: ");
                int quantity = Integer.parseInt(input.nextLine());
                quantities.add(quantity);

            }catch(NumberFormatException e){
                System.out.println("Fechando carrinho...");
                break;
            }
            // id do produto não existe
        }
        return new Order_item(id_products,quantities);
    }


    public void viewCart(Client client, List<Product> products){
        String name = client.getName();
        double total = 0;


        for (int i = 0; i < id_products.size(); i++) {
            String productName = products.get(id_products.get(i)).getName();
            total += products.get(id_products.get(i)).getPrice();
            System.out.printf("""
                    Client: %s
                    items:
                     - %s (%dx)""", client.getName(), productName, quantities.get(i));
        }
        System.out.println("Total: "+total);

    }
}

//Talvez createCart e viewCart devam ser metedos complemetares,
// um não pode ser chamado sem o outro

//Vou confirma a transação em ver o carrinho, dessa forma vai ser possivel adicionar
//mais coisas depois