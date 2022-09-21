package com.example.kalkulator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.python.core.PyException;
import org.python.indexer.ast.NSubscript;
import org.python.util.PythonInterpreter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class kalkulatorNaukowy {

    @FXML
    private TextArea textArea;
    @FXML
    private TextField doObliczenia;
    @FXML
    private TextField wynikK;
    @FXML
    private TextField ukryteDoObliczenia;
@FXML
private Pane pp;

float wynik;


    @FXML
    protected void calkanieozn() throws FileNotFoundException {

        Image image = new Image(new FileInputStream("C:\\Users\\48732\\Desktop\\Kalkulator\\src\\main\\resources\\image\\calka.png"));
        //symbol=new ImageView(new Image());
       ImageView symbol= new ImageView(image);
        symbol.setImage(image);
        symbol.setFitWidth(40);
        symbol.setFitHeight(70);
        symbol.setX(100);
        symbol.setY(100);
    pp.getChildren().add(symbol);

    }

    public void wynikOnAction(ActionEvent actionEvent) throws PyException {
        ukryteDoObliczenia.setText(doObliczenia.getText());
        try(PythonInterpreter pyInterp = new PythonInterpreter()) {

            //Pętla która przechodzi znak po znaku we wpisanym równaniu
            for(int i=0;i<ukryteDoObliczenia.getText().length();i++){

                //Warunek sprawdzający czy aktualnie przeglądany znak jest zgodny ze znakiem specjalnym
                if(ukryteDoObliczenia.getText().charAt(i)=='!'){
                    String liczbaSilni="";

                    //Pętla która odczutuje co jest przed !
                    for(int j=i-1; j>=0;j--){

                        //Pętla warunkowa jeśli aktualny znak jest równy +,-,/,* to pętla for zostaje przerwana w przeciwnym wypadku znak jest zapisany do liczbaSilni
                        if(!String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals("+") && !String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals("-") && !String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals("/") && !String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals("*")  ){
                            liczbaSilni+=ukryteDoObliczenia.getText().charAt(j);
                        }else break;
                    }

                    //Odwócenie liczbySilni, bo w pętli wyżej zapisuje się od tyłu i dlatego trzeba ją odwrócić
                    StringBuilder liczbaSilniOdwrocona = new StringBuilder();
                    liczbaSilniOdwrocona.append(liczbaSilni);
                    liczbaSilniOdwrocona.reverse();

                    //Wywołanie funkcji do liczenia silni
                    Long wynikSilni = silnia(Integer.parseInt(String.valueOf(liczbaSilniOdwrocona)));
                    ukryteDoObliczenia.setText(ukryteDoObliczenia.getText().replace(liczbaSilniOdwrocona+"!",String.valueOf(wynikSilni)));
                }

                else if (ukryteDoObliczenia.getText().charAt(i)=='e') {
                    ukryteDoObliczenia.setText(ukryteDoObliczenia.getText().replace(String.valueOf(ukryteDoObliczenia.getText().charAt(i)),String.valueOf(Math.E)));
                }

                else if (ukryteDoObliczenia.getText().charAt(i)=='π') {
                    ukryteDoObliczenia.setText(ukryteDoObliczenia.getText().replace(String.valueOf(ukryteDoObliczenia.getText().charAt(i)),String.valueOf(Math.PI)));
                }

                else if (ukryteDoObliczenia.getText().charAt(i)=='\u00B9' || ukryteDoObliczenia.getText().charAt(i)=='\u00B2' || ukryteDoObliczenia.getText().charAt(i)=='\u00B3' || ukryteDoObliczenia.getText().charAt(i)=='\u2074' || ukryteDoObliczenia.getText().charAt(i)=='\u2075' ||
                 ukryteDoObliczenia.getText().charAt(i)=='\u2076' || ukryteDoObliczenia.getText().charAt(i)=='\u2077' || ukryteDoObliczenia.getText().charAt(i)=='\u2078' || ukryteDoObliczenia.getText().charAt(i)=='\u2079' || ukryteDoObliczenia.getText().charAt(i)=='\u2070'|| ukryteDoObliczenia.getText().charAt(i)=='\u207B') {

                    String szukajPierwiastka="";
                    for(int j=i; j<i+5 && j<ukryteDoObliczenia.getText().length();j++){
                        szukajPierwiastka+=ukryteDoObliczenia.getText().charAt(j);
                    }
                    Pattern patternP = Pattern.compile("√");
                    Matcher matcherP = patternP.matcher(szukajPierwiastka);

                    if(matcherP.find()){
                        String liczbaPierwiastek="", stopienPierwiastkaOdkodowany="", stopienPierwiastka="";
                        int znakPierwiastka=0;

                        //Pętla która zapisuje i odkoduje stopien pierwiastka
                        for(int j=i;j<ukryteDoObliczenia.getText().length();j++){
                            if(ukryteDoObliczenia.getText().charAt(j)=='√'){
                                znakPierwiastka=j;
                                break;
                            }else{
                                stopienPierwiastka+=ukryteDoObliczenia.getText().charAt(j);
                                stopienPierwiastkaOdkodowany+=odkodowanieIndeksGorny(ukryteDoObliczenia.getText().charAt(j));
                            }
                        }

                        for(int j=znakPierwiastka+1;j<ukryteDoObliczenia.getText().length();j++){
                            if(ukryteDoObliczenia.getText().charAt(j)=='+' || ukryteDoObliczenia.getText().charAt(j)=='-' || ukryteDoObliczenia.getText().charAt(j)=='*' || ukryteDoObliczenia.getText().charAt(j)=='/' ){
                                break;
                            }else {
                                liczbaPierwiastek += ukryteDoObliczenia.getText().charAt(j);
                            }
                        }

                        double pierwiastek = Double.parseDouble(liczbaPierwiastek);
                        pierwiastek=Math.pow(pierwiastek,1.0/Double.parseDouble(stopienPierwiastkaOdkodowany));
                        ukryteDoObliczenia.setText(ukryteDoObliczenia.getText().replace(stopienPierwiastka+"√"+liczbaPierwiastek,String.valueOf(pierwiastek)));

                    }else {
                        String liczbaPotegi = "", wykladnik = String.valueOf(ukryteDoObliczenia.getText().charAt(i));
                        String wykladnikOdkodowany = "";
                        boolean nawias = false;
                        int k = 0;
                        double potega = 0.0;
                        String licznikS = "", mianownikS = "";

                        //Pętla która odczutuje co jest przed wykładnikiem
                        for (int j = i - 1; j >= 0; j--) {

                            //Pętla warunkowa jeśli aktualny znak jest równy +,-,/,* to pętla for zostaje przerwana w przeciwnym wypadku znak jest zapisany do liczbaPotegi
                            if (!String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals("+") && !String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals("-") && !String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals("/") && !String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals("*")) {
                                if (String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals(")")) {
                                    nawias = true;
                                } else liczbaPotegi += ukryteDoObliczenia.getText().charAt(j);
                            } else if (String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals("-")) {
                                if (j - 1 >= 0 && String.valueOf(ukryteDoObliczenia.getText().charAt(j - 1)).equals("(")) {
                                    nawias = true;
                                    liczbaPotegi += "-";
                                }
                                break;
                            } else break;
                        }

                        Pattern patternLog = Pattern.compile("l");
                        Matcher matcherLog = patternLog.matcher(liczbaPotegi);
                        if (matcherLog.find()) {
                            break;
                        } else {

                            //Odwrócenie liczby potęgi
                            StringBuilder liczbaPotegiOdwrocona = new StringBuilder();
                            liczbaPotegiOdwrocona.append(liczbaPotegi);
                            liczbaPotegiOdwrocona.reverse();

                            //Zapisanie wykladnika
                            for (int j = i + 1; j < ukryteDoObliczenia.getText().length(); j++) {
                                if (!String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals("+") && !String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals("-") && !String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals("/") && !String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals("*")) {
                                    if (String.valueOf(ukryteDoObliczenia.getText().charAt(j - 1)).equals("-")) {
                                        wykladnik += "-";
                                    } else if (String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals("/")) {
                                        wykladnik += "/";
                                    } else wykladnik += ukryteDoObliczenia.getText().charAt(j);
                                } else break;
                            }

                            //Odkodowanie wykladnika
                            for (int j = 0; j < wykladnik.length(); j++) {
                                if (odkodowanieIndeksGorny(wykladnik.charAt(j)).equals("/")) {
                                    wykladnikOdkodowany += "/";
                                } else wykladnikOdkodowany += odkodowanieIndeksGorny(wykladnik.charAt(j));
                            }

                            Pattern pattern = Pattern.compile("/");
                            Matcher matcher = pattern.matcher(wykladnikOdkodowany);

                            if (matcher.find()) {
                                //Oddzielenie licznika od mianownika w wykładniku
                                for (int j = 0; j < wykladnikOdkodowany.length(); j++) {
                                    if (wykladnikOdkodowany.charAt(j) == '/') {
                                        k = j;
                                        break;
                                    } else {
                                        licznikS += (wykladnikOdkodowany.charAt(j));
                                    }
                                }

                                for (int j = k + 1; j < wykladnikOdkodowany.length(); j++) {
                                    mianownikS += wykladnikOdkodowany.charAt(j);
                                }

                                double licznik = Double.parseDouble(licznikS), mianownik = Double.parseDouble(mianownikS);
                                //Policzenie potęgi
                                potega = Double.parseDouble(String.valueOf(liczbaPotegiOdwrocona));
                                potega = Math.pow(potega, licznik / mianownik);
                            } else {
                                potega = Double.parseDouble(String.valueOf(liczbaPotegiOdwrocona));
                                potega = Math.pow(potega, Double.parseDouble(wykladnikOdkodowany));
                            }


                            if (nawias) {
                                ukryteDoObliczenia.setText(ukryteDoObliczenia.getText().replace("(" + liczbaPotegiOdwrocona + ")" + wykladnik, String.valueOf(potega)));
                            } else
                                ukryteDoObliczenia.setText(ukryteDoObliczenia.getText().replace(liczbaPotegiOdwrocona + wykladnik, String.valueOf(potega)));
                        }
                    }

                }

                else if(ukryteDoObliczenia.getText().charAt(i)=='l' && ukryteDoObliczenia.getText().charAt(i+1)=='n'){
                    String liczbaLogarytmowana="", licznikLog="", mianownikLog="", potega="", potegaKodowana="", licznikPot="", mianownikPot="";
                    double wynikLogarytmu=0.0;
                    int k=0;
                    boolean czyPotega=false;

                    for(int j=i+3;j<ukryteDoObliczenia.getText().length();j++){
                        if(ukryteDoObliczenia.getText().charAt(j)==')'){
                            break;
                        }else{
                            liczbaLogarytmowana+=ukryteDoObliczenia.getText().charAt(j);
                        }
                    }

                    //Sprawdzenie czy logarytm jest podniesiony do potęgi
                    for(int j=i+1;j<ukryteDoObliczenia.getText().length();j++){
                        if (ukryteDoObliczenia.getText().charAt(j)=='\u00B9' || ukryteDoObliczenia.getText().charAt(j)=='\u00B2' || ukryteDoObliczenia.getText().charAt(j)=='\u00B3' || ukryteDoObliczenia.getText().charAt(j)=='\u2074' || ukryteDoObliczenia.getText().charAt(j)=='\u2075' || ukryteDoObliczenia.getText().charAt(j)=='\u2E0D' ||
                                ukryteDoObliczenia.getText().charAt(j)=='\u2076' || ukryteDoObliczenia.getText().charAt(j)=='\u2077' || ukryteDoObliczenia.getText().charAt(j)=='\u2078' || ukryteDoObliczenia.getText().charAt(j)=='\u2079' || ukryteDoObliczenia.getText().charAt(j)=='\u2070'|| ukryteDoObliczenia.getText().charAt(j)=='\u207B' || ukryteDoObliczenia.getText().charAt(j)=='\u02D9') {
                            czyPotega=true;
                            potega+=odkodowanieIndeksGorny(ukryteDoObliczenia.getText().charAt(j));
                            potegaKodowana+=ukryteDoObliczenia.getText().charAt(j);
                        }
                    }


                    Pattern pattern = Pattern.compile("/");
                    Matcher matcher = pattern.matcher(liczbaLogarytmowana);
                    Matcher matcherPot = pattern.matcher(potega);

                    if(matcher.find()){
                        for(int j=0; j<liczbaLogarytmowana.length();j++){
                            if(liczbaLogarytmowana.charAt(j)=='/'){
                                k=j;
                                break;
                            }else{
                                licznikLog+=liczbaLogarytmowana.charAt(j);
                            }
                        }

                        for(int j=k+1;j<liczbaLogarytmowana.length();j++){
                            mianownikLog+= liczbaLogarytmowana.charAt(j);
                        }

                        wynikLogarytmu=Math.log(Double.parseDouble(licznikLog)/Double.parseDouble(mianownikLog));

                    }else{
                        wynikLogarytmu=Math.log(Double.parseDouble(liczbaLogarytmowana));
                    }

                    if(czyPotega){
                        if(matcherPot.find()){
                            for(int j=0; j<potega.length();j++){
                                if(potega.charAt(j)=='/'){
                                    k=j;
                                    break;
                                }else {
                                    licznikPot+=potega.charAt(j);
                                }
                            }

                            for(int j=k+1;j<potega.length();j++){
                                mianownikPot+=potega.charAt(j);
                            }
                            wynikLogarytmu=wynikLogarytmu*(Double.parseDouble(licznikPot)/Double.parseDouble(mianownikPot));
                        }else{
                            wynikLogarytmu=wynikLogarytmu*Double.parseDouble(potega);
                        }

                        ukryteDoObliczenia.setText(ukryteDoObliczenia.getText().replace("ln("+liczbaLogarytmowana+")"+potegaKodowana, String.valueOf(wynikLogarytmu)));
                    }else ukryteDoObliczenia.setText(ukryteDoObliczenia.getText().replace("ln("+liczbaLogarytmowana+")", String.valueOf(wynikLogarytmu)));
                }

                else if(ukryteDoObliczenia.getText().charAt(i)=='l' && ukryteDoObliczenia.getText().charAt(i+1)=='o'){

                    String podstawa="", podstawaOdkodowana="", liczbaLogarytmowana="", licznikLog="", mianownikLog="", licznikPodstawy="",mianownikPodstawy="", potega="", potegaKodowana="", licznikPot="",mianownikPot="";
                    int k=0,m=0;
                    double wynikLog;
                    boolean czyPotega=false;

                    //Zapisanie i odkodwanie podstawy logarytmu
                    for(int j=i+3;j<ukryteDoObliczenia.getText().length();j++){
                        if(ukryteDoObliczenia.getText().charAt(j)=='('){
                            k=j;
                            break;
                        }else{
                            podstawa+=ukryteDoObliczenia.getText().charAt(j);
                            podstawaOdkodowana+=odkodowanieIndeksDolny(ukryteDoObliczenia.getText().charAt(j));
                        }
                    }

                    //Zapisanie liczby logarytmowanej
                    for(int j=k;j<ukryteDoObliczenia.getText().length();j++){
                        if(ukryteDoObliczenia.getText().charAt(j)==')'){
                            break;
                        }else{
                            if(ukryteDoObliczenia.getText().charAt(j)!='('){
                                liczbaLogarytmowana+=ukryteDoObliczenia.getText().charAt(j);
                            }
                        }
                    }

                    //Sprawdzenie czy logarytm jest podniesiony do potęgi
                    for(int j=i+1;j<ukryteDoObliczenia.getText().length();j++){
                        if (ukryteDoObliczenia.getText().charAt(j)=='\u00B9' || ukryteDoObliczenia.getText().charAt(j)=='\u00B2' || ukryteDoObliczenia.getText().charAt(j)=='\u00B3' || ukryteDoObliczenia.getText().charAt(j)=='\u2074' || ukryteDoObliczenia.getText().charAt(j)=='\u2075' || ukryteDoObliczenia.getText().charAt(j)=='\u2E0D' ||
                                ukryteDoObliczenia.getText().charAt(j)=='\u2076' || ukryteDoObliczenia.getText().charAt(j)=='\u2077' || ukryteDoObliczenia.getText().charAt(j)=='\u2078' || ukryteDoObliczenia.getText().charAt(j)=='\u2079' || ukryteDoObliczenia.getText().charAt(j)=='\u2070'|| ukryteDoObliczenia.getText().charAt(j)=='\u207B' || ukryteDoObliczenia.getText().charAt(j)=='\u02D9') {
                            czyPotega=true;
                            potega+=odkodowanieIndeksGorny(ukryteDoObliczenia.getText().charAt(j));
                            potegaKodowana+=ukryteDoObliczenia.getText().charAt(j);
                        }
                    }

                    Pattern pattern = Pattern.compile("/");
                    Matcher matcherLiczbaLog = pattern.matcher(liczbaLogarytmowana);
                    Matcher matcherPodstawa = pattern.matcher(podstawaOdkodowana);
                    Matcher matcherPotegaLog = pattern.matcher(potega);

                    if (matcherLiczbaLog.find()) {
                        //Oddzielenie licznika od mianownika w liczbie logarytmowanej
                        for (int j = 0; j < liczbaLogarytmowana.length(); j++) {
                            if (liczbaLogarytmowana.charAt(j) == '/') {
                                k = j;
                                break;
                            } else {
                                licznikLog += (liczbaLogarytmowana.charAt(j));
                            }
                        }

                        for (int j = k + 1; j < liczbaLogarytmowana.length(); j++) {
                            mianownikLog += liczbaLogarytmowana.charAt(j);
                        }

                        if(matcherPodstawa.find()){
                            // Oddzielenie licznika od mianownika w podstawie logarytmu
                            for(int j=0; j<podstawaOdkodowana.length();j++){
                                if(podstawaOdkodowana.charAt(j)=='/'){
                                    m=j;
                                    break;
                                }else{
                                    licznikPodstawy+=podstawaOdkodowana.charAt(j);
                                }
                            }

                            for(int j=m+1;j<podstawaOdkodowana.length();j++){
                                mianownikPodstawy+=podstawaOdkodowana.charAt(j);
                            }

                            double licznikL = Double.parseDouble(licznikLog), mianownikL = Double.parseDouble(mianownikLog), licznikP=Double.parseDouble(licznikPodstawy), mianownikP=Double.parseDouble(mianownikPodstawy);
                            //Policzenie logarytmu poprzez sprowadzenie do wspolnej podstawy rownej 10
                            wynikLog=Math.log10(licznikL/mianownikL)/Math.log10(licznikP/mianownikP);

                        }else{
                            double licznikL = Double.parseDouble(licznikLog), mianownikL = Double.parseDouble(mianownikLog);
                            //Policzenie logarytmu poprzez sprowadzenie do wspolnej podstawy rownej 10
                            wynikLog=Math.log10(licznikL/mianownikL)/Math.log10(Double.parseDouble(podstawaOdkodowana));
                        }

                    } else {
                        if(matcherPodstawa.find()){
                            // Oddzielenie licznika od mianownika w podstawie logarytmu
                            for(int j=0; j<podstawaOdkodowana.length();j++){
                                if(podstawaOdkodowana.charAt(j)=='/'){
                                    m=j;
                                    break;
                                }else{
                                    licznikPodstawy+=podstawaOdkodowana.charAt(j);
                                }
                            }

                            for(int j=m+1;j<podstawaOdkodowana.length();j++){
                                mianownikPodstawy+=podstawaOdkodowana.charAt(j);
                            }

                            double licznikP=Double.parseDouble(licznikPodstawy), mianownikP=Double.parseDouble(mianownikPodstawy);
                            //Policzenie logarytmu poprzez sprowadzenie do wspolnej podstawy rownej 10
                            wynikLog=Math.log10(Double.parseDouble(liczbaLogarytmowana))/Math.log10(licznikP/mianownikP);
                        }else{
                           // Policzenie logarytmu poprzez sprowadzenie do wspolnej podstawy rownej 10
                            wynikLog=Math.log10(Double.parseDouble(liczbaLogarytmowana))/Math.log10(Double.parseDouble(podstawaOdkodowana));
                        }
                    }

                    if(czyPotega){
                        if(matcherPotegaLog.find()){
                            for(int j=0; j<potega.length();j++){
                                if(potega.charAt(j)=='/'){
                                    k=j;
                                    break;
                                }else {
                                    licznikPot+=potega.charAt(j);
                                }
                            }

                            for(int j=k+1;j<potega.length();j++){
                                mianownikPot+=potega.charAt(j);
                            }
                            wynikLog=wynikLog*(Double.parseDouble(licznikPot)/Double.parseDouble(mianownikPot));
                        }else{
                            wynikLog=wynikLog*Double.parseDouble(potega);
                        }
                        ukryteDoObliczenia.setText(ukryteDoObliczenia.getText().replace("log"+podstawa+"("+liczbaLogarytmowana+")"+potegaKodowana,String.valueOf(wynikLog)));

                    } else{
                        ukryteDoObliczenia.setText(ukryteDoObliczenia.getText().replace("log"+podstawa+"("+liczbaLogarytmowana+")",String.valueOf(wynikLog)));
                    }


                }

                else if(ukryteDoObliczenia.getText().charAt(i)=='%'){
                    String liczbaPierwiastek="";
                    float procent=0;

                    //Pętla która odczytuje co jest przed %
                    for(int j=i-1;j>=0;j--){
                        //Pętla warunkowa jeśli aktualny znak jest równy +,-,/,* to pętla for zostaje przerwana w przeciwnym wypadku znak jest zapisany do liczbaPierwiastek
                        if(!String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals("+") && !String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals("-") && !String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals("/") && !String.valueOf(ukryteDoObliczenia.getText().charAt(j)).equals("*")  ){
                            liczbaPierwiastek+=ukryteDoObliczenia.getText().charAt(j);
                        }else break;
                    }

                    //Odwrócenie liczbaPierwiastek, bo w pętli zapisuje się od tyłu
                    StringBuilder liczbaPierwiastekOdwrocona=new StringBuilder();
                    liczbaPierwiastekOdwrocona.append(liczbaPierwiastek);
                    liczbaPierwiastekOdwrocona.reverse();

                    //Obliczenie wartosci procentu
                    procent=Float.parseFloat(String.valueOf(liczbaPierwiastekOdwrocona));
                    procent/=100;

                    ukryteDoObliczenia.setText(ukryteDoObliczenia.getText().replace(liczbaPierwiastekOdwrocona+"%", String.valueOf(procent)));
                }

                else if(ukryteDoObliczenia.getText().charAt(i)=='s' && ukryteDoObliczenia.getText().charAt(i+1)=='i' && ukryteDoObliczenia.getText().charAt(i+2)=='n'){
                    String wartoscKata="", licznikKat="", mianownikKat="";
                    int k=0;
                    double sin=0.0;

                    //Pętla która odczytuje wartość kąta
                    for(int j=i+4;j<ukryteDoObliczenia.getText().length();j++){
                        if(ukryteDoObliczenia.getText().charAt(j)==')'){
                            break;
                        }else {
                            wartoscKata+=ukryteDoObliczenia.getText().charAt(j);
                        }
                    }

                    Pattern patternKreska= Pattern.compile("/");
                    Pattern patternPi = Pattern.compile("π");
                    Matcher ulamek = patternKreska.matcher(wartoscKata);
                    Matcher Pi = patternPi.matcher(wartoscKata);

                    if(ulamek.find()){
                        for(int j=0; j<wartoscKata.length();j++){
                            if(wartoscKata.charAt(j)=='/'){
                                k=j;
                                break;
                            }else if(wartoscKata.charAt(j)=='π'){
                                licznikKat= String.valueOf(Math.PI);
                            }else{
                                licznikKat+=wartoscKata.charAt(j);
                            }
                        }

                        for(int j=k+1;j<wartoscKata.length();j++){
                            if(wartoscKata.charAt(j)=='π'){
                                mianownikKat= String.valueOf(Math.PI);
                            }else mianownikKat+=wartoscKata.charAt(j);
                        }

                            //Policzenie wartości sinusa
                            sin = Math.sin(Double.parseDouble(licznikKat)/Double.parseDouble(mianownikKat));

                    }else{
                        if(Pi.find()){
                            //Policzenie wartości sinusa
                            sin = 0;
                        }else {
                            //Policzenie wartości sinusa
                            sin = Math.sin(Double.parseDouble(wartoscKata));
                        }
                    }

                    ukryteDoObliczenia.setText(ukryteDoObliczenia.getText().replace("sin("+wartoscKata+")", String.valueOf(sin)));

                }

            }

            pyInterp.exec("c=float("+ukryteDoObliczenia.getText()+")");
            pyInterp.exec("print(c)");
            String p= String.valueOf(pyInterp.get("c"));
            wynikK.setText(String.valueOf(p));
        }
    }

    public static Long silnia(int i){
        if(i<1) return 1L;
        else return i*silnia(i-1);
    }

    public void kwadratOnAction(ActionEvent actionEvent) {
        if(doObliczenia.getText().isEmpty()){
            doObliczenia.setText(textArea.getText()+"²");
        }else{
            doObliczenia.setText(doObliczenia.getText()+textArea.getText()+"²");
        }
        textArea.clear();
    }

    public char kodowanieIndeksGorny(String wykladnik){
        char unicode = 0;
        switch (wykladnik){
            case "1":{
                unicode='\u00B9';
            }break;
            case "2":{
                unicode='\u00B2';
            }break;
            case "3":{
                unicode='\u00B3';
            }break;
            case "4":{
                unicode='\u2074';
            }break;
            case "5":{
                unicode='\u2075';
            }break;
            case "6":{
                unicode='\u2076';
            }break;
            case "7":{
                unicode='\u2077';
            }break;
            case "8":{
                unicode='\u2078';
            }break;
            case "9":{
                unicode='\u2079';
            }break;
            case "0":{
                unicode='\u2070';
            }break;
            case "-":{
                unicode='\u207B';
            }break;
            case "/":{
                unicode='\u2E0D';
            }break;
            case ".":{
                unicode= '\u02D9';
            }
        }
        return unicode;
    }

    public String odkodowanieIndeksGorny(char wykladnik){
        String unicode = null;
        switch (wykladnik){
            case '\u00B9':{
                unicode="1";
            }break;
            case '\u00B2':{
                unicode="2";
            }break;
            case '\u00B3':{
                unicode="3";
            }break;
            case '\u2074':{
                unicode="4";
            }break;
            case '\u2075':{
                unicode="5";
            }break;
            case '\u2076':{
                unicode="6";
            }break;
            case '\u2077':{
                unicode="7";
            }break;
            case '\u2078':{
                unicode="8";
            }break;
            case '\u2079':{
                unicode="9";
            }break;
            case '\u2070':{
                unicode="0";
            }break;
            case '\u207B':{
                unicode="-";
            }break;
            case '\u2E0D':{
                unicode="/";
            }break;
            case  '\u02D9':{
                unicode=".";
            }break;
        }
        return unicode;
    }

    public char kodowanieIndeksDolny(String wykladnik){
        char unicode = 0;
        switch (wykladnik){
            case "1":{
                unicode='\u2081';
            }break;
            case "2":{
                unicode='\u2082';
            }break;
            case "3":{
                unicode='\u2083';
            }break;
            case "4":{
                unicode='\u2084';
            }break;
            case "5":{
                unicode='\u2085';
            }break;
            case "6":{
                unicode='\u2086';
            }break;
            case "7":{
                unicode='\u2087';
            }break;
            case "8":{
                unicode='\u2088';
            }break;
            case "9":{
                unicode='\u2089';
            }break;
            case "0":{
                unicode='\u2080';
            }break;
            case "/":{
                unicode='\u2E1D';
            }break;
            case ".":{
                unicode= '\u002C';
            }
        }
        return unicode;
    }

    public String odkodowanieIndeksDolny(char wykladnik){
        String unicode = null;
        switch (wykladnik){
            case '\u2081':{
                unicode="1";
            }break;
            case '\u2082':{
                unicode="2";
            }break;
            case '\uu2083':{
                unicode="3";
            }break;
            case '\u2084':{
                unicode="4";
            }break;
            case '\u2085':{
                unicode="5";
            }break;
            case '\u2086':{
                unicode="6";
            }break;
            case '\u2087':{
                unicode="7";
            }break;
            case '\u2088':{
                unicode="8";
            }break;
            case '\u2089':{
                unicode="9";
            }break;
            case '\u2080':{
                unicode="0";
            }break;
            case '\u2E1D':{
                unicode="/";
            }break;
            case  '\u002C':{
                unicode=".";
            }break;
        }
        return unicode;
    }

    public void potegaStopniaNOnAction(ActionEvent actionEvent) {

        int daszek=0;
        String wykladnik="";
        String signUnicode = "";

        //Sprawdzenie i zapisanie do zmiennej indeksu gdzie napotka daszek
        for(int i=0; i<textArea.getText().length();i++){
            if(textArea.getText().charAt(i)=='^'){
                daszek=i;
                break;
            }
        }

        //Pętla w której zapisuje się wykładnik
        for(int i=daszek+1;i<textArea.getText().length();i++){
            wykladnik+=textArea.getText().charAt(i);
        }

        //Pętla która koduje wykładnik
        for(int i=0; i<wykladnik.length();i++) {
            signUnicode += kodowanieIndeksGorny(String.valueOf(wykladnik.charAt(i))) ;
        }
        doObliczenia.setText(doObliczenia.getText()+textArea.getText().replace(textArea.getText().charAt(daszek)+wykladnik, signUnicode));

        textArea.clear();
    }

    public void pierwiastekStopniaNOnAction(ActionEvent actionEvent) {

        int znakStopien=0;
        String stopien="", pierwiastek="", signUnicode="";

        //Sprawdzenie i zapisanie do zmienn indeksu gdzie napotka znka stopnia
        for(int i=0; i<textArea.getText().length();i++){
            if(textArea.getText().charAt(i)=='°'){
                znakStopien=i;
                break;
            }else{
                stopien+=textArea.getText().charAt(i);
            }
        }

        //Pętla w kótrej kosuje się stopień
        for(int i=0; i<stopien.length();i++) {
            signUnicode += kodowanieIndeksGorny(String.valueOf(stopien.charAt(i))) ;
        }

        //Pętla w której zapisuje się liczba pod pierwiastek
        for(int i=znakStopien+1;i<textArea.getText().length();i++){
            pierwiastek+=textArea.getText().charAt(i);
        }

        doObliczenia.setText(doObliczenia.getText()+signUnicode+"√"+pierwiastek);
        textArea.clear();
    }

    public void pierwiastekKwadratowyOnAction(ActionEvent actionEvent) {
        doObliczenia.setText(doObliczenia.getText()+"²√"+textArea.getText());
        textArea.clear();
    }

    public void logarytmNaturalnyOnAction(ActionEvent actionEvent) {
        doObliczenia.setText(doObliczenia.getText()+"ln("+textArea.getText()+")");
        textArea.clear();
    }

    public void logarytmOnAction(ActionEvent actionEvent) {
        String liczbaLog="" , podstawaKodowana="";
        int j=0;

        //Zapisanie podstawy logarytmu
        for(int i=1;i<textArea.getText().length();i++){
            if(textArea.getText().charAt(i)==')'){
                j=i;
                break;
            }else{
                podstawaKodowana+=kodowanieIndeksDolny(String.valueOf(textArea.getText().charAt(i)));
            }
        }
        //Zapisanie liczby logarytmowanej
        for(int i=j+2;i<textArea.getText().length();i++){
            if(textArea.getText().charAt(i)==')'){
                break;
            }else{
                liczbaLog+=textArea.getText().charAt(i);
            }
        }

        doObliczenia.setText(doObliczenia.getText()+"log"+podstawaKodowana+"("+liczbaLog+")");
        textArea.clear();
    }

    public void piOnAction(ActionEvent actionEvent) {
        doObliczenia.setText(doObliczenia.getText()+"π");
        textArea.clear();
    }

    public void procentaOnAction(ActionEvent actionEvent) {
        doObliczenia.setText(doObliczenia.getText()+textArea.getText()+"%");
        textArea.clear();
    }

    public void eOnAction(ActionEvent actionEvent) {
        doObliczenia.setText(doObliczenia.getText()+"e");
        textArea.clear();
    }

    public void silniaOnAction(ActionEvent actionEvent) {
        if(doObliczenia.getText().isEmpty()){
            doObliczenia.setText(textArea.getText()+"!");
        }else{
            doObliczenia.setText(doObliczenia.getText()+textArea.getText()+"!");
        }
        textArea.clear();
    }

    public void nawiasLewyOnAction(ActionEvent actionEvent) {
        doObliczenia.setText(doObliczenia.getText()+"("+textArea.getText());
        textArea.clear();
    }

    public void nawiasPrawyOnAction(ActionEvent actionEvent) {
        doObliczenia.setText(doObliczenia.getText()+")"+textArea.getText());
        textArea.clear();
    }

    public void dzieleniaOnAction(ActionEvent actionEvent) {
        doObliczenia.setPrefWidth(doObliczenia.getWidth()+10);
        if(doObliczenia.getText().isEmpty()){
            wynik=1;
            doObliczenia.setText(textArea.getText());
        }else{
            doObliczenia.setText(doObliczenia.getText()+"/"+textArea.getText());
        }
        textArea.clear();
    }

    public void mnozenieOnAction(ActionEvent actionEvent) {
        if(doObliczenia.getText().isEmpty()){
            wynik=1;
            doObliczenia.setText(textArea.getText());
        }else{
            doObliczenia.setText(doObliczenia.getText()+"*"+textArea.getText());
        }
        textArea.clear();
    }

    public void dodawanieOnAction(ActionEvent actionEvent) {
        if(doObliczenia.getText().isEmpty()){
            doObliczenia.setText(textArea.getText());
        }else{
            doObliczenia.setText(doObliczenia.getText()+"+"+textArea.getText());
        }
        textArea.clear();
    }

    public void odejmowanieOnAction(ActionEvent actionEvent) {
        if(doObliczenia.getText().isEmpty()){
            doObliczenia.setText("-"+textArea.getText());
        }else{
            doObliczenia.setText(doObliczenia.getText()+"-"+textArea.getText());
        }
        textArea.clear();
    }

    public void stopienOnAction(ActionEvent actionEvent) {
        textArea.setText(textArea.getText()+'\u00B0');
    }

    public void sinOnAction(ActionEvent actionEvent){
        doObliczenia.setText(doObliczenia.getText()+"sin("+textArea.getText()+")");
        textArea.clear();
    }
}