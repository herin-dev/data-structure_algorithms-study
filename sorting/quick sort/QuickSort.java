package sorting;

public class QuickSort {
	public static void main(String argc[]) {
		int arr[] = {5,6,7,2,16,80,-20,15,8,6};
		
		sorting(arr, 0, arr.length-1);		//배열, left, right
		
		for(int i=0; i<arr.length; i++)	//결과 출력
			System.out.print(arr[i] + " ");
	}
	
	public static void sorting(int arr[], int left, int right) {
		int pivot = partition(arr, left, right);	//pivot을 기준으로 나누기 
		
		//새로운 pivot을 기준으로 divide
		if(left < pivot-1)
			sorting(arr, left, pivot-1);
		
		if(pivot < right)
			sorting(arr, pivot, right);
	}
	
	//pivot을 기준으로 우선순위가 낮은 쪽, 높은 쪽으로 나누기
	public static int partition(int arr[], int left, int right) {
		int pivot = arr[(left+right)/2];	//배열의 중간
		
		while(left <= right) {
			while(arr[left] < pivot)	//pivot보다 큰 값을 찾는
				left++;
			
			while(arr[right] > pivot)	//pivot보다 작은 값을 찾는
				right--;
			
			if(left <= right) {			//교차되지 않았다면 값 교환
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				
				left++;
				right--;
			}
		}
		
		//pivot이 left로 바뀜
		return left;
	}
	
//	public static void swap(int arr[], int idx1, int idx2) {
//		int temp = arr[idx1];
//		arr[idx1] = arr[idx2];
//		arr[idx2] = temp;
//	}
}
