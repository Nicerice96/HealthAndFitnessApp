package org.backend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateClassSchedulesController {
    @FXML
    private Button addClassButton;

    @FXML
    private Button removeClassButton;

    @FXML
    private Label roomsDisplay;

    @FXML
    void addClass(ActionEvent event) {

        openFXML("/AddClass.fxml");
    }

    @FXML
    void removeClass(ActionEvent event) {
        openFXML("/RemoveClass.fmxl");
    }

    public void openFXML(String fxml){

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
