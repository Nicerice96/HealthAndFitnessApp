package org.backend.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TrainerLoginController {

    private ApplicationInterface applicationInterface = ApplicationInterface.getInstance();
    @FXML
    private Button createTrainerAccountButton;

    @FXML
    private Button trainerLoginButton;

    @FXML
    private TextField trainerNameField;



    public void trainerLoginButton(ActionEvent event) {
        if(applicationInterface.validateTrainerLogin(trainerNameField.getText())) {
            try {
                openFXML("/TrainerFunctions.fxml");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void createTrainerAccountAction(ActionEvent event) {

        try {
            openFXML("/CreateTrainerAccount.fxml");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void openFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}