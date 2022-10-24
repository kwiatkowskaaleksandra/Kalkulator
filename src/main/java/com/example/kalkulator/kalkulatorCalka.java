package com.example.kalkulator;/*
 * @project Kalkulator
 * @author kola
 */


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class kalkulatorCalka implements Initializable {

    @FXML
    public Label granicaDolnaWzor;
    @FXML
    public Label granicaGornaWzor;
    @FXML
    public TextField liczbaPodprzedzialow;
    @FXML
    public TextField granicaDolna;
    @FXML
    public TextField granicaGorna;
    @FXML
    public TextField wynikCalka;
    @FXML
    public TextField wpisanaCalka;
    @FXML
    public TextField ukrytyWzorCalki;
    @FXML
    public WebView webView;
    @FXML
    public RadioMenuItem radianItem;
    @FXML
    public RadioMenuItem stopnieItem;
    @FXML
    public RadioMenuItem mAnalitycznaItem;
    @FXML
    public RadioMenuItem mSimpsonaItem;
    @FXML
    public RadioMenuItem mTrapezowItem;
    @FXML
    public RadioMenuItem mProstokataNadomItem;
    @FXML
    public RadioMenuItem mProstokataNiedomItem;
    @FXML
    public MenuBar menuBar;


    static String granicaGornaPrzeksztalcona = null;
    static String granicaDolnaPrzeksztalcona = null;

    private static String pressedField = String.valueOf(KilknietePole.CALKA);
    public static String pressedJed = String.valueOf(Jednostka.STOPNIE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mAnalitycznaItem.setSelected(true);
      //  radianItem.setSelected(true);
        webView.getEngine().setUserStyleSheetLocation(Objects.requireNonNull(getClass().getResource("css/webView.css")).toString());
    }

    public void liczbaPodprzedzialowCheck() {
        String komunikat = "";
        try {
            if (liczbaPodprzedzialow.getText().isEmpty()) {
                komunikat = "Liczba podprzedziałów nie może pozostać pusta.";
                throw new Exception(komunikat);
            } else if (!liczbaPodprzedzialow.getText().matches("[0-9]+")) {
                komunikat = "Liczba podprzedziałów nie może zawierać liter i znaków specjalnych. Należy podać liczbę naturalną.";
                throw new Exception(komunikat);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, komunikat, "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean graniceCheck() {
        String komunikat = "";
        boolean er = false;
        try {
            if (granicaDolna.getText().isEmpty() || granicaGorna.getText().isEmpty()) {
                komunikat = "Granica całki nie może pozostać pusta.";
                throw new Exception(komunikat);
            }/*else
        if(!granicaDolna.getText().matches("[0-9]+") && !granicaDolna.getText().matches("cos.*") && !granicaDolna.getText().matches("sin.*")){
            JOptionPane.showMessageDialog(null,"Liczba podprzedziałów nie może zawierać liter i znaków specjalnych. Należy podać liczbę naturalną.","Alert",JOptionPane.WARNING_MESSAGE);
        }*/

            if (granicaDolna.getText().matches("[0-9]+")) {
                granicaDolna.setText(String.valueOf(Float.valueOf(granicaDolna.getText())));
            }
            if (granicaGorna.getText().matches("[0-9]+")) {
                granicaGorna.setText(String.valueOf(Float.valueOf(granicaGorna.getText())));
            }

            if (granicaDolna.getText().matches("(.*)∞(.*)")) {
                if (!granicaDolna.getText().equals("-∞") && !granicaDolna.getText().equals("+∞")) {
                    komunikat = "Błędna wartość dolnej granicy.";
                    er = true;
                    throw new Exception(komunikat);
                }
            }
            if (granicaGorna.getText().matches("(.*)∞(.*)")) {
                if (!granicaGorna.getText().equals("-∞") && !granicaGorna.getText().equals("+∞")) {
                    komunikat = "Błędna wartość górnej granicy.";
                    er = true;
                    throw new Exception(komunikat);
                }
            }
            if (granicaGorna.getText().matches("(.*)∞") || granicaDolna.getText().matches("(.*)∞")) {
                if (!mAnalitycznaItem.isSelected()) {
                    komunikat = "Granice nieskończoności mogą być jedynie użyte przy całkowaniu metodą analityczną.";
                    er = true;
                    throw new Exception(komunikat);
                }
            }

            if(wpisanaCalka.getText().matches("(.*)cos(.*)") || wpisanaCalka.getText().matches("(.*)sin(.*)") || wpisanaCalka.getText().matches("(.*)tan(.*)") || wpisanaCalka.getText().matches("(.*)cot(.*)") ||
                    granicaGorna.getText().matches("(.*)cos(.*)") || granicaGorna.getText().matches("(.*)sin(.*)") || granicaGorna.getText().matches("(.*)tan(.*)") || granicaGorna.getText().matches("(.*)cot(.*)") ||
            granicaDolna.getText().matches("(.*)cos(.*)") || granicaDolna.getText().matches("(.*)sin(.*)") || granicaDolna.getText().matches("(.*)tan(.*)") || granicaDolna.getText().matches("(.*)cot(.*)"))
            {
                if(!radianItem.isSelected() && !stopnieItem.isSelected()){
                    komunikat="Proszę wybrać jedną jednostkę: stopnie lub radiany.";
                    er=true;
                    throw new Exception(komunikat);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, komunikat, "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return er;
    }

    public void wynikOnAction() {
        MetodyCalkowania metodyCalkowania = new MetodyCalkowania();
        PrzeksztalcenieRownania przeksztalcenie = new PrzeksztalcenieRownania();
        WebEngine webEngine = webView.getEngine();
        if (!graniceCheck()) {

            if(stopnieItem.isSelected()){
                pressedJed= String.valueOf(Jednostka.STOPNIE);
            }else if(radianItem.isSelected()){
                pressedJed=String.valueOf(Jednostka.RADIANY);
            }

            granicaGornaWzor.setText(przeksztalcenie.zmianaRownania(granicaGorna.getText()));
            granicaDolnaWzor.setText(przeksztalcenie.zmianaRownania(granicaDolna.getText()));

            webEngine.loadContent("<p>("+przeksztalcenie.zmianaRownania(wpisanaCalka.getText())+")dx</p>","text/html");
            if(mProstokataNiedomItem.isSelected()){
                liczbaPodprzedzialowCheck();
                wynikPrzeksztalcenie(przeksztalcenie);
                wynikCalka.setText(metodyCalkowania.metodaProstokatowZNiedomiarem(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
            }else if(mProstokataNadomItem.isSelected()){
                liczbaPodprzedzialowCheck();
                wynikPrzeksztalcenie(przeksztalcenie);
                wynikCalka.setText(metodyCalkowania.metodaProstokatowZNadmiarem(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
            }else if(mTrapezowItem.isSelected()){
                liczbaPodprzedzialowCheck();
                wynikPrzeksztalcenie(przeksztalcenie);
                wynikCalka.setText(metodyCalkowania.metodaTrapezow(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
            }else if(mSimpsonaItem.isSelected()){
                liczbaPodprzedzialowCheck();
                wynikPrzeksztalcenie(przeksztalcenie);
                wynikCalka.setText(metodyCalkowania.metodaSimpsona(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
            }else if(mAnalitycznaItem.isSelected()){
                liczbaPodprzedzialow.clear();
                wynikPrzeksztalcenie(przeksztalcenie);
                wynikCalka.setText(metodyCalkowania.metodaAnalityczna(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona));
            }else{
                JOptionPane.showMessageDialog(null, "Proszę wybrać metodę całkowania.", "Alert", JOptionPane.WARNING_MESSAGE);
            }

        }
    }

    private void wynikPrzeksztalcenie(PrzeksztalcenieRownania przeksztalcenie) {
        granicaDolnaPrzeksztalcona = przeksztalcenie.przeksztalcenieRownania(granicaDolna.getText(), "math.",pressedJed);
        granicaGornaPrzeksztalcona = przeksztalcenie.przeksztalcenieRownania(granicaGorna.getText(), "math.",pressedJed);
        ukrytyWzorCalki.setText(przeksztalcenie.przeksztalcenieRownania(wpisanaCalka.getText(), "math.",pressedJed));
        if(Objects.equals(przeksztalcenie.pressedJednostka, "RADIANY")){
            stopnieItem.setSelected(false);
            radianItem.setSelected(true);
        }else if(Objects.equals(przeksztalcenie.pressedJednostka,"STOPNIE")){
            stopnieItem.setSelected(true);
            radianItem.setSelected(false);
        }
    }


    public void piOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "π");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "π");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "π");
        }
    }

    public void plusOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "+");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "+");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "+");
        }
    }

    public void minusOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "-");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "-");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "-");
        }
    }

    public void razyOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "*");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "*");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "*");
        }
    }

    public void dzielenieOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "/");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "/");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "/");
        }
    }

    public void nawiastLewyOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "(");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "(");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "(");
        }
    }

    public void nawiasPrawyOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + ")");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + ")");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + ")");
        }
    }

    public void silniaOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "!");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "!");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "!");
        }
    }

    public void eulerOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "e");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "e");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "e");
        }
    }

    public void logarytmOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "log[?][?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "log[?][?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "log[?][?]");
        }
    }

    public void logarytmNaturalnyOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "ln[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "ln[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "ln[?]");
        }
    }

    public void pierwiastekKwadratowyOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "sqrt[2,?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "sqrt[2,?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "sqrt[2,?]");
        }
    }

    public void potegaStopniaNOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "^[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "^[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "^[?]");
        }
    }

    public void pierwiastekStopniaNOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "sqrt[?,?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "sqrt[?,?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "sqrt[?,?]");
        }
    }

    public void potegaKwadratowaOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "^[2]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "^[2]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "^[2]");
        }
    }

    public void sinOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "sin[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "sin[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "sin[?]");
        }
    }

    public void cosOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "cos[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "cos[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "cos[?]");
        }
    }

    public void cotOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "cot[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "cot[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "cot[?]");
        }
    }

    public void atanOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "atan[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "atan[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "atan[?]");
        }
    }

    public void acosOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "acos[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "acos[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "acos[?]");
        }
    }

    public void acotOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "acot[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "acot[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "acot[?]");
        }
    }

    public void asinOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "asin[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "asin[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "asin[?]");
        }
    }

    public void tanOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "tan[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "tan[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "tan[?]");
        }
    }

    public void granicaDolnaPressed() {
        pressedField = String.valueOf(KilknietePole.GRANICA_DOLNA);
    }

    public void calkaPressed() {
        pressedField = String.valueOf(KilknietePole.CALKA);
    }

    public void granicaGornaPressed() {
        pressedField = String.valueOf(KilknietePole.GRANICA_GORNA);
    }

    public void nieskonczonoscOnAction() {
        String komunikat = "";
        try {
            switch (pressedField) {
                case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "∞");
                case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "∞");
                case "CALKA" -> {
                    komunikat = "Znak nieskończoności nie może zostać wpisany we wzór całki.";
                    throw new Exception(komunikat);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, komunikat, "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void menuGlowneItemOnAction() {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();

        try{
            Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Home.fxml")));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 606,614));
            menuStage.setTitle("Kalkulator");
            menuStage.setResizable(false);
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void kalkulatorNaukowyOnAction() {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();

        try{
            Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/kalkulatorNaukowy.fxml")));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 930, 600));
            menuStage.setTitle("Kalkulator Naukowy");
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void zamknijOnAction() {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();
    }
}