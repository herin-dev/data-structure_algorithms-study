package examples;

//앞에 삽입하는 LinkedList

public class SingleLinkedList {
	class Node {
		private int data;
		private Node next;
		
		public Node() {
			this.data = 0;
			this.next = null;
		}
		
		public Node(int data) {
			this.data = data;
			this.next = null;
		}
		
		public int getData() {
			return this.data;
		}
		
		public void setNext(Node node) {
			this.next = node;
		}
		
		public Node getNext() {
			return this.next;
		}
	}
	
	private Node head;			//list의 head(첫 노드)
	private Node tail;			//list의 tail(마지막 노드)
	private int nodeNum;		//list에 있는 node 수
	
	public SingleLinkedList() {		//init
		head = null;
		tail = null;
		nodeNum = 0;
	}
	
	public static void main(String[] argc) {
		SingleLinkedList list = new SingleLinkedList();
		
		System.out.print("[PRINT] ");
		list.print();
		
		System.out.print("[INSERT FRONT 2] ");
		list.insertFront(2);
		list.print();
		
		System.out.print("[INSERT FRONT 1] ");
		list.insertFront(1);
		list.print();
		
		System.out.print("[INSERT LAST 4] ");
		list.insertLast(4);
		list.print();
		
		System.out.print("[SEARCH 3] ");
		list.search(3);
		
		System.out.print("[INSERT MID 3] ");
		list.insertMid(3, 3);
		list.print();
		
		System.out.print("[SEARCH 3] ");
		list.search(3);
		
		System.out.print("[DELETE MID 4] ");
		list.deleteMid(4);
		
		System.out.print("[DELETE FRONT] ");
		list.deleteFront();
		list.print();
		
		System.out.print("[DELETE MID 5] ");
		list.deleteMid(5);
	}
	
	//삽입
	public void insertFront(int data) {		//list의 앞에 삽입
		Node newNode = new Node(data);		//새로운 노드 생성
		
		newNode.setNext(head);	//newNode.next가 현재 head를 가르키고
		head = newNode;			//newNode가 head가 된다
		
		nodeNum++;		//node 수 증가
		
		if(head.getNext() == null)	//node가 하나 있을 때, 
			tail = head;			//head와 tail은 같은 노드를 가르킨다
	}
	
	public void insertLast(int data) {		//lise의 뒤에 삽입
		Node newNode = new Node(data);		//새로운 노드 생성
		
		if(nodeNum == 0)			//list가 비어있으면
			insertFront(data);		//앞에 추가하는 것과 같음
		else {
			tail.setNext(newNode);	//현재 tail.next가 newNode가 되고
			tail = newNode;			//newNode가 tail이 된다
			
			nodeNum++;		//node 수 증가
		}
	}
	
	public void insertMid(int index, int data) {	//index: 삽입할 위치
		if(index == 1)		//index가 1이라면 list의 앞에 삽입
			insertFront(data);
		else {
			if(index > nodeNum+1) {		//list의 노드 수보다 크면
				System.out.println("Now we have " + nodeNum + " nodes.");
				return;
			}
			
			Node preNode = findPlace(index-1);	//삽입할 위치의 이전 노드
			Node newNode = new Node(data);
			
			newNode.setNext(preNode.getNext());	//newNode.next = preNode.next
			preNode.setNext(newNode);			//preNode.next = newNode
			
			nodeNum++;
			
			if(newNode.getNext() == null)		//newNode.next가 null이라면,
				tail = newNode;					//newNode가 tail이 된다
		}
	}
	
	public Node findPlace(int index) {		//index의 이전 노드를 반환
		Node current = head;
		
		for(int i=1; i<index; i++)			//node를 처음부터 훑으면서 탐색
			current = current.getNext();
		
		return current;
	}
	
	//삭제
	public boolean deleteFront() {			//list의 첫 노드 삭제
		if(head == null) {	//list가 비어있으면
			System.out.println("No Data.");
			return false;
		} else {			//list가 안 비어있으면
			head = head.getNext();	//head가 현재 head.next를 가르킨다
			nodeNum--;
			return true;
		}
	}
	
	public boolean deleteMid(int index) {
		if(index == 1)		//첫 노드 삭제
			deleteFront();
		else {
			if(index > nodeNum) {		//list의 노드 수보다 크면
				System.out.println("we have " + nodeNum + " nodes.");
				return false;
			}
			
			Node preNode = findPlace(index-1);	//삽입할 위치의 이전 노드
			Node delNode = preNode.getNext();	//삭제할 노드
			
			preNode.setNext(delNode.getNext());	//삭제할 노드의 앞노드의 next를 삭제할 노드의 next와 연결
			
			nodeNum--;
			
			if(delNode == tail)
				tail = preNode;
			
			delNode = null;		//데이터 삭제
			
			return true;
		}
		
		return false;
	}
	
	//탐색
	public boolean search(int data) {
		if(head == null) {		//list가 비어있을 때
			System.out.println("No Data.");
			return false;
		}
			
		Node current = head;	//head부터 차례대로 탐색
		
		while(current != null) {
			if(current.getData() == data) {		//찾는 data와 같은 값이면
				System.out.println(current.getData() + " in the list.");
				return true;
			} else				//찾는 data와 다른 값이면 다음 노드로 이동
				current = current.getNext();
		}
		
		//찾는 data가 없을 때
		System.out.println(data +" not in the list.");
		return false;
	}
	
	//출력
	public void print() {
		if(head == null) {		//head == null
			System.out.println("No Data.");
			return;
		}
		
		Node current = head;
		
		while(current != null) {
			System.out.print(current.getData() + " ");
			current = current.getNext();
		}
		System.out.println();
	}
}