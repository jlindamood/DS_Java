/*

Assignment: Hydra.java

for: CS201, Data Structures, Andy Exley

by James Lindamood

NOTE: I could not use generic Object arrays for this project in the iteration over index 0 of the monster bag array to create the child 
strings. Java could not treat the generic Object array workaround and cast them as Strings, so I had to rewrite the ArrayBag structure
specifically for Strings.
*/

import java.util.Scanner;

@SuppressWarnings("unchecked")
public class Hydra {

	// Intialization of "monster" ojbect (to be the Hydra and its child heads, or any other "monster" string)

	ArrayBagString monster = new ArrayBagString();

	// Constructor, takes "monster" string as parameter
	public Hydra(String monsterString) {
		this.monster.add(monsterString);
	}

	// Method to chop first head in the "monster" object's bag
	private void chopHead() {

		String headTarget = this.monster.stuff[0];
		String headSplit = "";


		// Iterate over "monster" string by character to form child strings without first letter
		// if loops to deal with null pointer exceptions
		if (headTarget != null) {

 		headSplit = headTarget.substring(1, headTarget.length());

 		// Remove or "destroy" the target "monster" head string
		this.monster.remove(headTarget);

	}


		// Check to see if child head is not an empty string; if so, add two children strings to bag
 		if (headSplit.length() > 0) {

 			this.monster.add(headSplit);
 			this.monster.add(headSplit);

 		}

	}

	public static void main(String[] args) {

		System.out.println("Input a monster to fight: ");
		Scanner keyboard = new Scanner(System.in);
		Hydra hydra = new Hydra(keyboard.next());
		System.out.println("This is the monster you are facing: " + hydra.monster.stuff[0]);
		
		while (hydra.monster.getCurrentSize() > 0) {

			hydra.chopHead();

			for (int i = 0; i < hydra.monster.getCurrentSize(); i++) {
				System.out.println(hydra.monster.stuff[i]);
			}

			System.out.println("--------------");

	}
}
}