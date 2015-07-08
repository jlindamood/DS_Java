public class MyPQ<T extends Comparable<? super T>>
implements PriorityQueueInterface<T>
{
	private class Node {

		private T data = null;
		private Node next = null;

		public Node(MyPQ q, T dataEnt, Node nextEnt) {
			this.data = dataEnt;
			this.next = nextEnt;
		}
	}

	private MyPQ<T>.Node front;

	public MyPQ()
	{
		this.front = null;
	}

	public void add(T paramT)
	{
		if ((this.front == null) || (paramT.compareTo(this.front.data) < 0))
		{
			this.front = new MyPQ.Node(this, paramT, this.front);
		}
		else
		{
			MyPQ<T>.Node localNode = this.front;
			while ((localNode.next != null) &&
				(paramT.compareTo(localNode.next.data) >= 0)) {
				localNode = localNode.next;
		}
		localNode.next = new MyPQ.Node(this, paramT, localNode.next);
	}
}

public T remove()
{
	T localComparable = this.front.data;
	this.front = this.front.next;
	return localComparable;
}

public T getFront()
{
	return this.front.data;
}

public boolean isEmpty()
{
	return this.front == null;
}

public void clear()
{
	this.front = null;
}
}