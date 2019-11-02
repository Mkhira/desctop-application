package sample;

import Database.Databases;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.scene.layout.StackPane;

import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {
  @FXML
    JFXToggleButton togel;
  @FXML
  private JFXTextField username;
  @FXML
  private JFXPasswordField password;
  @FXML

    private StackPane stac;

    public void log(ActionEvent event) throws IOException {

        String user = username.getText();
        String pass = password.getText();

        if (togel.isSelected() == true) {

            if (username.getText() != null && password.getText() != null) {

                try {
                    Connection connection = Databases.getConnection();

                    PreparedStatement statement2 = connection.prepareStatement("select user_name,password,type from employees where user_name=? and password=? and type='manger'");

                    ResultSet rs5 = null;

                    statement2.setString(1, user);
                    statement2.setString(2, pass);
                    rs5 = statement2.executeQuery();


                    if (rs5.next()) {


                        Parent home_parent = FXMLLoader.load(getClass().getResource("../fxml/Manger.fxml"));
                        Scene home = new Scene(home_parent);

                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        app_stage.setScene(home);


                        app_stage.setFullScreen(true);

                        app_stage.setResizable(false);


                        app_stage.show();
                    }


                    else{

                    JOptionPane.showMessageDialog(null, "Wrong user name or Password and make sure you use the right type");
                }

            } catch(ClassNotFoundException e1){
                e1.printStackTrace();
            } catch(SQLException e1){
                e1.printStackTrace();
            }


            }


        }
     else {


            if (username.getText() !=null && password.getText() !=null) {

                try {
                    Connection connection = Databases.getConnection();

                    PreparedStatement statement2 = connection.prepareStatement("select user_name,password,type from employees where user_name=? and password=? and type='employee'");

                    ResultSet rs5 = null;

                    statement2.setString(1, user);
                    statement2.setString(2, pass);
                    rs5 = statement2.executeQuery();


                    if (rs5.next()) {


            Parent home_parent = FXMLLoader.load(getClass().getResource("../fxml/user.fxml"));
            Scene home = new Scene(home_parent);

            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home);


            app_stage.setFullScreen(true);

            app_stage.setResizable(false);


            app_stage.show();

                    }
                    else {

                        JOptionPane.showMessageDialog(null,"Wrong user name or Password and make sure you use the right type");
                    }

                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }




        }


        }




    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

  public void shop(ActionEvent event)  {
     /* JFXDialog dialog = new JFXDialog(stac, new Label("Heloo"), JFXDialog.DialogTransition.CENTER);

JFXDialogLayout layout =new JFXDialogLayout();
layout.setHeading(new Text("hellow"));
layout.setBody(new Text("fffffffffffffffffffffffffff"));
JFXButton button =new JFXButton("done");
button.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {

        dialog.close();

    }
});

layout.setActions(button);

      dialog.show();
*/

      Parent home_parent = null;
      try {
          home_parent = FXMLLoader.load(getClass().getResource("../fxml/Exhibition.fxml"));
      } catch (IOException e) {
          e.printStackTrace();
      }
      Scene home= new Scene(home_parent);

      Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      app_stage.setScene(home);


      app_stage.setFullScreen(true);

      app_stage.setResizable(false);


      app_stage.show();



  }}
