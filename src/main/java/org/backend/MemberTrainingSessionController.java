package org.backend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MemberTrainingSessionController implements Initializable {

    private MemberFunctions memberFunctions = new MemberFunctions(HealthAndFitnessMemberJDBCConnect.getInstance(), ApplicationInterface.getInstance().getMember_id());


    @FXML
    private Label displayTrainer;

    @FXML
    private TextField endDateTextField;

    @FXML
    private Button findTrainer;

    @FXML
    private TextField startDateTextField;

    private String startDate;

    private String endDate;

    @FXML
    void findTrainerAction(ActionEvent event) {

        memberFunctions.scheduleManagement(this.startDate, this.endDate);
        updateTrainingLabel("Training with:" + memberFunctions.getTrainerName());


    }

    @FXML
    void setEndDate(ActionEvent event) {
        this.startDate = startDateTextField.getText();
        updateTrainingLabel(memberFunctions.getTrainerName());
    }

    @FXML
    void setStartDate(ActionEvent event) {
        this.endDate = endDateTextField.getText();
    }

    public void updateTrainingLabel(String txt){

        displayTrainer.setText(txt);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        updateTrainingLabel("Training with:" + memberFunctions.getTrainerName());
    }

}
