package org.backend;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminAuthenticationController implements Initializable {

    @FXML
    private Label ellipses;

    private ArrayList<String> elements = new ArrayList<>();
    private int index = 0;
    private Timeline timeline;


    void animateEllipses() {
        elements.add(".");
        elements.add("..");
        elements.add("...");
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ellipses.setText(elements.get(index));
                index = (index + 1) % elements.size();
            }
        }));
        timeline.setCycleCount(6);
        timeline.setOnFinished(event -> {
            try {
                openFXML("/AdminFunctions.fxml");
                Stage currentStage = (Stage) ellipses.getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        timeline.play();
    }

    public void openFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        animateEllipses();

    }

}
