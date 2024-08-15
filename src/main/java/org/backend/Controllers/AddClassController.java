package org.backend.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.backend.HealthAndFitnessMemberJDBCConnect;

public class AddClassController {

    private AdminFunctions adminFunctions = new AdminFunctions(HealthAndFitnessMemberJDBCConnect.getInstance());
    @FXML
    private Button addClassButton;

    @FXML
    private TextField classNameTextField;

    @FXML
    private TextField endTimeTextField;

    @FXML
    private TextField startTimeTextField;

    @FXML
    void addClass(ActionEvent event) {
        adminFunctions.addClass(classNameTextField.getText(), startTimeTextField.getText(), endTimeTextField.getText());
        UpdateClassSchedulesController.getInstance().refreshUI();
    }

}
