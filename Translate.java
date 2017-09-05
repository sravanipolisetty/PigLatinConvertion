import java.util.Scanner;
import java.util.StringTokenizer;

public class Translate {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		String word = null;
		while (true) { // Do until the program exits
			System.out.println("Enter the Word");

			String line = sc.nextLine();
			String output = ""; // Set here so that each new line's output is
			// set to null.

			// Break into words
			StringTokenizer st = new StringTokenizer(line);

			int count=1;
			while (st.hasMoreElements()) {
				word = (String) st.nextElement();
				if (word.equalsIgnoreCase("exit")) {
					exit = true;
					break; // will go outside the current while loop, print and
					// exit
				}

				// translate the word and add to output
				if (count++==1)
					output += translateWord(word);
				else
					output += " "+translateWord(word);
				//System.out.println("OUTPUT: "+ output);

			}

			// All words translated and added to output until exit occcurs.
			// Print the translation
			System.out.println(output+".");

			// Exit if exit present.
			if (exit)
				System.exit(0);

			// Else start again
		}
	}

	public static String translateWord(String word){

		final String vowels = "aeiouAEIUOy";
		final String puncts = ".,?!:;\"(){}[]<>^";
		boolean hasLetters=false;
		boolean hasCapitalStart=false;
		String beforeVowel = "";
		String stem="";
		int cut = 0;
		//System.out.println("translate Word1 : "+word+":"+beforeVowel.length());
		
		if (Character.isUpperCase(word.charAt(0))){ //Starts with Capital
			hasCapitalStart=true;
		}
		while (cut < word.length() ) {

			if(!hasLetters && Character.isLetter(word.charAt(cut)))
				hasLetters=true;
			if(!vowels.contains("" + word.charAt(cut))) 
				beforeVowel += word.charAt(cut);
			else 
				break; //is a vowel. So break prefix

			cut++; //move to next letter
		}
		
		//System.out.println("translate Word2 : "+word+":"+ beforeVowel+":"+hasLetters);
		
		if(!hasLetters){//and there are no letters
			return word; //no translation required
		}
		
		if(beforeVowel.length()==0){ //there are no vowels
				return checkForCapital(hasCapitalStart,word+"yay");
			}
			

			stem= word.substring(cut);
			return checkForCapital(hasCapitalStart,stem+beforeVowel+"ay");
		}

		public static String checkForCapital(Boolean hasCapitalStart, String translatedWord) {
			//System.out.println("checkForCapital1: "+ hasCapitalStart+":"+translatedWord);

			
			if (hasCapitalStart) {
				return Character.toUpperCase(translatedWord.charAt(0)) + translatedWord.substring(1);
			}
			return translatedWord;
		}
	}
