package sample.modling;

import java.sql.Date;

public class Billtable {

    private int id;
    private String name;
    private int phone;
    private int total;

    public Billtable(int id, String name, int phone, int total) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
