package Analyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Automata3 {

    static int validateString(String str, int initial) {
        
        str = str.stripIndent().replace("\n", "");

        int state = initial, index = 0;
        char symbol;
        String token = "";
        
        List<Character> symbols = Arrays.asList('(', ')', '{', '}', '[', ']', ',', ';', '"', '\'');

        String[] words = str.split(" ");
        
        if(Filter.isLibrary(str) || Filter.isSingleLineComment(str)){
            state = 22;
        }else {
            while(index < str.length() ){
                symbol = str.charAt(index);
                
                token = token.trim();
                
                switch(state) {
                    case 0:
                        if(symbol == ' ' || symbols.contains(symbol)){
                            //System.out.println("TOKEN0: " + token);
                            if(token.isEmpty()){
                                state = 0;
                            }else if(Filter.isKeyword(token)){
                                state = 1;
                            }else if(Filter.isID(token)){
                                state = (symbol ==';') ? 22 : 2;
                            }else if(symbol == '{' || symbol == '}'){
                                state = 0;
                            }else if(token.equals(",")){
                                state = 1;
                            }else {
                                state = 17;
                            }
                            System.out.println("token0: " + token + " state: " + state + " symbol: " + symbol);
                            token = "";
                        }
                    break;
                    
                    case 1:
                        if(symbol == ' ' || Filter.isSymbol(Character.toString(symbol)) || symbols.contains(symbol)){
                            //System.out.println("TOKEN1: " + token);
                            if((Filter.isID(token) || Filter.isNumber(token)) && symbol == ';'){
                                state = 22;
                            }else if(Filter.isID(token) && symbol == '('){
                                state = 0;
                            }else if(Filter.isID(token) && symbol == ')'){
                                state = 8;
                            }else if(Filter.isID(token)){
                                state = 2;
                            }else if(symbol == '(' || symbol == ' ' || symbol == '*' || symbol == '_'){
                                state = 1;
                            }else {
                                state = 17;
                            }
                            System.out.println("token1: " + token + " state: " + state + " symbol: " + symbol);
                            token = "";
                        }
                    break;
                        
                    case 2:
                        if(symbol == ' ' || symbols.contains(symbol)){
                            if(token.equals("(") && symbol == ')'){
                                state = 8;
                            }else if(symbol == '"') {
                                state = 5;
                            }else if(symbol == '\'') {
                                state = 6;
                            }else if(token.equals("(") || token.equals("[") ){
                                state = 7;
                            }else if("=".equals(token)){
                                state = 3;
                            }else if(symbol == ']'){
                                state = 2;
                            }else if(symbol == ')' ){
                                state = 8;
                            }else if(symbol == ';'){
                                state = 22;
                            }else if(token.equals(",")){
                                state = 0;
                            }else{
                                state = 17;
                            }
                                System.out.println("token2: " + token + " state: " + state + " symbol: " + symbol);
                                token = "";
                        }
                    break;
                    
                    case 3:
                        
                        if(symbol == ' ' || symbols.contains(symbol)){
                            if((Filter.isID(token) || Filter.isNumber(token)) && symbol == ';'){
                                state = 22;
                            }else if(Filter.isID(token) || Filter.isNumber(token)){
                                state = 4;
                            }else if(symbol == '{'){
                                state = 3;
                            }else if(symbol == '"'){
                                state = 5;
                            }else if(symbol == '\''){
                                state = 6;
                            }else {
                                state = 17;
                            }
                            System.out.println("token3: " + token + " state: " + state + " symbol: " + symbol);
                            token = "";
                        }
                    break;
                    
                    case 4:
                        if(symbol == ' ' || symbols.contains(symbol)){
                            if(token.equals("++") || token.equals("--")){
                                state = 2;
                            }else if(Filter.isSymbol(token.trim())){
                                state = 3;
                            }else if(symbol == ';'){
                                state = 22; //revisar
                            }else if(token.equals(",") || symbol == ','){
                                state = 0;
                            }else {
                                state = 17;
                            }
                            System.out.println("token4: " + token + " state: " + state + " symbol: " + symbol);
                            token = "";
                        }
                    break;
                    case 5:
                        state = (symbol == '"') ? 4 : 5;
                    break;
                    case 6:
                        state = (symbol == '\'') ? 4 : 6;
                    break;
                    
                    case 7:
                        if(symbol == ' ' || symbols.contains(symbol)){
                            if(Filter.isID(token)){
                                state = 2;
                            }else if(symbol == ']'){
                                state = 2;
                            }else if(symbol == ')') {
                                state = 8;
                            }else if(symbol == '"') {
                                state = 5;
                            }
                            System.out.println("token7: " + token + " state: " + state + " symbol: " + symbol);
                            token = "";
                        }
                    break;
                    case 8:
                        if(symbol == ';'){
                            state = 22;
                        }
                    break;
                    case 22:
                        
                    break;
                    
                        
                }
                
                token = (symbol != '(') ? token + symbol: token ;
                //token += symbol;
                index++;
            }
        }
        
        if (state == 26) {
            //Analyzer.tokens.add(token);
        }
        return state;
    }

    static boolean isAcceptingState(int state) {
        List < Integer > acceptingStates = Arrays.asList(0, 8, 22);
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