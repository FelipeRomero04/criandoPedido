package org.example.view;

import org.example.entitys.Client;

import java.util.List;
import java.util.Scanner;

import static org.example.validations.ClientValidation.*;

public class ViewClient {
    private final Scanner input = new Scanner(System.in);

    public Client clientData(){
        System.out.println("===== REGISTRANDO CLIENTE =====");
        System.out.print("Nome: ");
        String name = clientNameValid(input.nextLine());

        System.out.print("Email: ");
        String email = emailValid(input.nextLine());

        System.out.print("CPF: ");
        String cpf = cpfValid(input.nextLine());

        return new Client(name, email,cpf);
    }

    public Client clientLogged(){
        System.out.print("Informe o email do usuário:  ");
        String email = emailValid(input.nextLine());

        return new Client(email);
    }

    public int idClientChoose(List<Client> clients){
        clients.forEach(System.out::println);
        System.out.print("Selecione o usuário(Id): ");
        return Integer.parseInt(input.nextLine());

    }


    public Client clientUpdate(Client client){
        System.out.println("===== ATUALIZANDO DADOS DO CLIENTE =====");
        //Se der enter(vazio) continua com mesmo valor
        System.out.print("Nome (atual: "+client.getName()+"): ");
        String name = clientNameValid(input.nextLine().trim());
        if(name.isBlank()){
            name = client.getName();
        }

        System.out.print("Email (atual: "+client.getEmail()+"): ");
        String email = emailValid(input.nextLine().trim());
        if(email.isBlank()){
            email = client.getEmail();
        }
        System.out.print("CPF (atual: "+client.getCpf()+"): ");
        String cpf = cpfValid(input.nextLine().trim());
        if(cpf.isBlank()){
            cpf = client.getCpf();
        }
        return new Client(client.getId(), name, email, cpf);

    }

//    public Product productData(){
//
//    }
//
//
//
//    public Product chooseProduct(){
//
//    }
}

/*
    Metodo para cadatrar e sem retornar o produto

    Metodo para escolher o produto

    Criar uma classe Cart que herda produtos??
 */