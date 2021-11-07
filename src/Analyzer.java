import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Analyzer {
    public static void readFile(String path){
        String next;
        int line = 1, state = 0, mlState = 0;
        try{
            File file = new File(path);
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){

                next = Filter.filter(sc.nextLine());

                if(!next.isBlank()){

                    state = Automata.validateString(next, mlState);

                    mlState = state == 14 ? 14 : 0;

                    System.out.print(next + "\t");

                    System.out.println("Linea: " + String.valueOf(line) + " Estado: " + String.valueOf(state));
                }
                line++;
            }
            sc.close();
        }catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado");
        } //Return file or scanner and make other function that pass the next() value to the automaton
    }

    public static void main(String[] args) {
        readFile("src/EjemploPracticaAnalizador.txt");
    }

}
