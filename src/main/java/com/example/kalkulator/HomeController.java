package com.example.kalkulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class HomeController {
    public Button kNaukowy;
    public void KalkulatorNaukowyOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) kNaukowy.getScene().getWindow();
        stage.close();

        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("kalkulatorNaukowy.fxml")));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 600, 800.0D));
            menuStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
