package per.Algotithms;

import edu.princeton.cs.introcs.StdRandom;

public class SortTechnology {
		public static long swapCount = 0;
		public static long compareCount = 0;
		public static long arrayAccessCount = 0;
		public static void countZeroed() {
			swapCount = 0;
			compareCount = 0;
			arrayAccessCount = 0;
		}
		public static void swapNum(int arr[],int p,int q) {
			/*
			 * SwapTwoNum
			 * int i = 10;
			 * int j = 20;
			 * 
			 * int temp = i ^ j;
			 * i = temp ^ i;
			 * j = temp ^ j;
			 * 
			 */
			int temp = arr[p];
			arr[p] = arr[q];
			arr[q] = temp;
		}
		
		public static void SelectionSort(int arr[]) {
			int N = arr.length;
			for(int i = 0 ; i < N; i++) {
				int min = i;
				for(int j = i+1; j < N; j++) {
					compareCount++;
					if(arr[j] < arr[min])
						min = j;
				}
				if(i != min) {
					swapNum(arr, i, min);
					swapCount++;
				}
					
			}
		}
		public static void SeletionSort_ImproveEdition(int arr[]) {
			int N = arr.length;
			for(int i =0; i<N/2; i++) {
				int min = i;
				int max = i;
				for(int j = i+1;j < N-i;j++) {
					if(arr[j] < arr[min])
						min = j;
					if(arr[j] > arr[max])
						max = j;
					compareCount += 2;
				}
				if(i != min) {
					swapNum(arr, i, min);
					swapCount++;
				}
				if((N-i-1) != max) {
					swapNum(arr, max, N-i-1);
					swapCount++;
				}
			}
		}
		
		public static void InsertSort(int arr[]) {
			int N = arr.length;
			for(int i = 1; i<N; i++) {
				compareCount++;
				for(int j = i; j>0 && arr[j]<arr[j-1]; j--) {
					swapNum(arr, j, j-1);	//arrayAccess 4 times
					swapCount++;
					compareCount++;
					arrayAccessCount += 4;
				}
			}
		}
		public static void InsertSort_ImproveEdition(int arr[]) {
			int N = arr.length;
			for(int i = 1; i < N;i++) {
				int insertNum =  arr[i];
				arrayAccessCount++;
				int j = i;
				for(; j > 0 ;j--) {
					if(arr[j] < arr[j-1]) {
						arr[j] = arr[j-1];
						arrayAccessCount += 2;
					}else
						break;
				}
				arr[j] = insertNum;
				arrayAccessCount++;
			}
		}		
		
		public static void ShellSort(int arr[]) {
			int N = arr.length;
			int h = 1;
			while(h < N/3)
				h = h*3 + 1;	
			while(h>=1) {
				for(int i = h; i<N; i++) {
					compareCount++;
					for(int j = i ; j>=h && arr[j] < arr[j-h]; j -= h) {
						swapNum(arr, j , j-h);
						swapCount++;
					}
				}
				h = h / 3;
			}
		}
		
}

class Merge{
	private static int aux[];
	public static void sort(int arr[]) {
		aux = new int[arr.length];
		sort(arr, 0, arr.length-1);
	}
	
	private static void sort(int arr[],int lo,int hi) {
		if(hi <= lo) return;
		int mi = lo + (hi-lo)/2;
		sort(arr, lo, mi);
		sort(arr, mi+1, hi);
		merge(arr, lo, mi, hi);
	}
	public static void merge(int arr[],int lo,int mi,int hi) {
		if(arr[mi] < arr[mi+1]) {
			return ;
		}
		
		int i = lo;int j = mi + 1;
		
		for(int k = lo; k <= hi ; k++) {
			aux[k] = arr[k];
		}
		
		for(int k = lo; k <= hi; k++) {
			if	   (i > mi) 			
				arr[k] = aux[j++];
			else if(j > hi) 			
				arr[k] = aux[i++];
			else if(aux[j] < aux[i])
				arr[k] = aux[j++];
			else
				arr[k] = aux[i++];
		}
	}
}

class Quick{
	public static void sort(int arr[]) {
		StdRandom.shuffle(arr);
		sort(arr,0,arr.length-1);
	}
	private static void sort(int arr[],int lo,int hi) {
		if(hi <= lo) {
			return ;
		}
		int j = partition(arr, lo, hi);
		sort(arr, lo, j-1);
		sort(arr, j+1, hi);
	}
	private static int partition(int arr[],int lo,int hi) {
		int key = arr[lo];
		int i = lo;
		int j = hi+1;
		while(true) {
			while(i != hi && arr[++i] < key) {
				//i go ahead
			}
			while(j != lo && arr[--j] > key) {
				//j drop back
			}
			if(i > j)
				break;
			SortTechnology.swapNum(arr,i,j);
		}
		SortTechnology.swapNum(arr, j, lo);
		return j;
	}
}