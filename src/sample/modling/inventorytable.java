package sample.modling;

public class inventorytable {

    private int id;
    private String name;
    private String model;
    private String price;
    private String amount;

    public inventorytable(int id, String name, String model, String price, String amount) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.price = price;
        this.amount = amount;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
