package org.backend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateRoomController {

    private AdminFunctions adminFunctions = new AdminFunctions(HealthAndFitnessMemberJDBCConnect.getInstance());

    @FXML
    private TextField classNameTextField;

    @FXML
    private TextField classStartTimeTextField;

    @FXML
    private Button createButton;

    @FXML
    private TextField endTimeTextField;

    @FXML
    void createRoom(ActionEvent event) {
        adminFunctions.addRooms(Integer.parseInt(classNameTextField.getText()), classStartTimeTextField.getText(), endTimeTextField.getText());

    }

}