package sample;

import Database.Databases;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import sample.modling.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Sell implements Initializable {



    @FXML
    JFXTextField owner,phone,amount,total,searchbox;
    @FXML
    JFXComboBox bid,product,sold;

  public ObservableList list = FXCollections.observableArrayList();
    public ObservableList pro = FXCollections.observableArrayList();
    public ObservableList so = FXCollections.observableArrayList();
    ObservableList<selltable> selltables = FXCollections.observableArrayList();
    @FXML
    private TableView<selltable> table;
    @FXML
    public TableColumn<Sell, String> ownern;
    public TableColumn<selltable, Integer> productidt;
    public TableColumn<selltable, Integer> Billidt;
    public TableColumn<selltable, Integer> ophone;
    public TableColumn<selltable, String> pricet;
    public TableColumn<selltable, String> amountt;
    public TableColumn<selltable,Integer> priceamount;


    public TableColumn<selltable, Integer> totalt;






    @FXML
    private void searchrecord(KeyEvent keyEvent) {

        FilteredList<selltable> filteredList = new FilteredList<>(selltables, p -> true);
        searchbox.textProperty().addListener((observable, oldvalue, newvalue) -> {

            filteredList.setPredicate(pres -> {
                if (newvalue == null || newvalue.isEmpty()) {

                    return true;
                }
                String typedtext = newvalue.toLowerCase();

                if (pres.getProduct_id().toLowerCase().indexOf(typedtext) != -1) {

                    return true;

                }
                if (pres.getBill_owner().toLowerCase().indexOf(typedtext) != -1) {

                    return true;

                }


                return false;
            });


            SortedList<selltable> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedList);

        });


    }



    public void add() throws Exception{




              if (owner.getText().isEmpty()&& phone.getText().isEmpty()){

           try {
               Connection connection = Databases.getConnection();



               PreparedStatement statement =  connection.prepareStatement("insert into solds (bill_id,product_id,price,amount,total) values (?,?,?,?,price*amount)");

                 BillCompo bidId =  (BillCompo) bid.getValue();

                inventoryCompo prouid = (inventoryCompo) product.getValue();
               statement.setInt(1, bidId.getId());
              statement.setInt(2, prouid.getId());
               statement.setInt(3,prouid.getPrice());
               statement.setString(4,amount.getText());
               statement.executeUpdate();


               inventoryCompo comp = (inventoryCompo)product.getValue();
               PreparedStatement statement1 = connection.prepareStatement("update inventoryes set amount=? where product_id="+comp.getId());


               int vlaueAmount = (comp.getAmoun()-Integer.parseInt(amount.getText()));
               statement1.setInt(1,vlaueAmount);


               statement1.executeUpdate();

               bidId.setName("");
               prouid.setName("");

               amount.setText("");
        retrive();
       // retrivelistproduct();
           } catch (ClassNotFoundException e1) {
               e1.printStackTrace();
           } catch (SQLException e1) {
               e1.printStackTrace();
           }}

           else {
                try {


                  Connection connection = Databases.getConnection();
                  PreparedStatement statement =  connection.prepareStatement("insert into bills (Bill_owner,Owner_phone) values (?,?)");
                  statement.setString(1,owner.getText());
                  statement.setString(2,phone.getText());
                    statement.executeUpdate();
                  phone.setText("");
                  owner.setText("");
                  retrive();
                }catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();

              }

           }
       }











    public void sell(ActionEvent event) {


        try {


            Connection connection = Databases.getConnection();
            PreparedStatement statement =  connection.prepareStatement("select sum(price*amount) from solds where bill_id=?");
            PreparedStatement statement1 = connection.prepareStatement("update bills set total=? where bill_id=?");

            BillCompo bidId =  (BillCompo) bid.getValue();
            statement.setInt(1, bidId.getId());

            ResultSet resultSet =statement.executeQuery();
             if (resultSet.next()){
                 String sum =resultSet.getString("sum(price*amount)");
                 total.setText(sum);
                 BillCompo bill =  (BillCompo) bid.getValue();

                 statement1.setString(1,total.getText());
                 statement1.setInt(2, bidId.getId());


                 statement1.executeUpdate();
                 retrive();
             }

        }catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();

        }


    }

