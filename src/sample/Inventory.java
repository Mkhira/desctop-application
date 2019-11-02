package sample;

import Database.Databases;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.omg.PortableInterceptor.INACTIVE;
import sample.modling.Employeetable;
import sample.modling.inventorytable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class  Inventory implements Initializable {
    @FXML
     JFXButton ins;
    @FXML
    JFXTextField name,model,price,amount,searchbox;
    ObservableList<inventorytable> inv = FXCollections.observableArrayList();
    @FXML
    private TableView<inventorytable> invtable;
    @FXML
    public TableColumn<inventorytable,Integer> invid;
    public TableColumn<inventorytable,String> nvname;
    public TableColumn<inventorytable,String>nvmodel;
    public TableColumn<inventorytable, Integer> nvprice;
    public TableColumn<inventorytable, Integer> nvamount;

    inventorytable inventtable;


    @FXML
    private void searchrecord(KeyEvent keyEvent){

        FilteredList<inventorytable> filteredList = new FilteredList<>(inv, p->true);
        searchbox.textProperty().addListener((observable,oldvalue,newvalue) -> {

            filteredList.setPredicate(pres ->{
                if (newvalue == null||newvalue.isEmpty()){

                    return true;
                }
                String typedtext = newvalue.toLowerCase();

                if (pres.getModel().toLowerCase().indexOf(typedtext) !=-1 ){

                    return true;

                }
                if (pres.getName().toLowerCase().indexOf(typedtext) !=-1 ){

                    return true;

                }



                return false;
            });


            SortedList<inventorytable> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(invtable.comparatorProperty());
            invtable.setItems(sortedList);

        });
    }

    public void insert(ActionEvent event) throws IOException{

        if (name.getText().equals("")|| model.getText().equals("")||price.getText().equals("")||amount.getText().equals("")){

            System.out.println("Misssing data");

        }

          else{    try {
                  Connection connection = Databases.getConnection();
                  PreparedStatement statement =  connection.prepareStatement("insert into inventoryes (product_name,model,price,amount) values (?,?,?,?)");
                  statement.setString(1,name.getText());
                  statement.setString(2,model.getText());
                  statement.setString(3,price.getText());
                  statement.setString(4,amount.getText());
            statement.executeUpdate();

                  name.setText("");
                  model.setText("");
                  price.setText("");
                  amount.setText("");

                  retrive();
              } catch (ClassNotFoundException e1) {
                  e1.printStackTrace();
              } catch (SQLException e1) {
                  e1.printStackTrace();
              }

          }}
public void update(ActionEvent event)throws Exception{
    if (name.getText().equals("")|| model.getText().equals("")||price.getText().equals("")||amount.getText().equals("")){

        System.out.println("Misssing data");

    }

    else{
        try {
        Connection connection = Databases.getConnection();



        PreparedStatement statement = connection.prepareStatement("update  inventoryes set product_name=?, model=?,  price=?, amount=?  where product_id="+inventtable.getId()+"");
        statement.setString(1, name.getText());
        statement.setString(2, model.getText());
        statement.setString(3, price.getText());
        statement.setString(4, amount.getText());


        name.setText("");
        model.setText("");
        price.setText("");
        amount.setText("");

        statement.executeUpdate();
        retrive();
    } catch(ClassNotFoundException e1){
        e1.printStackTrace();
    } catch(SQLException e1){
        e1.printStackTrace();
    }

}}

public void clear(){

    name.setText("");
    model.setText("");
    price.setText("");
    amount.setText("");

}


    public void delete(ActionEvent event) throws IOException {
        if (name.getText().isEmpty() && model.getText().isEmpty() && price.getText().isEmpty() && amount.getText().isEmpty()){
            System.out.println("nothing to delete");
        }


else{


        try {
            Connection connection = Databases.getConnection();


            PreparedStatement statement = connection.prepareStatement("delete from inventoryes where product_id=" + inventtable.getId() + "");


            statement.executeUpdate();
            retrive();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        invtable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                inventtable=invtable.getSelectionModel().getSelectedItem() != null ? invtable.getSelectionModel().getSelectedItem() : null;
                if (inventtable != null){
                    name.setText(inventtable.getName());
                    model.setText(inventtable.getModel());
                    price.setText(inventtable.getPrice());
                    amount.setText(inventtable.getAmount());


                }

            }
        });



        try {
            Connection connection = Databases.getConnection();
            PreparedStatement statement2 =  connection.prepareStatement("select * from inventoryes ");

            ResultSet rs5 = null;



            rs5 =statement2.executeQuery();


            while (rs5.next()) {

                    inventorytable inventory=new inventorytable(rs5.getInt(1),rs5.getString(2),rs5.getString(3),rs5.getString(4),rs5.getString(5));

                System.out.println(inventory.getId()+inventory.getName()+inventory.getModel()+ inventory.getAmount()+inventory.getPrice());


                inv.addAll( inventory);

            }



            invid.setCellValueFactory(new PropertyValueFactory<inventorytable,Integer>("id"));
            nvname.setCellValueFactory(new PropertyValueFactory<inventorytable,String>("name"));
            nvmodel.setCellValueFactory(new PropertyValueFactory<inventorytable, String>("model"));
            nvprice.setCellValueFactory(new PropertyValueFactory<inventorytable,Integer>("Price"));
            nvamount.setCellValueFactory(new PropertyValueFactory<inventorytable,Integer>("amount"));

            invtable.setItems(inv);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }





        RequiredFieldValidator validator =new RequiredFieldValidator();
        name.getValidators().add(validator);
        model.getValidators().add(validator);
        price.getValidators().add(validator);
        amount.getValidators().add(validator);

        validator.setMessage("Empty");
        name.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){

                    name.validate();

                }
            }
        });
        model.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){

                    model.validate();

                }
            }
        });
        price.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){

                    price.validate();

                }
            }
        });
        amount.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){

                    amount.validate();

                }
            }
        });



    }

    public void retrive(){

        inv.clear();
        invtable.getItems().clear();

        try {
            Connection connection = Databases.getConnection();
            PreparedStatement statement2 =  connection.prepareStatement("select * from inventoryes ");

            ResultSet rs5 = null;



            rs5 =statement2.executeQuery();


            while (rs5.next()) {

                inventorytable inventory=new inventorytable(rs5.getInt(1),rs5.getString(2),rs5.getString(3),rs5.getString(4),rs5.getString(5));

                System.out.println(inventory.getId()+inventory.getName()+inventory.getModel()+ inventory.getAmount()+inventory.getPrice());




                inv.addAll( inventory);




            }



            invid.setCellValueFactory(new PropertyValueFactory<inventorytable,Integer>("id"));
            nvname.setCellValueFactory(new PropertyValueFactory<inventorytable,String>("name"));
            nvmodel.setCellValueFactory(new PropertyValueFactory<inventorytable, String>("model"));
            nvprice.setCellValueFactory(new PropertyValueFactory<inventorytable,Integer>("Price"));
            nvamount.setCellValueFactory(new PropertyValueFactory<inventorytable,Integer>("amount"));

            invtable.setItems(inv);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}




