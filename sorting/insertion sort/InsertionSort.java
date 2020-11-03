public class InsertionSort {
	public static void main(String argc[]) {
		int arr[] = {8, 2, 6, 9, 5};
		
		sorting(arr);	//삽입 정렬 소환
		
		for(int i=0; i<arr.length; i++)	//결과 출력
			System.out.print(arr[i] + " ");
	}
	
	public static void sorting(int arr[]) {	//오름차순
		int i, j;
		
		for(i=1; i<arr.length; i++) {
			int target = arr[i];
			
			for(j=i-1; j>=0; j--) {
				if(arr[j] > target)
					arr[j+1] = arr[j];		//한 칸 뒤로
				else
					break;
			}
			
			arr[j+1] = target;		//삽입
		}
	}
}
