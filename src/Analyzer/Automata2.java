package Analyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Automata2 {

    static int validateString(String str, int initial) {

        int state = initial, index = 0;
        char symbol;
        String token = "";

        String[] words = str.split(" ");
        
        for (int i = 0; i < words.length; i++) {
            
            System.out.println("WORDS["+i+"] = " + words[i]);
        }

        for (String pal: words) {
            System.out.println("WORD: " + pal);
            if (Filter.isKeyword(pal.trim())) {
                state = 0;
                System.out.println("PALL: " + pal.length());
                index += pal.length();
            } else {
                while(index < str.length() ){
                    symbol = str.charAt(index);
                    System.out.println("SYM: " + symbol);
                    System.out.println("Estado: " + state);
                    switch (state) {
                    case 0:
                        if (symbol == '0') {
                            state = 3;
                        } else if (symbol == '#') {
                            state = 18;
                        } else if (symbol == ' ') {
                            state = 0;
                        } else if (symbol == ';') {
                            state = 22;
                        } else if (symbol == '"' || symbol == '\'') {
                            state = 27;
                        } else if (Filter.isNumber(symbol, 10)) {
                            state = 1;
                        } else if (symbol == '+' || symbol == '-') {
                            state = 2;
                        } else if (symbol == '_') {
                            state = 10;
                        } else if (Character.isLetter(symbol) || symbol == '$') {
                            state = 11;
                        } else if (symbol == '/') {
                            state = 12;
                        } else {
                            state = 17; // Dead state
                        }
                        break;
                    case 1:
                        if (symbol == '.') {
                            state = 4;
                        } else if (symbol == ' ') {
                            state = 0;
                        } else if (!Filter.isNumber(symbol, 10)) {
                            state = 17;
                        }
                        break;
                    case 2:
                        if (symbol == '0') {
                            state = 3;
                        } else if (Filter.isNumber(symbol, 10)) {
                            state = 1;
                        } else {
                            state = 17;
                        }
                        break;
                    case 3:
                        if (symbol == 'x') {
                            state = 9;
                        } else if (symbol == '.') {
                            state = 4;
                        } else if (symbol == ' ') {
                            state = 0;
                        } else if (Filter.isNumber(symbol, 8)) {
                            state = 8;
                        } else {
                            state = 17;
                        }
                        break;
                    case 4:
                        if (symbol == 'E') {
                            state = 5;
                        } else if (symbol == ' ') {
                            state = 0;
                        } else if (!Filter.isNumber(symbol, 10)) {
                            state = 17;
                        }
                        break;
                    case 5:
                        if (symbol == '+' || symbol == '-') {
                            state = 6;
                        } else if (Filter.isNumber(symbol, 10)) {
                            state = 7;
                        } else {
                            state = 17;
                        }
                        break;
                    case 6:
                    case 7:
                        if (symbol == ' ') {
                            state = 0;
                        } else {
                            state = !Filter.isNumber(symbol, 10) ? 17 : 7;
                        }
                        break;
                    case 8:
                        if (symbol == ' ') {
                            state = 0;
                        } else {
                            state = !Filter.isNumber(symbol, 8) ? 17 : 8;
                        }
                        break;
                    case 9:
                        if (symbol == ' ') {
                            state = 0;
                        } else {
                            state = !Filter.isNumber(symbol, 16) ? 17 : 9;
                        }
                        break;
                    case 10:
                    case 11:
                        if (symbol == ' ') {
                            state = 0;
                        } else {
                            System.out.println(symbol);
                            state = (!Character.isLetterOrDigit(symbol) && symbol != '_') ? 17 : 11;
                        }
                        break;
                    case 12:
                        if (symbol == '/') {
                            state = 13;
                        } else if (symbol == '*') {
                            state = 14;
                        }
                        break;
                    case 13:
                    case 17:
                        break;
                    case 14:
                        state = symbol == '*' ? 15 : 14;
                        break;
                    case 15:
                        state = symbol == '/' ? 16 : 15;
                        break;
                    case 16:
                        if (symbol == ' ') {
                            state = 0;
                        } else {
                            state = !Character.isWhitespace(symbol) ? 0 : 16;
                        }
                        break;
                    case 18:
                        System.out.println("Str: " + str + " index: " + index);

                        if (str.contains("include")) {
                            index += 6;
                            token += "nclude";
                            state = 19;
                        } else if (str.contains("define")) {
                            index += 5;
                            token += "efine";
                            state = 20;
                        }
                        break;
                    case 19:
                        if (symbol == '<') {
                            state = 21;
                        } else if (symbol == ' ') {
                            state = 19;
                        } else {
                            state = 17;
                        }
                        break;
                    case 20:
                        System.out.println("/Str: " + str + " index: " + index);
                        //validar identificador define
                        state = (symbol == ' ') ? 23 : 17;
                        break;
                    case 21:
                        if (Character.isAlphabetic(symbol)) {
                            state = 21;
                        } else if (symbol == '>') {
                            state = 22;
                            //Analyzer.tokens.add(token);
                            token = "";
                        }
                        break;
                    case 22:
                        System.out.println("ACEP");
                        break;

                    case 23:
                        if (symbol == ' ') {
                            state = 24;
                        } else {
                            state = (Character.isLetterOrDigit(symbol) || symbol == '_') ? 23 : 17;
                        }

                        break;
                    case 24:
                        if (symbol == '"') {
                            state = 25;
                        } else if (Character.isDigit(symbol)) {
                            state = 26;
                        }
                        break;
                    case 25:
                        if (symbol == '"') {
                            state = 22;
                            //Analyzer.tokens.add(token);
                        } else {
                            state = (Character.isLetterOrDigit(symbol) || symbol == '_') ? 25 : 17;
                        }

                        break;
                    case 26:
                        state = (Character.isDigit(symbol)) ? 26 : 17;
                        break;
                    case 27:
                        state = (symbol == '"' || symbol == '\'') ? 22 : 27;
                        break;

                    }
                    index++;
                }
                
            }
        }

        if (state == 26) {
            //Analyzer.tokens.add(token);
        }
        return state;
    }

    static boolean isAcceptingState(int state) {
        List < Integer > acceptingStates = Arrays.asList(1, 3, 4, 7, 8, 9, 11, 13, 16, 22, 26);
        for (Integer aState: acceptingStates) {
            if (state == aState) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //Tests

    }
}