public void back (ActionEvent event) throws Exception{


    try {

        Connection connection =Databases.getConnection();

        solds solo = (solds) sold.getValue();
        PreparedStatement statement1 = connection.prepareStatement("update solds set amount=? where ID="+solo.getID());
        int amou = (solo.getAmount()-Integer.parseInt(amount.getText()));

        statement1.setInt(1,amou);
        statement1.executeUpdate();

        inventoryCompo comp = (inventoryCompo)product.getValue();
        PreparedStatement statement= connection.prepareStatement("update inventoryes set amount=? where product_id="+comp.getId());
        int vlaueAmount = (comp.getAmoun()+Integer.parseInt(amount.getText()));
        statement.setInt(1,vlaueAmount);


        statement.executeUpdate();
retrive();






    }catch (ClassNotFoundException e1) {
        e1.printStackTrace();
    } catch (SQLException e1) {
        e1.printStackTrace();

    }




}



    @Override
    public void initialize(URL location, ResourceBundle resources) {
            try {
                Connection connection = Databases.getConnection();
                PreparedStatement statement =  connection.prepareStatement("select Bill_id,Bill_owner from bills");

                ResultSet rs = null;



                rs =statement.executeQuery();

                while (rs.next()){
                    BillCompo bill = new BillCompo();
                    bill.setId(rs.getInt("Bill_id"));
                    bill.setName(rs.getString("Bill_owner"));
                  list.addAll(bill);

                }


                } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        bid.getItems().addAll(list);




        try {
            Connection connection = Databases.getConnection();
            PreparedStatement statement =  connection.prepareStatement("select  product_id,product_name,price ,amount from inventoryes");
            ResultSet rs1 = null;
            rs1 =statement.executeQuery();
            while (rs1.next()){
                inventoryCompo inventory =new inventoryCompo();
                inventory.setId(rs1.getInt("product_id"));
                inventory.setName(rs1.getString("product_name"));
                inventory.setPrice(rs1.getInt("price"));
                inventory.setAmoun(rs1.getInt("amount"));

                pro.add(inventory);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        product.getItems().addAll(pro);

        try {
            Connection connection = Databases.getConnection();
            PreparedStatement statement =  connection.prepareStatement("select  ID,bill_id,product_id,amount from solds");
            ResultSet rs1 = null;
            rs1 =statement.executeQuery();
            while (rs1.next()){

                solds solods =new solds();

                solods.setID(rs1.getInt("ID"));
                solods.setBill_ID(rs1.getInt("bill_id"));
                solods.setProduct_id(rs1.getInt("product_id"));
                solods.setAmount(rs1.getInt("amount"));

                so.add(solods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        sold.getItems().addAll(so);



        try {
            Connection connection = Databases.getConnection();
            PreparedStatement statement2 =  connection.prepareStatement("select bill.Bill_id,bill.Bill_owner,bill.Owner_phone,bill.Total ,sold.product_id,sold.price,sold.amount,sold.total from solds sold inner join bills bill on bill.Bill_id=sold.bill_id   ");

            ResultSet rs5 = null;



            rs5 =statement2.executeQuery();




            while (rs5.next()) {

                selltables.addAll(new selltable(rs5.getInt(1),rs5.getString(2),rs5.getInt(3)
                ,rs5.getInt(4),rs5.getString(5),rs5.getString(6),rs5.getString(7),rs5.getInt(8)));


            }

            Billidt.setCellValueFactory(new PropertyValueFactory<selltable,Integer>("Bill_ID"));
            ownern.setCellValueFactory(new PropertyValueFactory<Sell,String>("Bill_owner"));
            ophone.setCellValueFactory(new PropertyValueFactory<selltable,Integer>("Owner_phone"));
            productidt.setCellValueFactory(new PropertyValueFactory<selltable,Integer>("Product_id"));
            pricet.setCellValueFactory(new PropertyValueFactory<selltable,String>("Price"));
            amountt.setCellValueFactory(new PropertyValueFactory<selltable,String>("Amount"));
            priceamount.setCellValueFactory(new PropertyValueFactory<selltable,Integer>("price_X_amount"));
            totalt.setCellValueFactory(new PropertyValueFactory<selltable,Integer>("Total"));




            table.setItems(selltables);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Empty");
        owner.getValidators().add(validator);
        phone.getValidators().add(validator);
        amount.getValidators().add(validator);

        owner.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){

                    owner.validate();

                }
            }
        });



    }

    public void retrive (){


        selltables.clear();
        table.getItems().clear();

        try {
            Connection connection = Databases.getConnection();
            PreparedStatement statement2 =  connection.prepareStatement("select bill.Bill_id,bill.Bill_owner,bill.Owner_phone,bill.Total ,sold.product_id,sold.price,sold.amount,sold.total from solds sold inner join bills bill on bill.Bill_id=sold.bill_id   ");

            ResultSet rs5 = null;



            rs5 =statement2.executeQuery();




            while (rs5.next()) {

                selltables.addAll(new selltable(rs5.getInt(1),rs5.getString(2),rs5.getInt(3)
                        ,rs5.getInt(4),rs5.getString(5),rs5.getString(6),rs5.getString(7),rs5.getInt(8)));


            }

            Billidt.setCellValueFactory(new PropertyValueFactory<selltable,Integer>("Bill_ID"));
            ownern.setCellValueFactory(new PropertyValueFactory<Sell,String>("Bill_owner"));
            ophone.setCellValueFactory(new PropertyValueFactory<selltable,Integer>("Owner_phone"));
            productidt.setCellValueFactory(new PropertyValueFactory<selltable,Integer>("Product_id"));
            pricet.setCellValueFactory(new PropertyValueFactory<selltable,String>("Price"));
            amountt.setCellValueFactory(new PropertyValueFactory<selltable,String>("Amount"));
            priceamount.setCellValueFactory(new PropertyValueFactory<selltable,Integer>("price_X_amount"));
            totalt.setCellValueFactory(new PropertyValueFactory<selltable,Integer>("Total"));




            table.setItems(selltables);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

  /*  public void retrivelistproduct(){
        pro.clear();
        productidt.getColumns().clear();

        try {
            Connection connection = Databases.getConnection();
            PreparedStatement statement =  connection.prepareStatement("select  product_id,product_name,price ,amount from inventoryes");
            ResultSet rs1 = null;
            rs1 =statement.executeQuery();
            while (rs1.next()){
                inventoryCompo inventory =new inventoryCompo();
                inventory.setId(rs1.getInt("product_id"));
                inventory.setName(rs1.getString("product_name"));
                inventory.setPrice(rs1.getInt("price"));
                inventory.setAmoun(rs1.getInt("amount"));

                pro.add(inventory);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        product.getItems().addAll(pro);
}

    */


}
