package examples;

import java.util.Scanner;

//앞에 삽입하는 LinkedList

public class DoubleLinkedList {
	class Node {
		private int data;
		private Node next;
		private Node prev;
		
		public Node() {
			this.data = 0;
			this.next = null;
			this.prev = null;
		}
		
		public Node(int data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
		
		public int getData() { return this.data; }
		public void setNext(Node node) { this.next = node; }
		public Node getNext() { return this.next; }
		public void setPrev(Node node) { this.prev = node; }
		public Node getPrev() { return this.prev; }
	}
	
	private Node head;			//list의 head(첫 노드)
	private Node tail;
	private int nodeNum;		//list에 있는 node 수
	
	public DoubleLinkedList() {		//init
		head = null;
		tail = null;
		nodeNum = 0;
	}
	
	public void Menu() {
		System.out.println("\n=======================================================================");
		System.out.print("1. INSERT\t");
		System.out.print("2. DELETE\t");
		System.out.print("3. SEARCH\t");
		System.out.print("4. PRINT\t");
		System.out.println("5. QUIT");
		System.out.println("=======================================================================");
		System.out.print("Choose the action : ");
	}
	
	public static void main(String[] argc) {
		DoubleLinkedList list = new DoubleLinkedList();
		Scanner sc = new Scanner(System.in);
		
		int data = -1;
		boolean flag = true;
		int choice = 0;
		int choice2 = 0;
		int index = 0;
		
		while(flag) {
			list.Menu();
			choice = sc.nextInt();
			
			switch(choice) {
			case 1: 		//삽입
				System.out.println("1. FRONT\t2. MIDDLE\t3. LAST");
				System.out.print("Add data to : ");
				choice2 = sc.nextInt();
				System.out.print("Data to insert : ");
				data = sc.nextInt();
				
				if(choice2 == 1)
					list.insertFront(data);
				else if(choice2 == 2) {
					System.out.print("Index to insert : ");
					index = sc.nextInt();
					list.insertMid(index, data);
				} else if(choice2 == 3)
					list.insertLast(data);
				else
					System.out.println("   Wrong choice. Choose from 1~3.");
				
				break;
			case 2:			//삭제
				System.out.println("1. FRONT\t2. MIDDLE\t3. LAST");
				System.out.print("delete data from : ");
				choice2 = sc.nextInt();
				
				if(choice2 == 1)
					list.deleteFront();
				else if(choice2 == 2) {
					System.out.print("Index to delete : ");
					index = sc.nextInt();
					list.deleteMid(index);
				} else if(choice2 == 3)
					list.deleteLast();
				else
					System.out.println("   Wrong choice. Choose from 1~3.");
				
				break;
			case 3:			//탐색
				System.out.print("Data to search : ");
				data = sc.nextInt();	//탐색할 데이터
				
				list.search(data);	//탐색 진행 후 결과
				break;
			case 4:			//출력
				list.print();
				break;
			case 5:			//프로그램 종료
				flag = false;
				break;
			default :		//1,2,3,4,5 이외의 선택
				System.out.println("   Wrong choice. Choose from 1~5.");
			}
		}
		
		System.out.println("\n===FINISH PROGRAM===");
	}
	
	//삽입
	public void insertFront(int data) {		//list의 앞에 삽입
		Node newNode = new Node(data);		//새로운 노드 생성
		
		newNode.setNext(head);	//newNode.next가 현재 head를 가리킨다
		if(head != null)		//list가 비어있는 상태가 아니라면
			head.setPrev(newNode);	//list의 첫노드의 prev를 newNode로 연결
		
		newNode.setPrev(null);	//newNode는 첫노드라서 prev가 없다
		head = newNode;			//newNode가 head가 된다
		
		nodeNum++;		//node 수 증가
		
		if(head.getNext() == null)	//node가 하나 있을 때, 
			tail = head;			//head와 tail은 같은 노드를 가르킨다
	}
	
	public void insertLast(int data) {		//list의 뒤에 삽입
		Node newNode = new Node(data);		//새로운 노드 생성
		
		if(nodeNum == 0)			//list가 비어있으면
			insertFront(data);		//앞에 추가하는 것과 같음
		else {
			tail.setNext(newNode);	//현재 tail.next가 newNode가 되고
			newNode.setPrev(tail);	//newNode.prev는 현재 tail이 되고
			tail = newNode;			//newNode가 tail이 된다
			
			nodeNum++;		//node 수 증가
		}
	}
	
	public void insertMid(int index, int data) {	//index: 삽입할 위치
		Node newNode = new Node(data);
		
		if(index == 1)		//index가 1이라면 list의 앞에 삽입
			insertFront(data);
		else {
			if(index > nodeNum+1) {		//list의 노드 수보다 크면
				System.out.println("Now we have " + nodeNum + " nodes.");
				return;
			}
			
			Node preNode = findPlace(index-1);	//삽입할 위치의 이전 노드
			
			newNode.setNext(preNode.getNext());	//newNode.next = preNode.next
			newNode.setPrev(preNode);			//newNode.prev = preNode
			preNode.getNext().setPrev(newNode);	//preNode.next.prev = newNode
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
			head.setPrev(null);		//새로운 head의 prev를 null로
			
			nodeNum--;
			
			if(nodeNum == 0)		//삭제 후 노드가 0개면
				tail = null;
			
			return true;
		}
	}
	
	public boolean deleteLast() {
		if(head == null) {	//list가 비어있으면
			System.out.println("No Data.");
			return false;
		} else {			//list가 안 비어있으면
			tail = tail.getPrev();	//tail가 현재 tail.prev를 가리킨다
			tail.setNext(null);		//새로운 tail의 next를 null로
			
			nodeNum--;
			
			if(nodeNum == 0)		//삭제 후 노드가 0개면
				tail = null;
			
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
			
			if(delNode != tail)			//삭제할 노드가 tail이 아니면 
				delNode.getNext().setPrev(preNode);	//삭제할 노드의 뒷노드의 prev를 삭제할 노드의 앞노드와 연결
			else						//삭제할 노드가 tail이면
				tail = preNode;			//새로운 tail 지정
			
			nodeNum--;
			
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