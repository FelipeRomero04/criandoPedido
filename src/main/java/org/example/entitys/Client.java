package org.example.entitys;

public class Client {
    private Integer id;
    private String name;
    private String email;
    private String cpf;

    public Client(Integer id, String name, String email, String cpf){
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public Client(String name, String email, String cpf){
        this(null, name, email, cpf);
    }

    public Client(String email) {this.email = email;}
    public Client(){}

    @Override
    public String toString() {
//        return id +"\t\t"+
//                 name + "\t\t" +
//                 email +
//                "\t\t" + cpf;

        return "ID: "+String.format("%-20d", id) + "Nome: "+String.format("%-20s", name) +"Email: " + String.format("%-20s", email) + "CPF: " + String.format("%-20s", cpf);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}

