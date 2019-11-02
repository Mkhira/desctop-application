package sample.modling;

public class solds {
    private int ID;
    private int Bill_ID;
    private int Product_id;
    private int Amount;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return
                "ID=" + ID +
                ", Bill_ID=" + Bill_ID +
                ", Product_id=" + Product_id +
                ", Amount=" + Amount
                ;
    }

    public int getBill_ID() {
        return Bill_ID;
    }

    public void setBill_ID(int bill_ID) {
        Bill_ID = bill_ID;
    }

    public int getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(int product_id) {
        Product_id = product_id;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }
}
