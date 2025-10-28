package org.example.controllers;

import org.example.Dao.ClientDAO;
import org.example.entitys.Client;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import static org.example.utils.dateUtils.Utils.clientById;
import static org.example.utils.dateUtils.Utils.printTableClients;
import static org.example.validations.ClientValidation.*;

public class ControlClient {
    private final Scanner input = new Scanner(System.in);
    private final Connection conn;
    private final ClientDAO clientDAO;

    public ControlClient(Connection conn){
        this.conn = conn;
        this.clientDAO = new ClientDAO(conn);

    }

    public void clientMenu() {
        while (true) {
            List<Client> clients = clientDAO.selectClients();
            System.out.println("""
                    1. Cadastrar cliente
                    2. Atualizar dados do cliente
                    3. Mostrar lista de clientes
                    4. Deletar cliente""");

            System.out.print("Opção do Cliente: ");
            int option = Integer.parseInt(input.nextLine());
            if (option > 4) {
                break;
            }

            switch (option) {
                case (1) -> {
                    System.out.print("Nome do usuário: ");
                    String name = clientNameValid(input.nextLine());

                    System.out.print("Email: ");
                    String email = emailValid(input.nextLine());

                    System.out.print("CPF: ");
                    String cpf = cpfValid(input.nextLine());
                    clientDAO.saveClient(name, email, cpf);
                }
                case (2) -> {
                    printTableClients(clients);

                    System.out.print("Id do cliente a ser atualizado: ");
                    int id = Integer.parseInt(input.nextLine());
                    Client oldClient = clientById(id);

                    System.out.print("Nome do usuário(atual: " + oldClient.getName() + "): ");
                    String name = clientNameValid(input.nextLine());
                    if (name.isBlank()) {
                        name = oldClient.getName();
                    }


                    System.out.print("Email(atual: " + oldClient.getEmail() + "): ");
                    String email = emailValid(input.nextLine());
                    if (email.isBlank()) email = oldClient.getEmail();

                    System.out.print("Cpf(atual: " + oldClient.getCpf() + "): ");
                    String cpf = cpfValid(input.nextLine());
                    if (cpf.isBlank()) cpf = oldClient.getCpf();

                    Client newClient = new Client(oldClient.getId(), name, email, cpf);
                    clientDAO.updateClient(newClient);
                }

                case (3) -> printTableClients(clients);


                case (4) -> {
                    printTableClients(clients);

                    System.out.print("Informe o id do cliente: ");
                    int id = Integer.parseInt(input.nextLine());
                    clientDAO.deleteClient(id);

                    System.out.print("Cliente removido!");

                }
            }
        }
        System.out.println("Retornando ao menu principal...");
    }
}