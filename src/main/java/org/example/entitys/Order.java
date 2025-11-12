package org.example.entitys;

import java.time.LocalDateTime;


public class Order {
    private Integer id;
    private Integer client_id;
    private LocalDateTime date;
    private Double total_value = 0.0;

    public Order(Integer id, Integer client_id, Double total_value) {
        this.id = id;
        this.client_id = client_id;
        this.date = LocalDateTime.now();
        this.total_value = total_value;

    }

    public Order(Integer client_id, Double total_value) {
        this.client_id = client_id;
        this.total_value = total_value;
        this.date = LocalDateTime.now();
    }

    public Order(Integer client_id) {
        this.client_id = client_id;
        this.date = LocalDateTime.now();
    }

    public Order(){
        this.date = LocalDateTime.now();
    }


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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getTotal_value() {
        return total_value;
    }
    public void setTotal_value(Double total_value) {
        this.total_value = total_value;
    }
}
