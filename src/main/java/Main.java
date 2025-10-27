import org.example.Dao.ProductDao;
import org.example.entitys.Order;
import org.example.entitys.Product;
import org.example.service.ServiceOrder;
import org.example.service.ServiceOrders;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.example.utils.connectionFactory.Connectivity.connectionDb;

public class Main{
    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        try{
            conn = connectionDb();
            ServiceOrders order = new ServiceOrders(conn);
            conn.setAutoCommit(false);

            Order order1 = new Order(2, "2025-05-10 17:40:20", 0.0);

            int order_id = order.saveOrder(order1);
            order.fazendoPedido(order_id, List.of(4, 5, 6), List.of(2, 3, 7));

            conn.commit();
        }catch (NullPointerException e){
            if(conn != null) conn.rollback();
            e.printStackTrace();
        }
        if(conn != null) conn.close();
    }
}

//passar isso pro service e o service pro repossitory