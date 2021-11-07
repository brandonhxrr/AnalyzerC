import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Analyzer {
    public static void readFile(String path){
        String next;
        int line = 1;
        try{
            File file = new File(path);
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){

                next = filter(sc.nextLine());

                if(!next.isBlank()){
                    System.out.print(next + "\t");
                    System.out.println("Linea: " + String.valueOf(line) + " Estado: " + String.valueOf(Automata.validateString(next)));
                }
                line++;
            }
            sc.close();
        }catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado");
        } //Return file or scanner and make other function that pass the next() value to the automaton
    }

    public static String filter(String line){
        StringBuilder newLine = new StringBuilder();
        String[] words = line.split(" ");
        for (String word: words) {
            if(!word.isBlank() && !Automata.isSymbol(word) && !Automata.isKeyword(word)){
                newLine.append(" ").append(Automata.unresolvedWords(word));
            }
        }
        return String.valueOf(newLine).trim();
    }

    public static void main(String[] args) {
        readFile("src/EjemploPracticaAnalizador.txt");
    }

}
