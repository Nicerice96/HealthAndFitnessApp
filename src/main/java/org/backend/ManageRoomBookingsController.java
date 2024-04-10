package org.backend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageRoomBookingsController implements Initializable {
    private AdminFunctions adminFunctions = new AdminFunctions(HealthAndFitnessMemberJDBCConnect.getInstance());

    @FXML
    private Button createRoomButton;

    @FXML
    private Button deleteRoomButton;

    @FXML
    private Button rescheduleRoomButton;

    @FXML
    private Label roomsDisplay;

    @FXML
    void createRoom(ActionEvent event) {
        openFXML("/CreateRoom.fxml");
    }

    @FXML
    void deleteButton(ActionEvent event) {
        openFXML("/DeleteRoom.fxml");
    }

    @FXML
    void rescheduleRoom(ActionEvent event) {
        openFXML("/RescheduleRoom.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomsDisplay.setText(adminFunctions.displayAllRooms());
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
