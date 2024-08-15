package org.backend.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.backend.HealthAndFitnessMemberJDBCConnect;

import java.net.URL;
import java.util.ResourceBundle;

public class BillingController implements Initializable {

    private AdminFunctions adminFunctions = new AdminFunctions(HealthAndFitnessMemberJDBCConnect.getInstance());


    @FXML
    private Label billConfirmation;

    @FXML
    private TextField billDueDateTextField;

    @FXML
    private Button issueBillButton;

    @FXML
    private TextField memberIDTextField;

    @FXML
    private Label membersDisplay;

    @FXML
    private Label amount;


    @FXML
    void setBill(ActionEvent event) {

        if(!memberIDTextField.getText().isEmpty() && !billDueDateTextField.getText().isEmpty()){
            adminFunctions.billing(Integer.parseInt(memberIDTextField.getText()), billDueDateTextField.getText());
            billConfirmation.setText("");
            amount.setText("Amount issued: " + String.valueOf(adminFunctions.getTotal()));
        }
        else{
            billConfirmation.setText("missing input!");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        membersDisplay.setText(adminFunctions.dislpayBillPayees());
    }



}
