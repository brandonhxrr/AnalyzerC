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

    static boolean isSymbol(String str){
        List<String> symbols = Arrays.asList("(", ")", "{", "}", "[", "]", "=", "+", "-",
                "/", "*", "&", "|", "!", ">", "<", "++", "--", "*=", "+=", "-=", "/=", ">=", "<=",
                "=*", "=+", "=-", "=/", ",", "%", "<<", ">>");
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
                "while","do", "int", "struct", "_Packed","double", "printf", "scanf");
for (String kw: keywords) {
            if(str.equals(kw)){
                return true;
            }
        }
        return false;
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
