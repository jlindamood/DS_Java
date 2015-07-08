/*

Assignment: Hydra.java

for: CS201, Data Structures, Andy Exley

by James Lindamood

*/

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
		if (headTarget != null) {

		for (int i = 1; i < headTarget.length(); i++) {

 			headSplit += headTarget.charAt(i);

 		}

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
		Hydra hydra = new Hydra(args[0]);
		System.out.println("This is the monster you are facing: " + hydra.monster.stuff[0]);
		
		while (hydra.monster.getCurrentSize() > 0) {

			hydra.chopHead();

			//for (int i = 0; i < hydra.monster.getCurrentSize(); i++) {
				//System.out.println(hydra.monster.stuff[i]);
			}

			//System.out.println("--------------")

	}
}
