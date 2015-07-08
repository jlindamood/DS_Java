/*
CallStack (Linked Implementation)
CS201, Andy Exley
James Lindamood, Suhas Tummalapalli
October 10, 2014
*/

import java.util.EmptyStackException;

public class CallStack implements StackInterface<Method> {

	// Create Node class to be used by Linked stack
	private class Node {

		private Method data;
		private Node next;

		public Node(Method data, Node next) {
			this.data = data;
			this.next = next;
		}

		public Method getData() {
			return this.data;
		}

		public void setData(Method item) {
			this.data = item;
		}

		public Node getNext() {
			return this.next;
		}

		public void setNext(Node newNode) {
			this.next = newNode;
		}
	}

   	// Variable Initilization
	private Node firstNode;
	private SimulationComponent sCom;

	public CallStack(SimulationComponent sCom) {
		this.sCom = sCom;
	}

 	// Pushes item to the end of the array
 	// If the array is full, creates an array twice as large and copies over from the old array
	public void push(Method item) {

		this.firstNode = new Node(item, this.firstNode);
		sCom.addMethodToGraphicalStack(item);

	}

	// Returns the last item in the array and removes it from the array
	public Method pop() {

		if (this.firstNode != null) {
			Method item = this.firstNode.getData();
			this.firstNode = this.firstNode.getNext();
			sCom.removeMethodFromGraphicalStack(item);
			return item;
		} else {
			throw new EmptyStackException();
		}

	}

	// Returns the last item in the array
	public Method peek() {

		if (this.firstNode == null) {

			throw new EmptyStackException();

		} else {

		return this.firstNode.getData();

	}

	}

	public boolean isEmpty() {

		if (this.firstNode == null) {

			return true;

		} else {

			return false;

		}

	}

	public void clear() {

		this.firstNode = null;
		
	}

}
