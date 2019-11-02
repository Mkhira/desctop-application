package sample;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Manger implements Initializable{



    @FXML
    AnchorPane container;
    @FXML
    ImageView immm;

    public void exh(ActionEvent event) throws IOException {{


        try {
            Parent Exhibition = FXMLLoader.load(getClass().getResource("../fxml/Exhibition.fxml"));
            container.getChildren().setAll(Exhibition);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }

    public void inv(ActionEvent event) throws IOException {{

        try {
            Parent inventory = FXMLLoader.load(getClass().getResource("../fxml/Inventory.fxml"));
            container.getChildren().setAll(inventory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }
    public void out(ActionEvent event) throws IOException {{
        Parent home_parent = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml"));
        Scene home_scen = new Scene(home_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_scen);
        app_stage.show();}

    }


    public void empo(ActionEvent event) throws IOException {{

        try {
            Parent inventory = FXMLLoader.load(getClass().getResource("../fxml/Employee.fxml"));
            container.getChildren().setAll(inventory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }





    public void Bill(ActionEvent event) throws IOException {{

        }
        try {
            Parent inventory = FXMLLoader.load(getClass().getResource("../fxml/Bills.fxml"));
            container.getChildren().setAll(inventory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sold(ActionEvent event) throws IOException {{

    }
        try {
            Parent inventory = FXMLLoader.load(getClass().getResource("../fxml/solds.fxml"));
            container.getChildren().setAll(inventory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    }
}
