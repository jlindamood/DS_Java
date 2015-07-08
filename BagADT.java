import java.lang.Math;

public class ArrayBag<T> implements BagInterface<T> {
	private T[] stuff;
	private int numItems;

	@SuppressWarnings("unchecked")
	public ArrayBag() {
		this.numItems = 0;
		this.stuff = (T[]) new Object[50];
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
		if (this.isEmpty()) {
			System.out.println("Bag is already empty.");
			return null;
		}
		int randomNum = (int)Math.random() * numItems;
		T wordRemoved = this.stuff[randomNum];
		this.stuff[randomNum] = this.stuff[numItems];
		this.numItems--;
		return wordRemoved;
	}

	public boolean remove(T anEntry) {
		for (int i = 0; i < this.numItems; i++) {
			if (anEntry.equals(this.stuff[i])) {
				this.stuff[i] = this.stuff[numItems];
				this.numItems--;
				return true;
			}
		}
		return false;
	}

	public void clear() {
		numItems = 0;
	}
   
	public int getFrequencyOf(T anEntry) {
 		int counter = 0;
 		for (int i = 0; i < this.numItems; i++) {
 			if (anEntry.equals(this.stuff[i])) {
 				counter++;
 			}
 		}
 		return counter;
	}
   
	public boolean contains(T anEntry) {
		for (int i = 0; i < this.numItems; i++) {
			if (anEntry.equals(this.stuff[i])) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		BagInterface<Integer> b = new ArrayBag<Integer>();
		b.add(9);
		b.add(5);
		System.out.println(b.contains(9));
}
}