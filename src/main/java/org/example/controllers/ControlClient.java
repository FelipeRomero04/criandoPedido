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
            System.out.println(clientEmail.getEmail());
            if(clientEmail.getEmail().isBlank()){
                throw new RuntimeException("Nenhum email foi informado.");
            }
            return serviceClient.clientLogged(clientEmail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void controlClientUpdate(){
        try{
            List<Client> clients = serviceClient.getListClients();
            int idForUpdated = viewClient.idClientChoose(clients);
            Client clientUpdate = serviceClient.getClientById(idForUpdated);
            clientUpdate = viewClient.clientUpdate(clientUpdate);
            serviceClient.setClientUpdate(clientUpdate);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void controlClientDelete(){
        try{
            List<Client> clients = serviceClient.getListClients();
            int idForDelete = 0;
            if (clients.isEmpty()) {
                idForDelete = viewClient.idClientChoose(clients);
            }
            serviceClient.clientDeleted(idForDelete);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}