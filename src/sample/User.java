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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class User implements Initializable {


    @FXML
    private JFXButton in;
   @FXML
   private JFXButton ex;
   @FXML
    AnchorPane container;

    public void inv(ActionEvent event) throws IOException {{

        try {
            Parent inventory = FXMLLoader.load(getClass().getResource("../fxml/Inventory.fxml"));
            container.getChildren().setAll(inventory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }

    public void sell(ActionEvent event) throws IOException {{


        try {
            Parent inventory = FXMLLoader.load(getClass().getResource("../fxml/Sell.fxml"));
            container.getChildren().setAll(inventory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }




    public void log(ActionEvent event) throws IOException {{
        Parent home_parent = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml"));
        Scene home_scen = new Scene(home_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_scen);
        app_stage.show();}

    }

    public void exh(ActionEvent event) throws IOException {
        {

            try {
                Parent Exhibition = FXMLLoader.load(getClass().getResource("../fxml/Exhibition.fxml"));
                container.getChildren().setAll(Exhibition);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
