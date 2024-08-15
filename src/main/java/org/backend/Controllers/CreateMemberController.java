package org.backend.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.backend.HealthAndFitnessMemberJDBCConnect;

public class CreateMemberController {
    private ApplicationInterface applicationInterface = new ApplicationInterface(HealthAndFitnessMemberJDBCConnect.getInstance());
    @FXML
    private Button createAccountButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    void createAccount(ActionEvent event) {
        applicationInterface.createMemberAccount(userNameTextField.getText(), passwordTextField.getText());
    }

}
