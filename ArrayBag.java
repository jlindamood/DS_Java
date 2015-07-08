// ArrayBag.java; 9-25-14; Suhas Tummalapalli, James Lindamood

import java.lang.Math;

public class ArrayBag<T> implements BagInterface<T> {

	public T[] stuff;
	private int numItems = 0;

	//Constructor
	@SuppressWarnings("unchecked")
	public ArrayBag() {

		//Instance Variable Initialization
		this.numItems = 0;
		this.stuff = (T[]) new Object[300000];

	}

	public int getCurrentSize() {

		return this.numItems;

	}
 
	public boolean isFull() {

		if (this.numItems == this.stuff.length) {

			return true;

		}

		return false;

	}

	public boolean isEmpty() {

		if (this.numItems == 0) {

			return true;

		}

		return false;

	}

	public boolean add(T newEntry) {

		if(this.isFull()) {

			return false;

		}

		this.stuff[this.numItems] = newEntry;
		this.numItems++;
		return true;

	}

	public T remove() {

		// Checks if the bag is empty and has nothing to remove.
		if (this.isEmpty()) {

			System.out.println("Bag is already empty.");
			return null;

		}

		// If the bag is not empty, randomly removes a word, replaces
		// it with the last item and adjusts size by -1.
		int randomNum = (int)Math.random() * numItems;
		T wordRemoved = this.stuff[randomNum];
		this.stuff[randomNum] = this.stuff[numItems];
		this.numItems--;
		return wordRemoved;

	}

	public boolean remove(T anEntry) {

		// Linearly searches through array for anEntry
		// removes the entry, and replaces it with the last entry of the array
		for (int i = 0; i < this.numItems; i++) {

			if (anEntry.equals(this.stuff[i])) {

				this.stuff[i] = this.stuff[numItems];
				this.numItems--;
				return true;

			}

		}

		// Returns false if input is not found in the array
		return false;

	}

	public void clear() {

		numItems = 0;

	}
   
	public int getFrequencyOf(T anEntry) {

		// Keeps a counter for all found instances of anEntry
		// in the array until the end, and returns the value.
 		int counter = 0;
 		for (int i = 0; i < this.numItems; i++) {

 			if (anEntry.equals(this.stuff[i])) {

 				counter++;

 			}

 		}

 		return counter;
	}
   
	public boolean contains(T anEntry) {

		// Linearly searches through the array until the end
		// Returns true if found, false if not.
		for (int i = 0; i < this.numItems; i++) {

			if (anEntry.equals(this.stuff[i])) {

				return true;

			}

		}

		return false;
	}

	// Test main
	public static void main(String[] args) {

		BagInterface<Integer> b = new ArrayBag<Integer>();
		b.add(9);
		b.add(5);
		System.out.println(b.isFull());
}

}