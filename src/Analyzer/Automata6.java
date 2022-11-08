package Analyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Automata6 {

    static int validateString(String str, int initial, int line) {
        
        str = str.stripIndent().replace("\n", "");

        int state = initial, index = 0;
        char symbol;
        String token = "";
        
        List<String> error = new ArrayList<>();
        
        List<Character> symbols = Arrays.asList('(', ')', '{', '}', '[', ']', ',', ';', '"');

        String[] words = str.split(" ");
        
        if(Filter.isLibrary(str) || Filter.isSingleLineComment(str)){
            state = 22;
        }else {
            while(index < str.length() ){
                symbol = str.charAt(index);
                
                token += symbol;
                
                token = token.trim();
                
                if((symbol == ' ' || symbols.contains(symbol)) && (state != 5 && state != 6 && state != 17) ){
                    System.out.println("TOKEN: " + token);
                    Filter.addToken(token);
                }
                
                switch(state) {
                    case 0:
                        if(symbol == ' ' || symbols.contains(symbol)){
                            //System.out.println("TOKEN0: " + token);
                            if(token.isEmpty()){
                                state = 0;
                            }else if(symbol == '"'){
                                state = 5;
                            }else if(symbol == ','){
                                state = 0;
                            }else if(Filter.isKeyword(token) || Filter.isKeyword(token.substring(0, token.length() - 1)) ){
                                state = 1;
                            }else if(Filter.isID(token) || Filter.isID(token.substring(0, token.length() - 1)) ){
                                state = (symbol ==';') ? 22 : 2;
                            }else if(symbols.contains(symbol)){
                                state = 0;
                            }else {
                                state = 17;
                                
                                error.add("Linea " + String.valueOf(line));
                                error.add("Símbolo no válido");
                                error.add(Character.toString(symbol));
                            }
                            //System.out.println("token0: " + token + " state: " + state + " symbol: " + symbol);
                            token = "";
                        }
                    break;
                    
                    case 1:
                        if(symbol == ' ' || symbols.contains(symbol)){
                            //System.out.println("TOKEN1: " + token);
                            if(symbol == '(' || symbol == ',' || symbol == ')'){
                                state = 0;
                            }else if(symbol == '[' || symbol == ']'){
                                state = 7;
                            }else if(Filter.isKeyword(token.trim())){
                                state = 1;
                            }else if(symbol == ';') {
                                state = 22;
                            }else if(Filter.isID(token.trim()) || Filter.isID(token.substring(0, token.length() - 1)) ){
                                state = 2;
                            }
                            //System.out.println("token1: " + token + " state: " + state + " symbol: " + symbol);
                            token = "";
                            
                        }
                    break;
                        
                    case 2:
                        
                        if(symbol == ' ' || symbols.contains(symbol)){
                            //System.out.println("TOKEN2: " + token);
                            if("=".equals(token)){
                                state = 3;
                            }else if(symbol == ';'){
                                state = 22;
                            }else if("\"".equals(token)){
                                state = 5;
                            }else if(Filter.isSymbol(token) && symbol != '('){
                                state = 1;
                            }else if(Filter.isSymbol(Character.toString(symbol)) && symbol != '('){
                                state = 3;
                            }else if(Filter.isID(token.trim()) || Filter.isID(token.substring(0, token.length() - 1)) ){
                                state = 17;
                                
                                error.add("Linea " + String.valueOf(line));
                                error.add("Identificador no válido");
                                error.add("");
                            }else if(symbol == ' '){
                                state = 2;
                            }else if("(".equals(token)){
                                state = 3;
                            }else{
                                state = 17;
                                
                                error.add("Linea " + String.valueOf(line));
                                error.add("Símbolo no válido");
                                error.add(token);
                            }
                            //System.out.println("token2: " + token + " state: " + state + " symbol: " + symbol);
                            token = "";
                        }
                    break;
                    
                    case 3:
                        if(symbol == ' ' || symbols.contains(symbol)){
                            //System.out.println("TOKEN3: " + token);
                            if(Filter.isID(token.trim()) || Filter.isNumber(token.trim())){
                                state = 4;
                            }else if(symbol == ')' || symbol == '('){
                                state = 0;
                            }else if(symbol == '[' || symbol == ']'){
                                state = 3 ;
                            }else if(symbol == ';'){
                                state = 0;
                            }else if(symbol == '"'){
                                state = 5;
                            }else if(symbol == '\''){
                                state = 6;
                            }else if(symbol == ','){
                                state = 0;
                            }else {
                                state = 17;
                                
                                error.add("Linea " + String.valueOf(line));
                                error.add("Símbolo no válido");
                                error.add(Character.toString(symbol));
                            }
                            //System.out.println("token3: " + token + " state: " + state + " symbol: " + symbol);
                            token = "";
                        }
                    break;
                    
                    case 4:
                        if(symbol == ' ' || symbols.contains(symbol)){
                            //System.out.println("TOKEN4: " + token);
                            if(Filter.isSymbol(token.trim())){
                                state = 3;
                            }else if(symbol == ';'){
                                state = 22;
                            }else if(symbol == ')'){
                                state = 0;
                            }else if(symbol == ','){
                                state = 0;
                            }else {
                                state = 17;
                                
                                error.add("Linea " + String.valueOf(line));
                                error.add("Símbolo no válido");
                                error.add(Character.toString(symbol));
                            }
                            //System.out.println("token4: " + token + " state: " + state + " symbol: " + symbol);
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
                            //System.out.println("TOKEN7: " + token);
                            if(Filter.isID(token)){
                                state = 2;
                            }else if(symbol == ';'){
                                state = 8;
                            }
                            //System.out.println("token7: " + token + " state: " + state + " symbol: " + symbol);
                            token = "";
                        }
                    break;  
                    case 8:
                        //System.out.println("TOKEN8: " + token);
                    break;
                }
                index++;
            }
        }
        
        Analyzer.errors.add(error);
        return state;
    }

    static boolean isAcceptingState(int state) {
        List < Integer > acceptingStates = Arrays.asList(0, 1, 7, 8, 22);
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