package com.example.kalkulator;/*
 * @project Kalkulator
 * @author kola
 */

public class Unicode {
    public char kodowanieIndeksGorny(String wykladnik) {
        char unicode = 0;
        switch (wykladnik) {
            case "1" -> unicode = '\u00B9';
            case "2" -> unicode = '\u00B2';
            case "3" -> unicode = '\u00B3';
            case "4" -> unicode = '\u2074';
            case "5" -> unicode = '\u2075';
            case "6" -> unicode = '\u2076';
            case "7" -> unicode = '\u2077';
            case "8" -> unicode = '\u2078';
            case "9" -> unicode = '\u2079';
            case "0" -> unicode = '\u2070';
            case "-" -> unicode = '\u207B';
            case "/" -> unicode = '\u002F';
            case "." -> unicode = '\u02D9';
            case "a" -> unicode = '\u1D43';
            case "c" -> unicode = '\u1D9C';
            case "e" -> unicode = '\u1D49';
            case "g" -> unicode = '\u1DA2';
            case "i" -> unicode = '\u1DA4';
            case "l" -> unicode = '\u02E1';
            case "n" -> unicode = '\u207F';
            case "t" -> unicode = '\u1D57';
            case "o" -> unicode = '\u1D52';
            // case "(" -> unicode = '\u207D';
            // case ")" -> unicode = '\u207E';
            //   case "[" -> unicode = '\u207D';
            //   case "]" -> unicode = '\u207E';
            case "s" -> unicode = '\u02E2';
            // case "x" -> unicode = '\u036F';
            // case "pi" -> unicode = '\u209C';

        }
        return unicode;
    }

    public char kodowanieIndeksDolny(String wykladnik) {
        char unicode = 0;
        switch (wykladnik) {
            case "1" -> unicode = '\u2081';
            case "2" -> unicode = '\u2082';
            case "3" -> unicode = '\u2083';
            case "4" -> unicode = '\u2084';
            case "5" -> unicode = '\u2085';
            case "6" -> unicode = '\u2086';
            case "7" -> unicode = '\u2087';
            case "8" -> unicode = '\u2088';
            case "9" -> unicode = '\u2089';
            case "0" -> unicode = '\u2080';
            case "/" -> unicode = '\u2E1D';
            case "." -> unicode = '\u002C';
            case "-" -> unicode = '\u208B';
            // case "(" -> unicode = '\u208D';
            // case ")" -> unicode = '\u208E';
            //  case "[" -> unicode = '\u208D';
            //   case "]" -> unicode = '\u208E';
            case "a" -> unicode = '\u2090';
            //  case "c" -> unicode = '\u1D9C';
            case "e" -> unicode = '\u2091';
            // case "g" -> unicode = '\u1DA2';
            //case "i" -> unicode = '\u1DA4';
            // case "pi" -> unicode = '\u209C';
            case "l" -> unicode = '\u2097';
            case "n" -> unicode = '\u2099';
            case "t" -> unicode = '\u209C';
            case "o" -> unicode = '\u2092';
            case "s" -> unicode = '\u209B';
            case "x" -> unicode = '\u2093';

        }
        return unicode;
    }
}
