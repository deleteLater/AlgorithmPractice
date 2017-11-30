package per.Algotithms;
import java.util.Iterator;

class Node<Item>{
	Item item;
	Node<Item> next;
	public Node(Item item){
		this.item = item;
		this.next = null;
	}
}

public class FoundationalDataStructure {
	
	public static void main(String[] args) {
		//BagTest();
		//StackTest();
		//QueueTest();
	}
	
	public static void BagTest() {
		Bag<Double> numbers = new Bag<Double>();
		for(int i = 0;i<10;i++) {
			numbers.add(Math.random()*100);
		}
		System.out.println("Bag size: "+numbers.size());
		System.out.println("Elements: ");
		for(double value : numbers) {
			System.out.print(value+" ");
		}
	}
	
	public static void StackTest() {
		Stack<Character> stack = new Stack<Character>();
		for(int i = 0;i<26;i++) {
			stack.push((char)('a'+i));
		}
		System.out.println("Stack size: "+stack.size());
		System.out.print("Elements: ");
		for(char value : stack) {
			System.out.print(value);
		}
		System.out.print("\nTop Elements: "+stack.top());
		System.out.print("\nClear Stack... ...");
		System.out.println("\nstack is Empty: "+stack.isEmpty());
	}
	
	public static void QueueTest(){
		Queue<Character> chs = new Queue<Character>();
		for(int i = 0;i<26;i++) {
			chs.enqueue((char)('A'+i));
		}
		for(char value : chs) {
			System.out.print(value);
		}
	}
}

//Bag ADT
class Bag<Item> implements Iterable<Item>{
	private Node<Item> first;
	private int N;
	public Bag(){
		first = null;
		N = 0;
	}
	public void add(Item item) {
		Node<Item> oldfirst = first;
		first = new Node<Item>(item);
		first.next = oldfirst;
		N++;
	}
	//IterationOrder is not matters,this iterator is FIFO
	public Iterator<Item> iterator(){
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item>{
		private Node<Item> current = first;
		public boolean hasNext(){
			return ( current!=null);
		}
		public void remove() {}
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	
	boolean isEmpty() {return (N == 0) && ( first == null);}
	int size() {return N;}
}

//Stack ADT
class Stack<Item> implements Iterable<Item>{
	private int N;
	private Item[] datas;
	@SuppressWarnings("unchecked")
	private void resize(int size) {
		Item temp[] = (Item[])new Object[size];
		for(int i = 0;i<N;i++) {
			temp[i] = datas[i];
		}
		datas = temp;
	}
	public int size() {return N;}
	public boolean isEmpty() { return N==0;}
	@SuppressWarnings("unchecked")
	public Stack() {
		N = 0;
		datas = (Item[])new Object[1];
	}
	public void push(Item item) {
		if(N==datas.length)
			resize(2*datas.length);
		datas[N++] = item;
	}
	public Item pop() {
		Item item = datas[--N];
		datas[N] = null;
		if(N==datas.length/4 && N>0)
			resize(datas.length/2);
		return item;
	}
	
	public Item top() {
		if(this.isEmpty())
			throw new IllegalStateException("TryToGetTheTopOfAEmptyStack");
		else
			return datas[N-1];
	}
	public void clear() {
		while(!this.isEmpty()) {
			this.pop();
		}
	}
	
	public Iterator<Item> iterator(){
		return new ReverseArrayIterator();
	}
	private class ReverseArrayIterator implements Iterator<Item>{
		private int i = N;
		public boolean hasNext() {return i>0;}
		public Item next() {return datas[--i];}
		public void remove() {}
	}
}

// Queue ADT
class Queue<Item> implements Iterable<Item>{
	private Node<Item> first;
	private Node<Item> last;
	private int N;
	public Queue() {
		N = 0;
		first = null;
		last = null;
	}
	public boolean isEmpty() {
		return first == null;
	}
	public int size() {return N;}
	public void enqueue(Item item) {
		Node<Item> oldLast = last;
		last = new Node<Item>(item);
		if(this.isEmpty())
			first = last;
		else
			oldLast.next = last;
		N++;
	}
	
	public Item dequeue() {
		Node<Item> oldFirst = first;
		Item item = oldFirst.item;
		oldFirst = null;
		first = first.next;
		if(this.isEmpty())
			last = null;
		N--;
		return item;
	}
	
	public Iterator<Item> iterator(){
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item>{
		Node<Item> current = first;
		public boolean hasNext() {return current!= null;}
		public void remove() {}
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}
