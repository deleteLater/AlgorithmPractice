package per.Algotithms;

import java.util.Arrays;

public class Union_Find {
	private int id[];
	private int count;
	private int sz[];

	public Union_Find(int N) {
		id = new int[N];
		sz = new int[N];
		count = N;
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
		for (int i = 0; i < N; i++) {
			sz[i] = 1;
		}
	}

	public int count() {
		return count;
	}

	public boolean connected(int p, int q) {
		return (find(p) == find(q));
	}

	public void printArray() {
		System.out.println("ids: " + Arrays.toString(id));
		System.out.println("szs: " + Arrays.toString(sz));
	}
	/*
	 * The key is: public int find(); public void union(int p,int q);
	 */

	/*
	 * quick_find!!! public int find(int p) { return id[p]; } public void union(int
	 * p,int q) { int pId = find(p); int qId = find(q);
	 * 
	 * if(pId == qId) return ; for(int i = 0;i<id.length;i++) { if(id[i] == pId) {
	 * id[i] = qId; } } count--; }
	 */
	/*
	 * quick_union!!! public int find(int p) { while(p != id[p]) p = id[p]; return
	 * p; } public void union(int p,int q) { int pRoot = find(p); int qRoot =
	 * find(q);
	 * 
	 * if(pRoot == qRoot) return ; id[pRoot] = qRoot; count--; }
	 */
	public int find(int p) {
		// returns the Component's RootNode which p belongs to
		while (p != id[p]) {
			// compressPath
			id[p] = id[id[p]];
			p = id[p];
		}
		return p;
	}

	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);

		if (i == j)
			return;
		if (sz[i] > sz[j]) {
			id[j] = i;
			sz[i] += sz[j];
		} else {
			id[i] = j;
			sz[j] += sz[i];
		}
		count--;
	}

	public int treeDepth() {
		// get MaxWeightsNodeInfos
		int MaxWeights = 1;
		int MaxWeightsIndex = 0;
		for (int i = 0; i < sz.length; i++) {
			if (sz[i] > MaxWeights) {
				MaxWeights = sz[i];
				MaxWeightsIndex = i;
			}
		}
		int MaxWeightsValue = id[MaxWeightsIndex];
		if (MaxWeightsValue == 1)
			return 0;
		// calculate depth
		for (int value : id) {
			if (value == MaxWeightsValue)
				MaxWeights--;
		}
		return MaxWeights;
	}
}

class Percolation {
	private Union_Find grid = null;
	boolean[] state = null;
	private int N;

	public Percolation(int N) {
		this.N = N;
		if (this.N > 0) {
			int size = this.N * this.N + 1;
			grid = new Union_Find(size);
			state = new boolean[size];
			for (int index = 1; index < size; index++) {
				state[index] = false;
			}
		} else
			throw new IllegalArgumentException("ArgumentMustGreaterThanZero");
	}

	private boolean isInGrid(int i, int j) {
		if ((i <= 0 || i > this.N) || (j <= 0 || j > this.N))
			return false;
		else
			return true;
	}

	public void open(int i, int j) {
		if (isInGrid(i, j)) {
			state[(i - 1) * N + j] = true; // set state is true
			if (isInGrid(i - 1, j) && isopen(i - 1, j))
				grid.union((i - 1) * this.N + j, (i - 2) * this.N + j);
			if (isInGrid(i, j + 1) && isopen(i, j + 1))
				grid.union((i - 1) * this.N + j, (i - 1) * this.N + j + 1);
			if (isInGrid(i, j - 1) && isopen(i, j - 1))
				grid.union((i - 1) * this.N + j, (i - 1) * this.N + j - 1);
			if (isInGrid(i + 1, j) && isopen(i + 1, j))
				grid.union((i - 1) * this.N + j, (i) * this.N + j);
		} else
			throw new IndexOutOfBoundsException("Index out of bound!");
	}

	public boolean isopen(int i, int j) {
		if (isInGrid(i, j)) {
			return state[(i - 1) * N + j] == true;
		} else
			throw new IndexOutOfBoundsException();
	}

	public boolean isFull(int i, int j) { // is this site can connect to the first raw we can say this site is full
		if (isopen(i, j)) {
			for (int index = 1; index <= this.N; index++) {
				if (grid.connected(index, (i - 1) * N + j))
					return true;
			}
		}
		return false;
	}
	
	public boolean percolates() {
		for (int index = 1; index <= this.N; index++) {
			if (isopen(this.N, index) && isFull(this.N, index)) {
				return true;
			}
		}
		return false;
	}
}