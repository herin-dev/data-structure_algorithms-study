package examples;

import java.util.Scanner;

//Linked List

public class HashChaining {
	public class Person {
		private int ssn;		//생년월일, key
		private String name;	//이름
		private String addr;	//주소
		
		public Person(int ssn, String name, String addr) {
			this.ssn = ssn;
			this.name = name;
			this.addr = addr;
		}
		
		public void setSsn(int ssn) { this.ssn = ssn; }
		public int getSsn() { return this.ssn; }
		public void setName(String name) { this.name = name; }
		public String getName() { return this.name; }
		public void setAddr(String addr) { this.addr = addr; }
		public String getAddr() { return this.addr; }
	}
	
	public class Node {
		private int key;
		private Person person;
		private Node next;
		
		public Node() {				//init
			this.key = -1;
			this.person = null;
			this.next = null;
		}
		
		public Node(Person person) {	//new node			
			this.key = person.ssn;
			this.person = person;
			this.next = null;
		}
		
		public void setPerson(Person person) { this.person = person; }
		public Person getPerson() { return this.person; }
		public void setNext(Node node) { this.next = node; }
		public Node getNext() { return this.next; }
		
		//바로 access 가능하도록
		public int getKey() { return this.key; }
		public int getSSn() { return this.person.getSsn(); }
		public String getName() { return this.person.getName(); }
		public String getAddr() { return this.person.getAddr(); }
	}
	
	private final static int MAX_TBL = 32;	//해시 테이블 크기
	private static Node[] MyTbl;			//해시 테이블
	
	public HashChaining() {				//init
		MyTbl = new Node[MAX_TBL];
		
		for(int i=0; i<MAX_TBL; i++)
			MyTbl[i] = new Node();
	}
	
	public void Menu() {
		System.out.println("\n=======================================================================");
		System.out.print("1. INSERT\t");
		System.out.print("2. DELETE\t");
		System.out.print("3. SEARCH\t");
		System.out.print("4. PRINT TREE\t");
		System.out.println("5. QUIT");
		System.out.println("=======================================================================");
		System.out.print("Choose the action : ");
	}
	
	public static void main(String[] args) {
		HashChaining hc = new HashChaining();
		Scanner sc = new Scanner(System.in);
		
		int ssn = -1;
		String name = null;
		String addr = null;
		int key = -1;
		boolean result = false;
		boolean flag = true;
		
		while(flag) {
			hc.Menu();
			int choice = sc.nextInt();
			
			switch(choice) {	//선택에 따른 함수 호출
			case 1:			//삽입
				System.out.println("Data that already exists will not be inserted.");
				System.out.print("Birth(ex.990831) : ");
				ssn = sc.nextInt();	//삽입할 데이터 이름
				sc.nextLine();
				System.out.print("Name : ");
				name = sc.nextLine();	//삽입할 데이터 이름
				System.out.print("Address : ");
				addr = sc.nextLine();	//삽입할 데이터 이름
				
				hc.insert(ssn, name, addr);
				
				break;
			case 2:			//삭제
				System.out.print("Data to delete(ssn) : ");
				key = sc.nextInt();	//삭제할 데이터
				
				result = hc.delete(key);	//삭제 진행 후 결과
				break;
			case 3:			//탐색
				System.out.print("Data to search(ssn) : ");
				key = sc.nextInt();	//탐색할 데이터
				
				hc.search(key);	//탐색 진행 후 결과
				break;
			case 4:			//출력
				hc.print();
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
	
	//해시 함수
	public int hashing(int key) {			//key값으로 hash(index)값을 계산
		return key%100;						//생일 날짜
	}
	
	//삽입
	public void insert(int ssn, String name, String addr) {
		Person person = new Person(ssn, name, addr);	//Person 만들기
		int key = person.getSsn();					//ssn == key
		int hash = hashing(person.getSsn());		//hash 계산 (테이블의 index 값이 된다)
		
		if(check(key) != null) {			//key가 중복된다면
			System.out.println("Already exist.");
			return;
		}
		
		Node newNode = new Node(person);		//노드 생성
		Node current = MyTbl[hash];				//hash에 해당하는 테이블 첫 노드
		
		if(current.getNext() == null)			//테이블이 비어있다 == 이 hash값에 node가 없다
			MyTbl[hash].setNext(newNode);		//테이블의 첫 node
		else {									//list의 제일 앞에 추가
			newNode.setNext(current.getNext());
			current.setNext(newNode);
		}	
	}
	
	//삭제
	public boolean delete(int key) {
		Node current = MyTbl[hashing(key)];		//MyTbl[hash] 첫번째 Node부터 시작
		
		if(current.getNext() != null ) {		//해당 hash에 노드가 있을 때
			while(current != null) {				//null이 아닐 때까지 == list의 끝까지
				if(current.getNext() == null)		//다음 노드가 null == 마지막 노드, 더 찾을 필요 없다
					break;
				
				if(current.getNext().getKey() == key) {	//current.next == key (다음 node가 지우고자 하는 노드일때)
					Node delNode = current.getNext();	//지우고자 하는 노드
					current.setNext(delNode.getNext());	//current.next = delNode.next (delNode를 지우고 이전 노드와 다음 노드를 연결)
					
					System.out.println("key[" + key + "] deleted.");
					return true;
				}
				
				current = current.getNext();		//다음 노드로
			}
		}
			
		System.out.println("No such data.");
		return false;
	}
	
	//탐색
	public void search(int key) {
		Node result = check(key);		//존재 여부 확인
		
		if(result != null) {			//null이 아니면 정보 출력
			System.out.print("[" + key + "'s info] ");
			System.out.print("Name: " + result.getName());
			System.out.println(", Address: " + result.getAddr());
		} else
			System.out.println("No key[" + key + "]");
	}
	
	public Node check(int key) {		//존재 여부 확인 후, 해당 노드 반환
		int hash = hashing(key);
		
		Node current = MyTbl[hash].getNext();	//해당 hash 테이블 
		
		while(current != null ) {
			if(current.getKey() == key)
				return current;
			
			current = current.getNext();
		}
		
		return null;
	}
	
	//출력
	public void print() {
		for(int i=0; i<MAX_TBL; i++) {
			if(MyTbl[i].getNext() != null) {
				System.out.print(i + "\t");
				
				Node current = MyTbl[i].getNext();
				
				while(current != null) {
					System.out.print(current.getName());
					current = current.getNext();
					
					if(current != null)
						System.out.print(", ");
				}
				System.out.println();
			}
		}
	}
}
