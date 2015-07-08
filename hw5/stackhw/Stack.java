/*
Stack ADT
CS201, Andy Exley
James Lindamood & Suhas Tummalapalli
October 8, 2014
*/


import java.util.EmptyStackException;

@SuppressWarnings("unchecked")
public class Stack<T> implements StackInterface<T> {

	// Variable Initilization
	private T[] stackArray = (T[]) new Object[100];
	private T[] stackArrayNew = (T[]) new Object[0];
 	private int stackArraySize = 0;

 	// Pushes item to the end of the array
 	// If the array is full, creates an array twice as large and copies over from the old array
	public void push(T item) {

		if (this.stackArraySize == stackArray.length) {

			stackArrayNew = (T[]) new Object[stackArray.length * 2];
			for (int i = 0; i < stackArraySize; i++) {

				stackArrayNew[i] = stackArray[i];

			}

			stackArray = stackArrayNew;
		}

		this.stackArray[stackArraySize] = item;
		this.stackArraySize++;

	}

	// Returns the last item in the array and removes it from the array
	public T pop() {

		if (this.stackArraySize == 0) {

			throw new EmptyStackException();

		} else {

		T lastItem = this.stackArray[stackArraySize - 1];
		this.stackArray[stackArraySize - 1] = null;
		stackArraySize--;
		return lastItem;

	}

	}

	// Returns the last item in the array
	public T peek() {

		if (this.stackArraySize == 0) {

			throw new EmptyStackException();

		} else {

		return this.stackArray[stackArraySize - 1];

	}

	}

	public boolean isEmpty() {

		if (stackArraySize == 0) {

			return true;

		} else {

			return false;

		}

	}

	public void clear() {

		this.stackArraySize = 0;
		
	}

}