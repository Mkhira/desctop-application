package sample.modling;

public class inventoryCompo {

    private int id;
    private int price;
    private String name;
    private int amoun;

    public int getAmoun() {
        return amoun;
    }

    public void setAmoun(int amoun) {
        this.amoun = amoun;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    @Override
    public String toString() {
        return
                "id=" + id +
                " price=" + price +
                " name= " + name + '\'' +
                " amount=" + amoun
               ;
    }
}


