package sorting;

import java.util.Scanner;

public class HeapSort {
	private static int numData;
	
	static int[] heapArr;		//index 0은 사용 안 함
	
	public static boolean priority(int fisrt, int second) {
		//Max Heap, 내림차순
		if(fisrt > second)
			return true;
		else
			return false;
		
		//Min Heap, 오름차순
//		if(fisrt < second)
//			return true;
//		else
//			return false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Input heap size: ");
		int HEAP_LEN = sc.nextInt();
		heapArr = new int[HEAP_LEN+1];
		
		init();
		
		System.out.println("insert n / delete / size / quit\t");
		
		boolean flag = true;
		
		while(flag) {
			switch(sc.next()) {
			case "insert":
				insert(sc.nextInt(), HEAP_LEN);
				break;
			case "delete":
				delete();
				break;
			case "size":
				if(isEmpty())
					System.out.println("   There is no element in Heap.");
				else
					System.out.println("   There are " + numData + "elements in Heap.");
				break;
			case "quit":
				flag = false;
				break;
			default:
				System.out.println("   Wrong command");
			}
			
			if(!isEmpty()) {
				System.out.print("   RESULT : ");
				print(heapArr, HEAP_LEN);
			}
			else
				System.out.println("   No data to print out.");
		}
		
		System.out.println("***QUIT");
		sc.close();
	}

	public static void insert(int newData, int HEAP_LEN) {
		if(numData == HEAP_LEN) {
			System.out.println("   Heap is already full.");
			return;
		}
		
		int idx = numData + 1;			//마지막 데이터 다음
		heapArr[idx] = newData;			//마지막 노드에 데이터 임시 삽입
		
		//임시로 마지막에 추가된 노드의 맞는 위치 탐색
		while(idx != 1) {				//루트까지 올라가며 위치 탐색
			//내림차순(오름차순)
			if(priority(heapArr[idx], heapArr[GetParentIdx(idx)])) {	//부모보다 값이 크면(작으면) 부모와 바꿈
				int temp = heapArr[idx];
				heapArr[idx] = heapArr[GetParentIdx(idx)];
				heapArr[GetParentIdx(idx)] = temp;
				
				idx = GetParentIdx(idx);
			}
			else			//부모보다 작으면(크면) 맞는 위치
				break;
		}
		
		numData += 1;
	}
	
	public static int delete() {
		if(isEmpty()) {
			System.out.println("No data in the Heap.");
			return -1;
		}
		
		int parentIdx = 1;
		int childIdx = GetHigherIdx(1);
		
		int delData = heapArr[1];			//루트 값 임시저장
		heapArr[1] = heapArr[numData];	//루트 삭제 == 루트에 마지막 노드 삽입
		heapArr[numData] = 0;			//마지막 노드 초기화
		
		//임시로 루트에 대신 들어간 마지막 노드의 맞는 위치 탐색
		while(heapArr[GetHigherIdx(parentIdx)] != 0) {		//마지막 노드까지 내려가며 위치 탐색
			childIdx = GetHigherIdx(parentIdx);
			
			//내림차순(오름차순)
			if(!priority(heapArr[parentIdx], heapArr[childIdx])) {			//자식보다 값이 작으면(크면) 자식과 바꿈
				int temp = heapArr[parentIdx];
				heapArr[parentIdx] = heapArr[childIdx];
				heapArr[childIdx] = temp;

				parentIdx = childIdx;
			}
			else			//자식보다 값이 크면(작으면) 맞는 위치
				break;
		}
		
		numData -= 1;
		
		return delData;			//삭제된 루트값 반환
	}

	
	public static void init() {		//힙 초기화
		numData = 0;
	}
	
	public static boolean isEmpty() {		//비어있는지 확인
		if(numData == 0)
			return true;
		else
			return false;
	}
	
	public static int GetParentIdx(int idx) {		//부모노드
		return idx/2;
	}
	
	public static int GetLeftIdx(int idx) {			//왼쪽
		return idx*2;
	}
	
	public static int GetRightIdx(int idx) {		//오른쪽
		return idx*2+1;
	}
	
	public static int GetHigherIdx(int idx) {	//높은 우선순위의 자식 index 반환
		if(GetLeftIdx(idx) > numData)			//왼쪽 자식이 없다 == 자식 노드가 없을 때
			return 0;
		else if(GetLeftIdx(idx) == numData)		//왼쪽 자식이 마지막 노드다 == 마지막 노드일 때
			return GetLeftIdx(idx);
		else {									//자식 노드가 두개 다 있을 때, 둘 중 우선순위 높은 index 반환
			if(priority(heapArr[GetRightIdx(idx)], heapArr[GetLeftIdx(idx)]))	
				return GetRightIdx(idx);
			else
				return GetLeftIdx(idx);
		}
	}
	
	public static void print(int[] heapArr, int HEAP_LEN) {
		int data = -1;
		
		for(int i=1; i<HEAP_LEN+1; i++) {
			data = heapArr[i];
			if(data != 0)
				System.out.print(heapArr[i] + " ");
		}
		
		System.out.println();
	}
}
