package sample;


import Database.Databases;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import sample.modling.Employeetable;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Employee implements Initializable {

    @FXML
    JFXTextField name,user,pass,type,salary,phone,address,id,searchbox;

    ObservableList<Employeetable> list = FXCollections.observableArrayList();
    @FXML
    private TableView<Employeetable> emptable;
    @FXML
    public TableColumn<Employeetable, Integer> emidt;
    public TableColumn<Employeetable, String> emnt;

    public TableColumn<Employeetable, String> usert;
    public TableColumn<Employeetable, String> passwordt;
    public TableColumn<Employeetable, String> typet;
    public TableColumn<Employeetable, String> salaryt;
    public TableColumn<Employeetable, String> phonet;
    public TableColumn<Employeetable, String> adresst;
    @FXML
            private void searchrecord(KeyEvent keyEvent){

            FilteredList<Employeetable> filteredList = new FilteredList<>(list,p->true);
            searchbox.textProperty().addListener((observable,oldvalue,newvalue) -> {

                filteredList.setPredicate(pres ->{
                    if (newvalue == null||newvalue.isEmpty()){

                        return true;
                    }
                    String typedtext = newvalue.toLowerCase();

                    if (pres.getEmployee_Name().toLowerCase().indexOf(typedtext) !=-1 ){

                        return true;

                    }
                    if (pres.getUsername().toLowerCase().indexOf(typedtext) !=-1 ){

                        return true;

                    }



                    return false;
                });


                SortedList<Employeetable> sortedList = new SortedList<>(filteredList);
                sortedList.comparatorProperty().bind(emptable.comparatorProperty());
                emptable.setItems(sortedList);

            });
    }

    Employeetable employeeobject;




public void search (ActionEvent event)throws IOException{





}



    public void insert(ActionEvent event) throws IOException {

         if (name.getText().equals("") && user.getText().equals("") && phone.getText().equals("") && pass.getText().equals("") && type.getText().equals("")

          && address.getText().equals("") ){


             System.out.println("ffffffffffffff");

         }else {

            try {
                Connection connection = Databases.getConnection();


                PreparedStatement statement = connection.prepareStatement("insert into employees (employee_name,user_name,password,type,salary,phone,address) values (?,?,?,?,?,?,?)");

                statement.setString(1, name.getText());
                statement.setString(2, user.getText());
                statement.setString(3, pass.getText());
                statement.setString(4, type.getText());
                statement.setString(5, salary.getText());
                statement.setString(6, phone.getText());
                statement.setString(7, address.getText());

                name.setText("");
                user.setText("");
                pass.setText("");
                type.setText("");
                salary.setText("");
                phone.setText("");
                address.setText("");
                id.setText("");
                statement.executeUpdate();
                retrive();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }}



    public void update(ActionEvent event) throws IOException {

        if (name.getText().equals("") && user.getText().equals("") && phone.getText().equals("") && pass.getText().equals("") && type.getText().equals("")

                && address.getText().equals("") ){


            System.out.println("no result");

        }else {

            try {
                Connection connection = Databases.getConnection();


                PreparedStatement statement = connection.prepareStatement("update  employees set employee_name=?, user_name=?,  password=?, type=?,  salary=?,  phone=?, address=? where employee_id=" + employeeobject.getEmployee_id() + "");

                statement.setString(1, name.getText());
                statement.setString(2, user.getText());
                statement.setString(3, pass.getText());
                statement.setString(4, type.getText());
                statement.setString(5, salary.getText());
                statement.setString(6, phone.getText());
                statement.setString(7, address.getText());

                statement.executeUpdate();
                name.setText("");
                user.setText("");
                pass.setText("");
                type.setText("");
                salary.setText("");
                phone.setText("");
                address.setText("");

                retrive();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }



    }

    public  void clear(ActionEvent event){

        name.setText("");
        user.setText("");
        pass.setText("");
        type.setText("");
        salary.setText("");
        phone.setText("");
        address.setText("");
        id.setText("");

    }
    public void delete(ActionEvent event) throws IOException {

        if (name.getText().isEmpty() && user.getText().isEmpty() && pass.getText().isEmpty() && type.getText().isEmpty() && salary.getText().isEmpty() && phone.getText().isEmpty() && address.getText().isEmpty()) {


            System.out.println("nothing to delete");
        } else {
            try {
                Connection connection = Databases.getConnection();


                PreparedStatement statement = connection.prepareStatement("delete  from employees where employee_id=" + employeeobject.getEmployee_id() + "");

                name.setText("");
                user.setText("");
                pass.setText("");
                type.setText("");
                salary.setText("");
                phone.setText("");
                address.setText("");
                id.setText("");
                statement.executeUpdate();
                retrive();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        emptable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                employeeobject=emptable.getSelectionModel().getSelectedItem() != null ? emptable.getSelectionModel().getSelectedItem() : null;
                if (employeeobject != null){
                    name.setText(employeeobject.getEmployee_Name());
                    user.setText(employeeobject.getUsername());
                    pass.setText(employeeobject.getPassword());
                    phone.setText(employeeobject.getPhone());
                    salary.setText(employeeobject.getSalary());
                    type.setText(employeeobject.getType());
                    salary.setText(employeeobject.getSalary());
                    address.setText(employeeobject.getAddress());
                    id.setText(String.valueOf(employeeobject.getEmployee_id()));

                }

            }
        });






        try {
            Connection connection = Databases.getConnection();
            PreparedStatement statement2 =  connection.prepareStatement("select * from employees");

            ResultSet rs5 = null;
            rs5=statement2.executeQuery();
            while (rs5.next()){

               list.addAll(new Employeetable(rs5.getInt(1),rs5.getString(2),rs5.getString(3)
               ,rs5.getString(4),rs5.getString(5),rs5.getString(6)
               ,rs5.getString(7),rs5.getString(8)));


            }
            emidt.setCellValueFactory(new PropertyValueFactory<Employeetable, Integer>("Employee_id"));
            emnt.setCellValueFactory(new PropertyValueFactory<Employeetable,String>("Employee_Name"));
            usert.setCellValueFactory(new PropertyValueFactory<Employeetable,String>("Username"));
            passwordt.setCellValueFactory(new PropertyValueFactory<Employeetable,String>("Password"));
            typet.setCellValueFactory(new PropertyValueFactory<Employeetable,String>("Type"));
            salaryt.setCellValueFactory(new PropertyValueFactory<Employeetable,String>("Salary"));
            phonet.setCellValueFactory(new PropertyValueFactory<Employeetable,String>("Phone"));
            adresst.setCellValueFactory(new PropertyValueFactory<Employeetable,String>("Address"));
             emptable.getItems().addAll(list);






        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        RequiredFieldValidator validator =new RequiredFieldValidator();
        name.getValidators().add(validator);
        user.getValidators().add(validator);
        pass.getValidators().add(validator);
        type.getValidators().add(validator);
        salary.getValidators().add(validator);
        phone.getValidators().add(validator);
        address.getValidators().add(validator);
        validator.setMessage("Empty");
        name.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){

                    name.validate();

                }
            }
        });
        user.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){

                    user.validate();

                }
            }
        });
        pass.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){

                    pass.validate();

                }
            }
        });
        type.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){

                    type.validate();

                }
            }
        });
        salary.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){

                    salary.validate();


                }
            }
        });
        phone.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){

                    phone.validate();

                }
            }
        });
        address.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){

                    address.validate();

                }
            }
        });

    }
    public void retrive(){
        try {
            list.clear();
            emptable.getItems().clear();
            Connection connection = Databases.getConnection();
            PreparedStatement statement2 =  connection.prepareStatement("select * from employees");

            ResultSet rs5 = null;
            rs5=statement2.executeQuery();
            while (rs5.next()){

                list.addAll(new Employeetable(rs5.getInt(1),rs5.getString(2),rs5.getString(3)
                        ,rs5.getString(4),rs5.getString(5),rs5.getString(6)
                        ,rs5.getString(7),rs5.getString(8)));


            }
            emidt.setCellValueFactory(new PropertyValueFactory<Employeetable, Integer>("Employee_id"));
            emnt.setCellValueFactory(new PropertyValueFactory<Employeetable,String>("Employee_Name"));
            usert.setCellValueFactory(new PropertyValueFactory<Employeetable,String>("Username"));
            passwordt.setCellValueFactory(new PropertyValueFactory<Employeetable,String>("Password"));
            typet.setCellValueFactory(new PropertyValueFactory<Employeetable,String>("Type"));
            salaryt.setCellValueFactory(new PropertyValueFactory<Employeetable,String>("Salary"));
            phonet.setCellValueFactory(new PropertyValueFactory<Employeetable,String>("Phone"));
            adresst.setCellValueFactory(new PropertyValueFactory<Employeetable,String>("Address"));

            emptable.getItems().addAll(list);






        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}




