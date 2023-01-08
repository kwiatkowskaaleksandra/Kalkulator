package com.example.kalkulator;/*
 * @project Kalkulator
 * @author kola
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Pomoc implements Initializable {
    @FXML
    public ImageView zamknijPomoc;
    @FXML
    public WebView webView;
    @FXML
    public Label labelRodzajKalkulatora;
    @FXML
    public Label labelOpis;

    @FXML
    public void zamknijOnAction() {
        Stage stage = (Stage) zamknijPomoc.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (kalkulatorCalka.rodzaj.equals("Kalkulator naukowy")) {
            WebEngine webEngine = webView.getEngine();

            URL url2 = this.getClass().getResource("css/pomocNaukowy.html");
            webEngine.load(Objects.requireNonNull(url2).toString());

            labelRodzajKalkulatora.setText(kalkulatorCalka.rodzaj);
            labelOpis.setText("Kalkulator naukowy jest przeznaczony do prostych działań, jak i bardziej skomplikowanych, takich jak: dodawanie, odejmowanie, mnożenie, dzielenie, pierwiastkowanie, potęgowanie, logarytmowanie, obliczenia silni, działań z wykorzystaniem trygonometrii oraz obliczenia pochodnej.");

        } else if (kalkulatorCalka.rodzaj.equals("Kalkulator całek")) {
            WebEngine webEngine = webView.getEngine();

            URL url2 = this.getClass().getResource("css/pomocCalka.html");
            webEngine.load(Objects.requireNonNull(url2).toString());

            labelRodzajKalkulatora.setText(kalkulatorCalka.rodzaj);
            labelOpis.setText("Kalkulator całek jest przeznaczony do obliczania całek nieoznaczonych i oznaczonych. Całkę oznaczoną można wyznaczyć metodami numerycznymi: prostokątów z nadmiarem, prostokątów z niedomiarem, trapezów, Simpsona oraz metodą analityczną. Aby poprawnie obliczyć całkę oznaczoną należy uzupełnić pola dotyczące granicy dolnej i górnej, wzór całki oraz w przypadku wykorzystywania metod numerycznych liczbę podprzedziałów. Natomiast w przypadku całki nieoznaczonej należy wypełnić jedynie pole ze wzorem całki.");
        }
    }
}
