public class Automata {

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

    static int validateString(String str){

        int state = 0, index = 0;
        char symbol = ' ';

        while(symbol != ';' && index != (str.length() )){

            symbol = str.charAt(index);

            switch (state){
                case 0:
                    if(symbol == '0'){
                        state = 3;
                    }else if (isNumber(symbol, 10)){
                        state = 1;
                    }else if ( symbol == '+' || symbol == '-'){
                        state = 2;
                    }else if(symbol == '_'){
                        state = 11;
                    }else if(Character.isLetter(symbol)){
                        state = 10;
                    }else if(symbol == '/'){
                        state = 13;
                    }else{
                        state = 18; // Dead state
                    }
                break;
                case 1:
                    if(symbol == '.'){
                        state = 4;
                    }else if (!isNumber(symbol, 10)){
                        state = 18;
                    }
                break;
                case 2:
                    if(symbol == '0'){
                        state = 3;
                    }else if (isNumber(symbol, 10)){
                        state = 1;
                    }else{
                        state = 18;
                    }
                break;
                case 3:
                    if(symbol == 'x'){
                        state = 9;
                    }else if (isNumber(symbol, 8)){
                        state = 8;
                    }else{
                        state = 18;
                    }
                break;
                case 4:
                    if(symbol == 'E'){
                        state = 5;
                    }else if (!isNumber(symbol, 10)){
                        state = 18;
                    }
                break;
                case 5:
                    if ( symbol == '+' || symbol == '-'){
                        state = 6;
                    }else if(isNumber(symbol, 10)){
                        state = 7;
                    }else{
                        state = 18;
                    }
                break;
                case 6:
                case 7:
                    if(!isNumber(symbol, 10)){
                        state = 18;
                    }
                break;
            }

            index++;
        }

        return state;
    }

    public static void main(String[] args){
        //Tests
        System.out.println("State: " + validateString("-123.42E-6"));
    }
}
