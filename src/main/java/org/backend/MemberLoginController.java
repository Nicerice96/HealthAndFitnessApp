package org.backend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class MemberLoginController {

    private ApplicationInterface applicationInterface = ApplicationInterface.getInstance();

    @FXML
    private Button createAccountButton;

    @FXML
    private Button loginButton;
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void createAccountAction(ActionEvent event) {



    }

    @FXML
    void loginAction(ActionEvent event) {

        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean loginStatus = applicationInterface.validateMemberLogin(username, password);
        System.out.println(loginStatus);

        if (loginStatus) {
            openFXML("/MemberFunctions.fxml");
        }
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
