package Analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Analyzer {
    
    // Listas de tokens y errores

    public static List < List < String >> tokens = new ArrayList < > ();
    public static List < List < String >> errors = new ArrayList < > ();
    
    //Función para analizar un archivo, devuelve una lista con las lineas con error

    public static List < Integer > analyzeFile(String path) {
        String next;
        int line = 1, state, mlState = 0, ca = 0, cc = 0, lastState = 0;
        List < Integer > acceptingStates = new ArrayList < > ();
        try {
            File file = new File(path);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {

                next = sc.nextLine();

                if (!next.isBlank()) {

                    state = Automata.validateString(next, mlState, line);

                    //Validar comentario multilinea

                    mlState = state == 14 ? 14 : 0;
                    ca = ((state == 14 && lastState != 14) || (state == 16 && lastState != 14)) ? ca + 1 : ca;
                    cc = state == 16 ? cc + 1 : cc;

                    lastState = state;

                    if (state != 14 && !Automata.isAcceptingState(state)) acceptingStates.add(line);

                    System.out.print(next + "\t");
                    System.out.println("Linea: " + line + " Estado: " + state);
                }
                line++;
            }
            if (ca != cc) acceptingStates.add(line - 1);

            //System.out.println("CA: " + ca + " CC: " + cc);
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        }
        return acceptingStates;
    }
    
    //Imprimir las lineas donde hay un error

    static void printErrors(List < Integer > errors) {
        if (!errors.isEmpty()) {
            for (Integer error: errors) {
                System.out.println("Error en la linea " + error);
            }
        } else {
            System.out.println("No hay errores");
        }

    }

    //Imprimir los tokens de la lista de tokens a un archivo
    static void printTokens() {
        FileWriter fichero = null;
        PrintWriter pw = null;

        try {
            fichero = new FileWriter("C:/tokens.txt");
            pw = new PrintWriter(fichero);

            pw.println("TOKENS: ");
            for (List < String > tokenList: tokens) {
                for (String token: tokenList) {
                    pw.print(token + " \t");
                }
                pw.println("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    //Imprimir los errores de la lista de errores a un archivo
        static void printErrors() {
        FileWriter fichero = null;
        PrintWriter pw = null;

        try {
            fichero = new FileWriter("C:/errors.txt");
            pw = new PrintWriter(fichero);

            pw.println("ERRORES: ");
            for (List < String > errorList: errors) {
                for (String token: errorList) {
                    pw.print(token + " \t");
                    System.out.println(token);
                }
                pw.println("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}