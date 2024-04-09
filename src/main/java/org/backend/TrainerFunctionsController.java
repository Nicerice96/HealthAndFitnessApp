package org.backend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class TrainerFunctionsController implements Initializable {

    private TrainerFunctions trainerFunctions = new TrainerFunctions(HealthAndFitnessMemberJDBCConnect.getInstance(), ApplicationInterface.getInstance().getMember_id());

    @FXML
    private Label dashMember;

    @FXML
    private Circle statusAvailable;

    @FXML
    private Button setDateButton;

    @FXML
    private Label displayTrainerName;

    @FXML
    private Button makeAvailableButton;

    @FXML
    private Button makeUnavaiableButton;

    @FXML
    private TextField trainerEndDate;

    @FXML
    private TextField trainerStartDate;

    @FXML
    private Label errorMsg;

    @FXML
    void setAvailable(ActionEvent event) {
        trainerFunctions.makeAvailableToMembers();
        updateDashDisplay();
        statusAvailable.setFill(Color.LIMEGREEN);
    }
    @FXML
    void setDate(ActionEvent event) {
        if (!trainerStartDate.getText().equals(null) && trainerEndDate.getText().equals(null)) {
            trainerFunctions.updateAvailability(trainerStartDate.getText(), trainerEndDate.getText());
            errorMsg.setText("");
        }else{
            errorMsg.setText("Both dates are not entered!");
        }
    }

    @FXML
    void setUnavailable(ActionEvent event) {
        trainerFunctions.makeUnavailableToMembers();
        updateDashDisplay();
        statusAvailable.setFill(Color.RED);
    }

    public void updateDashDisplay(){
        dashMember.setText(trainerFunctions.viewMemberProfiles());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayTrainerName.setText(trainerFunctions.getTrainerName());
        updateDashDisplay();
        if (trainerFunctions.checkAvailable()){
            statusAvailable.setFill(Color.LIMEGREEN);
        }else{
            statusAvailable.setFill(Color.RED);
        }
    }
}
