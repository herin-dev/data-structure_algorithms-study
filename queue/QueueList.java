import java.util.Scanner;

public class QueueList {
	class Node {		//구조체
		private int data;
		public Node next;
		
		public Node(int data) {
			this.data = data;
			this.next = null;
		}
		
		public int getData() { return this.data; }
	}
	
	private Node front = null;
	private Node rear = null;
	private int queueSize, currentSize = 0;
	
	//init
	public QueueList(int size) {
		front = null;
		rear = null;
		this.queueSize = size;
	}
		
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int size = 0;
		while(size <= 0) {			//queue 사이즈
			System.out.print("Input queue size: ");
			size = sc.nextInt();
			
			if(size <= 0)
				System.out.println("Size should be greater than 0.");
		}
		QueueList queue = new QueueList(size);		//init
		
		boolean flag = true;
		int choice = -1;
		
		while(flag) {
			System.out.println("\n====================================================================");
			System.out.println("1. push   2. pop   3. size   4. empty   5. front   6. rear   7. quit");
			System.out.println("====================================================================");
			System.out.print("Choose the action : ");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				System.out.print("Data to push : ");
				queue.push(sc.nextInt());
				break;
			case 2:
				queue.pop();
				break;
			case 3:
				System.out.println("There are " + queue.currentSize + " elements in Queue.");
				break;
			case 4:
				if(queue.isEmpty())
					System.out.println("There is no element in Queue.");
				else
					System.out.println("There are elements in Queue.");
				break;
			case 5:
				queue.front();
				break;
			case 6:
				queue.rear();
				break;
			case 7:
				flag = false;
				break;
			default:
				System.out.println("   Wrong choice. Choose from 1~7.");	
			}
		}
		
		System.out.println("\n===QUIT===");
		sc.close();
	}

	//데이터 삽입
	public void push(int data) {
		Node newNode = new Node(data);
		
		if(isFull()) {
			System.out.println("The Queue is full.");
			return;
		} else if(isEmpty()) {		//queue가 비어있으면
			front = newNode;		//front와 rear 둘 다 newNode 가르킨다
			rear = newNode;
		} else {
			rear.next = newNode;	//현재 rear의 next가 newNode를 가르키고
			rear = newNode;			//rear은 newNode를 가르킨다
		}
		
		System.out.println("push " + data);
		currentSize += 1;
	}
	
	//첫번째 데이터 추출 (삭제)
	public int pop() {
		if(isEmpty()) {				//비어있을 경우
			System.out.println("No element to pop.");
			return -1;
		}
		
		//안 비어있다면
		Node delNode = this.front;	//첫 노드가 삭제되고
		this.front = this.front.next;	//그 다음노드가 front가 된다
		
		currentSize -= 1;
		
		System.out.println("pop " + delNode.getData());
		
		return delNode.getData();
	}
	
	//queue 비어있는지 확인
	public boolean isEmpty() {
		return (front == null);
	}
	
	//queue 찼는지 확인
	public boolean isFull() {
		if(isEmpty())				//비어있으면
			return false;
		else						//안 비어있을 때
			return(currentSize == queueSize);
	}
	
	//첫번째 데이터 확인 (삭제X)
	public int front() {
		if(isEmpty()) {				//비어있을 경우
			System.out.println("No element in queue.");
			return -1;
		}
		
		//안 비어있을 경우
		System.out.println("The first element is " + front.getData());
		
		return front.getData();
	}
	
	//마지막 데이터 확인 (삭제X)
	public int rear() {
		if(isEmpty()) {				//비어있을 경우
			System.out.println("No element in queue.");
			return -1;
		}
		
		//안 비어있을 경우
		System.out.println("The last element is " + rear.getData());
		
		return rear.getData();
	}
}