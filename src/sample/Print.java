package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Print implements Initializable {
@FXML
    GridPane grid;
public void TitlePane(ActionEvent event){}
public void gen(){
    grid.add(pane(),1,2);
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
gen();
    }
    public TitledPane pane(){
    TitledPane tpane=new TitledPane();

    return tpane;
    }
}
