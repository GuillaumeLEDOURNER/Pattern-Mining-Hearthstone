import java.io.File;
import java.util.Scanner;

public class App {
    
    public static void main(String[] args) throws Exception {
        String filename = "files/test.txt";
        System.out.println(new File(filename).getAbsolutePath());
        try (Scanner scanner = new Scanner(new File(filename));) {
            scanner.useDelimiter(" ");
            String all = "";
            while(scanner.hasNext()){
                all =  all + scanner.next();
            }
            
            System.out.println(all);
        }
    }
}
