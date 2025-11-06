import org.example.controllers.ControlClient;
import org.example.controllers.ControlOrder;
import org.example.controllers.ControlProduct;
import org.example.entitys.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static org.example.utils.Connectivity.connectionDb;
import static org.example.utils.MenuMain.*;

public class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Connection conn = connectionDb();
        ControlClient controlClient = new ControlClient(conn);
        ControlProduct controlProduct = new ControlProduct(conn);
        ControlOrder controlOrder = new ControlOrder(conn);

        try {
            Client client;
            while(true){
                menuMain();
                System.out.println("Opção: ");
                int option = Integer.parseInt(input.nextLine());
                switch (option){
                    case 1 -> {
                        while(true){
                            try{
                                menuClients();
                                System.out.println("Opção: ");
                                option = Integer.parseInt(input.nextLine());

                                switch (option) {
                                    case 1 ->{
                                        client = controlClient.controlClientRegister();
                                    }
                                    case 2 -> {
                                        client = controlClient.controlClientLogged();
                                    }
                                    case 3 -> controlClient.controlClientUpdate();
                                    case 4 -> controlClient.controlClientDelete();
                                }
                            }catch (NumberFormatException e){
                                System.out.println("Voltando ao menu principal");
                                break;
                            } catch (SQLException e) {
                                throw new RuntimeException(e); //generalizar excessão no control
                            }

                        }
                    }
                    case 2 -> {
                        while (true) {
                            try {
                                menuProduct();
                                System.out.println("Opção: ");
                                option = Integer.parseInt(input.nextLine());

                                switch (option) {
                                    case 1 -> controlProduct.productRegister();
                                    case 2 -> controlProduct.productUpdate();
                                    case 3 -> controlProduct.productDelete();
                                }
                            } catch (NumberFormatException | SQLException e) {
                                System.out.println("Voltando ao menu principal");
                                break;
                            }
                        }
                    }
                    case 3 -> {
                        while (true) {
                            try {
                                menuOrder();
                                System.out.println("Opção: ");
                                option = Integer.parseInt(input.nextLine());

                                switch (option) {
                                    case 1 -> controlOrder.startedTransaction();
                                    //case 2 -> ver pedido
                                }
                            } catch (NumberFormatException | SQLException e) {
                                System.out.println("Voltando ao menu principal");
                                break;
                            }

                        }
                    }
                }
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

// Terminar de colocar os DAOs e util, etc
// Anotar reforçar a responsabilidade de cada camada (apenas uma camada controla conexão)
// Fazer commit sempre que achar que vai estragar tudo
// Ficar atento com acoplamento
// Injetar pelo construtor, permite eu usar a classe livremente. (como?)




// 1.Clientes -> cadastrar, logar, atualizar dados do clinte, deletar
// 2.Pedidos -> cadastrar produto, atualizar dados do produto, deletar
// 3.Pedido -> Ir as compras, ver carrinho