package com.example.kalkulator;/*
 * @project Kalkulator
 * @author kola
 */


import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.swing.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

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
    public Button kalkulatorNaukowyButton;
    @FXML
    public CheckBox pochodnaCheckBox;
    @FXML
    public ChoiceBox<String> jednostkaChoiceBox;
    @FXML
    public ChoiceBox<String> metodaChoiceBox;
    @FXML
    public Button pomocButton;
    @FXML
    public Button historiaButton;
    @FXML
    public VBox vBoxHistoria;
    @FXML
    public VBox vBoxMenu;
    @FXML
    public ImageView wroc;
    @FXML
    public ListView<String> listViewHistoria;
    @FXML
    public Label menu;
    @FXML
    public Label menuZamknij;
    @FXML
    public ImageView zamknij;
    @FXML
    public AnchorPane slider;
    double x,y;
    static String granicaGornaPrzeksztalcona = null;
    static String granicaDolnaPrzeksztalcona = null;

    private static String pressedField = String.valueOf(KilknietePole.CALKA);
    public static String pressedJed = String.valueOf(Jednostka.STOPNIE);
    public static String rodzaj;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      //  mAnalitycznaItem.setSelected(true);
      //  radianItem.setSelected(true);
        webView.getEngine().setUserStyleSheetLocation(Objects.requireNonNull(getClass().getResource("css/webView.css")).toString());
        menuBoczne();
        choiceBoxJednostka();
        choiceBoxMetodaCalkowania();
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
                if (!metodaChoiceBox.getValue().equals("Metoda analityczna")) {
                    komunikat = "Granice nieskończoności mogą być jedynie użyte przy całkowaniu metodą analityczną.";
                    er = true;
                    throw new Exception(komunikat);
                }
            }

            if(wpisanaCalka.getText().matches("(.*)cos(.*)") || wpisanaCalka.getText().matches("(.*)sin(.*)") || wpisanaCalka.getText().matches("(.*)tan(.*)") || wpisanaCalka.getText().matches("(.*)cot(.*)") ||
                    granicaGorna.getText().matches("(.*)cos(.*)") || granicaGorna.getText().matches("(.*)sin(.*)") || granicaGorna.getText().matches("(.*)tan(.*)") || granicaGorna.getText().matches("(.*)cot(.*)") ||
            granicaDolna.getText().matches("(.*)cos(.*)") || granicaDolna.getText().matches("(.*)sin(.*)") || granicaDolna.getText().matches("(.*)tan(.*)") || granicaDolna.getText().matches("(.*)cot(.*)"))
            {
                if(jednostkaChoiceBox.getValue().equals("Jednostka")){
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

            if(jednostkaChoiceBox.getValue().equals("Stopnie")){
                pressedJed= String.valueOf(Jednostka.STOPNIE);
            }else if(jednostkaChoiceBox.getValue().equals("Radiany")){
                pressedJed=String.valueOf(Jednostka.RADIANY);
            }

            granicaGornaWzor.setText(przeksztalcenie.zmianaRownania(granicaGorna.getText()));
            granicaDolnaWzor.setText(przeksztalcenie.zmianaRownania(granicaDolna.getText()));

            webEngine.loadContent("<p>("+przeksztalcenie.zmianaRownania(wpisanaCalka.getText())+")dx</p>","text/html");
            if(metodaChoiceBox.getValue().equals("Metoda prostokątów z niedomiarem")){
                liczbaPodprzedzialowCheck();
                wynikPrzeksztalcenie(przeksztalcenie);
                wynikCalka.setText(metodyCalkowania.metodaProstokatowZNiedomiarem(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
            }else if(metodaChoiceBox.getValue().equals("Metoda prostokątów z nadmiarem")){
                liczbaPodprzedzialowCheck();
                wynikPrzeksztalcenie(przeksztalcenie);
                wynikCalka.setText(metodyCalkowania.metodaProstokatowZNadmiarem(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
            }else if(metodaChoiceBox.getValue().equals("Metoda trapezów")){
                liczbaPodprzedzialowCheck();
                wynikPrzeksztalcenie(przeksztalcenie);
                wynikCalka.setText(metodyCalkowania.metodaTrapezow(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
            }else if(metodaChoiceBox.getValue().equals("Metoda Simpsona")){
                liczbaPodprzedzialowCheck();
                wynikPrzeksztalcenie(przeksztalcenie);
                wynikCalka.setText(metodyCalkowania.metodaSimpsona(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
            }else if(metodaChoiceBox.getValue().equals("Metoda analityczna")){
                liczbaPodprzedzialow.clear();
                wynikPrzeksztalcenie(przeksztalcenie);
                wynikCalka.setText(metodyCalkowania.metodaAnalityczna(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona));
            }else{
                JOptionPane.showMessageDialog(null, "Proszę wybrać metodę całkowania.", "Alert", JOptionPane.WARNING_MESSAGE);
            }
            listViewHistoria.getItems().addAll(wpisanaCalka.getText());
        }
    }

    private void wynikPrzeksztalcenie(PrzeksztalcenieRownania przeksztalcenie) {
        granicaDolnaPrzeksztalcona = przeksztalcenie.przeksztalcenieRownania(granicaDolna.getText(), "math.",pressedJed);
        granicaGornaPrzeksztalcona = przeksztalcenie.przeksztalcenieRownania(granicaGorna.getText(), "math.",pressedJed);
        ukrytyWzorCalki.setText(przeksztalcenie.przeksztalcenieRownania(wpisanaCalka.getText(), "math.",pressedJed));
        if(Objects.equals(przeksztalcenie.pressedJednostka, "RADIANY")){
            jednostkaChoiceBox.setValue("Radiany");
        }else if(Objects.equals(przeksztalcenie.pressedJednostka,"STOPNIE")){
            jednostkaChoiceBox.setValue("Stopnie");
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

    public void choiceBoxJednostka(){
        String a ="Jednostka";
        String b ="Radiany";
        String c ="Stopnie";
        jednostkaChoiceBox.getItems().addAll(b,c);
        jednostkaChoiceBox.setValue(a);
    }

    public void choiceBoxMetodaCalkowania(){
        String a ="Metoda całkowania";
        String b ="Metoda analityczna";
        String c ="Metoda prostokątów z nadmiarem";
        String d ="Metoda prostokątów z niedomiarem";
        String e ="Metoda trapezów";
        String f ="Metoda Simpsona";
        metodaChoiceBox.getItems().addAll(b,c,d,e,f);
        metodaChoiceBox.setValue(a);
    }

    public void menuBoczne(){
        zamknij.setOnMouseClicked(event-> System.exit(0));

        slider.setTranslateX(222);
        menu.setOnMouseClicked(event->{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(222);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)->{
                menu.setVisible(false);
                menuZamknij.setVisible(true);
            });
        });

        menuZamknij.setOnMouseClicked(event->{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(222);

            slide.setOnFinished((ActionEvent e)->{
                menu.setVisible(true);
                menuZamknij.setVisible(false);
            });
        });

        vBoxHistoria.setTranslateX(222);
        historiaButton.setOnMouseClicked(event->{
            vBoxHistoria.setVisible(true);
            TranslateTransition transition=new TranslateTransition(Duration.seconds(0.4),vBoxHistoria);
            transition.setToX(0);
            transition.play();
        });

        wroc.setOnMouseClicked(event->{
            TranslateTransition transition=new TranslateTransition(Duration.seconds(0.4),vBoxHistoria);
            transition.setToX(222);
            transition.play();
            //vBoxMenu.setVisible(true);
            transition.setOnFinished((ActionEvent e)-> vBoxHistoria.setVisible(false));
        });
    }

    public void kalkulatorNaukowyOnAction() {
        Stage stage = (Stage) kalkulatorNaukowyButton.getScene().getWindow();
        stage.close();

        try{
            Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/kalkulatorNaukowy.fxml")));
            Stage menuStage = new Stage();
            menuStage.setTitle("Kalkulator naukowy");
            menuStage.initStyle(StageStyle.UNDECORATED);
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    x=menuStage.getX()-event.getSceneX();
                    y=menuStage.getY()-event.getSceneY();
                }
            });

            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    menuStage.setX(event.getSceneX()+x);
                    menuStage.setY(event.getSceneY()+y);
                }
            });
            menuStage.setScene(new Scene(root, 972, 600));
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }



    public void pomocOnAction() {
        rodzaj="Kalkulator całek";
        try{
            Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/pomoc.fxml")));
            Stage menuStage = new Stage();
            menuStage.setTitle("Pomoc");
            menuStage.initStyle(StageStyle.UNDECORATED);

            root.setOnMousePressed(event -> {
                x=menuStage.getX()-event.getSceneX();
                y=menuStage.getY()-event.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                menuStage.setX(event.getSceneX()+x);
                menuStage.setY(event.getSceneY()+y);
            });

            menuStage.setScene(new Scene(root, 500, 600));
            menuStage.setX(1400);
            menuStage.setY(300);
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void historiaOnMouseClicked() {
        listViewHistoria.setOnMouseClicked(event2-> wpisanaCalka.setText( listViewHistoria.getSelectionModel().getSelectedItem()));
    }
}