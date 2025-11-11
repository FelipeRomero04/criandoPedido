package org.example.validations;

import java.sql.SQLIntegrityConstraintViolationException;

public class inputValidations {

    public static String clientNameValid(String name){
        if(name.matches("^[\\p{L} ]+$") || name.isBlank()){
            return name;
        }
        throw new IllegalArgumentException("Campo \"nome\" preenchido incorretamente. Tente novamente.");

    }

    public static String emailValid(String email){
        if(email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z.-]+\\.[A-Za-z]{2,}$")
           || email.isBlank()){
            return email;
        }


        throw new IllegalArgumentException("Campo \"email\" preenchido incorretamente. Tente novamente.");
    }

    public static String cpfValid(String cpf){
        if(cpf.isBlank()){
            return cpf;
        }

        if (cpf.matches("^\\d{3}[.]\\d{3}[.]\\d{3}-\\d{2}$")){
            cpf = cpf.replaceAll("[^0-9]", "");
        }
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("O CPF informado e inválido. Deve conter 11 números.");
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (10 - i);
        }
        int rest = (sum * 10) % 11;
        int firstCod = (rest > 9) ? 0: rest;

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * (11 - i);
        }
        rest = (sum * 10) % 11;

        int secondCode = (rest > 9) ? 0 : rest;

        if((cpf.charAt(9) - '0') == firstCod && (cpf.charAt(10) - '0') == secondCode ){
            return cpf;
        }

        System.out.println((cpf.charAt(10) == secondCode));
        throw new IllegalArgumentException("O cpf informado não existe. Informe novamente.");


    }

    public static String productValidName(String name){
        if (name.matches("^[0-9]{0,3}[\\p{L} ]+[0-9]{0,3}$") || name.isBlank()){
            return name;
        }
        throw new IllegalArgumentException("O nome informado é inválido. Reformule e tente novamente.");

    }

    public static double priceProductValid(String price){
        try{
            if(price.isBlank()){
                return -1;
            }
            return Double.parseDouble(price);
        }catch (NumberFormatException e){
            throw new NumberFormatException("O preço informado é inválido. Reajuste e tente novamente.");
        }
    }

    public static int stockProductValid(String stock){
        try{
            if(stock.isBlank()){
                return -1;
            }
            return Integer.parseInt(stock);
        }catch (NumberFormatException e){
            throw new NumberFormatException("O estoque informado é inválido. Reajuste e tente novamente;");
        }
    }

    public static String inputValid(String option){
        if(option.matches("[^0-9]")){
            throw new NumberFormatException("Opção inválida. Tente Novamente");
        }
        return option;
    }

}
