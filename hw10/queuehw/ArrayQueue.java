// 10/17/2014
// Coded by Suhas Tummalapalli and James Lindamood

import java.lang.ArrayIndexOutOfBoundsException;

@SuppressWarnings("unchecked")
class ArrayQueue<T> implements QueueInterface<T> {

	private T[] arrayq = (T[]) new Object[1000];
	// len keeps track of how many non-null strings are in the array
	private int frontIndex = 0;
	private int backIndex = -1;
	private static final int INITIAL_SIZE = 30;

	public boolean isFull() {
		if ((backIndex+2) % this.arrayq.length == (frontIndex) % this.arrayq.length) {
			return true;
		} else {
			return false;
		}
	}

	// enqueue adds the item to the end of the queue
	public void enqueue(T item) {

		if (this.isFull()) {

		T[] newArrayQ = (T[]) new Object[arrayq.length * 2];

		// Iterates over the queue from the frontIndex, wraps around, to the backIndex and adds to the newArrayQ
		// which then copies to be a new arrayq.
		for (int i = frontIndex % arrayq.length; i < backIndex % arrayq.length; i++) {
			for (int j = 0; j < arrayq.length; j++) {
				newArrayQ[j] = arrayq[i];
			}
		}

		arrayq = newArrayQ;
	}

		backIndex = (backIndex + 1) % arrayq.length;
		arrayq[backIndex] = item;

	}

	// dequeue removes the item at the front of the queue
	// It also returns the item or null if the queue is empty
	public T dequeue() {

		if (this.arrayq[frontIndex] != null) {
			T firstObject = this.arrayq[frontIndex];

			// Initially had this code, was unncessary due to frontIndex.
			/*int iterator = frontIndex;
			while (this.arrayq[(iterator + 1) % this.arrayq.length] != null) {
				this.arrayq[(iterator) % this.arrayq.length] = this.arrayq[(iterator + 1) % this.arrayq.length];
			}
			*/
			frontIndex++;
			return firstObject;
		} else {
			throw new ArrayIndexOutOfBoundsException("Queue is empty.");
		}

	}

	// Returns true if the queue is empty or returns false otherwise
	public boolean isEmpty() {

		if (this.arrayq[frontIndex] == null) {
			return true;
		} else {
			return false;
		}
	}

	// Returns the frontmost item in the queue
	public T getFront() {

		return this.arrayq[frontIndex];

	}

	// Clears the entire queue
	public void clear() {

		frontIndex = 0;
		backIndex = -1;

	}

}