package org.backend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UpdateEquipmentDateController {
    private AdminFunctions adminFunctions = new AdminFunctions(HealthAndFitnessMemberJDBCConnect.getInstance());

    private TextField lastMaintained;

    @FXML
    private Button updateLastMaintainedButton;

    @FXML
    void updateLastMaintained(ActionEvent event) {
        adminFunctions.updateMaintainceDate(lastMaintained.getText());
        MonitorFitnessEquipmentController.getInstance().refreshUI();
    }

}
