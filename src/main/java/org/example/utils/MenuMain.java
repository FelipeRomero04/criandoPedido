package org.example.utils;

public class MenuMain {

    public static void menuMain(){
        System.out.println("""
                1. Clientes
                2. Produto
                3. Pedidos""");
    }

    public static void menuClients(){
        System.out.println("""
                1. Cadastrar-se
                2. Entrar
                3. Atualizar Dados do Cliente
                4. Deletar Cliente""");
    }

    public static void menuProduct(){
        System.out.println("""
                1. Cadastrar produto
                2. Atualizar Dados do Produto
                3. Deletar Produto""");
    }

    public static void menuOrder(){
        System.out.println("""
                1. Ir as Compras
                """);
    }

}
