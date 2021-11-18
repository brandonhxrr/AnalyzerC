package Analyzer;

import java.util.Arrays;
import java.util.List;

public class Automata {

    static int validateString(String str, int initial){

        int state = initial, index = 0;
        char symbol;

        while(index != (str.length() )){

            symbol = str.charAt(index);

            switch (state){
                case 0:
                    if(symbol == '0'){
                        state = 3;
                    }else if (Filter.isNumber(symbol, 10)){
                        state = 1;
                    }else if ( symbol == '+' || symbol == '-'){
                        state = 2;
                    }else if(symbol == '_'){
                        state = 10;
                    }else if(Character.isLetter(symbol) || symbol == '$'){
                        state = 11;
                    }else if(symbol == '/'){
                        state = 12;
                    }else{
                        state = 17; // Dead state
                    }
                break;
                case 1:
                    if(symbol == '.'){
                        state = 4;
                    }else if(symbol == ' '){
                        state = 0;
                    }else if (!Filter.isNumber(symbol, 10)){
                        state = 17;
                    }
                break;
                case 2:
                    if(symbol == '0'){
                        state = 3;
                    }else if (Filter.isNumber(symbol, 10)){
                        state = 1;
                    }else{
                        state = 17;
                    }
                break;
                case 3:
                    if(symbol == 'x'){
                        state = 9;
                    }else if (symbol == '.'){
                        state = 4;
                    }else if(symbol == ' '){
                        state = 0;
                    }else if (Filter.isNumber(symbol, 8)){
                        state = 8;
                    }else{
                        state = 17;
                    }
                break;
                case 4:
                    if(symbol == 'E'){
                        state = 5;
                    }else if(symbol == ' '){
                        state = 0;
                    }else if (!Filter.isNumber(symbol, 10)){
                        state = 17;
                    }
                break;
                case 5:
                    if ( symbol == '+' || symbol == '-'){
                        state = 6;
                    }else if(Filter.isNumber(symbol, 10)){
                        state = 7;
                    }else{
                        state = 17;
                    }
                break;
                case 6:
                case 7:
                    if(symbol == ' '){
                        state = 0;
                    }else{
                        state = !Filter.isNumber(symbol, 10) ? 17 : 7 ;
                    }
                break;
                case 8:
                    if(symbol == ' '){
                        state = 0;
                    }else{
                        state = !Filter.isNumber(symbol, 8) ? 17 : 8 ;
                    }
                break;
                case 9:
                    if(symbol == ' '){
                        state = 0;
                    }else{
                        state = !Filter.isNumber(symbol, 16) ? 17 : 9 ;
                    }
                break;
                case 10:
                case 11:
                    if(symbol == ' '){
                        state = 0;
                    }else{
                        state = (!Character.isLetterOrDigit(symbol) && symbol != '_' && symbol != '$') ? 17 : 11;
                    }
                break;
                case 12:
                    if(symbol == '/'){
                        state = 13;
                    }else if(symbol == '*'){
                        state = 14;
                    }
                    break;
                case 13:
                case 17:
                    break;
                case 14:
                    state = symbol == '*' ? 15 : 14 ;
                break;
                case 15:
                    state = symbol == '/' ? 16 : 15 ;
                break;
                case 16:
                    if(symbol == ' '){
                        state = 0;
                    }else{
                        state = !Character.isWhitespace(symbol) ? 0 : 16 ;
                    }
                break;
            }

            index++;
        }
        return state;
    }

    static boolean isAcceptingState(int state){
        List<Integer> acceptingStates = Arrays.asList(1, 4, 7, 8, 9, 11, 13, 16);
        for(Integer aState: acceptingStates) {
            if(state == aState){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        //Tests

    }
}
