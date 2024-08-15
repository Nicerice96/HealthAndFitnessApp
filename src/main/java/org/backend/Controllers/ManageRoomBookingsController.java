package org.backend.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.backend.HealthAndFitnessMemberJDBCConnect;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageRoomBookingsController implements Initializable {

    private static ManageRoomBookingsController manageRoomBookingsController;
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
        roomsDisplay.setText(adminFunctions.displayAllRooms());
    }

    @FXML
    void deleteButton(ActionEvent event) {
        openFXML("/DeleteRoom.fxml");
        roomsDisplay.setText(adminFunctions.displayAllRooms());
    }

    @FXML
    void rescheduleRoom(ActionEvent event) {
        openFXML("/RescheduleRoom.fxml");
        roomsDisplay.setText(adminFunctions.displayAllRooms());
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

    public void refreshUI(){
        roomsDisplay.setText(adminFunctions.displayAllRooms());
    }

    public static ManageRoomBookingsController getInstance(){
        if(manageRoomBookingsController == null){
            return manageRoomBookingsController = new ManageRoomBookingsController();
        }
        else{
            return manageRoomBookingsController;
        }
    }
}
