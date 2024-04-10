package org.backend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RemoveClassController {

    private AdminFunctions adminFunctions = new AdminFunctions(HealthAndFitnessMemberJDBCConnect.getInstance());

    @FXML
    private TextField classNameTextField;

    @FXML
    private Button removeClassButton;

    @FXML
    void removeClass(ActionEvent event) {
        adminFunctions.removeClass(Integer.parseInt(classNameTextField.getText()));
    }
}
