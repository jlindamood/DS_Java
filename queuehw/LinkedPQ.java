/*
CS201
Data Structures
by James L, Suhas T.
22 October 2014

Note: Null pointer exceptions that we ran out of time to fix. Pretty sure the rest the philosophy behind the code works and does what is expected.

*/

import java.lang.ArrayIndexOutOfBoundsException;


class LinkedPQ<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {

	private class Node {

		private T data;
		private Node next;

		private Node(T dataPortion) {

			this.data = dataPortion;
			this.next = null;

		}

		private Node(T dataPortion, Node nextNode) {

			this.data = dataPortion;
			this.next = nextNode;

		}

		public T getData() {

			return this.data;

		}

		public Node getNext() {

			return this.next;

		}

	}

	private Node endNode = new Node(null, null);
	private Node firstNode = new Node(null, endNode);
	private int numberofEntries = 0;

	/*
	* Adds an item to the queue
	*/
	public void add(T mItem) {

		Node newNode = new Node(mItem);

		// If the queue is empty, sets the new node as the first node.

		if (this.isEmpty()) {
			newNode.next = endNode;
			firstNode = newNode;
		}

		Node nodeIter = firstNode;

		// Loops through the queue and compares the item added, mItem, with the rest.
		// If it is greater, it adds before. If less, it adds after. This add method, thus, keeps
		// the queue in priority and sorted.
		if (nodeIter.getNext() == endNode) {

			if (newNode.getData().compareTo(firstNode.getData()) == -1) {
				firstNode.next = newNode;
				newNode.next = endNode;
			} else {
				Node firstNode2 = new Node(firstNode.getData(), firstNode.getNext());
				firstNode = newNode;
				firstNode.next = firstNode2;
			}
		}
		
		while (nodeIter.getNext() != endNode) {

			Node nodePlus1 = nodeIter.getNext();

			// Conditional to check if greater

			if (newNode.getData().compareTo(nodePlus1.getData()) == 1) {
				newNode.next = nodePlus1;
				nodeIter.next = newNode;

			// Conditional to check if equal

			} else if (newNode.getData().compareTo(nodePlus1.getData()) == 0) {
				newNode.next = nodePlus1;
				nodeIter.next = newNode;

			// Conditional to check if less

			} else if (newNode.getData().compareTo(nodePlus1.getData()) == -1) {

				if (nodePlus1.getNext() == endNode) {
					newNode.next = endNode;
					nodePlus1.next = newNode;
				}

				nodeIter = nodeIter.getNext();

			}
		}

		this.numberofEntries++;

	}

	public T remove() {

		if (this.isEmpty()) {
			throw new ArrayIndexOutOfBoundsException("Queue is empty.");
		}

		T val = this.firstNode.data;
		this.firstNode = this.firstNode.next;
		this.numberofEntries--;
		return val;

	}

	public T getFront() {

		if (this.isEmpty()) {
			throw new ArrayIndexOutOfBoundsException("Queue is empty.");
		}

		if (this.firstNode.data == null) {
			throw new ArrayIndexOutOfBoundsException("Queue is empty.");
		}

		return this.firstNode.data;

	}

	public boolean isEmpty() {

		if (this.numberofEntries == 0) {

			return true;

		} else {

			return false;

		}
	}

	public void clear() {

		this.numberofEntries = 0;
		firstNode = new Node(null, null);

	}

	public int getSize() {
		return this.numberofEntries;
	}

}