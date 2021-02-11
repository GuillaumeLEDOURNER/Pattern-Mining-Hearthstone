import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws Exception {
		//String filename = "files/all_absolute+.txt";
		String filename = "files/test.txt";
		System.out.println(new File(filename).getAbsolutePath());
		List<String> l = new ArrayList<>();
		HashSet<String> joueurA = new HashSet<>();
		HashSet<String> joueurB = new HashSet<>();
		HashSet<String> bddCarte = new HashSet<>();
		List<HashSet<String>> total = new ArrayList<>();
		int numPartie = -1;

		try (Scanner scanner = new Scanner(new File(filename));) {
			scanner.useDelimiter("");

			while(scanner.hasNext()) {
				l.add(scanner.nextLine());
			}
			for(String line : l) {
				if(!line.toLowerCase().contains("begin")) { 
					String[] oneLine = line.split(" ");
					bddCarte.add(oneLine[1].substring(1));
					if(numPartie == Integer.valueOf(oneLine[0])) {
						if(oneLine[1].charAt(0) == 'M') {
							//System.out.println(oneLine[1].substring(1));
							joueurA.add(oneLine[1].substring(1));
						}else {
							//System.out.println(oneLine[1].substring(1));
							joueurB.add(oneLine[1].substring(1));
						}

					}else {
						total.add(joueurA);
						total.add(joueurB);
						numPartie = Integer.valueOf(oneLine[0]);
						joueurA.clear();
						joueurB.clear();
					}
				} 

			}
			System.out.println("SIZE " + total.size());
			String res = "";
			int cpt = 0;
			
            for(HashSet<String> h : total) {

            	
            	
            	for(String s : h) {
            	
            		System.out.println(cpt +" " + s);
            		
            	}
            	cpt++;
            }
			/*
			int cpt2 = 0;
			for(String s : bddCarte) {
				System.out.println(cpt2 +" " + s);
				cpt2++;
			}
			*/
		}
	}
}
