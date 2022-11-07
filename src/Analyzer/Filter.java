package Analyzer;

import java.util.Arrays;
import java.util.List;

public class Filter {
    
    //Corregir y agregar a la lista de tokens
    static String unresolvedWords(String str, char ini){
        List<Character> symbols = Arrays.asList('(', ')', '{', '}', '[', ']', ',', ';');
        System.out.println("STR: " + str);
        for(Character c: str.toCharArray()) {
            System.out.println("CHAR: " + c);
            if((ini != '#' ) && (symbols.contains(c) || isSymbol(Character.toString(c)))){
                Analyzer.tokens.add(Character.toString(c));
                str =  str.replace(c, ' ');
            }
        }
        return str;
    }
    
    static boolean isNumber(String str){
        for(Character c : str.toCharArray()){
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }

    static boolean isSymbol(String str){
        List<String> symbols = Arrays.asList("(", ")", "{", "}", "[", "]", "=", "+", "-",
                "/", "*", "&", "|", "!", ">", "<", "++", "--", "*=", "+=", "-=", "/=", ">=", "<=",
                "=*", "=+", "=-", "=/", ",", "%", "<<", ">>", "&&", "||", "==", "!=",
                "<=", ">=");
        for(String symbol: symbols) {
            if(str.equals(symbol)){
                return true;
            }
        }
        return false;
    }
    static boolean isKeyword(String str){
        List<String> keywords = Arrays.asList("auto","else", "long", "switch","break", 
                "enum", "register", "typedef","case", "exter", "union",
                "char", "float", "short", "unsigned","const", "for", "signed", "void",
                "continue", "goto", "sizeof", "volatile", "default", "if", "static",
                "while","do", "int", "struct", "_Packed","double", "return");
for (String kw: keywords) {
            if(str.equals(kw)){
                return true;
            }
        }
        return false;
    }
    
    static boolean isLibrary(String str) {
        int index = 0, state = 0;
        while(index < str.length() ){
            char symbol = str.charAt(index);
            switch(state) {
                case 0:
                    state = (symbol == '#') ? 1 : 17;
                break;
                case 1:
                    if("include".equals(str.substring(1, 8))){
                        state = 2;
                        index += 6;
                    }else if("define".equals(str.substring(1, 7))){
                        state = 6;
                        index += 5;
                    }
                break;
                case 2:
                    if(symbol == '<'){
                        state = 3;
                    }else if(symbol == ' ') {
                        state = 2;
                    }else {
                        state = 17;
                    }
                break;
                case 3:
                    if(Character.isAlphabetic(symbol)){
                        state = 3;
                    }else if(symbol == '.'){
                        state = 4;
                    }
                break;
                case 4:
                    if(Character.isAlphabetic(symbol)){
                        state = 4;
                    }else if(symbol == '>'){
                        state = 5;
                    }
                break;
                case 5:
                break;
                case 6:
                    if(symbol == ' ') {
                        state = 6;
                    }else if(Character.isAlphabetic(symbol) || symbol == '_') {
                        state = 7;
                    }
                break;
                case 7:
                    if(Character.isLetterOrDigit(symbol) || symbol == '_') {
                        state = 7;
                    }else if(symbol == ' '){
                        state = 8;
                    }
                break;
                case 8:
                    if(Character.isDigit(symbol)){
                        state = 9;
                    }else if(symbol == '"') {
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
        
        //System.out.println("LIB: " + state);
        return (state == 5 || state == 9 || state == 11);
    }
    
    static boolean isSingleLineComment(String str) {
        int index = 0, state = 0;
        while(index < str.length() ){
            char symbol = str.charAt(index);
            switch(state) {
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
            
            //System.out.println("SLC: " + state);
        }
        return state == 2;
    }
    
    static boolean isID(String str) {
        int index = 0, state = 0;
        while(index < str.length() ){
           char symbol = str.charAt(index);
           switch(state) {
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

    static String convert(int num, int base){
        int x = num % base;
        String digits = "0123456789ABCDEF";
        int rest = (int) num / base;
        if(rest == 0){
            return String.valueOf(digits.charAt(x));
        }
        return convert(rest, base) + digits.charAt(x);
    }

    static boolean isNumber(char n, int limit){
        for (int i = 0; i < limit; i++) {
            if(convert(i, limit).equals(String.valueOf(n))){
                return true;
            }
        }
        return false;
    }

    public static String filter(String line){
        StringBuilder newLine = new StringBuilder();
        String[] words = line.split(" ");
        for (String pal: words) {
            String word = (line.length() > 0) ? unresolvedWords(pal, line.charAt(0)) : unresolvedWords(pal, ' ');
            System.out.println("WORD11: " + word);
            if(!word.isBlank() && !isSymbol(word) && !isKeyword(word)){
                //newLine.append(" ").append(unresolvedWords(word));
                newLine.append(" ").append(word);
            }
        }System.out.println("WORD: " + newLine);
        return String.valueOf(newLine).trim();
    }
}
