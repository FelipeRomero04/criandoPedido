package org.example.utils;

public class MenuMain {

    public static void menuMain(){
        menuName("MENU PRINCIPAL");
        System.out.println(
                """
                1. Clientes
                2. Produto
                3. Pedidos
                -> PRESSIONE[ENTER] P/VOLTAR""");
    }

    public static void menuClients(){
        menuName("MENU CLIENTES");
        System.out.println(
                """
                1. Cadastrar-se
                2. Entrar
                3. Atualizar Dados do Cliente
                4. Deletar Cliente
                -> PRESSIONE[ENTER] P/VOLTAR""");
    }

    public static void menuProduct(){
        menuName("MENU PRODUTOS");
        System.out.println(
                """
                1. Cadastrar produto
                2. Atualizar Dados do Produto
                3. Deletar Produto
                -> PRESSIONE[ENTER] P/VOLTAR""");
    }

    public static void menuOrder(){
        menuName("MENU PEDIDOS");
        System.out.println(
                """
                1. Ir as Compras
                -> PRESSIONE[ENTER] P/VOLTAR
                """);
    }

    private static void menuName(String msg){
        System.out.println("=".repeat(50));
        System.out.printf("%33s\n", msg);
        System.out.println("=".repeat(50));
    }

}
