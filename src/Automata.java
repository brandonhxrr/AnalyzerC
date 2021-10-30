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
                    if(symbol == '+' || symbol == '-'){
                        state = 0;
                    }else if(symbol == '0'){
                        state = 2;
                    }else if (isNumber(symbol, 10)){
                        state = 1;
                    }
                break;
                case 1:
                    //Code for state 1 transition
                break;
            }

            index++;
        }

        return state;
    }

    public static void main(String[] args){
        //Tests
        System.out.println("State: " + validateString("123"));
    }
}
