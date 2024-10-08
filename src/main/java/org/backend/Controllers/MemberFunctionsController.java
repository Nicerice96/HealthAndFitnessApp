package org.backend.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.backend.HealthAndFitnessMemberJDBCConnect;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MemberFunctionsController implements Initializable{

    private static MemberFunctionsController memberContollerFunctionsInstance;
    private MemberFunctions memberFunctions = new MemberFunctions(HealthAndFitnessMemberJDBCConnect.getInstance(), ApplicationInterface.getInstance().getMember_id());
    @FXML
    private TextField bmiTextField;

    @FXML
    private TextField bodyFatPercentageTextField;

    @FXML
    private Label dashDisplayBMI;

    @FXML
    private Label dashDisplayBodyFatPercentage;

    @FXML
    private Label dashDisplayHeight;

    @FXML
    private Label dashDisplayWeight;

    @FXML
    private Label dashFitnessStartEndDate;

    @FXML
    private Label dashFitnessGoal;

    @FXML
    private Label dashTrainer;

    @FXML
    private Label dashLastMeasured;

    @FXML
    private Label displayNameLabel;

    @FXML
    private Button fitnessGoalsButton;

    @FXML
    private TextField heightTextField;

    @FXML
    private Button personalTrainingButton;

    @FXML
    private TextField weightTextField;

    @FXML
    void createFitnessGoal(ActionEvent event) {
        try {
            openFXML("/FitnessGoals.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void scheduleTraining(ActionEvent event) {
        try {
            openFXML("/MemberTrainingSession.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void updateBMI(ActionEvent event) {
        memberFunctions.updateBMI(Float.parseFloat(bmiTextField.getText()));
        dashDisplayBMI.setText(bmiTextField.getText());
    }

    @FXML
    void updateBodyFatPercentage(ActionEvent event) {
        memberFunctions.updateBodyFatPercentage(Float.parseFloat(bodyFatPercentageTextField.getText()));
        dashDisplayBodyFatPercentage.setText(bodyFatPercentageTextField.getText());
    }

    @FXML
    void updateHeight(ActionEvent event) {
        memberFunctions.updateHeight(Float.parseFloat(heightTextField.getText()));
        dashDisplayHeight.setText(heightTextField.getText());

    }

    @FXML
    void updateWeight(ActionEvent event) {

        memberFunctions.updateWeight(Float.parseFloat(weightTextField.getText()));
        dashDisplayWeight.setText(weightTextField.getText());


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        memberContollerFunctionsInstance = this;
        displayNameLabel.setText(memberFunctions.getMemberUserName());
        dashDisplayWeight.setText(memberFunctions.getWeight());
        dashDisplayHeight.setText(memberFunctions.getHeight());
        dashDisplayBMI.setText(memberFunctions.getBMI());
        dashDisplayBodyFatPercentage.setText(memberFunctions.getBodyFatPercentage());
        dashFitnessGoal.setText(memberFunctions.getFitnessGoal());
        dashFitnessStartEndDate.setText(memberFunctions.getFitnessGoalStartEndDate());
        dashTrainer.setText(memberFunctions.getTrainerDetails());

    }

    public void openFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void refreshUI(){

        displayNameLabel.setText(memberFunctions.getMemberUserName());
        dashDisplayWeight.setText(memberFunctions.getWeight());
        dashDisplayHeight.setText(memberFunctions.getHeight());
        dashDisplayBMI.setText(memberFunctions.getBMI());
        dashDisplayBodyFatPercentage.setText(memberFunctions.getBodyFatPercentage());
        dashFitnessGoal.setText(memberFunctions.getFitnessGoal());
        dashFitnessStartEndDate.setText(memberFunctions.getFitnessGoalStartEndDate());
        dashTrainer.setText(memberFunctions.getTrainerName());
        dashTrainer.setText(memberFunctions.getTrainerDetails());


    }

    public static MemberFunctionsController getInstance(){

        if (memberContollerFunctionsInstance == null){
            memberContollerFunctionsInstance = new MemberFunctionsController();
        }
        return memberContollerFunctionsInstance;
    }


}
