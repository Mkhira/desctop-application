package sample;

import Database.Databases;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Sell;
import sample.modling.Employeetable;
import sample.modling.selltable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Bills implements Initializable {

    ObservableList<selltable> selltables = FXCollections.observableArrayList();
@FXML
    JFXTextField searchbox;
    @FXML
    ImageView immm;
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
    private void searchrecord(KeyEvent keyEvent){
        FilteredList<selltable> filteredList = new FilteredList<>(selltables, p->true);
        searchbox.textProperty().addListener((observable,oldvalue,newvalue) -> {
            filteredList.setPredicate(pres ->{
                if (newvalue == null||newvalue.isEmpty()){
                    return true;
                }
                String typedtext = newvalue.toLowerCase();

                if (pres.getBill_owner().toLowerCase().indexOf(typedtext) !=-1 ){
                    return true;
                }
                if (pres.getBill_owner().toLowerCase().indexOf(typedtext) !=-1 ){
                    return true;
                }
                return false;
            });
            SortedList<selltable> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedList);
        });
    }

//    public void print (ActionEvent event) throws IOException {
//
//
//        Parent home_parent = FXMLLoader.load(getClass().getResource("../fxml/print.fxml"));
//        Scene home = new Scene(home_parent);
//
//        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        app_stage.setScene(home);
//
//
//        app_stage.setFullScreen(false);
//
//        app_stage.setResizable(false);
//
//
//        app_stage.show();
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        final Duration SEC_3 = Duration.millis(2000);
        FadeTransition fadeTransition = new FadeTransition(SEC_3);
        fadeTransition.setFromValue(2.5);
        fadeTransition.setToValue(.02);
        fadeTransition.setCycleCount(FadeTransition.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.setNode(immm);
        fadeTransition.play();
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
}
