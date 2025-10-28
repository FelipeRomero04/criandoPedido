package org.example.Dao;

import org.example.entitys.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private final Connection conn;

    public ClientDAO(Connection conn){
        this.conn = conn;
    }

    public void saveClient(String name, String email, String cpf){
        String query = "INSERT INTO client(name, email, cpf) VALUES (?, ?, ?);";

        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, cpf);

            stmt.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException("FALHA: Ao cadastrar cliente. Verifique query/conexão.");
        }

        System.out.println("Cliente cadastrado com sucesso.");
    }

    public List<Client> selectClients(){
        String query = "SELECT * FROM client ORDER BY id;";
        List<Client> clients = new ArrayList<>();

        try(PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                clients.add(new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("cpf")
                ));
            }
            return clients;

        }catch (SQLException e){
            throw new RuntimeException("FALHA: Ao retornar clientes. Verifique query/conexão ");
        }
    }

    public void updateClient(Client client){
        String query = "UPDATE client SET name = ?, email = ?, cpf = ? WHERE id = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, client.getName());
            stmt.setString(2, client.getEmail());
            stmt.setString(3, client.getCpf());
            stmt.setInt(4, client.getId());

            stmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("FALHA: Ao atualizar cliente. Verificar query/conexão");
        }
        System.out.println("Cliente atualizado com sucesso.");
    }

    public void deleteClient(int id){
        String query = "DELETE FROM client WHERE id = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("FALHA: Ao remover cliente. Verifique query/conexão;");
        }
        System.out.println("Cliente removido com sucesso.");
    }

}
