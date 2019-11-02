package sample.modling;

public class selltable {

private int Bill_ID;
private String Bill_owner;
private int Owner_phone;
private int Total;
private String Product_id;
private String Price;
private String Amount;
private int price_X_amount;

    public selltable(int bill_ID, String bill_owner, int owner_phone, int total, String product_id, String price, String amount, int price_X_amount) {
        Bill_ID = bill_ID;
        Bill_owner = bill_owner;
        Owner_phone = owner_phone;
        Total = total;
        Product_id = product_id;
        Price = price;
        Amount = amount;
        this.price_X_amount = price_X_amount;
    }

    public int getBill_ID() {
        return Bill_ID;
    }

    public void setBill_ID(int bill_ID) {
        Bill_ID = bill_ID;
    }

    public String getBill_owner() {
        return Bill_owner;
    }

    public void setBill_owner(String bill_owner) {
        Bill_owner = bill_owner;
    }

    public int getOwner_phone() {
        return Owner_phone;
    }

    public void setOwner_phone(int owner_phone) {
        Owner_phone = owner_phone;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public String getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(String product_id) {
        Product_id = product_id;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public int getPrice_X_amount() {
        return price_X_amount;
    }

    public void setPrice_X_amount(int price_X_amount) {
        this.price_X_amount = price_X_amount;
    }
}
