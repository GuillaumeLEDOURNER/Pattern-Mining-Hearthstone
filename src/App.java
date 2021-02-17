import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class App {
	static String filename = "files/all_absolute+.txt";
	//String filename = "files/test.txt";
	//System.out.println(new File(filename).getAbsolutePath());

	static HashSet<String> joueurA = new HashSet<>();
	static HashSet<String> joueurB = new HashSet<>();

	static List<HashSet<String>> total = new ArrayList<>();
	static List<HashSet<Integer>> totalMetEnInt = new ArrayList<>();
	static List<String> banList = Arrays.asList(
			"armorup!","daggermastery","fireblast","lesserheal","lifetap",
			"reinforce","shapeshift","steadyshot","totemiccall","egin","begin","thecoin");
	static int numPartie = 0;

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

	public static void input() throws FileNotFoundException {
		try (Scanner scanner = new Scanner(new File(filename));) {
			scanner.useDelimiter("");
			List<String> l = new ArrayList<>();

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
					/*
					for(String s : joueurA) {
						System.out.println("jA " + s);
					}
					for(String s : joueurB) {
						System.out.println("jB " + s);
					}*/
					total.add(joueurA);
					total.add(joueurB);
					numPartie = Integer.valueOf(oneLine[0]);
					joueurA = new HashSet<>();
					joueurB = new HashSet<>();
				}

			}
		}
		HashSet<Integer> transversation = new HashSet<>();
		for(HashSet<String> h : total) {

			for(String s : h) {
				String id = search(s);
				if(!id.equals("null")) {
					transversation.add(Integer.valueOf(id));
				}else {
					System.out.println("GROSEZAKEZAJLKEAZ");
				}
			}
			totalMetEnInt.add(transversation);
			transversation = new HashSet<>();
		}
	}

	public static void afficheDeckName() {
		int cpt = 0;
		for(HashSet<String> h : total) {
			System.out.println("Size deck : "+h.size());
			for(String s : h) {
				System.out.println(cpt +" " + s);
			}
			cpt++;
		}
	}

	public static void afficheBDD() {
		for(Integer truc : deckDictionnary.keySet()) {
			System.out.println(truc +" "+deckDictionnary.get(truc));
		}
	}
	public static String DeckList(){
		
		String res = "";
		List<Integer> hSorted = new ArrayList<>();
		for(HashSet<Integer> h : totalMetEnInt) {
			hSorted = new ArrayList<>(h);
			Collections.sort(hSorted);
			
			for(Integer s : hSorted){
				res += s.toString()+" ";
			}
			res+="\n";
		}
		return res;
	}
	

	public static String joliId() {
		String res ="@CONVERTED_FROM_TEXT \n";
		for(Integer key : deckDictionnary.keySet()) {
			res += "@ITEM="+key+"="+deckDictionnary.get(key)+"\n";
		}
		return res;
	}
	public static void output() {
		try {
			FileWriter output = new FileWriter("./files/output1.txt");
			output.write(joliId());
			output.write(DeckList());
			output.close();
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void main(String[] args) throws Exception {
		input();
		//afficheDeckEnInt();
		//afficheDeckName();
		//afficheBDD();
		//System.out.println(DeckList());
		output();

	}
}


