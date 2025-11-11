import org.example.controllers.ControlClient;
import org.example.controllers.ControlOrder;
import org.example.controllers.ControlProduct;
import org.example.entitys.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static org.example.utils.Connectivity.connectionDb;
import static org.example.utils.MenuMain.*;
import static org.example.validations.inputValidations.inputValid;

public class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Connection conn = connectionDb();
        ControlClient controlClient = new ControlClient(conn);
        ControlProduct controlProduct = new ControlProduct(conn);
        ControlOrder controlOrder = new ControlOrder(conn);

        Client client = new Client();
        String optionCase;
        while(true){
            try{
                menuMain();
                System.out.println("Opção: ");
                String option = inputValid(input.nextLine());
                 //validar isso
                switch (option) {
                    case "1" -> {
                        do {
                            menuClients();
                            System.out.println("Opção: ");
                            optionCase = inputValid(input.nextLine());

                            switch (optionCase) {
                                case "1" -> client = controlClient.controlClientRegister();

                                case "2" -> client = controlClient.controlClientLogged();

                                case "3" -> controlClient.controlClientUpdate();

                                case "4" -> controlClient.controlClientDelete();
                            }

                        }while (!optionCase.isBlank());
                    }
                    case "2" -> {
                        do {
                            menuProduct();
                            System.out.println("Opção: ");
                            optionCase = inputValid(input.nextLine());

                            switch (optionCase) {
                                case "1" -> controlProduct.productRegister();
                                case "2" -> controlProduct.productUpdate();
                                case "3" -> controlProduct.productDelete();
                            }

                        } while(!optionCase.isBlank());
                    }
                    case "3" -> {
                        menuOrder();
                        System.out.println("Opção: ");
                        optionCase = inputValid(input.nextLine());

                        if (optionCase.equals("1")) {
                            controlOrder.startedTransaction(client);
                            return;
                        }
                    }
                    default -> System.out.println("Não existe essa opção no Menu no momento.");

                }

                if(option.isBlank()){
                    break;
                }

            }catch(RuntimeException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.out.println("Voltando ao Menu...");
            }
        }
        System.out.println("VOLTE SEMPRE!");

    }
}

//DAr erro "voce precisa estar logado para fazer um pedido"


// Terminar de colocar os DAOs e util, etc
// Anotar reforçar a responsabilidade de cada camada (apenas uma camada controla conexão)
// Fazer commit sempre que achar que vai estragar tudo
// Ficar atento com acoplamento
// Injetar pelo construtor, permite eu usar a classe livremente. (como?)




// 1.Clientes -> cadastrar, logar, atualizar dados do clinte, deletar
// 2.Pedidos -> cadastrar produto, atualizar dados do produto, deletar
// 3.Pedido -> Ir as compras, ver carrinho

//Erro quando o id do produto não existe