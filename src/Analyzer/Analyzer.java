package Analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Analyzer {
    
    public static List<List<String>> tokens = new ArrayList<>();
    public static List<List<String>> errors = new ArrayList<>();
    
    public static List<Integer> analyzeFile(String path){
        String next;
        int line = 1, state, mlState = 0, ca = 0, cc = 0, lastState = 0;
        List<Integer> acceptingStates = new ArrayList<>();
        try{
            File file = new File(path);
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                
                //next = Filter.filter(sc.nextLine());
                next = sc.nextLine();

                if(!next.isBlank()){

                    //state = Automata.validateString(next, mlState);
                    
                    state = Automata6.validateString(next, mlState, line);
                    
                    //Validar comentario multilinea

                    mlState = state == 14 ? 14 : 0;
                    ca = ((state == 14 && lastState != 14) || (state == 16 && lastState != 14))? ca + 1 : ca;
                    cc = state == 16 ? cc + 1 : cc;

                    lastState = state;

                    if(state != 14 && !Automata6.isAcceptingState(state)) acceptingStates.add(line);

                    System.out.print(next + "\t");
                    System.out.println("Linea: " + line + " Estado: " + state);
                }
                line++;
            }
            if(ca != cc) acceptingStates.add(line - 1);

            System.out.println("CA: " + ca + " CC: " + cc);
            sc.close();
        }catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado");
        }
        return acceptingStates;
    }

    static void printErrors(List<Integer> errors){
        if(!errors.isEmpty()){
            for(Integer error: errors) {
                System.out.println("Error en la linea " + error);
            }
        }else{
            System.out.println("No hay errores");
        }

    }
    
    static void printTokens() {
        System.out.println("TOKENS: ");
        for(List<String> tokenList : tokens) {
            for(String token : tokenList ){
                System.out.print(token + "\t");
            }
            System.out.println("");
            
        }
    }
    
    static void printErrors() {
        System.out.println("Errors: ");
        for(List<String> tokenList : errors) {
            for(String token : tokenList ){
                System.out.print(token + "\t");
            }
            System.out.println("");
            
        }
    }

    public static void main(String[] args) {
        printErrors(analyzeFile("src/EjemploPracticaAnalizador.txt"));
    }

}
