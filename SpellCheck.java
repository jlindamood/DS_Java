// 9-25-14, SpellCheck application. Suhas Tummalapalli and James Lindamood

//credit to file reading to tschaible @ StackOverflow ; http://stackoverflow.com/questions/2788080/reading-a-text-file-in-java

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SpellCheck {

	public static void main(String[] args) throws FileNotFoundException {

		BagInterface<String> dictionary = new ArrayBag<String>();

		// Reading in the dictionary file to a Bag data structure
		try {
		Scanner fileScanner = new Scanner(new File("words.txt"));

			while (fileScanner.hasNext()){
				
			dictionary.add(fileScanner.next());

			}

		}
		catch (FileNotFoundException ex) {

			System.out.println("File not found.");

		}

			// Initializing the loop to spellcheck input words
			while (true) {

				System.out.print("Enter a word to spellcheck: ");
				Scanner userInput = new Scanner(System.in).useDelimiter("\n");
				String test = userInput.next();

				// Enter twice exits the program
				if (userInput.nextLine().equals("")) {

					System.exit(1);

				}

				else if (dictionary.contains(test)) {

					System.out.println(test + " is spelled correctly.");
				}

				else {

					System.out.println(test + " is not spelled correctly.");

				}
			}
		}
	}