package org.backend.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

import java.io.IOException;

public class LandingController {


    @FXML
    private Button adminLoginButton;

    @FXML
    private Menu exitLanding;

    @FXML
    private Button memberLoginButton;

    @FXML
    private Button trainerLoginButton;



    @FXML
    private void memberButtonAction(ActionEvent event) {

        openFXML("/MemberLogin.fxml");
    }
    @FXML
    private void trainerButtonAction(ActionEvent event) {

        openFXML("/TrainerLogin.fxml");
    }
    @FXML
    private void adminButtonAction(ActionEvent event) {

        openFXML("/AdminAuthentication.fxml");
    }

    private void openFXML(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
