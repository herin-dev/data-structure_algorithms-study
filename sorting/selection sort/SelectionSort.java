package sorting;

public class SelectionSort {
	public static void main(String argc[]) {
		int arr[] = {8, 2, 6, 9, 5};
		
		sorting(arr);	//tjsxor 정렬 소환
		
		for(int i=0; i<arr.length; i++)	//결과 출력
			System.out.print(arr[i] + " ");
	}

	private static void sorting(int[] arr) {
		int minIdx;
		
		for(int i=0; i<arr.length-1; i++) {	//마지막 요소는 자연스럽게 정렬된 상태
			minIdx = i;
			
			for(int j=i+1; j<arr.length; j++) {	//최솟값 찾기
				if(arr[j] < arr[minIdx])
					minIdx = j;
			}
			
			int temp = arr[i];		//최솟값 제일 왼쪽으로 이동
			arr[i] = arr[minIdx];
			arr[minIdx] = temp;
		}
	}
}
