import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.FileWriter;
import java.io.IOException;

public class PostLCM {

	private static TreeSet<String> joueurA = new TreeSet<>();
	private static TreeSet<String> joueurB = new TreeSet<>();

	private static List<TreeSet<String>> total = new ArrayList<>();
	private static List<TreeSet<Integer>> totalMetEnInt = new ArrayList<>();
	private static List<String> classCard = Arrays.asList("armorup!","daggermastery","fireblast","lesserheal","lifetap",
			"reinforce","shapeshift","steadyshot","totemiccall");
	private static List<String> banList = Arrays.asList("egin","begin","thecoin");
	private static int numPartie = 0;

	private static Map<Integer, String> deckDictionnary = new HashMap<>();
	private static int lastUpdatedIndex = 1;
	
	/**
	 * 
	 * @param deck
	 * @return true si deck possède une carte de classe
	 */
	public static int containsClassCard(TreeSet<Integer> deck) {
		for(String c : classCard) {
			
			if(deck.contains(searchInteger(c))) {
				
				return searchInteger(c);
			}
		}
		return -1;
	}
	/**
	 * (a la suite de l'input)
	 * Va creer un fichier pour chaque classe contenant tout les decks (en nombre)
	 * en enlevant les cartes de classes du deck
	 * @throws IOException
	 */
	public static void OneFileOneClass() throws IOException {
		Map<Integer,List<TreeSet<Integer>>> totalByClass = new HashMap<>();
		
		//Initiliase les cles de la map
		for(String s : classCard) {		
			totalByClass.put(searchInteger(s),new ArrayList<TreeSet<Integer>>());
		}
		
	
		for(TreeSet<Integer> deck : totalMetEnInt) {
			
			int isCard = containsClassCard(deck);
			
			if(isCard != -1) {
				removeClassCard(deck);
				totalByClass.get(isCard).add(deck);
				
			}
			
		}
		for(Integer key : totalByClass.keySet()) {
			FileWriter output = new FileWriter("./files/class_"+ deckDictionnary.get(key)+".txt");
			String res ="@CONVERTED_FROM_TEXT \n";
		
			output.write(joliId());
			
			output.write(deckToString(totalByClass.get(key)));
			output.close();
		}
			
	}
	/**
	 * Supprimer les class card d'un treeset
	 * @param tree l'arbre a modifié
	 */
	public static void removeClassCard(TreeSet<Integer> tree) {
		for(String card : classCard) {
			tree.remove(searchInteger(card));
		}
	}
	
	/**
	 * 
	 * @param truc list de treeset
	 * @return string des deck en Integer
	 */
	public static String deckToString(List<TreeSet<Integer>> truc) {
		String res = "";
		if(truc.isEmpty()) System.out.println("is empty");
		
		for(TreeSet<Integer> h : truc) {
			
			for(Integer i : h) {
				//System.out.println(String.valueOf(i) + " ");
				res+= String.valueOf(i) + " ";
			}
			res+= "\n";
		}
		return res;
	}
	
	
	/**
	 * Ajoute une carte à la bdd si absente de la bdd
	 * @param item 
	 */
	public static void updateDictionnary(String item) {
		if(!deckDictionnary.containsValue(item)){
			deckDictionnary.put(lastUpdatedIndex, item);
			lastUpdatedIndex++;
		}
	}
	
	
	/**
	 * Search a card in the bdd and give it's ID
	 * @param item name of the card
	 * @return id of the card
	 */
	public static String searchString(String item) {
		String res ="null";
		for(Integer truc : deckDictionnary.keySet()) {
			if(deckDictionnary.get(truc).equals(item)) {
				res = String.valueOf(truc);
			}
		}
		return res;
	}
	/**
	 * Search a card in the bdd and give it's ID
	 * @param item name of the card
	 * @return id of the card
	 */
	public static Integer searchInteger(String item) {
		
		for(Integer truc : deckDictionnary.keySet()) {
			if(deckDictionnary.get(truc).equals(item)) {
				return truc;
			}
		}
		return -1;
	}
	/**
	 * 
	 * @param l List of string
	 * @param line string
	 * @return true if line is the last element of l
	 */
	public static boolean isLast(List<String> l, String line) {
		return l.get(l.size()-1).equals(line);
	}
	/**
	 * Scan the input .txt file
	 * Complete the data base of all cards
	 * Create the list of decks
	 * 
	 * @throws FileNotFoundException
	 */
	public static void input(String in) throws FileNotFoundException {
		try (Scanner scanner = new Scanner(new File(in));) {
			scanner.useDelimiter("");
			List<String> l = new ArrayList<>();

			while(scanner.hasNext()) {
				l.add(scanner.nextLine());

			}

			for(String line : l) {

				String[] oneLine = line.split(" ");
				String cardName = oneLine[1].substring(1).toLowerCase();
				if(!banList.contains(cardName)){

					updateDictionnary(cardName);	

					if(oneLine[1].charAt(0) == 'M') {

						joueurA.add(cardName);
					}else {

						joueurB.add(cardName);
					}					
				}

				if(numPartie != Integer.valueOf(oneLine[0]) || isLast(l,line)) {
					total.add(joueurA);
					total.add(joueurB);
					numPartie = Integer.valueOf(oneLine[0]);
					joueurA = new TreeSet<>();
					joueurB = new TreeSet<>();
				}

			}
		}
		TreeSet<Integer> transversation = new TreeSet<>();
		for(TreeSet<String> h : total) {
			
			for(String s : h) {
				
				String id = searchString(s);
				if(!id.equals("null")) {
					transversation.add(Integer.valueOf(id));
				}
			}
			totalMetEnInt.add(transversation);
			transversation = new TreeSet<>();
		}
	}
	/**
	 * method toString for all deck
	 */
	public static void afficheDeckName() {
		int cpt = 0;
		for(TreeSet<String> h : total) {
			System.out.println("Size deck : "+h.size());
			for(String s : h) {
				System.out.println(cpt +" " + s);
			}
			cpt++;
		}
	}
	/*
	 * method toString for data base
	 */
	public static void afficheBDD() {
		for(Integer truc : deckDictionnary.keySet()) {
			System.out.println(truc +" "+deckDictionnary.get(truc));
		}
	}
	/**
	 * 
	 * @return String of the deck list sorted
	 */
	public static String DeckList(){
		
		String res = "";
		List<Integer> hSorted = new ArrayList<>();
		for(TreeSet<Integer> h : totalMetEnInt) {
			hSorted = new ArrayList<>(h);
			Collections.sort(hSorted);
			
			for(Integer s : hSorted){
				res += s.toString()+" ";
			}
			res+="\n";
		}
		return res;
	}
	
	/**
	 * 
	 * @return le debut de texte necessaire pour LCM 
	 */
	public static String joliId() {
		String res ="@CONVERTED_FROM_TEXT \n";
		for(Integer key : deckDictionnary.keySet()) {
			res += "@ITEM="+key+"="+deckDictionnary.get(key)+"\n";
		}
		return res;
	}
	
	/**
	 * Create the output file to use for LCM
	 */
	public static void output(String out) {
		try {
			FileWriter output = new FileWriter(out);
			output.write(joliId());
			output.write(DeckList());
			output.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) throws Exception {
		String in = "files/all_absolute+.txt";
		input(in);
	
		//afficheDeckName();
		//afficheBDD();
		//System.out.println(DeckList());
		String out = "./files/output1.txt";
		//output(out);
		OneFileOneClass();

	}
}


