class LinkedPQ<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {

	private class Node {

		private T data;
		private Node next;
		private int priority;

		private Node(T dataPortion) {

			this.data = dataPortion;

		}

		private Node(T dataPortion, Node nextNode) {

			this.data = dataPortion;
			this.next = nextNode;

		}

		private Node(T dataPortion, Node nextNode, int prio) {

			this.data = dataPortion;
			this.next = nextNode;
			this.priority = prio;

		}

	private Node firstNode;
	private int numberofEntries;

	public void add_with_priority(T mItem) {

		Node newNode = new Node(mItem, firstNode, 1);
		this.numberofEntires++;

	}

	public void add(T mItem) {

		Node newNode = new Node(mItem);
		newNode.next = this.firstNode;
		this.firstNode = newNode;
		this.numberofEntries++;

	}

	public T remove() {

		T val = this.firstNode.data;
		this.firstNode = this.firstNode.next;
		return val;

	}

	public T getFront() {

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
		this.firstNode = null;

	}

	}
}