package sorting;

//import sorting.HeapSort_heapClass;		//같은 package에 있어 import가 필요없다

public class HeapSort {
	public static final int ArrNum = 6;
	
	public static void main(String argc[]) {
		int arr[] = {8, 2, 6, 9, 5, 11};
		
		sorting(arr);	//병합 정렬 소환
		
		for(int i=0; i<arr.length; i++)	//결과 출력
			System.out.print(arr[i] + " ");
	}
	
	public static void sorting(int arr[]) {

	}
}
