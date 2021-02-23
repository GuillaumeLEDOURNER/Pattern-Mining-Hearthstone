import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PreLCLM {
	
	private static Scanner scan;
	private static int moy;
	
	public static void calculMoy() {
		List<String> l = new ArrayList<>();
		int total = 0;
		int moyTemp = 0;
		while(scan.hasNext()) {
			String line = scan.nextLine();
			String[] truc = line.split(": ");
			total++;
			moyTemp+= Integer.valueOf(truc[1]);
		}
		moy = moyTemp/total;
		System.out.println(moy);
	}
	
	public static void input(String in) throws FileNotFoundException {
		scan = new Scanner(new File(in));
	}

	public static void main(String[] args) throws FileNotFoundException {
		input("./files/warrior10.txt");
		calculMoy();

	}

}
