import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Analyzer {
    public static void readFile(String path){
        String next;
        try{
            File file = new File(path);
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                next = sc.nextLine();
                if(!next.isBlank()){
                    analyze(next);
                }
            }
            sc.close();
        }catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado");
        } //Return file or scanner and make other function that pass the next() value to the automaton
    }

    public static void analyze(String line){
        StringBuilder newLine = new StringBuilder();
        String aux = "";
        String[] words = line.split(" ");
        for (String word: words) {
            if(!Automata.isSymbol(word) && !Automata.isKeyword(word) && !word.isBlank()){
                aux = Automata.unresolvedWords(word);
                newLine.append(" ").append(aux);
            }
        }
        System.out.println(String.valueOf(newLine).trim());

    }

    public static void main(String[] args) {
        readFile("C:\\Users\\Brand\\IdeaProjects\\Analyzer\\src\\EjemploPracticaAnalizador.txt");
    }

}
