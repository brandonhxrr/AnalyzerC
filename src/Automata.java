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
                        state = 10;
                    }else if(Character.isLetter(symbol)){
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
                    }else if (!isNumber(symbol, 10)){
                        state = 17;
                    }
                break;
                case 2:
                    if(symbol == '0'){
                        state = 3;
                    }else if (isNumber(symbol, 10)){
                        state = 1;
                    }else{
                        state = 17;
                    }
                break;
                case 3:
                    if(symbol == 'x'){
                        state = 9;
                    }else if (isNumber(symbol, 8)){
                        state = 8;
                    }else{
                        state = 17;
                    }
                break;
                case 4:
                    if(symbol == 'E'){
                        state = 5;
                    }else if (!isNumber(symbol, 10)){
                        state = 17;
                    }
                break;
                case 5:
                    if ( symbol == '+' || symbol == '-'){
                        state = 6;
                    }else if(isNumber(symbol, 10)){
                        state = 7;
                    }else{
                        state = 17;
                    }
                break;
                case 6:
                    if(isNumber(symbol, 10)){
                        state = 7;
                    }
                break;
                case 7:
                    if(!isNumber(symbol, 10)){
                        state = 17;
                    }
                break;
                case 8:
                    if(!isNumber(symbol, 8)){
                        state = 17;
                    }
                break;
                case 9:
                    if(!isNumber(symbol, 16)){
                        state = 17;
                    }
                break;
                case 10:
                    if(Character.isLetterOrDigit(symbol) || symbol == '_' || symbol == '$'){
                        state = 11;
                    }
                break;
                case 11:
                    if(!Character.isLetterOrDigit(symbol) && symbol != '_' && symbol != '$'){
                        state = 17;
                    }
                break;
                case 12:
                    if(symbol == '/'){
                        state = 13;
                    }else if(symbol == '*'){
                        state = 14;
                    }
                    break;
                case 14:
                    if(symbol == '*'){
                        state = 15;
                    }
                break;
                case 15:
                    if(symbol == '/'){
                        state = 16;
                    }
                break;
                case 16:
                    if(!Character.isWhitespace(symbol)){
                        state = 0;
                    }
                break;
            }

            index++;
        }

        return state;
    }

    public static void main(String[] args){
        //Tests
        System.out.println("State1: " + validateString("/*Este es un comentario de varias lineas\nOtra linea*/"));
        System.out.println("State2: " + validateString("//Este es un comentario de una linea\nOtra linea"));
        System.out.println("State3: " + validateString("//Este es un comentario de una linea"));
        System.out.println("State4: " + validateString("java34"));
        System.out.println("State5: " + validateString("__"));
        System.out.println("State6: " + validateString("_65"));
        System.out.println("State7: " + validateString("java es cool"));
        System.out.println("State8: " + validateString("123"));
        System.out.println("State9: " + validateString("076"));
        System.out.println("State10: " + validateString("098"));
        System.out.println("State11: " + validateString("0xFA"));
        System.out.println("State11: " + validateString("0xJK"));
        System.out.println("State13: " + validateString("1.24"));
        System.out.println("State14: " + validateString("+1.24"));
        System.out.println("State15: " + validateString("+1.24E"));
        System.out.println("State16: " + validateString("+1.024"));
        System.out.println("State17: " + validateString("+1.24E-"));
        System.out.println("State18: " + validateString("+1.24E-8"));
    }
}
