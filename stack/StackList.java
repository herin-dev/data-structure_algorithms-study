import java.util.Scanner;

public class StackList {
	class Node {		//구조체
		private int data;
		public Node next;
		
		public Node() {
			this.data = 0;
			this.next = null;
		}
		
		public Node(int data) {
			this.data = data;
			this.next = null;
		}
		
		public int getData() { return this.data; }
	}
	
	private Node head;
	private int stackSize;
	private int nodeNum;
	
	//init
	public StackList(int size) {
		head = null;
		this.stackSize = size;
		this.nodeNum = 0;
	}
		
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int size = 0;
		while(size <= 0) {			//stack 사이즈
			System.out.print("Input stack size: ");
			size = sc.nextInt();
			
			if(size <= 0)
				System.out.println("Size should be greater than 0.");
		}
		StackList stack = new StackList(size);		//init
		
		boolean flag = true;
		int choice = -1;
		
		while(flag) {
			System.out.println("\n=========================================================");
			System.out.println("1. push   2. pop   3. size   4. empty   5. peek   6. quit");
			System.out.println("=========================================================");
			System.out.print("Choose the action : ");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				System.out.print("Data to push : ");
				stack.push(sc.nextInt());
				break;
			case 2:
				stack.pop();
				break;
			case 3:
				System.out.println("There are " + stack.nodeNum + " elements in Stack.");
				break;
			case 4:
				if(stack.isEmpty())
					System.out.println("There is no element in Queue.");
				else
					System.out.println("There are elements in Queue.");
				break;
			case 5:
				stack.peek();
				break;
			case 6:
				flag = false;
				break;
			default:
				System.out.println("   Wrong choice. Choose from 1~6.");	
			}
		}
		
		System.out.println("===QUIT===");
		
		sc.close();
	}
	
	//데이터 삽입
	public void push(int data) {
		Node newNode = new Node(data);
		
		if(isFull()) {				//스택 다 찼을 경우
			System.out.println("The Stack is full.");
			return;
		} else if(isEmpty()) {		//스택이 비어있을 경우 == 첫 노드
			this.head = newNode;	//새 노드가 head가 된다
		} else {
			newNode.next = this.head;	//새 노드가 현재 head의 앞에 오고 
			this.head = newNode;		//새 노드가 head가 된다
		}
		
		nodeNum++;
		
		System.out.println("push " + data);
	}
	
	//마지막 데이터 추출 (삭제)
	public int pop() {
		if(isEmpty()) {				//비어있을 경우
			System.out.println("No element to pop.");
			return -1;
		}
		
		//안 비어있다면
		Node topNode = this.head;
		this.head = this.head.next;
		
		System.out.println("pop " + topNode.getData());
		
		nodeNum--;
		
		return topNode.getData();
	}
	
	//마지막 데이터 반환(삭제X)
	public int peek() {
		if(isEmpty()) {				//비어있을 경우
			System.out.println("No element to pop.");
			return -1;
		}
		
		//안 비어있다면
		System.out.println("The current top element is " + head.getData());
		
		return head.getData();
	}
	
	//스택 비어있는지 확인
	public boolean isEmpty() {
		if(head == null)
			return true;
		else
			return false;
	}
	
	//스택이 찼는지 확인
	public boolean isFull() {
		if(isEmpty())				//비어있으면
			return false;
		else						//안 비어있을 때
			return ((this.stackSize) == nodeNum);	//현재 노드 수 & 스택 크기 비교
	}
}