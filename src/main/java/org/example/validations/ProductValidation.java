package org.example.validations;

public class ProductValidation {

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
}
