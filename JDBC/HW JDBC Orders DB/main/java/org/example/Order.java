package org.example;

import java.util.Arrays;
import java.util.List;

public class Order {
    private int id;
    private Client client;
    private List<Good> goods;
    private int orderSum;

    public Order() {
    }

    public Order(int id, Client client, List<Good> goods) {
        this.id = id;
        this.client = client;
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", goods=" + goods +
                '}';
    }
}
