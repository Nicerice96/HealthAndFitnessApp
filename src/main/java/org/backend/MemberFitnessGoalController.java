package org.backend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MemberFitnessGoalController {
    private  MemberFunctionsController memberFunctionsController = MemberFunctionsController.getInstance();

    private MemberFunctions memberFunctions = new MemberFunctions(HealthAndFitnessMemberJDBCConnect.getInstance(), ApplicationInterface.getInstance().getMember_id());



    @FXML
    private TextField endFitnessGoalDateTextField;

    @FXML
    private TextField setFitnessGoalDescriptionTextField;

    @FXML
    private TextField setFitnessGoalTitleTextField;

    @FXML
    private TextField startFitnessGoalDateTextField;

    @FXML
    void setEndDate(ActionEvent event) {

        memberFunctions.setFitnessEndDate(endFitnessGoalDateTextField.getText());
        memberFunctionsController.refresUI();

    }

    @FXML
    void setFitnessGoalDescription(ActionEvent event) {
        memberFunctions.setFitnessGoalDescription(setFitnessGoalDescriptionTextField.getText());
        memberFunctionsController.refresUI();

    }

    @FXML
    void setFitnessGoalTitle(ActionEvent event) {
        memberFunctions.setFitnessGoalTitle(setFitnessGoalTitleTextField.getText());
        memberFunctionsController.refresUI();

    }

    @FXML
    void setStartDate(ActionEvent event) {
        memberFunctions.setFitnessStartDate(startFitnessGoalDateTextField.getText());
        memberFunctionsController.refresUI();

    }

}