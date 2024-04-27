package org.backend.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.backend.HealthAndFitnessMemberJDBCConnect;

public class AddEquipmentController {
    private AdminFunctions adminFunctions = new AdminFunctions(HealthAndFitnessMemberJDBCConnect.getInstance());

    @FXML
    private Button addEquipmentButton;

    @FXML
    private TextField equipmentDescriptionTextField;

    @FXML
    private TextField lastMaintained;

    @FXML
    void equipmentAdd(ActionEvent event) {

        adminFunctions.addEquipment(equipmentDescriptionTextField.getText(), lastMaintained.getText());
        MonitorFitnessEquipmentController.getInstance().refreshUI();
    }

}
