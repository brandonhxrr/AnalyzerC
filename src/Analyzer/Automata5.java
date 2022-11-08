package Analyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Automata5 {

    static int validateString(String str, int initial) {

        int state = initial, index = 0;
        char symbol;
        String token = "";
        
        List<String> symbols = Arrays.asList("(", ")", "{", "}", "[", "]", ",");

        String[] words = str.split(" ");
        
        for (int i = 0; i < words.length; i++) {
            
            System.out.println("WORDS["+i+"] = " + words[i]);
        }
        
        if(Filter.isLibrary(str) || Filter.isSingleLineComment(str)){
            state = 22;
        }else {
           for (String pal: words) {
               switch(state) {
                   case 0:
                       if(Filter.isKeyword(pal)){
                           state = 1;
                       }else if(Filter.isID(pal)) {
                           state = 2;
                       }else if(symbols.contains(pal)){
                           state = 3;
                       }
                       System.out.println("PAL: " + pal + " STATE: " + state);
                   break;
                   
                   /*case 1: 
                       if(Filter.isID(pal)){
                           state = 3;
                       }
                    break;*/
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