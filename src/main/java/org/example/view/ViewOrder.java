package org.example.view;

import org.example.entitys.Order_item;
import org.example.entitys.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewOrder {
    private final Scanner input = new Scanner(System.in);
    private final List<Integer> id_products = new ArrayList<>();
    private final List<Integer> quantities = new ArrayList<>();

    public Order_item createCart(List<Product> products){ // montando carrinho
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
}
