package com.example.kalkulator;/*
 * @project Kalkulator
 * @author kola
 */

import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Pomoc implements Initializable {
    public ImageView zamknij;
    public TextArea textAreaPomoc;

    public void zamknijOnAction(MouseEvent event) {
        Stage stage = (Stage) zamknij.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(kalkulatorCalka.rodzaj.equals("Kalkulator naukowy")){
            textAreaPomoc.setText("nau" +
                    "");
        }else if (kalkulatorCalka.rodzaj.equals("Kalkulator całek")){
            textAreaPomoc.setText("cal" +
                    "");
        }


    }
}
