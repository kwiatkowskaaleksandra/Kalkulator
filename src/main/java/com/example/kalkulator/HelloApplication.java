package com.example.kalkulator;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    double x,y;
    @Override
    public void start(Stage stage) throws IOException {
      /*  FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 606,614);
        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();*/

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Home.fxml")));
        stage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x=stage.getX()-event.getSceneX();
                y=stage.getY()-event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getSceneX()+x);
                stage.setY(event.getSceneY()+y);
            }
        });

        stage.setScene(new Scene(root, 606,614));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}