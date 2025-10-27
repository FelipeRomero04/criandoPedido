import org.example.service.ServiceOrders;
import java.sql.Connection;
import java.sql.SQLException;
import static org.example.utils.connectionFactory.Connectivity.connectionDb;

public class Main{
    public static void main(String[] args) throws SQLException {

        Connection conn = connectionDb();
        try{
            ServiceOrders servOrder = new ServiceOrders(conn);
            servOrder.orderTransaction();
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
}

// Terminar de colocar os DAOs e util, etc
// Anotar reforçar a responsabilidade de cada camada (apenas uma camada controla conexão)
// Fazer commit sempre que achar que vai estragar tudo
// Ficar atento com acoplamento
// Injetar pelo construtor, permite eu usar a classe livremente. (como?)
