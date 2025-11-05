package org.example.entitys;

public class Product {
    private Integer id;
    private String name;
    private Double price;
    private Integer stock;

    public Product(Integer id, String name, Double price, Integer stock){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, Double price, Integer stock){
        this(null, name, price, stock);
    }

    public Product(){}

    @Override
    public String toString() {
        return "ID: "+String.format("%-20d", id) + "Nome: "+String.format("%-20s", name) +"Preço: " + String.format("%-20.2f", price) + "Estoque: " + String.format("%-20d", stock);
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
