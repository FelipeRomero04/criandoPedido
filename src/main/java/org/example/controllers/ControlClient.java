package org.example.controllers;

import org.example.entitys.Client;
import org.example.service.ServiceClient;
import org.example.view.ViewClient;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ControlClient {
    private final ServiceClient serviceClient;
    private final ViewClient viewClient;

    public ControlClient(Connection conn){
        this.serviceClient = new ServiceClient(conn);
        this.viewClient = new ViewClient();
    }

    public Client controlClientRegister(){
        try{
            Client client = viewClient.clientData();
            return serviceClient.serviceSaveClient(client);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Client controlClientLogged(){
        try{
            Client clientEmail = viewClient.clientLogged();
            return serviceClient.clientLogged(clientEmail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void controlClientUpdate() throws SQLException{
        List<Client> clients = serviceClient.getListClients();
        int idForUpdated = viewClient.idClientChoose(clients);
        Client clientChoose = serviceClient.getClientById(idForUpdated);
        Client newClient = viewClient.clientUpdate(clientChoose);
        serviceClient.clientUpdated(newClient);

    } //Tente refatorar

    public void controlClientDelete() throws SQLException{
        List<Client> clients = serviceClient.getListClients();
        int idForDelete = viewClient.idClientChoose(clients);
        serviceClient.clientDeleted(idForDelete);
    }
}