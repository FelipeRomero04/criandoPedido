package org.example.entitys;

public class Order {
    private Integer id;
    private Integer client_id;
    private String date;
    private Double total_value = 0.0;

    public Order(Integer id, Integer client_id,String date, Double total_value) {
        this.id = id;
        this.client_id = client_id;
        this.date = date;
        this.total_value = total_value;
    }

    public Order(Integer client_id, String date, Double total_value) {
        this.client_id = client_id;
        this.date = date;
        this.total_value = total_value;
    }

    public Order(){}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getTotal_value() {
        return total_value;
    }

    public void setTotal_value(Double total_value) {
        this.total_value = total_value;
    }
}
