package org.example.entitys;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Order_item {
    private Integer client_id;
    private Map<Integer,Integer> product_id;

    public Order_item(Integer client_id, Map<Integer, Integer> product_id) {
        this.client_id = client_id;
        this.product_id = product_id;
    }
}
