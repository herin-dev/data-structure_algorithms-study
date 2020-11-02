package sorting;

public class BubbleSort {
	public static void main(String argc[]) {
		int arr[] = {6,3,7,9,2};
		
		sorting(arr);	//버블 정렬 소환
		
		for(int i=0; i<arr.length; i++)	//결과 출력
			System.out.print(arr[i] + " ");
	}
	
	public static void sorting(int arr[]) {
		for(int i=0; i<arr.length; i++) {		//n회전
			for(int j=0; j<arr.length-i-1; j++) {	//인접한 요소 비교
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}
}