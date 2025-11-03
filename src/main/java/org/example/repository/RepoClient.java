package org.example.repository;

import org.example.entitys.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepoClient {
    private final Connection conn;

    public RepoClient(Connection conn){
        this.conn = conn;
    }

    public void saveClient(Client client){
        String query = "INSERT INTO clients(name, email, cpf) VALUES (?, ?, ?);";

        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getEmail());
            stmt.setString(3, client.getCpf());

            stmt.executeUpdate();
        }catch(SQLException e){
            //throw new RuntimeException("FALHA: Ao cadastrar cliente. Verifique query/conexão.");
            e.printStackTrace();
        }

        System.out.println("Cliente cadastrado com sucesso.");

    }

    public Client LoginUser(Client client) throws SQLException{
        String query = "SELECT * FROM clients WHERE email = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1,client.getEmail());
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    System.out.println("Login feito com êxito");
                    return new Client(rs.getInt(1),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("cpf"));
                }
            }
        }
        throw new IllegalArgumentException("Falha no Login. Usuário não existe.");
    }



    public List<Client> findAll(){
        String query = "SELECT * FROM clients ORDER BY id;";
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


    public Client findById(int client_id) throws SQLException{
        String query = "SELECT * FROM clients WHERE id = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, client_id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return new Client(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("cpf"));
                }
            }
        }
        throw new SQLException("Nenhum usuário com esse ID foi encontrado");

    }

    public void clientUpdate(Client client) throws SQLException{
        String query = "UPDATE clients SET name=?, email=?, cpf=? WHERE id = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getEmail());
            stmt.setString(3, client.getCpf());
            stmt.setInt(4, client.getId());

            stmt.executeUpdate();
            System.out.println("Cliente atualizado com sucesso!");
        }

    }

    public void clientDelete(int idDeleted) throws SQLException{
        String query = "DELETE FROM clients WHERE id = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, idDeleted);

            stmt.executeUpdate();
        }
    }


    public boolean isExist(String email){
        String query = "SELECT COUNT(*) FROM clients WHERE email = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, email);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt("count") > 0;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }




    public boolean isExistById(int id) throws SQLException{
        String query = "SELECT COUNT(*) FROM clients WHERE id = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt("id") > 0;
                }
            }
        }
        return false;
    } //Ver pro que esta sendo usado





}
