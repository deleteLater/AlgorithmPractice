package per.Algotithms;

import java.util.Arrays;

public class SearchTechnology {
	public static int BinarySearch(int key,int arr[]) {
		Arrays.sort(arr);
		int lo = 0;
		int hi = arr.length;
		int mid = 0;
		while(hi > lo) {
			mid = (lo + hi) /2;
			if(key < arr[mid])
				hi = mid-1;
			else if(key > arr[mid])
				lo = mid+1;
			else 
				return mid;
		}
		return -1;
	}
}
