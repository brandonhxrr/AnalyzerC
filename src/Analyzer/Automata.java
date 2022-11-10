package Analyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Automata {

    //Función para validar una línea, retorna el estado del automáta
    static int validateString(String str, int initial, int line) {
        
        //Quitar saltos de línea de la cadena
        str = str.stripIndent().replace("\n", "");

        int state = initial, index = 0;
        char symbol;
        String token = "";
        
        //Lista para capturar el error en la línea si lo hay
        List<String> error = new ArrayList<>();
        
        //Lista que contiene los símbolos que separan a un token
        List<Character> symbols = Arrays.asList('(', ')', '{', '}', '[', ']', ',', ';', '"');
        
        //Validar si la linea es una librería valida o un comentario de una sola linea e ir a un estado de aceptación
        if(Filter.isLibrary(str) || Filter.isSingleLineComment(str)){
            state = 22;
        }else {
            //Recorrer la linea caracter por caracter
            while(index < str.length() ){
                
                //Asignar el simbolo al simbolo actual
                symbol = str.charAt(index);
                
                //Agregar el simbolo al token
                token += symbol;
               
                //Quitarespacios vacios del token
                token = token.trim();
                
                //Agregar los tokens separandolos con los simbolos o espacio
                if((symbol == ' ' || symbols.contains(symbol)) && (state != 5 && state != 6 && state != 17) ){
                    System.out.println("TOKEN: " + token);
                    Filter.addToken(token);
                }
                
                //Switch para validar en que estado se encuentra el automata
                switch(state) {
                    
                    //Estado inicial
                    case 0:
                        //Separar el token por espacio o algun simbolo
                        if(symbol == ' ' || symbols.contains(symbol)){
                            
                            //Validaciones para decidir a que estado ir
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
                                
                                //En caso de haber algun error, agregarlo a la lista de errores
                                error.add("Linea " + String.valueOf(line));
                                error.add("Símbolo no válido");
                                error.add(Character.toString(symbol));
                            }
                            
                            //Resetear el token
                            token = "";
                        }
                    break;
                    
                    //Estado 1: Validar lo que llega después de una palabra reservada
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
                            }else{
                                
                            }
                            
                            token = "";
                            
                        }
                    break;
                    
                    //Estado 2: Validar lo que llega después de un identificador
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
                            }else if(symbol == ' '){
                                state = 2;
                            }else if("(".equals(token)){
                                state = 3;
                            }else if(Filter.isID(token.trim()) || Filter.isID(token.substring(0, token.length() - 1)) ){
                                state = 17;
                                
                                error.add("Linea " + String.valueOf(line));
                                error.add("Identificador no válido");
                                error.add("");
                            }else{
                                state = 17;
                                
                                error.add("Linea " + String.valueOf(line));
                                error.add("Símbolo no válido");
                                error.add(token);
                            }
                            
                            token = "";
                        }
                    break;
                
                    //Estado 3: Validar lo que llega después de un = en una expresión
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
                            
                            token = "";
                        }
                    break;
                    
                    //Validar lo que le llega después de una expresión de tipo int a = 10
                    // puede llegar un ;, un parentesis, una coma o algún simbolo
                    case 4:
                        if(symbol == ' ' || symbols.contains(symbol)){
                           
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
                            
                            token = "";
                        }
                    break;
                    
                    //Estado 5: Validar una cadena con comillas dobles
                    case 5:
                        state = (symbol == '"') ? 4 : 5;
                    break;
                    
                    //Estado 6: Validar una cadena con comillas simples
                    case 6:
                        state = (symbol == '\'') ? 4 : 6;
                    break;
                    
                    //Estado 7: Validar si despues de llaves le lleva un identificador o un punto y coma
                    case 7:
                        if(symbol == ' ' || symbols.contains(symbol)){
                            
                            if(Filter.isID(token)){
                                state = 2;
                            }else if(symbol == ';'){
                                state = 8;
                            }
                            
                            token = "";
                        }
                    break;  
                    case 8:
                    break;
                }
                index++;
            }
        }
        
        //Agregar los errores que hayan surgido a la lista de errores
        Analyzer.errors.add(error);
        return state;
    }

    //Validar que el estado recibido sea un estado de aceptación
    //  con el fin de validar que líneas son aceptadas y cuales no
    static boolean isAcceptingState(int state) {
        List < Integer > acceptingStates = Arrays.asList(0, 1, 7, 8, 22);
        for (Integer aState: acceptingStates) {
            if (state == aState) {
                return true;
            }
        }
        return false;
    }

}