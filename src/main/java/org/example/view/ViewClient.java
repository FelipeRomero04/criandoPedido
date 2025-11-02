package org.example.view;

import org.example.entitys.Client;

import java.util.Scanner;

import static org.example.validations.ClientValidation.*;

public class ViewClient {
    private final Scanner input = new Scanner(System.in);

    public Client clientData(){
        System.out.println("Nome: ");
        String name = clientNameValid(input.nextLine());

        System.out.println("Email: ");
        String email = emailValid(input.nextLine());

        System.out.println("CPF: ");
        String cpf = cpfValid(input.nextLine());

        return new Client(name, email,cpf);
    }

    public Client clientLogged(){
        System.out.println("Informe o email do usuário:  ");
        String email = emailValid(input.nextLine());

        return new Client(email);
    }
}
