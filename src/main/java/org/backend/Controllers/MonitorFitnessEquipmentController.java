package org.backend.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.backend.HealthAndFitnessMemberJDBCConnect;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MonitorFitnessEquipmentController implements Initializable {

    private static MonitorFitnessEquipmentController monitorFitnessEquipmentController;
    private AdminFunctions adminFunctions = new AdminFunctions(HealthAndFitnessMemberJDBCConnect.getInstance());

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

    public void refreshUI(){
        fitnessEquipmentDisplay.setText(adminFunctions.displayAllEquipment());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fitnessEquipmentDisplay.setText(adminFunctions.displayAllEquipment());
    }


    public static MonitorFitnessEquipmentController getInstance(){
        if (monitorFitnessEquipmentController == null){
            return monitorFitnessEquipmentController = new MonitorFitnessEquipmentController();
        }
        else{
            return monitorFitnessEquipmentController;
        }
    }
}
