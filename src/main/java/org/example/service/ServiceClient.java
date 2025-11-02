package org.example.service;

import org.example.entitys.Client;
import org.example.repository.RepoClient;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceClient {
    private final RepoClient repoClient;

    public ServiceClient(Connection conn){
        repoClient = new RepoClient(conn);
    }

    public Client serviceSaveClient(Client client) throws SQLException{
        if(repoClient.isExist(client.getEmail())){
            System.out.println("Esse usuário ja existe");
            return clientLogged(client);
        }
        repoClient.saveClient(client);
        return clientLogged(client);
    }

    public Client clientLogged(Client client) throws SQLException {
        return repoClient.LoginUser(client);
    }



}
