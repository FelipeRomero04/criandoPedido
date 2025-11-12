import org.example.controllers.ControlClient;
import org.example.controllers.ControlOrder;
import org.example.controllers.ControlProduct;
import org.example.entitys.Client;

import java.sql.Connection;

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
                System.out.print("Opção: ");
                String option = inputValid(input.nextLine());

                switch (option) {
                    case "1" -> {
                        do {
                            menuClients();
                            System.out.print("Opção: ");
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
                            System.out.print("Opção: ");
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
                        System.out.print("Opção: ");
                        optionCase = inputValid(input.nextLine());

                        if (optionCase.equals("1")) {
                            controlOrder.startedTransaction(client);
                            return;
                        }
                    }
                    default -> System.out.println("Nenhuma opção do menu foi selecionada.");
                }

                if(option.isBlank()){
                    break;
                }

            }catch(RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Voltando ao Menu...");
            }
        }
        System.out.println("VOLTE SEMPRE!");
    }
}