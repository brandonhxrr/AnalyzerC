package Analyzer;

import java.util.Arrays;
import java.util.List;

public class Filter {
    static String unresolvedWords(String str){
        List<Character> symbols = Arrays.asList('(', ')', '{', '}', '[', ']', '\'', '\"', ',', ';');
        for(Character c: symbols) {
            if(str.charAt(0) == c){
                return str.substring(1);
            }else if(str.charAt(str.length() -1) == c){
                return str.substring(0, str.length() - 1 );
            }
        }
        if(str.charAt(0) == '\'' || str.charAt(0) == '\"'){
            return "";
        }

        return str;
    }

    static boolean isSymbol(String str){
        List<String> symbols = Arrays.asList("(", ")", "{", "}", "[", "]", "\"", "\"", "=", "+", "-",
                "/", "*", "&", "|", "!", ">", "<", "++", "--", "*=", "+=", "-=", "/=", ">=", "<=",
                "=*", "=+", "=-", "=/", ",");
        for(String symbol: symbols) {
            if(str.equals(symbol)){
                return true;
            }
        }
        return false;
    }
    static boolean isKeyword(String str){
        List<String> keywords = Arrays.asList("String", "abstract", "continue", "for", "new", "switch" +
                        "assert", "default", "goto", "package", "synchronized",
                "boolean", "do", "if", "private", "this",
                "break", "double", "implements", "protected", "throw" ,
                "byte", "else", "import", "public", "throws" ,
                "case", "enum", "instanceof", "return", "transient" ,
                "catch", "extends", "int", "short", "try" +
                        "char", "final", "interface", "static", "void" ,
                "class", "finally", "long", "strictfp", "volatile" ,
                "const", "float", "native", "super", "while", "System.out.println", "main(String[]", "(float)");
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
        for (String word: words) {
            if(!word.isBlank() && !isSymbol(word) && !isKeyword(word)){
                newLine.append(" ").append(unresolvedWords(word));
            }
        }
        return String.valueOf(newLine).trim();
    }
}
