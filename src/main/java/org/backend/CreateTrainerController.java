package org.backend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateTrainerController {
    private ApplicationInterface applicationInterface = new ApplicationInterface(HealthAndFitnessMemberJDBCConnect.getInstance());
    @FXML
    private Button createAccountButton;

    @FXML
    private PasswordField specializationTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    void createAccount(ActionEvent event) {
        applicationInterface.createTrainerAccount(userNameTextField.getText(), specializationTextField.getText());
    }

}
