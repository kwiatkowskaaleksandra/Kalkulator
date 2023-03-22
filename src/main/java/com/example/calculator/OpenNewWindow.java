package com.example.calculator;/*
 * @project Kalkulator.iml
 * @author kola
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class OpenNewWindow {
    double x, y;

    public void openWindow(String kindOfWindow, double width, double height, double positionX, double positionY){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/"+kindOfWindow+".fxml")));
            Stage menuStage = new Stage();

            menuStage.initStyle(StageStyle.UNDECORATED);

            root.setOnMousePressed(event -> {
                x = menuStage.getX() - event.getSceneX();
                y = menuStage.getY() - event.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                menuStage.setX(event.getSceneX() + x);
                menuStage.setY(event.getSceneY() + y);
            });

            menuStage.setScene(new Scene(root, width, height));
            menuStage.setX(positionX);
            menuStage.setY(positionY);
            menuStage.show();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
