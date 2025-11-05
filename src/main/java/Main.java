import org.example.controllers.ControlClient;
import org.example.controllers.ControlOrder;
import org.example.controllers.ControlProduct;

import java.sql.Connection;
import java.sql.SQLException;

import static org.example.utils.Connectivity.connectionDb;

public class Main{
    public static void main(String[] args){
        Connection conn = connectionDb();
        ControlClient controlClient = new ControlClient(conn);
        ControlProduct controlProduct = new ControlProduct(conn);
        ControlOrder controlOrder = new ControlOrder(conn);

        try {

            controlOrder.startedTransaction();
            System.out.println("Produto Deletado");


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
