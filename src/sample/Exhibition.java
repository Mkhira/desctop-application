package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class Exhibition implements Initializable {
@FXML
AnchorPane container;


    public void exit(ActionEvent event) throws IOException {{

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.close();}

    }
     public  void  camera (ActionEvent event) throws Exception{


             try {
                 Parent cameraa = FXMLLoader.load(getClass().getResource("../fxml/camera.fxml"));
                 container.getChildren().setAll(cameraa);
             } catch (IOException e) {
                 e.printStackTrace();
             }




     }
    public  void  router (ActionEvent event) throws Exception{


        try {
            Parent router = FXMLLoader.load(getClass().getResource("../fxml/router.fxml"));
            container.getChildren().setAll(router);
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
    public  void  monitor (ActionEvent event) throws Exception{


        try {
            Parent router = FXMLLoader.load(getClass().getResource("../fxml/monitor.fxml"));
            container.getChildren().setAll(router);
        } catch (IOException e) {
            e.printStackTrace();
        }




    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
