package examples;

import java.util.Scanner;

public class CircularLinkedList {
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
		
		public int getData() { return this.data; }
		public void setNext(Node node) { this.next = node; }
		public Node getNext() { return this.next; }
	}
	
	private Node tail;
	
	public CircularLinkedList() {		//init
		tail = null;
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
		CircularLinkedList list = new CircularLinkedList();
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
				System.out.println("1. FRONT\t2. LAST");
				System.out.print("Add data to : ");
				choice2 = sc.nextInt();
				System.out.print("Data to insert : ");
				data = sc.nextInt();
				
				if(choice2 == 1)
					list.insertFront(data);
				else if(choice2 == 2)
					list.insertLast(data);
				else
					System.out.println("   Wrong choice. Choose from 1~2.");
				
				break;
			case 2:			//삭제
				list.deleteFront();
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
		
		if(tail == null) {		//list가 비어있으면
			tail = newNode;
			newNode.setNext(newNode);
		} else {				//list에 노드가 있으면
			newNode.setNext(tail.getNext());	//newNode.next = tail.next(첫노드)
			tail.setNext(newNode);				//tail.next는 새로운 노드를 가리킨다
		}
	}
	
	public void insertLast(int data) {		//list의 뒤에 삽입
		Node newNode = new Node(data);		//새로운 노드 생성
		
		if(tail == null) {		//list가 비어있으면
			tail = newNode;
			newNode.setNext(newNode);
		} else {				//list에 노드가 있으면
			newNode.setNext(tail.getNext());	//newNode.next = tail.next(첫노드)
			tail.setNext(newNode);				//tail.next는 새로운 노드를 가리킨다
			tail = newNode;						//newNode가 tail이 된다
		}
	}
	
	//삭제
	public boolean deleteFront() {			//list의 첫 노드 삭제
		if(tail == null) {		//list가 비어있으면
			System.out.println("   No Data.");
			return false;
		} else {				//list가 안 비어있으면
			Node delNode = tail.getNext();		//삭제할 노드(첫 노드)
			
			if(delNode == tail)	//삭제할 노드가 마지막 노드일 때 (노드가 하나일 때)
				tail = null;
			else				//리스트에 2개 이상 노드가 있을 때
				tail.setNext(tail.getNext().getNext());
			return true;
		}
	}
	
	//탐색
	public boolean search(int data) {
		if(tail == null) {		//list가 비어있을 때
			System.out.println("  No Data.");
			return false;
		}
			
		Node current = tail.getNext();	//첫 노드부터 차례대로 탐색
		
		while(current != tail) {		//마지막 노드 전까지 탐색
			if(current.getData() == data) {		//찾는 data와 같은 값이면
				System.out.println("   " + current.getData() + " in the list.");
				return true;
			} else				//찾는 data와 다른 값이면 다음 노드로 이동
				current = current.getNext();
		}
		
		if(current.getData() == data) {	//마지막 노드 탐색
			System.out.println("   " + current.getData() + " in the list.");
			return true;
		}
		
		//찾는 data가 없을 때
		System.out.println("   " + data +" not in the list.");
		return false;
	}
	
	//출력
	public void print() {
		if(tail == null) {			//list가 비어있을 때
			System.out.println("   No Data.");
			return;
		}
		
		Node current = tail.getNext();	//첫노드
		
		while(current != tail) {		//마지막 노드 전까지 출력
			System.out.print(current.getData() + " ");
			current = current.getNext();
		}
		
		System.out.print(current.getData() + " ");	//마지막 노드 출력
		System.out.println();
	}
}