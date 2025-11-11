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

    public Order_item createCart(Client client ,List<Product> products){
        id_products.clear();
        quantities.clear();

        while(true){
            try{
                products.forEach(System.out::println);
                System.out.println("Qual produto deseja(id): ");
                String id_product = input.nextLine();
                if (id_product.isBlank()){
                    System.out.println("Visualizando carrinho: ");
                    viewCart(client, products);
                    System.out.println("Deseja continuar[S/N]:");
                    String option = input.nextLine();
                    if (option.equals("Sim")){ //Melhorar isso
                        continue;
                    }
                    break;
                }


                System.out.println("Quantidade: ");
                String quantity = input.nextLine();

                id_products.add(Integer.parseInt(id_product));
                quantities.add(Integer.parseInt(quantity));


            }catch(NumberFormatException e){
                System.out.println("Quantidade não foi inserida corretamente");
            }
            // id do produto não existe
        }
        return new Order_item(id_products,quantities);
    }


    public void viewCart(Client client, List<Product> products){

        double total = 0;
        System.out.println("Client: "+client.getName()+"\nItems:");
        for (int i = 0; i < id_products.size(); i++) {

            int id = id_products.get(i);
            Product product = products.stream().filter(prod -> prod.getId() == id).findFirst().orElse(new Product());
            total += product.getPrice() * quantities.get(i);
            System.out.printf("- %s (%dx)\n", product.getName(), quantities.get(i));
        }
        System.out.println("Total: R$"+ String.format("%.2f", total));

    }
}
//Talvez createCart e viewCart devam ser metedos complemetares,
// um não pode ser chamado sem o outro

//Vou confirma a transação em ver o carrinho, dessa forma vai ser possivel adicionar
//mais coisas depois