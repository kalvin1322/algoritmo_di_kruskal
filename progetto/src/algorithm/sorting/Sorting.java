package src.algorithm.sorting;

/**
 * This class contains various sorting algorithms
 */
public class Sorting {

	private static <T extends Comparable<T>> void swap(T A[], int i, int j) {
		T tmp = A[i];
		A[i]  = A[j];
		A[j]  = tmp;
	}
	


	/**
	 * Sorts the specified array according to the ordering induced by the compareTo() method in &Theta;(nlogn)
	 * <P>
	 * Implements the mergesort algorithm.
	 * <ul>
	 * <li> Worst/Average/Best-case cost: &Theta;(nlogn)
	 * </ul>
	 * @param A the array to be sorted
	 * @param <T> class of the object in the array
	 */
	public static <T extends Comparable<T>> void mergesort(T A[]) {	
		sort2(A, 0, A.length-1);
	}
	public static<T extends Comparable<T>> void sort2(T A[], int p, int r){
		if(p<r){
			int q = (p+r)/2;
			sort2(A, p, q);
			sort2(A, q+1, r);
			merge(A, p, q, r);
		}
	}

public static <T extends Comparable<T>> void merge(T A[], int p, int q, int r){
	T[] B;
    T[] tmp = (T[]) new Comparable[r-p+1];
    B = tmp;
	int i=p;
	int j=q+1;
	int k=0;
	
	
	while(i<=q && j<=r){
		
		if(A[i].compareTo(A[j])<0){
			B[k]=A[i];
			i++;
		}
		else{
			B[k]=A[j];
			j++;
		}
		k++;
	}	
	while(i<=q){
		B[k]=A[i];
		k++;
		i++;
	}
	while(j<=r){
		B[k]=A[j];
		k++;
		j++;
	}
	for(int y=0;y<r-p+1;y++){
		A[p+y]=B[y];
	}

}				
}