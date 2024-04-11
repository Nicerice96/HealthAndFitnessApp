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

public class UpdateClassSchedulesController implements Initializable {
    private static UpdateClassSchedulesController updateClassSchedulesController;
    private AdminFunctions adminFunctions = new AdminFunctions(HealthAndFitnessMemberJDBCConnect.getInstance());
    @FXML
    private Button addClassButton;

    @FXML
    private Button removeClassButton;

    @FXML
    private Label classDisplay;

    @FXML
    void addClass(ActionEvent event) {

        openFXML("/AddClass.fxml");
        classDisplay.setText(adminFunctions.displayAllClasses());
    }

    @FXML
    void removeClass(ActionEvent event) {

        openFXML("/RemoveClass.fxml");
        classDisplay.setText(adminFunctions.displayAllClasses());
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
        classDisplay.setText(adminFunctions.displayAllClasses());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        classDisplay.setText(adminFunctions.displayAllClasses());
    }

    public static UpdateClassSchedulesController getInstance(){
        if (updateClassSchedulesController == null){
            return updateClassSchedulesController = new UpdateClassSchedulesController();
        }
        else{
            return updateClassSchedulesController;
        }
    }
}
