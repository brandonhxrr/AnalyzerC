import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Analyzer {
    public static void readFile(String path){
        //Add code to read files
        String next;
        try{
            File file = new File(path);
            Scanner sc = new Scanner(file);
            while(sc.hasNext()){
                next = sc.next();
                if(!Automata.isSymbol(next) && !Automata.isKeyword(next)){
                    System.out.println(next);
                }

            }
            sc.close();
        }catch(FileNotFoundException e){

            System.out.println("Archivo no encontrado");
        } //Return file or scanner and make other function that pass the next() value to the automaton
    }

    public static void main(String[] args) {
        readFile("C:\\Users\\Brand\\IdeaProjects\\Analyzer\\src\\EjemploPracticaAnalizador.txt");
    }

}
