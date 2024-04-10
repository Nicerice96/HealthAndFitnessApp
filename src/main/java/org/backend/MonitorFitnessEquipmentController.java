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

public class MonitorFitnessEquipmentController {

    @FXML
    private Button addEquipmentButton;

    @FXML
    private Label fitnessEquipmentDisplay;

    @FXML
    private Label lastMaintainedDate;

    @FXML
    private Button removeEquipmentButton;

    @FXML
    private Button updateDateButton;

    @FXML
    void addEquipment(ActionEvent event) {
        openFXML("/AddEquipment.fxml");
    }

    @FXML
    void removeEquipment(ActionEvent event) {
        openFXML("/RemoveEquipment.fxml");

    }

    @FXML
    void updateDate(ActionEvent event) {
        openFXML("/UpdateEquipmentDate.fxml");
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
