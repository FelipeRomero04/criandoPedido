package org.example.entitys;

import java.util.List;

public class Order_item {
    private Integer id;
    private Integer order_id;
    private List<Integer> products_id;
    private List<Integer> quantities;

    public Order_item(Integer id, Integer order_id, List<Integer> products_id, List<Integer> quantities) {
        this.id = id;
        this.order_id = order_id;
        this.products_id = products_id;
        this.quantities = quantities;
    }

    public Order_item(Integer order_id,List<Integer> products_id, List<Integer> quantities){
        this(null, order_id, products_id, quantities);
    }

    public Order_item(List<Integer> products_id, List<Integer> quantities){
        this(null, null, products_id, quantities);
    }

    public Order_item(){}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public List<Integer> getProducts_id() {
        return products_id;
    }

    public void setProducts_id(List<Integer> products_id) {
        this.products_id = products_id;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Integer> quantities) {
        this.quantities = quantities;
    }
}
