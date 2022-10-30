package com.example.kalkulator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class HomeController {
    public Button kNaukowy;
    public Button kCalek;
    double x,y;
    public void KalkulatorNaukowyOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) kNaukowy.getScene().getWindow();
        stage.close();

        try{
            Stage menuStage = new Stage();
            menuStage.setTitle("Kalkulator naukowy");
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/kalkulatorNaukowy.fxml")));
            menuStage.initStyle(StageStyle.UNDECORATED);

            root.setOnMousePressed(event -> {
                x=menuStage.getX()-event.getSceneX();
                y=menuStage.getY()-event.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                menuStage.setX(event.getSceneX()+x);
                menuStage.setY(event.getSceneY()+y);
            });
            menuStage.setScene(new Scene(root, 972, 600));
            menuStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void KalkulatorCalekOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) kCalek.getScene().getWindow();
        stage.close();

        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/kalkulatorCalka.fxml")));
            Stage menuStage = new Stage();
            menuStage.setTitle("Kalkulator całek");
            menuStage.initStyle(StageStyle.UNDECORATED);
            root.setOnMousePressed(event -> {
                x=menuStage.getX()-event.getSceneX();
                y=menuStage.getY()-event.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                menuStage.setX(event.getSceneX()+x);
                menuStage.setY(event.getSceneY()+y);
            });
            menuStage.setScene(new Scene(root, 972, 600));
            menuStage.setResizable(false);
            menuStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
