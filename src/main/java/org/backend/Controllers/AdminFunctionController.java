package org.backend.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminFunctionController {

    @FXML
    private Button billingButton;

    @FXML
    private Button manageRoomBookingsButton;

    @FXML
    private Button monitorFitnessEquipment;

    @FXML
    private Button updateClassSchedulesButton;

    @FXML
    void billingButtonAction(ActionEvent event) { openFXML("/Billing.fxml");
    }

    @FXML
    void manageRoomBooks(ActionEvent event) {
        openFXML("/ManageRoomBooking.fxml");
    }

    @FXML
    void monitorFitnessEquipmentAction(ActionEvent event) {
        openFXML("/MonitorFitnessEquipment.fxml");
    }

    @FXML
    void updateClassSchedulesAction(ActionEvent event) {
        openFXML("/UpdateClassSchedules.fxml");
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
