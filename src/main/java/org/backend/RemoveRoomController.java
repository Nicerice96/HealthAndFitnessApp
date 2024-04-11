package org.backend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class RemoveRoomController {
    private AdminFunctions adminFunctions = new AdminFunctions(HealthAndFitnessMemberJDBCConnect.getInstance());

    @FXML
    private TextField classIDTextField;

    @FXML
    private Button deleteRoomButton;

    @FXML
    void deleteRoom(ActionEvent event) {
     adminFunctions.removeRooms(Integer.parseInt(classIDTextField.getText()));
        ManageRoomBookingsController.getInstance().refreshUI();

    }

    public void rescheduleRoom(MouseEvent mouseEvent) {
    }
}
