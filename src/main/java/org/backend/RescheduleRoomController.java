package org.backend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RescheduleRoomController {
    private AdminFunctions adminFunctions = new AdminFunctions(HealthAndFitnessMemberJDBCConnect.getInstance());


    @FXML
    private TextField endDateTextField;

    @FXML
    private TextField enterRoomNumberTextField;

    @FXML
    private VBox rescheduleRoomButon;

    @FXML
    private Button rescheduleRoomButton;

    @FXML
    private TextField startDateTextField;

    @FXML
    void rescheduleRoom(ActionEvent event) {
        adminFunctions.updateRoomAvailability(Integer.parseInt(enterRoomNumberTextField.getText()), startDateTextField.getText(), endDateTextField.getText());
        ManageRoomBookingsController.getInstance().refreshUI();

    }
}
