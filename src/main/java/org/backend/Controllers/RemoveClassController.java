package org.backend.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.backend.HealthAndFitnessMemberJDBCConnect;

public class RemoveClassController {

    private AdminFunctions adminFunctions = new AdminFunctions(HealthAndFitnessMemberJDBCConnect.getInstance());

    @FXML
    private TextField classNameTextField;

    @FXML
    private Button removeClassButton;

    @FXML
    void removeClass(ActionEvent event) {
        adminFunctions.removeClass(Integer.parseInt(classNameTextField.getText()));
        UpdateClassSchedulesController.getInstance().refreshUI();

    }
}
