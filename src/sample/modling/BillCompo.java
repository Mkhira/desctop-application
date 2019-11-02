package sample.modling;

public class BillCompo {
    private int id;
    private String name;
    private int prodid;
    private  int am;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "Bill id=" + id +
                " owner name=" + name + '\''


                ;
    }

    public int getProdid() {
        return prodid;
    }

    public void setProdid(int prodid) {
        this.prodid = prodid;
    }

    public int getAm() {
        return am;
    }

    public void setAm(int am) {
        this.am = am;
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

}
