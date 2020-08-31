package examples;

import java.util.Scanner;

//리스트로 구현

public class BinarySearchTree {
	public class Node {			//Node라는 구조체
		private int data;
		private Node left;
		private Node right;
		
		public Node(int data) {
			this.setData(data);
			setLeft(null);
			setRight(null);
		}
		
		public void setData(int data) {
			this.data = data;
		}
		
		public int getData() {
			return data;
		}
		
		public void setLeft(Node left) {
			this.left = left;
		}
		
		public Node getLeft() {
			return left;
		}
		
		public void setRight(Node right) {
			this.right = right;
		}
		
		public Node getRight() {
			return right;
		}
	}
	
	public Node root;			//루트 node
	public BinarySearchTree() {	//init, root 초기화
		this.root = null;
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
		BinarySearchTree bst = new BinarySearchTree();
		Scanner sc = new Scanner(System.in);
		
		int data = 0;
		boolean result = false;
		boolean flag = true;
		
		while(flag) {		//반복
			bst.Menu();		//메뉴 출력
			int choice = sc.nextInt();
			
			switch(choice) {	//선택에 따른 함수 호출
			case 1:			//삽입
				System.out.println("Data that already exists will not be inserted.");
				System.out.print("Data to insert(int) : ");
				data = sc.nextInt();	//삽입할 데이터
				
				bst.insert(data);
				
				break;
			case 2:			//삭제
				System.out.print("Data to delete(int) : ");
				data = sc.nextInt();	//삭제할 데이터
				
				result = bst.delete(data);	//삭제 진행 후 결과
				if(result)				//result == true
					System.out.println("   " + data + " has deleted.");
				else					//result == false
					System.out.println("   No such data.");
				
				break;
			case 3:			//탐색
				System.out.print("Data to search(int) : ");
				data = sc.nextInt();	//탐색할 데이터
				
				result = bst.search(data);	//탐색 진행 후 결과
				if(result)				//result == true
					System.out.println("   " + data + " is in the tree.");
				else					//result == false
					System.out.println("   No such data.");
				
				break;
			case 4:			//출력
				System.out.println("1. inorder\t2. preorder\t3.postorder");
				System.out.print("Choose the way to print : ");
				int choice2 = sc.nextInt();	//출력 방식 선택
				
				if(choice2 == 1)		//inorder
					bst.inorderTraverse(bst.root);
				else if(choice2 == 2)	//preorder
					bst.preorderTraverse(bst.root);
				else if(choice2 == 3)	//postorder
					bst.postorderTraverse(bst.root);
				else					//1,2,3 이외의 선택
					System.out.println("   Wrong choice. Choose from 1~3.");
				
				System.out.println();
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
	public void insert(int data) {
		Node newNode = new Node(data);	//insert하고자 하는 node
		
		if(root == null) {			//첫 노드라면 루트에 삽입
			root = newNode;
			return;
		}
		
		Node parent = null;
		Node current = root;		//root부터 위치 탐색하기 때문에
		
		//insert할 위치 탐색
		while(true) {
			if(data == current.getData())		//중복 허용하지 않는다
				return;
			
			parent = current;		//다음 노드로 이동
			
			if(data < current.getData()) {		//current보다 작은 값이면
				current = current.getLeft();	//왼쪽서브트리로 이동
				
				if(current == null) {			//왼쪽서브트리가 null이면 삽입
					parent.setLeft(newNode);
					return;
				}
			} else if(data > current.getData()) {	//current보다 큰 값이면
				current = current.getRight();		//오른쪽서브트리로 이동
				
				if(current == null) {				//오른쪽서브트리가 null이면 삽입
					parent.setRight(newNode);
					return;
				}
			}
		}
	}
	
	//삭제
	public boolean delete(int target) {
		Node parent = root;
		Node current = root;
		boolean isLeftChild = true;
		
		while(current.getData() != target) {	//삭제대상 탐색
			parent = current;
			
			if(target < current.getData()) {
				isLeftChild = true;
				current = current.getLeft();
			} else {
				isLeftChild = false;
				current = current.getRight();
			}
			
			if(current == null)		//target이 존재하지 않는다면
				return false;
		}
		
		Node delete = current;	//delete할 대상 node
		
		//case1 : 삭제대상이 자식노드가 없을 때
		if(delete.getLeft() == null && delete.getRight() == null) {
			if(delete == root)
				root = null;
			else if(isLeftChild)
				parent.setLeft(null);
			else
				parent.setRight(null);
		}
		
		//case2 : 삭제대상이 자식노드가 하나일 때
		//case2-1 : 삭제대상이 왼쪽 자식노드만 있는 경우
		else if(delete.getRight() == null) {
			if(delete == root)
				root = delete.getLeft();
			else if(isLeftChild)
				parent.setLeft(delete.getLeft());
			else
				parent.setRight(delete.getLeft());
		}
		//case2-2 : 삭제대상이 오른쪽 자식노드만 있는 경우
		else if(delete.getLeft() == null) {
			if(delete == root)
				root = delete.getRight();
			else if(isLeftChild)
				parent.setLeft(delete.getRight());
			else
				parent.setRight(delete.getRight());
		}
				
		//case3 : 자식노드가 두개일 때 (오른쪽 서브트리의 최소값으로 대체)
		else if(delete.getLeft() != null && delete.getRight() != null) {
			Node replacement = getRightSubMinNode(delete);
			
			if(delete == root) 
				root = replacement;
			else if(isLeftChild)		//삭제대상이 부모의 왼쪽이었으면 대체노드를 왼쪽에 연결
				parent.setLeft(replacement);
			else						//삭제대상이 부모의 오른쪽이었으면 대체노드를 오른쪽에 연결
				parent.setRight(replacement);
			
			replacement.setLeft(delete.getLeft());	//삭제대상의 왼쪽서브트리를 대체노드에 연결
		}
		
		// 또다른 방법으로 case3구현
//		else if(current.getLeft() != null && current.getRight() != null){
//		//step1 : 삭제 대상을 대체할 노드 찾기
//		Node replacement = getRightSubMinNode(delete);
//		Node repParent = delete;
//		
//		//step2 : 대체할 노드에 저장된 값을 삭제할 노드에 대입
//		//노드를 대입하지 않고 값을 대입하는 이유는 단말노드가 아닌 오른쪽자식노드가 있을 경우를 대비
//		int delData = delete.getData();		//삭제대상 데이터 백업
//		delete.setData(replacement.getData());	//삭제대상 노드에 대체노드 값 대입
//		
//		//step3 : 대체할 노드의 부모와 자식을 연결
//		if(repParent.getLeft() == replacement)	//대체노드가 부모의 왼쪽서브트리일 때
//			repParent.setLeft(replacement.getRight());
//		else			//대체노드가 부모의 오른쪽서브트리일 때
//			repParent.setRight(replacement.getRight());
//		}
		
		return true;
	}
	
	public Node getRightSubMinNode(Node deleteNode) {
		Node replacement = null;
		Node repParent = null;
		Node current = deleteNode.getRight();
		
		while(current != null) {		//왼쪽 서브트리의 마지막 노드가 가장 작은 값
			repParent = replacement;
			replacement = current;
			current = current.getLeft();	//다음 왼쪽 서브트리로 내려간다
		}
			
		if(replacement != deleteNode.getRight()) {	//replacement가 왼쪽서브트리였다면
			repParent.setLeft(replacement.getRight());		//대체노드의 오른쪽서브트리를 부모에 연결
			replacement.setRight(deleteNode.getRight());	//삭제대상의 오른쪽서브트리를 대체노드에 연결
		}
		
		return replacement;
	}
	
	//탐색
	public boolean search(int target) {
		Node current = root;
		
		while(current != null) {
			if(target == current.getData())		//찾음
				return true;
			else if(target < current.getData())	//target이 current보다 작으면 왼쪽
				current = current.getLeft();
			else					//target이 current보다 크면 오른쪽
				current = current.getRight();
		}
		
		return false;
	}
	
	//출력
	public void inorderTraverse(Node root) {		//오름차순으로 출력 가능
		if(root == null)
			return;
		
		inorderTraverse(root.getLeft());
		System.out.print(root.getData() + " ");
		inorderTraverse(root.getRight());
	}
	
	public void preorderTraverse(Node root) {
		if(root == null)
			return;
		
		System.out.print(root.getData() + " ");
		inorderTraverse(root.getLeft());
		inorderTraverse(root.getRight());
	}
	
	public void postorderTraverse(Node root) {
		if(root == null)
			return;
		
		inorderTraverse(root.getLeft());
		inorderTraverse(root.getRight());
		System.out.print(root.getData() + " ");
	}
}
