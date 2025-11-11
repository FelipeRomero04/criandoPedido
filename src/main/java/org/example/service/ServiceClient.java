package org.example.service;

import org.example.entitys.Client;
import org.example.repository.RepoClient;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ServiceClient {
    private final RepoClient repoClient;

    public ServiceClient(Connection conn){
        repoClient = new RepoClient(conn);
    }

    public Client serviceSaveClient(Client client) throws SQLException{
        if(repoClient.isExist(client.getEmail())){
            System.out.println("Esse usuário ja existe. Logando na conta...");
            return clientLogged(client);
        }
        repoClient.saveClient(client);
        return clientLogged(client);
    }

    public Client clientLogged(Client client) throws SQLException {
        Client clientDb = repoClient.LoginUser(client);
        if(clientDb == null){
            throw new RuntimeException("Esse usuário não foi encontrado. Selecione a opção de Registrar-se.");
        }
        return clientDb;
    }

    public List<Client> getListClients(){
        return repoClient.findAll();
    }

    public Client getClientById(int id) throws SQLException{
        return repoClient.findById(id);
    }

    public void setClientUpdate(Client newClient) throws SQLException{
        repoClient.clientUpdate(newClient);
    }

    public void clientDeleted(int idDeleted) throws SQLException{
        repoClient.clientDelete(idDeleted);
    }


}
