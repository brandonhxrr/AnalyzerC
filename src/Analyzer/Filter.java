package Analyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Filter {

    //Validar que una cadena esté compuesta solo por números
    static boolean isNumber(String str) {
        for (Character c: str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    //Validar que una cadena sea uno de los simbolos de la lista
    static boolean isSymbol(String str) {
        List < String > symbols = Arrays.asList("(", ")", "{", "}", "[", "]", "=", "+", "-",
            "/", "*", "&", "|", "!", ">", "<", "++", "--", "*=", "+=", "-=", "/=", ">=", "<=",
            "=*", "=+", "=-", "=/", ",", "%", "<<", ">>", "&&", "||", "==", "!=",
            "<=", ">=");
        for (String symbol: symbols) {
            if (str.equals(symbol)) {
                return true;
            }
        }
        return false;
    }

    //Validar que una cadena sea alguna de las palabras reservadas de la lista
    static boolean isKeyword(String str) {
        List < String > keywords = Arrays.asList("auto", "else", "long", "switch", "break",
            "enum", "register", "typedef", "case", "exter", "union",
            "char", "float", "short", "unsigned", "const", "for", "signed", "void",
            "continue", "goto", "sizeof", "volatile", "default", "if", "static",
            "while", "do", "int", "struct", "_Packed", "double", "return");
        for (String kw: keywords) {
            if (str.equals(kw)) {
                return true;
            }
        }
        return false;
    }

    //Automata para validar si una linea es una definición de libreria o define
    static boolean isLibrary(String str) {
        int index = 0, state = 0;
        while (index < str.length()) {
            char symbol = str.charAt(index);
            switch (state) {
            case 0:
                state = (symbol == '#') ? 1 : 17;
                break;
            case 1:
                if ("include".equals(str.substring(1, 8))) {
                    state = 2;
                    index += 6;
                } else if ("define".equals(str.substring(1, 7))) {
                    state = 6;
                    index += 5;
                }
                break;
            case 2:
                if (symbol == '<') {
                    state = 3;
                } else if (symbol == ' ') {
                    state = 2;
                } else {
                    state = 17;
                }
                break;
            case 3:
                if (Character.isAlphabetic(symbol)) {
                    state = 3;
                } else if (symbol == '.') {
                    state = 4;
                }
                break;
            case 4:
                if (Character.isAlphabetic(symbol)) {
                    state = 4;
                } else if (symbol == '>') {
                    state = 5;
                }
                break;
            case 5:
                break;
            case 6:
                if (symbol == ' ') {
                    state = 6;
                } else if (Character.isAlphabetic(symbol) || symbol == '_') {
                    state = 7;
                }
                break;
            case 7:
                if (Character.isLetterOrDigit(symbol) || symbol == '_') {
                    state = 7;
                } else if (symbol == ' ') {
                    state = 8;
                }
                break;
            case 8:
                if (Character.isDigit(symbol)) {
                    state = 9;
                } else if (symbol == '"') {
                    state = 10;
                }
                break;
            case 9:
                state = (Character.isDigit(symbol)) ? 9 : 17;
                break;
            case 10:
                state = (symbol == '"') ? 11 : 10;
                break;
            case 11:
                break;
            }

            index++;
        }

        return (state == 5 || state == 9 || state == 11);
    }
    
    //Automata para validar si una linea es un comentario de una sola línea
    static boolean isSingleLineComment(String str) {
        int index = 0, state = 0;
        while (index < str.length()) {
            char symbol = str.charAt(index);
            switch (state) {
            case 0:
                state = (symbol == '/') ? 1 : 17;
                break;
            case 1:
                state = (symbol == '/') ? 2 : 17;
                break;
            case 2:
                break;
            }

            index++;
        }
        return state == 2;
    }

    //Automata para validar si la cadena es un identificador valido
    static boolean isID(String str) {
        int index = 0, state = 0;
        while (index < str.length()) {
            char symbol = str.charAt(index);
            switch (state) {
            case 0:
                state = (Character.isLetter(symbol) || symbol == '_' || symbol == '*') ? 1 : 17;
                break;
            case 1:
                state = (Character.isLetterOrDigit(symbol) || symbol == '_') ? 1 : 17;
                break;
            }

            index++;
        }
        return state == 1;
    }

    //Función para agregar un token a la lista de token junto con su descripción según el tipo de token
    public static void addToken(String token) {
        List < String > tok = new ArrayList < > ();

        if (token.equals(" ") || token.isEmpty()) {

        } else if (isLibrary(token)) {
            tok.add("Librería");//Componente léxico
            tok.add(token); //Lexema
            tok.add(token); //Valor
        } else if (isSingleLineComment(token)) {
            tok.add("Comentario");
            tok.add(token);
            tok.add(token);
        } else if (isKeyword(token)) {
            tok.add("Palabra reservada");
            tok.add(token);
            tok.add(token);

        } else if (isID(token)) {
            tok.add("Identificador");
            tok.add(token);
            tok.add(token);
        } else if (isSymbol(token)) {
            tok.add("Símbolo");
            tok.add(token);
            tok.add(token);
        } else if (isNumber(token)) {
            tok.add("Número entero");
            tok.add(token);
            tok.add(token);
        } else if (isID(token.substring(0, token.length() - 1))) {
            tok.add("Identificador");
            tok.add(token.substring(0, token.length() - 1));
            tok.add(token.substring(0, token.length() - 1));

            addSymbol(Character.toString(token.charAt(token.length() - 1)));
        } else if (isNumber(token.substring(0, token.length() - 1))) {
            tok.add("Número entero");
            tok.add(token.substring(0, token.length() - 1));
            tok.add(token.substring(0, token.length() - 1));

            addSymbol(Character.toString(token.charAt(token.length() - 1)));
        } else if (token.charAt(token.length() - 2) == '"') {
            tok.add("Cadena");
            tok.add("\"" + token.substring(0, token.length() - 1));
            tok.add("\"" + token.substring(0, token.length() - 1));

            addSymbol(Character.toString(token.charAt(token.length() - 1)));
        } else if (isSymbol(Character.toString(token.charAt(token.length() - 1)))) {
            tok.add("Expresión");
            tok.add(token.substring(0, token.length() - 1));
            tok.add(token.substring(0, token.length() - 1));

            addSymbol(Character.toString(token.charAt(token.length() - 1)));
        } else if ((token.charAt(token.length() - 2) == '"') && (token.charAt(token.length() - 3) == ';')) {
            tok.add("Cadena");
            tok.add("\"" + token.substring(0, token.length() - 1));
            tok.add("\"" + token.substring(0, token.length() - 1));

            addSymbol(Character.toString(token.charAt(token.length() - 1)));

            addSymbol(";");
        } else {
            tok.add("Expresión");
            tok.add(token);
            tok.add(token);
        }
        Analyzer.tokens.add(tok);

    }

    //Función para añadir un simbolo a la lista de tokens
    public static void addSymbol(String token) {
        List < String > tok = new ArrayList < > ();
        if (isSymbol(token)) {
            tok.add("Símbolo");
            tok.add(token);
            tok.add(token);
        }
        Analyzer.tokens.add(tok);
    }
}