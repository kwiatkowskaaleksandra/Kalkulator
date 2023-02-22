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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class kalkulatorCalka extends MetodyCalkowania implements Initializable {

    @FXML
    public Label granicaDolnaWzor;
    @FXML
    public Label granicaGornaWzor;
    @FXML
    public Label granicaDolnaLabel;
    @FXML
    public Label granicaGornaLabel;
    @FXML
    public Label wynikZnak;
    @FXML
    public Label liczbaPodprzedzialowLabel;
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
    @FXML
    public Button zeroOnAction;
    @FXML
    public Button jedenOnAction;
    @FXML
    public Button dwaOnAction;
    @FXML
    public Button trzyOnAction;
    @FXML
    public Button czteryOnAction;
    @FXML
    public Button piecOnAction;
    @FXML
    public Button szescOnAction;
    @FXML
    public Button siedemOnAction;
    @FXML
    public Button osiemOnAction;
    @FXML
    public Button dziewiecOnAction;
    @FXML
    public Label wykresOtworz;
    @FXML
    public Label wykresZamknij;
    @FXML
    public Pane paneWykres;
    @FXML
    public Spinner<Integer> xMinSpinner;
    @FXML
    public Spinner<Integer> xMaxSpinner;
    @FXML
    public WebView webView1;
    @FXML
    public WebView grGornaWebView;
    @FXML
    public WebView grDolnaWebView;
    double x, y;
    static String granicaGornaPrzeksztalcona = null;
    static String granicaDolnaPrzeksztalcona = null;
    private static String pressedField = String.valueOf(KilknietePole.CALKA);
    public static String pressedJed = String.valueOf(Jednostka.STOPNIE);
    public static int clickedWpisanaCalka = 0;
    public static int clickedWpisanaGranicaDolna = 0;
    public static int clickedWpisanaGranicaGorna = 0;
    public static int clickedLiczbaPrzedzialow = 0;
    public static String rodzaj;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webView.getEngine().setUserStyleSheetLocation(Objects.requireNonNull(getClass().getResource("css/webView.css")).toString());
        webView1.getEngine().setUserStyleSheetLocation(Objects.requireNonNull(getClass().getResource("css/webView.css")).toString());
        grGornaWebView.getEngine().setUserStyleSheetLocation(Objects.requireNonNull(getClass().getResource("css/webView.css")).toString());
        grDolnaWebView.getEngine().setUserStyleSheetLocation(Objects.requireNonNull(getClass().getResource("css/webView.css")).toString());
        menuBoczne();
        choiceBoxJednostka();
        choiceBoxMetodaCalkowania();
        cyfryOnAction();
        EventHandler<KeyEvent> handler = new EventHandler<>() {
            private boolean willConsume = false;

            @Override
            public void handle(KeyEvent event) {
                if (willConsume) {
                    event.consume();
                }

                if (event.getCode() == KeyCode.B || event.getCode() == KeyCode.D || event.getCode() == KeyCode.F || event.getCode() == KeyCode.H || event.getCode() == KeyCode.J || event.getCode() == KeyCode.K || event.getCode() == KeyCode.M ||
                        event.getCode() == KeyCode.P || event.getCode() == KeyCode.U || event.getCode() == KeyCode.V || event.getCode() == KeyCode.W || event.getCode() == KeyCode.Y || event.getCode() == KeyCode.Z) {
                    if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                        willConsume = true;
                    } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                        willConsume = false;
                    }
                }
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
                clickedLiczbaPrzedzialow = liczbaPodprzedzialow.getCaretPosition();
            }
        };
        wpisanaCalka.addEventFilter(KeyEvent.ANY, handler);
        granicaGorna.addEventFilter(KeyEvent.ANY, handler);
        granicaDolna.addEventFilter(KeyEvent.ANY, handler);
        liczbaPodprzedzialow.addEventFilter(KeyEvent.ANY, handler);

        EventHandler<MouseEvent> mouseEvent = event -> {
            clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            clickedLiczbaPrzedzialow = liczbaPodprzedzialow.getCaretPosition();
        };
        wpisanaCalka.addEventFilter(MouseEvent.ANY, mouseEvent);
        granicaGorna.addEventFilter(MouseEvent.ANY, mouseEvent);
        granicaDolna.addEventFilter(MouseEvent.ANY, mouseEvent);
        liczbaPodprzedzialow.addEventFilter(MouseEvent.ANY, mouseEvent);

        metodaChoiceBox.setOnAction(actionEvent -> {
            if (metodaChoiceBox.getValue().equals("Całka nieoznaczona")) {
                granicaGorna.setVisible(false);
                granicaDolna.setVisible(false);
                liczbaPodprzedzialow.setVisible(false);
                granicaDolnaLabel.setVisible(false);
                granicaGornaLabel.setVisible(false);
                liczbaPodprzedzialowLabel.setVisible(false);
                granicaDolnaWzor.setVisible(false);
                granicaGornaWzor.setVisible(false);
                wynikCalka.setVisible(false);
                wynikZnak.setVisible(false);
            } else {
                granicaGorna.setVisible(true);
                granicaDolna.setVisible(true);
                liczbaPodprzedzialow.setVisible(true);
                granicaDolnaLabel.setVisible(true);
                granicaGornaLabel.setVisible(true);
                liczbaPodprzedzialowLabel.setVisible(true);
                granicaDolnaWzor.setVisible(true);
                granicaGornaWzor.setVisible(true);
                wynikCalka.setVisible(true);
                wynikZnak.setVisible(true);
            }
        });

    }

    public boolean liczbaPodprzedzialowCheck(String liczbaPodprzedzialow) {
        String komunikat = "";
        boolean blad = false;
        try {
            if (liczbaPodprzedzialow.isEmpty()) {
                komunikat = "Liczba podprzedziałów nie może pozostać pusta.";
                blad = true;
                throw new Exception(komunikat);
            } else if (!liczbaPodprzedzialow.matches("[0-9]+")) {
                komunikat = "Liczba podprzedziałów nie może zawierać liter i znaków specjalnych. Należy podać liczbę naturalną.";
                blad = true;
                throw new Exception(komunikat);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, komunikat, "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return !blad;
    }

    public void menuWykres() {
        paneWykres.setTranslateX(222);
        wykresZamknij.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(paneWykres);
            slide.setToX(222);
            slide.play();
            paneWykres.setTranslateX(0);
            slide.setOnFinished((ActionEvent e) -> {
                wykresOtworz.setVisible(true);
                wykresZamknij.setVisible(false);
            });
        });

        wykresOtworz.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(paneWykres);
            slide.setToX(0);
            slide.play();
            paneWykres.setTranslateX(222);
            slide.setOnFinished((ActionEvent e) -> {
                wykresOtworz.setVisible(false);
                wykresZamknij.setVisible(true);
            });
        });

        SpinnerValueFactory<Integer> valueFactoryMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(-9999, 9999);
        valueFactoryMin.setValue(-4);
        xMinSpinner.setValueFactory(valueFactoryMin);
        SpinnerValueFactory<Integer> valueFactoryMax = new SpinnerValueFactory.IntegerSpinnerValueFactory(-9999, 9999);
        valueFactoryMax.setValue(4);
        xMaxSpinner.setValueFactory(valueFactoryMax);
    }

    public boolean wykresCheck(String dzialanie, int max, int min) {
        boolean err = false;
        String komunikat = "";
        try {
            if (dzialanie.isEmpty()) {
                komunikat = "Aby wyświetlić wykres należy najpierw wpisać funkcję.";
                err = true;
                throw new Exception(komunikat);
            }

            if (dzialanie.matches("(.*)ln(.*)") || dzialanie.matches("(.*)log(.*)") || dzialanie.matches("(.*)sqrt(.*)")) {
                if (max < 0 || min < 0) {
                    komunikat = "Funkcja nie może przyjmować ujemnych argumentów.";
                    err = true;
                    throw new Exception(komunikat);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, komunikat, "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return !err;
    }

    @FXML
    public void wykres() {
        wykresFunkcji wykres = new wykresFunkcji();
        if (wykresCheck(wpisanaCalka.getText(), xMaxSpinner.getValue(), xMinSpinner.getValue())) {
            Plot2DPanel plotPanel = new Plot2DPanel();
            double[] x = wykres.listaX(wpisanaCalka.getText(), xMaxSpinner.getValue(), xMinSpinner.getValue());
            double[] y = wykres.listaY(wpisanaCalka.getText(), xMaxSpinner.getValue(), xMinSpinner.getValue(), x);

            plotPanel.addLegend("SOUTH");
            plotPanel.addLinePlot("f(x)", Color.BLUE, x, y);

            if (!wynikCalka.getText().isEmpty()) {
                String przeksztalconaFunckja = wzorCalki;
                if (przeksztalconaFunckja.matches("(.*)log(.*)")) {
                    przeksztalconaFunckja = przeksztalconaFunckja.replace("log", "sympy.log");
                }
                double[] xP = wykres.listaX(przeksztalconaFunckja, xMaxSpinner.getValue(), xMinSpinner.getValue());
                double[] yP = wykres.listaY(przeksztalconaFunckja, xMaxSpinner.getValue(), xMinSpinner.getValue(), xP);
                plotPanel.addLinePlot("F(x))", Color.GREEN, xP, yP);
            }

            JFrame frame = new JFrame("Wykres");
            frame.setContentPane(plotPanel);
            frame.setSize(700, 700);
            frame.setVisible(true);
        }
    }

    public boolean wpisanaCalkaGraniceCheck(String grDolna, String grGorna, String wzor, String metoda, String jednostka) {
        String komunikat = "";
        boolean blad = false;
        try {
            if (!metodaChoiceBox.getValue().equals("Całka nieoznaczona")) {
                if (grDolna.isEmpty() || grGorna.isEmpty()) {
                    komunikat = "Granica całki nie może pozostać pusta.";
                    blad = true;
                    throw new Exception(komunikat);
                }
                if (wzor.isEmpty()) {
                    komunikat = "Wzór całki nie może pozostać pusty.";
                    blad = true;
                    throw new Exception(komunikat);
                }
                if (grDolna.matches("(.*)∞(.*)")) {
                    if (!grDolna.equals("-∞") && !grDolna.equals("+∞")) {
                        komunikat = "Błędna wartość dolnej granicy.";
                        blad = true;
                        throw new Exception(komunikat);
                    }
                }
                if (grGorna.matches("(.*)∞(.*)")) {
                    if (!grGorna.equals("-∞") && !grGorna.equals("+∞")) {
                        komunikat = "Błędna wartość górnej granicy.";
                        blad = true;
                        throw new Exception(komunikat);
                    }
                }
                if (grGorna.matches("(.*)∞") || grDolna.matches("(.*)∞")) {
                    if (!metoda.equals("Metoda analityczna")) {
                        komunikat = "Granice nieskończoności mogą być jedynie użyte przy całkowaniu metodą analityczną.";
                        blad = true;
                        throw new Exception(komunikat);
                    }
                }
                if (wzor.matches("(.*)cos(.*)") || wzor.matches("(.*)sin(.*)") || wzor.matches("(.*)tan(.*)") || wzor.matches("(.*)cot(.*)") ||
                        grGorna.matches("(.*)cos(.*)") || grGorna.matches("(.*)sin(.*)") || grGorna.matches("(.*)tan(.*)") || grGorna.matches("(.*)cot(.*)") ||
                        grDolna.matches("(.*)cos(.*)") || grDolna.matches("(.*)sin(.*)") || grDolna.matches("(.*)tan(.*)") || grDolna.matches("(.*)cot(.*)")) {
                    if (jednostka.equals("Jednostka")) {
                        komunikat = "Proszę wybrać jedną jednostkę: stopnie lub radiany.";
                        blad = true;
                        throw new Exception(komunikat);
                    }
                }
                if (wzor.matches("(.*)\\?(.*)") || grDolna.matches("(.*)\\?(.*)") || grGorna.matches("(.*)\\?(.*)")) {
                    komunikat = "Błędnie wpisane działanie. W miejsce '?' należy wpisać wartość liczbową.";
                    blad = true;
                    throw new Exception(komunikat);
                }
            } else {
                if (wzor.matches("(.*)cos(.*)") || wzor.matches("(.*)sin(.*)") || wzor.matches("(.*)tan(.*)") || wzor.matches("(.*)cot(.*)")) {
                    if (jednostka.equals("Jednostka")) {
                        komunikat = "Proszę wybrać jedną jednostkę: stopnie lub radiany.";
                        blad = true;
                        throw new Exception(komunikat);
                    }
                }
                if (wzor.matches("(.*)\\?(.*)")) {
                    komunikat = "Błędnie wpisane działanie. W miejsce '?' należy wpisać wartość liczbową.";
                    blad = true;
                    throw new Exception(komunikat);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, komunikat, "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return !blad;
    }

    public void wynikOnAction() {
        PrzeksztalcenieRownania przeksztalcenie = new PrzeksztalcenieRownania();
        WebEngine webEngine = webView.getEngine();
        WebEngine webEngine1 = webView1.getEngine();
        WebEngine grD = grDolnaWebView.getEngine();
        WebEngine grG = grGornaWebView.getEngine();
        if (wpisanaCalkaGraniceCheck(granicaDolna.getText(), granicaGorna.getText(), wpisanaCalka.getText(), metodaChoiceBox.getValue(), jednostkaChoiceBox.getValue())) {
            if (granicaDolna.getText().matches("[0-9]+")) {
                if (!granicaDolna.getText().matches("(.*)x(.*)")) {
                    granicaDolna.setText(String.valueOf(Float.valueOf(granicaDolna.getText())));
                }
            }
            if (granicaGorna.getText().matches("[0-9]+")) {
                if (!granicaGorna.getText().matches("(.*)x(.*)")) {
                    granicaGorna.setText(String.valueOf(Float.valueOf(granicaGorna.getText())));
                }
            }

            if (jednostkaChoiceBox.getValue().equals("Stopnie")) {
                pressedJed = String.valueOf(Jednostka.STOPNIE);
            } else if (jednostkaChoiceBox.getValue().equals("Radiany")) {
                pressedJed = String.valueOf(Jednostka.RADIANY);
            }

            String gora = (przeksztalcenie.zmianaRownania(granicaGorna.getText()));
            String dol = (przeksztalcenie.zmianaRownania(granicaDolna.getText()));
            grD.loadContent("<p scroll=\"no\" style=\"font-size: 12px;\">" + przeksztalcenie.przeksztalcenieWyniku(dol) + "</p>", "text/html");
            grG.loadContent("<p scroll=\"no\" style=\"font-size: 12px;\">" + przeksztalcenie.przeksztalcenieWyniku(gora) + "</p>", "text/html");

            webEngine.loadContent("<p scroll=\"no\">(" + przeksztalcenie.zmianaRownania(wpisanaCalka.getText()) + ")dx</p>", "text/html");
            if (metodaChoiceBox.getValue().equals("Metoda prostokątów z niedomiarem")) {
                if (liczbaPodprzedzialowCheck(liczbaPodprzedzialow.getText())) {
                    wynikPrzeksztalcenie(przeksztalcenie);
                    if (!metodaProstokatowZNiedomiarem(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()).equals("")) {
                        wynikCalka.setText(metodaProstokatowZNiedomiarem(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
                        webEngine1.loadContent("<p> <p2 style=\"font-size:23;\">[</p2>" + przeksztalcenie.przeksztalcenieWyniku(wzorCalki) + "<p2 style=\"font-size:23;\">]</p2><sup style=\"position: relative; left: 3px; top:-4px;\">" + przeksztalcenie.przeksztalcenieWyniku(granicaGornaPrzeksztalcona) + "</sup><sub style=\"position: relative; left: -15px; top: 3px;\">" + przeksztalcenie.przeksztalcenieWyniku(granicaDolnaPrzeksztalcona) + "</sub></p>", "text/html");
                    }
                }
            } else if (metodaChoiceBox.getValue().equals("Metoda prostokątów z nadmiarem")) {
                if (liczbaPodprzedzialowCheck(liczbaPodprzedzialow.getText())) {
                    wynikPrzeksztalcenie(przeksztalcenie);
                    if (!metodaProstokatowZNadmiarem(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()).equals("")) {
                        wynikCalka.setText(metodaProstokatowZNadmiarem(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
                        webEngine1.loadContent("<p><p2 style=\"font-size:23;\">[</p2>" + przeksztalcenie.przeksztalcenieWyniku(wzorCalki) + "<p2 style=\"font-size:23;\">]</p2><sup style=\"position: relative; left: 3px; top:-4px;\">" + przeksztalcenie.przeksztalcenieWyniku(granicaGornaPrzeksztalcona) + "</sup><sub style=\"position: relative; left: -15px; top: 3px;\">" + przeksztalcenie.przeksztalcenieWyniku(granicaDolnaPrzeksztalcona) + "</sub></p>", "text/html");
                    }
                }
            } else if (metodaChoiceBox.getValue().equals("Metoda trapezów")) {
                if (liczbaPodprzedzialowCheck(liczbaPodprzedzialow.getText())) {
                    wynikPrzeksztalcenie(przeksztalcenie);
                    if (!metodaTrapezow(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()).equals("")) {
                        wynikCalka.setText(metodaTrapezow(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
                        webEngine1.loadContent("<p><p2 style=\"font-size:23;\">[</p2>" + przeksztalcenie.przeksztalcenieWyniku(wzorCalki) + "<p2 style=\"font-size:23;\">]</p2><sup style=\"position: relative; left: 3px; top:-4px;\">" + przeksztalcenie.przeksztalcenieWyniku(granicaGornaPrzeksztalcona) + "</sup><sub style=\"position: relative; left: -15px; top: 3px;\">" + przeksztalcenie.przeksztalcenieWyniku(granicaDolnaPrzeksztalcona) + "</sub></p>", "text/html");
                    }
                }
            } else if (metodaChoiceBox.getValue().equals("Metoda Simpsona")) {
                if (liczbaPodprzedzialowCheck(liczbaPodprzedzialow.getText())) {
                    wynikPrzeksztalcenie(przeksztalcenie);
                    if (!metodaSimpsona(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()).equals("")) {
                        wynikCalka.setText(metodaSimpsona(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
                        webEngine1.loadContent("<p><p2 style=\"font-size:23;\">[</p2>" + przeksztalcenie.przeksztalcenieWyniku(wzorCalki) + "<p2 style=\"font-size:23;\">]</p2><sup style=\"position: relative; left: 3px; top:-4px;\">" + przeksztalcenie.przeksztalcenieWyniku(granicaGornaPrzeksztalcona) + "</sup><sub style=\"position: relative; left: -15px; top: 3px;\">" + przeksztalcenie.przeksztalcenieWyniku(granicaDolnaPrzeksztalcona) + "</sub></p>", "text/html");
                    }
                }
            } else if (metodaChoiceBox.getValue().equals("Metoda analityczna")) {
                liczbaPodprzedzialow.clear();
                wynikPrzeksztalcenie(przeksztalcenie);
                if (!metodaAnalityczna(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona).equals("")) {
                    wynikCalka.setText(metodaAnalityczna(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona));
                    webEngine1.loadContent("<p scroll=\"no\">[" + przeksztalcenie.przeksztalcenieWyniku(wzorCalki) + "<p2 style=\"font-size:23;\">]</p2><sup style=\"position: relative; left: 3px; top:-4px;\">" + przeksztalcenie.przeksztalcenieWyniku(granicaGornaPrzeksztalcona) + "</sup><sub style=\"position: relative; left: -15px; top: 3px;\">" + przeksztalcenie.przeksztalcenieWyniku(granicaDolnaPrzeksztalcona) + "</sub></p>", "text/html");
                }
            } else if (metodaChoiceBox.getValue().equals("Całka nieoznaczona")) {
                wynikPrzeksztalcenie(przeksztalcenie);
                if (!calkaNieoznaczona(ukrytyWzorCalki.getText()).equals("")) {
                    wynikCalka.setText(calkaNieoznaczona(ukrytyWzorCalki.getText()));
                    webEngine1.loadContent("<p scroll=\"no\">" + przeksztalcenie.przeksztalcenieWyniku(wzorCalki) + "</p>", "text/html");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Proszę wybrać metodę całkowania.", "Alert", JOptionPane.WARNING_MESSAGE);
            }
            listViewHistoria.getItems().addAll(wpisanaCalka.getText());
        }
    }

    public void wynikPrzeksztalcenie(PrzeksztalcenieRownania przeksztalcenie) {
        granicaDolnaPrzeksztalcona = przeksztalcenie.przeksztalcenieRownania(granicaDolna.getText(), "sympy.", pressedJed);
        granicaGornaPrzeksztalcona = przeksztalcenie.przeksztalcenieRownania(granicaGorna.getText(), "sympy.", pressedJed);
        ukrytyWzorCalki.setText(przeksztalcenie.przeksztalcenieRownania(wpisanaCalka.getText(), "sympy.", pressedJed));
        if (Objects.equals(przeksztalcenie.pressedJednostka, "RADIANY")) {
            jednostkaChoiceBox.setValue("Radiany");
        } else if (Objects.equals(przeksztalcenie.pressedJednostka, "STOPNIE")) {
            jednostkaChoiceBox.setValue("Stopnie");
        }
    }


    public void piOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "π");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "π");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "π");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void plusOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "+");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "+");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "+");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void minusOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "-");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "-");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "-");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void razyOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "*");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "*");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "*");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void dzielenieOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "/");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "/");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "/");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void nawiastLewyOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "(");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "(");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "(");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void nawiasPrawyOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, ")");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, ")");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, ")");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void silniaOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "!");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "!");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "!");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void eulerOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "e");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "e");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "e");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void logarytmOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "log[?][?]");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "log[?][?]");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "log[?][?]");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void logarytmNaturalnyOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "ln[?]");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "ln[?]");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "ln[?]");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void pierwiastekKwadratowyOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "sqrt[2,?]");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "sqrt[2,?]");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "sqrt[2,?]");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void potegaStopniaNOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "^[?]");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "^[?]");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "^[?]");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void pierwiastekStopniaNOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "sqrt[?,?]");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "sqrt[?,?]");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "sqrt[?,?]");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void potegaKwadratowaOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "^[2]");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "^[2]");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "^[2]");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void sinOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "sin[?]");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "sin[?]");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "sin[?]");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void cosOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "cos[?]");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "cos[?]");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "cos[?]");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void cotOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "cot[?]");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "cot[?]");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "cot[?]");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void atanOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "atan[?]");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "atan[?]");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "atan[?]");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void acosOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "acos[?]");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "acos[?]");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "acos[?]");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void acotOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "acot[?]");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "acot[?]");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "acot[?]");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void asinOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "asin[?]");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "asin[?]");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "asin[?]");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void tanOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "tan[?]");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "tan[?]");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "tan[?]");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void nieskonczonoscOnAction() {
        String komunikat = "";
        try {
            switch (pressedField) {
                case "GRANICA_GORNA" -> {
                    granicaGorna.insertText(clickedWpisanaGranicaGorna, "∞");
                    clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
                }
                case "GRANICA_DOLNA" -> {
                    granicaDolna.insertText(clickedWpisanaGranicaDolna, "∞");
                    clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
                }
                case "CALKA" -> {
                    komunikat = "Znak nieskończoności nie może zostać wpisany we wzór całki.";
                    throw new Exception(komunikat);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, komunikat, "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void historiaOnMouseClicked() {
        listViewHistoria.setOnMouseClicked(event2 -> wpisanaCalka.setText(listViewHistoria.getSelectionModel().getSelectedItem()));
    }

    public void cyfryOnAction() {
        zeroOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "GRANICA_GORNA" -> {
                    granicaGorna.insertText(clickedWpisanaGranicaGorna, "0");
                    clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
                }
                case "GRANICA_DOLNA" -> {
                    granicaDolna.insertText(clickedWpisanaGranicaDolna, "0");
                    clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
                }
                case "CALKA" -> {
                    wpisanaCalka.insertText(clickedWpisanaCalka, "0");
                    clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
                }
                case "LICZBA_PODPRZEDZIALOW" -> {
                    liczbaPodprzedzialow.insertText(clickedLiczbaPrzedzialow, "0");
                    clickedLiczbaPrzedzialow = liczbaPodprzedzialow.getCaretPosition();
                }
            }
        });

        jedenOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "GRANICA_GORNA" -> {
                    granicaGorna.insertText(clickedWpisanaGranicaGorna, "1");
                    clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
                }
                case "GRANICA_DOLNA" -> {
                    granicaDolna.insertText(clickedWpisanaGranicaDolna, "1");
                    clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
                }
                case "CALKA" -> {
                    wpisanaCalka.insertText(clickedWpisanaCalka, "1");
                    clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
                }
                case "LICZBA_PODPRZEDZIALOW" -> {
                    liczbaPodprzedzialow.insertText(clickedLiczbaPrzedzialow, "1");
                    clickedLiczbaPrzedzialow = liczbaPodprzedzialow.getCaretPosition();
                }
            }
        });

        dwaOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "GRANICA_GORNA" -> {
                    granicaGorna.insertText(clickedWpisanaGranicaGorna, "2");
                    clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
                }
                case "GRANICA_DOLNA" -> {
                    granicaDolna.insertText(clickedWpisanaGranicaDolna, "2");
                    clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
                }
                case "CALKA" -> {
                    wpisanaCalka.insertText(clickedWpisanaCalka, "2");
                    clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
                }
                case "LICZBA_PODPRZEDZIALOW" -> {
                    liczbaPodprzedzialow.insertText(clickedLiczbaPrzedzialow, "2");
                    clickedLiczbaPrzedzialow = liczbaPodprzedzialow.getCaretPosition();
                }
            }
        });

        trzyOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "GRANICA_GORNA" -> {
                    granicaGorna.insertText(clickedWpisanaGranicaGorna, "3");
                    clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
                }
                case "GRANICA_DOLNA" -> {
                    granicaDolna.insertText(clickedWpisanaGranicaDolna, "3");
                    clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
                }
                case "CALKA" -> {
                    wpisanaCalka.insertText(clickedWpisanaCalka, "3");
                    clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
                }
                case "LICZBA_PODPRZEDZIALOW" -> {
                    liczbaPodprzedzialow.insertText(clickedLiczbaPrzedzialow, "3");
                    clickedLiczbaPrzedzialow = liczbaPodprzedzialow.getCaretPosition();
                }
            }
        });

        czteryOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "GRANICA_GORNA" -> {
                    granicaGorna.insertText(clickedWpisanaGranicaGorna, "4");
                    clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
                }
                case "GRANICA_DOLNA" -> {
                    granicaDolna.insertText(clickedWpisanaGranicaDolna, "4");
                    clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
                }
                case "CALKA" -> {
                    wpisanaCalka.insertText(clickedWpisanaCalka, "4");
                    clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
                }
                case "LICZBA_PODPRZEDZIALOW" -> {
                    liczbaPodprzedzialow.insertText(clickedLiczbaPrzedzialow, "4");
                    clickedLiczbaPrzedzialow = liczbaPodprzedzialow.getCaretPosition();
                }
            }
        });

        piecOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "GRANICA_GORNA" -> {
                    granicaGorna.insertText(clickedWpisanaGranicaGorna, "5");
                    clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
                }
                case "GRANICA_DOLNA" -> {
                    granicaDolna.insertText(clickedWpisanaGranicaDolna, "5");
                    clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
                }
                case "CALKA" -> {
                    wpisanaCalka.insertText(clickedWpisanaCalka, "5");
                    clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
                }
                case "LICZBA_PODPRZEDZIALOW" -> {
                    liczbaPodprzedzialow.insertText(clickedLiczbaPrzedzialow, "5");
                    clickedLiczbaPrzedzialow = liczbaPodprzedzialow.getCaretPosition();
                }
            }
        });

        szescOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "GRANICA_GORNA" -> {
                    granicaGorna.insertText(clickedWpisanaGranicaGorna, "6");
                    clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
                }
                case "GRANICA_DOLNA" -> {
                    granicaDolna.insertText(clickedWpisanaGranicaDolna, "6");
                    clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
                }
                case "CALKA" -> {
                    wpisanaCalka.insertText(clickedWpisanaCalka, "6");
                    clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
                }
                case "LICZBA_PODPRZEDZIALOW" -> {
                    liczbaPodprzedzialow.insertText(clickedLiczbaPrzedzialow, "6");
                    clickedLiczbaPrzedzialow = liczbaPodprzedzialow.getCaretPosition();
                }
            }
        });

        siedemOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "GRANICA_GORNA" -> {
                    granicaGorna.insertText(clickedWpisanaGranicaGorna, "7");
                    clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
                }
                case "GRANICA_DOLNA" -> {
                    granicaDolna.insertText(clickedWpisanaGranicaDolna, "7");
                    clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
                }
                case "CALKA" -> {
                    wpisanaCalka.insertText(clickedWpisanaCalka, "7");
                    clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
                }
                case "LICZBA_PODPRZEDZIALOW" -> {
                    liczbaPodprzedzialow.insertText(clickedLiczbaPrzedzialow, "7");
                    clickedLiczbaPrzedzialow = liczbaPodprzedzialow.getCaretPosition();
                }
            }
        });

        osiemOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "GRANICA_GORNA" -> {
                    granicaGorna.insertText(clickedWpisanaGranicaGorna, "8");
                    clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
                }
                case "GRANICA_DOLNA" -> {
                    granicaDolna.insertText(clickedWpisanaGranicaDolna, "8");
                    clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
                }
                case "CALKA" -> {
                    wpisanaCalka.insertText(clickedWpisanaCalka, "8");
                    clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
                }
                case "LICZBA_PODPRZEDZIALOW" -> {
                    liczbaPodprzedzialow.insertText(clickedLiczbaPrzedzialow, "8");
                    clickedLiczbaPrzedzialow = liczbaPodprzedzialow.getCaretPosition();
                }
            }
        });

        dziewiecOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "GRANICA_GORNA" -> {
                    granicaGorna.insertText(clickedWpisanaGranicaGorna, "9");
                    clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
                }
                case "GRANICA_DOLNA" -> {
                    granicaDolna.insertText(clickedWpisanaGranicaDolna, "9");
                    clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
                }
                case "CALKA" -> {
                    wpisanaCalka.insertText(clickedWpisanaCalka, "9");
                    clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
                }
                case "LICZBA_PODPRZEDZIALOW" -> {
                    liczbaPodprzedzialow.insertText(clickedLiczbaPrzedzialow, "9");
                    clickedLiczbaPrzedzialow = liczbaPodprzedzialow.getCaretPosition();
                }
            }
        });
    }

    public void kropkaOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, ".");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, ".");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, ".");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void przecinekOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, ",");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, ",");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, ",");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void xOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                granicaGorna.insertText(clickedWpisanaGranicaGorna, "x");
                clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
            }
            case "GRANICA_DOLNA" -> {
                granicaDolna.insertText(clickedWpisanaGranicaDolna, "x");
                clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
            }
            case "CALKA" -> {
                wpisanaCalka.insertText(clickedWpisanaCalka, "x");
                clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
            }
        }
    }

    public void usunCaloscOnAction() {
        wpisanaCalka.clear();
        wynikCalka.clear();
        granicaDolna.clear();
        granicaGorna.clear();
        liczbaPodprzedzialow.clear();
        granicaDolnaWzor.setText("");
        granicaGornaWzor.setText("");
        webView.getEngine().load("");
        webView1.getEngine().load("");
    }

    public void usunOstatnieOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> {
                if (granicaGorna.getLength() > 0) {
                    granicaGorna.setText(granicaGorna.getText(0, granicaGorna.getLength() - 1));
                    clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
                }
            }
            case "GRANICA_DOLNA" -> {
                if (granicaDolna.getLength() > 0) {
                    granicaDolna.setText(granicaDolna.getText(0, granicaDolna.getLength() - 1));
                    clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
                }
            }
            case "CALKA" -> {
                if (wpisanaCalka.getLength() > 0) {
                    wpisanaCalka.setText(wpisanaCalka.getText(0, wpisanaCalka.getLength() - 1));
                    clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
                }
            }
            case "LICZBA_PODPRZEDZIALOW" -> {
                if (liczbaPodprzedzialow.getLength() > 0) {
                    liczbaPodprzedzialow.setText(liczbaPodprzedzialow.getText(0, liczbaPodprzedzialow.getLength() - 1));
                    clickedLiczbaPrzedzialow = liczbaPodprzedzialow.getCaretPosition();
                }
            }
        }
    }

    public void granicaDolnaPressed() {
        pressedField = String.valueOf(KilknietePole.GRANICA_DOLNA);
        clickedWpisanaGranicaDolna = granicaDolna.getCaretPosition();
    }

    public void calkaPressed() {
        pressedField = String.valueOf(KilknietePole.CALKA);
        clickedWpisanaCalka = wpisanaCalka.getCaretPosition();
    }

    public void granicaGornaPressed() {
        pressedField = String.valueOf(KilknietePole.GRANICA_GORNA);
        clickedWpisanaGranicaGorna = granicaGorna.getCaretPosition();
    }

    public void liczbaPodprzedzialowPressed() {
        pressedField = String.valueOf(KilknietePole.LICZBA_PODPRZEDZIALOW);
        clickedLiczbaPrzedzialow = liczbaPodprzedzialow.getCaretPosition();
    }

    public void choiceBoxJednostka() {
        String a = "Jednostka";
        String b = "Radiany";
        String c = "Stopnie";
        jednostkaChoiceBox.getItems().addAll(b, c);
        jednostkaChoiceBox.setValue(a);
    }

    public void choiceBoxMetodaCalkowania() {
        String a = "Metoda całkowania";
        String b = "Metoda analityczna";
        String c = "Metoda prostokątów z nadmiarem";
        String d = "Metoda prostokątów z niedomiarem";
        String e = "Metoda trapezów";
        String f = "Metoda Simpsona";
        String g = "Całka nieoznaczona";
        metodaChoiceBox.getItems().addAll(b, c, d, e, f, g);
        metodaChoiceBox.setValue(a);
    }

    public void menuBoczne() {
        menuWykres();
        zamknij.setOnMouseClicked(event -> System.exit(0));

        slider.setTranslateX(222);
        menuZamknij.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(222);
            slide.play();
            slider.setTranslateX(0);
            slide.setOnFinished((ActionEvent e) -> {
                menu.setVisible(true);
                menuZamknij.setVisible(false);
            });
        });

        menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();
            slider.setTranslateX(222);
            slide.setOnFinished((ActionEvent e) -> {
                menu.setVisible(false);
                menuZamknij.setVisible(true);
            });
        });

        vBoxHistoria.setTranslateX(222);
        historiaButton.setOnMouseClicked(event -> {
            vBoxHistoria.setVisible(true);
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.4), vBoxHistoria);
            transition.setToX(0);
            transition.play();
        });

        wroc.setOnMouseClicked(event -> {
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.4), vBoxHistoria);
            transition.setToX(222);
            transition.play();
            transition.setOnFinished((ActionEvent e) -> vBoxHistoria.setVisible(false));
        });
    }

    public void kalkulatorNaukowyOnAction() {
        Stage stage = (Stage) kalkulatorNaukowyButton.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/kalkulatorNaukowy.fxml")));
            Stage menuStage = new Stage();
            menuStage.setTitle("Kalkulator naukowy");
            menuStage.initStyle(StageStyle.UNDECORATED);
            root.setOnMousePressed(event -> {
                x = menuStage.getX() - event.getSceneX();
                y = menuStage.getY() - event.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                menuStage.setX(event.getSceneX() + x);
                menuStage.setY(event.getSceneY() + y);
            });
            menuStage.setScene(new Scene(root, 1035, 653));
            menuStage.show();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public void pomocOnAction() {
        rodzaj = "Kalkulator całek";
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/pomoc.fxml")));
            Stage menuStage = new Stage();
            menuStage.setTitle("Pomoc");
            menuStage.initStyle(StageStyle.UNDECORATED);

            root.setOnMousePressed(event -> {
                x = menuStage.getX() - event.getSceneX();
                y = menuStage.getY() - event.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                menuStage.setX(event.getSceneX() + x);
                menuStage.setY(event.getSceneY() + y);
            });

            menuStage.setScene(new Scene(root, 600, 600));
            menuStage.setX(1200);
            menuStage.setY(300);
            menuStage.show();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

}