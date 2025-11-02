import org.example.controllers.ControlClient;
import org.example.entitys.Client;
import org.example.service.ServiceOrders;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.example.utils.connectionFactory.Connectivity.connectionDb;

public class Main{
    public static void main(String[] args) throws SQLException {
        Connection conn = connectionDb();
        ControlClient controlClient = new ControlClient(conn);
        try{
            Client client = controlClient.controlClientRegister();
            System.out.println(client.getId());


//            ServiceOrders servOrder = new ServiceOrders(conn);
//            servOrder.orderTransaction(2, List.of(4,5,7), List.of(2, 3, 4));
// 20712750711

        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }
}

// Terminar de colocar os DAOs e util, etc
// Anotar reforçar a responsabilidade de cada camada (apenas uma camada controla conexão)
// Fazer commit sempre que achar que vai estragar tudo
// Ficar atento com acoplamento
// Injetar pelo construtor, permite eu usar a classe livremente. (como?)
