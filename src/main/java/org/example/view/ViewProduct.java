package org.example.view;

import org.example.entitys.Product;

import java.util.List;
import java.util.Scanner;

import static org.example.validations.inputValidations.*;


public class ViewProduct {
    private final Scanner input = new Scanner(System.in);

    public Product productData(){
        System.out.println("===== REGISTRANDO PRODUTO =====");
        System.out.print("Nome Produto: ");
        String name = productValidName(input.nextLine());

        System.out.print("Preço: ");
        Double price = priceProductValid(input.nextLine());

        System.out.print("Estoque: ");
        Integer stock = stockProductValid(input.nextLine());

        return new Product(name,price, stock);
    }

    public int idProductChoose(List<Product> products){
        products.forEach(System.out::println);
        System.out.print("Selecione o produto(Id): ");
        return Integer.parseInt(input.nextLine()); //tratar esse retorno

    }

    public Product productUpdate(Product product){
        System.out.println("===== ATUALIZANDO DADOS DO PRODUTO=====");
        System.out.print("Nome (atual: "+product.getName()+"): ");
        String name = clientNameValid(input.nextLine().trim());
        if(name.isBlank()){
            name = product.getName();
        }

        System.out.print("Preço (atual: "+ product.getPrice()+"): ");
        double price = priceProductValid(input.nextLine().trim());
        if(price == -1){
            price = product.getPrice();
        }

        System.out.print("Estoque (atual: "+ product.getStock()+"): ");
        int stock = stockProductValid(input.nextLine().trim());
        if(stock == -1){
            stock = product.getStock();
        }

        return new Product(product.getId(), name, price, stock);

    }
}
