package per.Algotithms;

import java.util.Arrays;

public class PriorityQueue {
	private int pq[];
	private int N;
	private int size;
	public PriorityQueue() {
		pq = new int[1];
		N = 0;
	}
	public PriorityQueue(int size) {
		pq = new int[size+1];
		N = 0;
		this.size = size;
	}
	private boolean less(int i,int j) {
		return pq[i] < pq[j];
	}
	private void exch(int i,int j) {
		int temp  = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	private void swim(int k) {
		while(k > 1 && less(k/2, k)) {
			exch(k/2, k);
			k = k/2;
		}
	}
	private void sink(int k) {
		while(2 * k <= N) {
			int j = 2*k;
			if(j < N && less(j, j+1)) j++;
			if(less(j, k))
				break;
			exch(k, j);
			k = j;
		}
	}
	public int size() {
		return this.size;
	}
	public int length() {
		return N;
	}
	public boolean isEmpty() {
		return (N==0) ;
	}
	public void add(int k) {
		pq[++N] = k;
		swim(N);
	}
	public int deleteMax() {
		int oldMax = pq[1];
		exch(1, N--);
		sink(1);
		pq[N+1] = 0;
		return oldMax;
	}
	public void printArray() {
		System.out.println(Arrays.toString(pq));
	}
}
