package org.example.controllers;

import org.example.Dao.ProductDAO;
import org.example.entitys.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static org.example.utils.dateUtils.Utils.printTableProducts;
import static org.example.utils.dateUtils.Utils.productById;
import static org.example.validations.ProductValidation.*;

public class ControlProduct {
    private final Scanner input = new Scanner(System.in);
    private final Connection conn;
    private final ProductDAO productDAO;

    public ControlProduct(Connection conn){
        this.conn = conn;
        this.productDAO = new ProductDAO(conn);
    }

    public void productMenu() throws SQLException {
        while(true) {
            List<Product> productsTable = productDAO.selectProduct();
            System.out.println("""
                    1. Cadastrar produto
                    2. Atualizar dados do produto
                    3. Mostrar lista de produtos
                    4. Deletar produto""");

            System.out.print("Opção do Produto: ");
            int option = Integer.parseInt(input.nextLine());
            switch (option) {
                case (1) -> {
                    System.out.print("Nome do produto: ");
                    String name = nameProductValid(input.nextLine());

                    System.out.print("Preço: ");
                    Double price = priceProductValid(input.nextLine());

                    System.out.print("Estoque: ");
                    int quantity = quantityProductValid(input.nextLine());
                    productDAO.saveProduct(name, price, quantity);
                }
                case (2) -> {
                    printTableProducts(productsTable);

                    System.out.print("Id do Produto a ser atualizado: ");
                    int id = Integer.parseInt(input.nextLine());
                    Product oldProduct = productById(id);

                    System.out.print("Nome do produto(atual: " + oldProduct.getName() + "): ");
                    String name = nameProductValid(input.nextLine());
                    if (name.isBlank()){
                        name = oldProduct.getName();
                    }

                    System.out.print("Preço(atual: " + oldProduct.getPrice() + "): ");
                    double price = priceProductValid(input.nextLine());
                    if(price == -1){
                        price = oldProduct.getPrice();
                    }

                    System.out.print("Estoque(atual: " + oldProduct.getQuantity() + "): ");
                    int quantity = quantityProductValid(input.nextLine());
                    if(price == -1){
                        price = oldProduct.getQuantity();
                    }

                    Product newProduct = new Product(oldProduct.getId(), name, price, quantity);
                    productDAO.updateProduct(newProduct);
                }

                case (3) -> printTableProducts(productsTable);


                case (4) -> {

                    printTableProducts(productsTable);

                    System.out.print("Informe o id do cliente: ");
                    int id = Integer.parseInt(input.nextLine());
                    productDAO.deleteProduct(id);

                }

                default -> System.out.print("Retornando ao menu principal...");

            }
            if (option > 4) {
                break;
            }

        }

    }
}
