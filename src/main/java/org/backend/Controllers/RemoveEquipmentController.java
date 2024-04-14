package org.backend.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.backend.HealthAndFitnessMemberJDBCConnect;

public class RemoveEquipmentController {
    private AdminFunctions adminFunctions = new AdminFunctions(HealthAndFitnessMemberJDBCConnect.getInstance());
    @FXML
    private TextField enterRoomNumberTextField;

    @FXML
    private Button removeEquipmentButton;

    @FXML
    void remove(ActionEvent event) {
        adminFunctions.removeRooms(Integer.parseInt(enterRoomNumberTextField.getText()));
        MonitorFitnessEquipmentController.getInstance().refreshUI();
    }

}
