import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
	static Map<Integer, String> deckDictionnary = new HashMap<>();
	static int lastUpdatedIndex = 1;
	
	public static void updateDictionnary(String item) {
		if(!deckDictionnary.containsValue(item)){
			deckDictionnary.put(lastUpdatedIndex, item);
			lastUpdatedIndex++;
		}
	}
	public static String search(String item) {
		String res ="null";
		for(Integer truc : deckDictionnary.keySet()) {
			if(deckDictionnary.get(truc).equals(item)) {
				res = String.valueOf(truc);
			}
		}
		return res;
	}
	public static boolean isLast(List<String> l, String line) {
		return l.get(l.size()-1).equals(line);
	}
	public static void main(String[] args) throws Exception {
		String filename = "files/all_absolute+.txt";
		//String filename = "files/test.txt";
		System.out.println(new File(filename).getAbsolutePath());
		List<String> l = new ArrayList<>();
		HashSet<String> joueurA = new HashSet<>();
		HashSet<String> joueurB = new HashSet<>();
		
		List<HashSet<String>> total = new ArrayList<>();
		List<HashSet<String>> totalMetEnInt = new ArrayList<>();
		List<String> banList = Arrays.asList(
				"armorup!","daggermastery","fireblast","lesserheal","lifetap",
				"reinforce","shapeshift","steadyshot","totemiccall","egin","begin","thecoin");
		int numPartie = 0;
		
		try (Scanner scanner = new Scanner(new File(filename));) {
			scanner.useDelimiter("");

			while(scanner.hasNext()) {
				l.add(scanner.nextLine());

			}
			
			for(String line : l) {

				String[] oneLine = line.split(" ");
				String cardName = oneLine[1].substring(1);
				if(!banList.contains(cardName.toLowerCase())){
											
					updateDictionnary(cardName);	
				
					if(oneLine[1].charAt(0) == 'M') {

						joueurA.add(cardName);
					}else {

						joueurB.add(cardName);
					}					
				}

				if(numPartie != Integer.valueOf(oneLine[0]) || isLast(l,line)) {

					for(String s : joueurA) {
						System.out.println("jA " + s);
					}
					for(String s : joueurB) {
						System.out.println("jB " + s);
					}
					total.add(joueurA);
					total.add(joueurB);
					numPartie = Integer.valueOf(oneLine[0]);
					joueurA = new HashSet<>();
					joueurB = new HashSet<>();
				}

			}
			System.out.println("////////////////////////////");
			System.out.println("SIZE total " + total.size());
			System.out.println("SIZE bdd " + deckDictionnary.size());
			String res = "";
			int cpt = 0;
			
			
			
			
			HashSet<String> transversation = new HashSet<>();
			for(HashSet<String> h : total) {

				for(String s : h) {
					String id = search(s);
					if(!id.equals("null")) {
						transversation.add(id);
					}else {
						System.out.println("GROSEZAKEZAJLKEAZ");
					}
				}
				totalMetEnInt.add(transversation);
				transversation = new HashSet<>();
			}
			for(HashSet<String> h : total) {
				//System.out.println(h.size());
				for(String s : h) {

					//System.out.println(cpt +" " + s);

				}
				cpt++;
			}
			cpt = 0;
			for(HashSet<String> h : totalMetEnInt) {
				//System.out.println(h.size());
				for(String s : h) {

					//System.out.println(cpt +" " + s);

				}
				cpt++;
			}
			/*int cpt2 = 0;
			for(String s : bddCarte) {
				System.out.println(cpt2 +" " + s);
				cpt2++;
			}*/
			
			for(Integer truc : deckDictionnary.keySet()) {
				System.out.println(truc +" "+deckDictionnary.get(truc));
			}
		}
	}
	
}
