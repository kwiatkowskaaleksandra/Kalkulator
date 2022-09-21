package com.example.kalkulator;/*
 * @project Kalkulator
 * @author kola
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.python.core.PyException;
import org.python.util.PythonInterpreter;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class kalkulatorCalka implements Initializable {

    @FXML
    private ChoiceBox<String> metodaCalkowania;
    @FXML
    private TextField liczbaPodprzedzialow;
    @FXML
    private TextField granicaDolna;
    @FXML
    private Label granicaDolnaWzor;
    @FXML
    private Label granicaGornaWzor;
    @FXML
    private TextField granicaGorna;
    @FXML
    private TextField wartoscCalki;
    @FXML
    private TextField wynikCalka;
    @FXML
    private TextField wpisanaCalka;

    public void ChoiceBoxMetodaCalk(){
        ObservableList rodzajMetodyCalkowania = FXCollections.observableArrayList();
        rodzajMetodyCalkowania.removeAll(rodzajMetodyCalkowania);
        String a = "Metoda całkowania";
        String b = "Metoda prostokątów z niedomiarem (c. numeryczne)";
        String c = "Metoda prostokątów z nadmiarem (c. numeryczne)";
        String d = "Metoda trapezów (c. numeryczne)";
        String e = "Metoda Simpsona (c.numeryczne)";
//JESZCZE METODA ANALITYCZNA
        rodzajMetodyCalkowania.addAll(b,c,d,e);
        metodaCalkowania.getItems().addAll(rodzajMetodyCalkowania);
        metodaCalkowania.setValue(a);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChoiceBoxMetodaCalk();
        granicaDolna.setText("1");
        granicaGorna.setText("5");
        liczbaPodprzedzialow.setText("4");
    }

    public void wynikOnAction(ActionEvent actionEvent) throws PyException {
        granicaGorna.setText(String.valueOf(Float.valueOf(granicaGorna.getText())));
        granicaDolna.setText(String.valueOf(Float.valueOf(granicaDolna.getText())));
        przeksztalcenieRownania();
       // zmianaRownania();
        switch (metodaCalkowania.getValue()) {
            case "Metoda prostokątów z niedomiarem (c. numeryczne)" -> {
                metodaProstokatowZNiedomiarem();
            }
            case "Metoda prostokątów z nadmiarem (c. numeryczne)" -> {
                metodaProstokatowZNadmiarem();
            }
            case "Metoda trapezów (c. numeryczne)" -> {
                metodaTrapezow();
            }
            case "Metoda Simpsona (c.numeryczne)" -> {
                metodaSimpsona();
            }
            default -> {
////KOMUNIKAT
            }
        }
    }

    public String przeksztalcenieRownania(){
        PythonInterpreter interpreter=new PythonInterpreter();
        // wartoscCalki.setText(wpisanaCalka.getText());
         granicaDolnaWzor.setText(granicaDolna.getText());
         granicaGornaWzor.setText(granicaGorna.getText());
         String calka=wpisanaCalka.getText();
         Pattern kropka = Pattern.compile(".");

         String licznik="", licznikNowy="";
         int b=0;


         for(int i=0;i<calka.length();i++){
             if(calka.charAt(i)=='/'){
                 for(int j=i-1;j>=0; j--){
                     if(calka.charAt(j)=='+' || calka.charAt(j)=='π' || calka.charAt(j)=='-' || calka.charAt(j)=='*' || calka.charAt(j)=='/' || calka.charAt(j)=='x' || calka.charAt(j)=='(' || calka.charAt(j)==')'  || calka.charAt(j)=='[' || calka.charAt(j)==']'){
                         break;
                     }else {
                         licznik+=calka.charAt(j);
                     }
                 }

                 Matcher kropkaUlamka=kropka.matcher(licznik);

                 if(kropkaUlamka.find()){
                     if(!kropkaUlamka.find()){
                         StringBuilder str = new StringBuilder(calka);
                         str.insert(i,".");
                         calka= String.valueOf(str);
                     }
                 }

                 licznik="";
             }

             if(calka.charAt(i)=='^'){
                 calka= calka.replace("^", "**");
             }

         }

         for(int i=0; i<calka.length();i++){

             if(calka.charAt(i)=='s' && calka.charAt(i+1)=='q' && calka.charAt(i+2)=='r' && calka.charAt(i+3)=='t'){
                String stopienPierwiastka="", liczbaPierwiastkowana="";
                int k=0;

                for(int j=i+5;j<calka.length();j++){
                    if(calka.charAt(j)==','){
                        k=j;
                        break;
                    }else stopienPierwiastka+=calka.charAt(j);
                }
                for(int j=k+1;j<calka.length();j++){
                    if(calka.charAt(j)==')'){
                        break;
                    }else liczbaPierwiastkowana+=calka.charAt(j);
                }
                calka=calka.replace("sqrt("+stopienPierwiastka+","+liczbaPierwiastkowana+")",liczbaPierwiastkowana+"**(1./("+stopienPierwiastka+"))");
            }
            else if(calka.charAt(i)=='π'){
                calka=calka.replace("π","pi");
            }
            else if(calka.charAt(i)=='!'){
                String liczbaSilni="";

                //Pętla która odczutuje co jest przed !
                for(int j=i-1; j>=0;j--){

                    //Pętla warunkowa jeśli aktualny znak jest równy +,-,/,* to pętla for zostaje przerwana w przeciwnym wypadku znak jest zapisany do liczbaSilni
                    if(!String.valueOf(calka.charAt(j)).equals("+") && !String.valueOf(calka.charAt(j)).equals("-") && !String.valueOf(calka.charAt(j)).equals("/") && !String.valueOf(calka.charAt(j)).equals("*")  ){
                        liczbaSilni+=calka.charAt(j);
                    }else break;
                }

                //Odwócenie liczbySilni, bo w pętli wyżej zapisuje się od tyłu i dlatego trzeba ją odwrócić
                StringBuilder liczbaSilniOdwrocona = new StringBuilder();
                liczbaSilniOdwrocona.append(liczbaSilni);
                liczbaSilniOdwrocona.reverse();

                //Wywołanie funkcji do liczenia silni
                Long wynikSilni = silnia(Integer.parseInt(String.valueOf(liczbaSilniOdwrocona)));
                calka=calka.replace(liczbaSilniOdwrocona+"!",String.valueOf(wynikSilni));
            }
            else if(calka.charAt(i)=='e'){
                calka=calka.replace("e",String.valueOf(Math.E));
            }
            else if(calka.charAt(i)=='l' && calka.charAt(i+1)=='o' && calka.charAt(i+2)=='g' && calka.charAt(i+3)=='['){

                String podstawaLogarytm="", liczbaLogarytm="";
                int k=0;

                for(int j=i+4; j<calka.length();j++){
                    if(calka.charAt(j)==']'){
                       k=j;
                       break;
                    }else{
                        podstawaLogarytm+=calka.charAt(j);
                    }
                }

                for(int j=k+2;j<calka.length();j++){
                    if(calka.charAt(j)==']'){
                        break;
                    }else{
                        liczbaLogarytm+=calka.charAt(j);
                    }
                }

                calka=calka.replace("log["+podstawaLogarytm+"]["+liczbaLogarytm+"]","math.log10("+liczbaLogarytm+")/math.log10("+podstawaLogarytm+")");

               // interpreter.exec("import math \nq=float(math.log10("+liczbaLogarytm+")/math.log10("+podstawaLogarytm+"))");
              //  System.out.println("calosc "+interpreter.get("q"));


            }
            else if(calka.charAt(i)=='l' && calka.charAt(i+1)=='n'){
               ///SPRAWDZA JAKI WYSZEDL LOGARYTM
                /*String liczbaLogarytmowana="";
                 for(int j=0; j<calka.length();j++){
                     if(calka.charAt(j+1)=='x' && j+1<calka.length()){
                         break;
                     }else{
                         liczbaLogarytmowana+=calka.charAt(j);
                     }
                 }
                 for(int j=0;j<liczbaLogarytmowana.length();j++){
                     if (liczbaLogarytmowana.charAt(j)=='^') {
                         liczbaLogarytmowana=liczbaLogarytmowana.replace("^","**");

                     }
                 }
                 System.out.println(liczbaLogarytmowana);
                 liczbaLogarytmowana=liczbaLogarytmowana.replace("ln", "math.log");
                 interpreter.exec("import math \nd=float("+liczbaLogarytmowana+")");
                 System.out.println("log="+interpreter.get("d"));*/

                calka=calka.replace("ln","math.log");
            }

        }

        Pattern cosPatter=Pattern.compile("cos");
        Matcher cosMatcher = cosPatter.matcher(calka);



         wartoscCalki.setText(calka);

         return calka;
    }

    public static Long silnia(int i){
        if(i<1) return 1L;
        else return i*silnia(i-1);
    }

    public void zmianaRownania(){
        String wzorCalki=wpisanaCalka.getText();


        for(int i=0; i<wzorCalki.length();i++){
            if(wzorCalki.charAt(i)=='^'){
                String wykladnik="", wykladnikKodowany="";
                System.out.println(wzorCalki.charAt(i+1));
                //Zapisanie wykladnika
                for (int j = i + 1; j < wzorCalki.length(); j++) {
                    if (!String.valueOf(wzorCalki.charAt(j)).equals("+") && !String.valueOf(wzorCalki.charAt(j)).equals("-") && !String.valueOf(wzorCalki.charAt(j)).equals("/") && !String.valueOf(wzorCalki.charAt(j)).equals("*")) {
                        if (String.valueOf(wzorCalki.charAt(j - 1)).equals("-")) {
                            wykladnik += "-";
                        } else if (String.valueOf(wzorCalki.charAt(j)).equals("/")) {
                            wykladnik += "/";
                        } else wykladnik += wzorCalki.charAt(j);
                    } else break;
                }
                //Kodowanie wykladnika
                for (int j = 0; j < wykladnik.length(); j++) {
                    wykladnikKodowany += kodowanieIndeksGorny(String.valueOf(wykladnik.charAt(j)));
                }

                wzorCalki=wzorCalki.replace("^"+wykladnik, wykladnikKodowany);

            }
            else if(wzorCalki.charAt(i)=='√'){

            }

        }



        wartoscCalki.setText(wzorCalki);
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
    public char kodowanieIndeksGorny(String wykladnik){
        char unicode = 0;
        switch (wykladnik) {
            case "1" -> {
                unicode = '\u00B9';
            }
            case "2" -> {
                unicode = '\u00B2';
            }
            case "3" -> {
                unicode = '\u00B3';
            }
            case "4" -> {
                unicode = '\u2074';
            }
            case "5" -> {
                unicode = '\u2075';
            }
            case "6" -> {
                unicode = '\u2076';
            }
            case "7" -> {
                unicode = '\u2077';
            }
            case "8" -> {
                unicode = '\u2078';
            }
            case "9" -> {
                unicode = '\u2079';
            }
            case "0" -> {
                unicode = '\u2070';
            }
            case "-" -> {
                unicode = '\u207B';
            }
            case "/" -> {
                unicode = '\u2E0D';
            }
            case "." -> {
                unicode = '\u02D9';
            }
        }
        return unicode;
    }

    public void metodaProstokatowZNiedomiarem(){
        try(PythonInterpreter interpreter = new PythonInterpreter()){
            interpreter.exec("""
                    import math
                    def f(x):
                    \treturn\040"""+przeksztalcenieRownania()+
                    """
                    \ndef prostokatNiedomiar(a, b, n):
                    \tsuma=0.0
                    \th=(b-a)/n
                    \twynik=0.0
                                   	
                    \tfor i in range(0,n):
                    \t\tx=(a+(i*h))
                    \t\tsuma+=f(x)
                                   			
                    \twynik=h*suma
                    \treturn wynik
                    c=float(prostokatNiedomiar(""" + granicaDolna.getText() + """
                    ,""" + granicaGorna.getText() + """
                    ,""" + liczbaPodprzedzialow.getText() + """
                    ))
                    """);
            interpreter.exec("print(c)");

            wynikCalka.setText(String.valueOf(interpreter.get("c")));
        }
    }

    public void metodaProstokatowZNadmiarem(){
        try(PythonInterpreter interpreter = new PythonInterpreter()){
            interpreter.exec("""
                    import math
                    def f(x):
                    \treturn\040"""+wartoscCalki.getText()+
                    """
                    \ndef prostokatNadmiar(a, b, n):
                    \tsuma=0.0
                    \th=(b-a)/n
                    \twynik=0.0
                                   	
                    \tfor i in range(1,n+1):
                    \t\tx=(a+(i*h))
                    \t\tsuma+=f(x)
                                   			
                    \twynik=h*suma
                    \treturn wynik
                    c=float(prostokatNadmiar(""" + granicaDolna.getText() + """
                    ,""" + granicaGorna.getText() + """
                    ,""" + liczbaPodprzedzialow.getText() + """
                    ))
                    """);
            interpreter.exec("print(c)");

            wynikCalka.setText(String.valueOf(interpreter.get("c")));
        }
    }

    public void metodaTrapezow(){
        try(PythonInterpreter interpreter = new PythonInterpreter()){
            interpreter.exec("""
                    import math
                    def f(x):
                    \treturn\040"""+wartoscCalki.getText()+
                    """
                    \ndef trapez(a,b,n):
                    \tx1=0.0
                    \tx2=0.0
                    \th=(b-a)/n
                    \twynik=0.0
                    \tfor i in range(0,n+1):
                    \t\tx=(a+(i*h))
                    \t\tif i==0 or i==n:
                    \t\t\tx2+=f(x)
                    \t\telse:
                    \t\t\tx1+=f(x)
                    \twynik=h*((x2/2)+x1)
                    \treturn wynik
                    c=float(trapez(""" + granicaDolna.getText() + """
                    ,""" + granicaGorna.getText() + """
                    ,""" + liczbaPodprzedzialow.getText() + """
                    ))"""
            );
            interpreter.exec("print(c)");

            wynikCalka.setText(String.valueOf(interpreter.get("c")));
        }
    }

    public void metodaSimpsona() {
        try (PythonInterpreter interpreter = new PythonInterpreter()) {

/// a - dolna granica calkowania, b- gorna granica calkowania , n - liczba podprzedziałów
            interpreter.exec("""
                    
                    import sympy
                    def f(x):
                    \treturn\040""" + wartoscCalki.getText() +
                    """
                    \ndef simpson(a, b, n):
                    \tx1=0.0
                    \tx2=0.0
                    \tx0=f(a)+f(b)
                    \th=(b-a)/n
                    \twynik=0.0
                                         	
                    \tfor i in range(1,n):
                    \t\tx=(a+(i*h))
                    \t\tif i%2==0:
                    \t\t\tx2+=f(x)
                    \t\telse:
                    \t\t\tx1+=f(x)
                    \twynik=h*(x0+2*x2+4*x1)/3
                    \treturn wynik
                    c=float(simpson(""" + granicaDolna.getText() + """
                    ,""" + granicaGorna.getText() + """
                    ,""" + liczbaPodprzedzialow.getText() + """
                    ))"""
            );
            interpreter.exec("print(c)");

            wynikCalka.setText(String.valueOf(interpreter.get("c")));

         //   interpreter.exec("d=float(16**(1./(1./4)))");
             //       interpreter.exec("print(d)");
        }


    }

    public void piOnAction(ActionEvent event){
        wpisanaCalka.setText(wpisanaCalka.getText()+"π");
    }



}
