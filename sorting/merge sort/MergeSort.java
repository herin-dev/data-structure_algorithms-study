package sorting;

public class MergeSort {
	public static final int ArrNum = 6;
	
	public static void main(String argc[]) {
		int arr[] = {8, 2, 6, 9, 5, 11};
		
		sorting(arr, 0, arr.length-1);	//병합 정렬 소환
		
		for(int i=0; i<arr.length; i++)	//결과 출력
			System.out.print(arr[i] + " ");
	}
	
	//분할
	public static void sorting(int arr[], int left, int right) {
		int mid = -1;		//중간 지점
		
		if(left < right) {
			mid = (left + right) / 2;	//중간 지점 계간
			
			sorting(arr, left, mid);	//앞 영역에 대한 분할
			sorting(arr, mid+1, right);	//뒷 영역에 대한 분할
			
			merge(arr, left, mid, right);	//정렬과 병합
		}
		
		//else : data가 하나인 경우  
	}
	
	//정렬 및 병합 
	public static void merge(int arr[], int left, int mid, int right) {
		int fIdx = left;	//앞 영역의 시작 위치
		int bIdx = mid + 1;	//뒷 영역의 시작 위치
		int curIdx = left;
		
		int sorted[] = new int[ArrNum];		//임시배열
		
		//1차병합
		while(fIdx <= mid && bIdx <= right) {
			if(arr[fIdx] <= arr[bIdx])
				sorted[curIdx] = arr[fIdx++];
			else
				sorted[curIdx] = arr[bIdx++];
			
			curIdx++;
		}
		
		//2차병합 : 남은 데이터 병합
		if(fIdx > mid) {	//뒷 영역이 남았을 때
			for(int i=bIdx; i<=right; i++, curIdx++)
				sorted[curIdx] = arr[i];
		} else {			//앞 영역이 남았을 때
			for(int i=fIdx; i<=mid; i++, curIdx++)
				sorted[curIdx] = arr[i];
		}
		
		//원래 배열에 복사
		for(int i=left; i<=right; i++)
			arr[i] = sorted[i];
	}
}
