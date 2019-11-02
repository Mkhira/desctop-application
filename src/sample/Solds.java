package sample;

import Database.Databases;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import sample.modling.Employeetable;
import sample.modling.SoldTable;
import sample.modling.selltable;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Solds implements Initializable {
    ObservableList<SoldTable> list = FXCollections.observableArrayList();
    @FXML
    private TableView<SoldTable> tbsold;
    @FXML
    JFXTextField searchbox;
    @FXML
    public TableColumn<SoldTable, Integer> id;

    public TableColumn<SoldTable, Integer> price;
    public TableColumn<SoldTable, Integer> amount;

int x;

    @FXML
    private void searchrecord(KeyEvent keyEvent){
        FilteredList<SoldTable> filteredList = new FilteredList<>(list, p->true);
        searchbox.textProperty().addListener((observable,oldvalue,newvalue) -> {
            filteredList.setPredicate(pres ->{
                if (newvalue == null||newvalue.isEmpty()){
                    return true;
                }
                int typedtext = Integer.parseInt(newvalue.toLowerCase());

                if (oldvalue.toLowerCase().indexOf(typedtext) !=-1 ){
                    return true;
                }
                return false;
            });
            SortedList<SoldTable> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tbsold.comparatorProperty());
            tbsold.setItems(sortedList);
        });
    }







    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            Connection connection = Databases.getConnection();
            PreparedStatement statement2 =  connection.prepareStatement("select product_id,price,amount from solds");

            ResultSet rs5 = null;
            rs5=statement2.executeQuery();

            while (rs5.next()){
                System.out.println(rs5.getInt(1));

                list.addAll(new SoldTable(rs5.getInt(1),rs5.getInt(2),rs5.getInt(3)));

            }
            id.setCellValueFactory(new PropertyValueFactory<SoldTable,Integer>("id"));
            price.setCellValueFactory(new PropertyValueFactory<SoldTable,Integer>("price"));
            amount.setCellValueFactory(new PropertyValueFactory<SoldTable,Integer>("amount"));
            tbsold.getItems().addAll(list);

    }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }}
