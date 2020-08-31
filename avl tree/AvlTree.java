package examples;

public class AvlTree {
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
	public AvlTree() {			//init, root 초기화
		root = null;
	}
	
	
	public static void main(String[] args) {
		AvlTree avl = new AvlTree();
		
//		avl.root = avl.insert(avl.root, 4);
//		avl.root = avl.insert(avl.root, 10);
//		avl.root = avl.insert(avl.root, 15);
		
//		System.out.println("루트노드 : " + avl.root.getData());
//		System.out.println("왼쪽 : " + avl.root.getLeft().getData());
//		System.out.println("오른쪽 : " + avl.root.getRight().getData() + "\n");	
		
		
		Node currentL;
		Node currentR;

		//삽입확인
//		System.out.println("insert1");
//		avl.root = avl.insert(avl.root, 1);
//		System.out.println("루트노드 : " + avl.root.getData());
//		System.out.println("왼쪽 : " + avl.root.getLeft() + ", 오른쪽 : " + avl.root.getRight() + "\n");
//		
//		System.out.println("insert2");
//		avl.root = avl.insert(avl.root, 2);
//		currentR = avl.root.getRight();
//		System.out.println("루트노드 : " + avl.root.getData());
//		System.out.println("왼쪽 : " + avl.root.getLeft() + ", 오른쪽 : " + avl.root.getData() + "\n");		
//		
//		System.out.println("insert3");
//		avl.root = avl.insert(avl.root, 3);
//		currentL = avl.root.getLeft();
//		currentR = avl.root.getRight();
//		System.out.println("루트노드 : " + avl.root.getData());
//		System.out.println("왼쪽 : " + currentL.getData() + ", 오른쪽 : " + currentR.getData() + "\n");
//		
//		System.out.println("insert4");
//		avl.root = avl.insert(avl.root, 4);
//		currentL = avl.root.getLeft();
//		currentR = avl.root.getRight();
//		System.out.println("루트노드 : " + avl.root.getData());
//		System.out.println("왼쪽 : " + currentL.getData() + ", 오른쪽 : " + currentR.getData() + "\n");
//		
//		System.out.println("insert5");
//		avl.root = avl.insert(avl.root, 5);
//		currentL = avl.root.getLeft();
//		currentR = avl.root.getRight();
//		System.out.println("루트노드 : " + avl.root.getData());
//		System.out.println("왼쪽 : " + currentL.getData() + ", 오른쪽 : " + currentR.getData() + "\n");
//		
//		System.out.println("insert6");
//		avl.root = avl.insert(avl.root, 6);
//		currentL = avl.root.getLeft();
//		currentR = avl.root.getRight();
//		System.out.println("루트노드 : " + avl.root.getData());
//		System.out.println("왼쪽 : " + currentL.getData() + ", 오른쪽 : " + currentR.getData() + "\n");
		
		//삭제 확인
		avl.root = avl.insert(avl.root, 1);
		avl.root = avl.insert(avl.root, 2);
		avl.root = avl.insert(avl.root, 3);
		avl.root = avl.insert(avl.root, 4);
		avl.root = avl.insert(avl.root, 5);
		avl.root = avl.insert(avl.root, 6);
		avl.root = avl.insert(avl.root, 7);
		avl.root = avl.insert(avl.root, 8);
		avl.root = avl.insert(avl.root, 9);
		
		currentL = avl.root.getLeft();
		currentR = avl.root.getRight();
		
		System.out.println("6 삭제 전");
		System.out.println("루트노드 : " + avl.root.getData());
		System.out.println("왼쪽 : " + currentL.getData() + ", 오른쪽 : " + currentR.getData() + "\n");
		
		System.out.println("6 삭제 후");
		avl.delete(6);
		currentL = avl.root.getLeft();
		currentR = avl.root.getRight();
		System.out.println("루트노드 : " + avl.root.getData());
		System.out.println("왼쪽 : " + currentL.getData() + ", 오른쪽 : " + currentR.getData() + "\n");
		
		System.out.print("중위순회 : ");
		avl.inorderTraverse(avl.root);
	}
	
	
	//삽입
	public Node insert(Node node, int data) {
		if(node == null) {		//비어있다면(비교할 값이 더 없으면) 새로 생성
			Node newNode = new Node(data);
			node = newNode;
		} else if(data < node.getData()) {		//현재 노드보다 작은 값이면
			node.setLeft(insert(node.getLeft(), data));
			node = rebalance(node);
		} else if(data > node.getData()) {		//현재 노드보다 큰 값이면
			node.setRight(insert(node.getRight(), data));
			node = rebalance(node);
		}
		
		//이미 있는 데이터인 경우 아무 작업도 일어나지 않는다.
		return node;
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
//		else if(current.getLeft() != null && current.getRight() != null){
//			//step1 : 삭제 대상을 대체할 노드 찾기
//			Node replacement = getRightSubMinNode(delete);
//			Node repParent = delete;
//			
//			//step2 : 대체할 노드에 저장된 값을 삭제할 노드에 대입
//			//노드를 대입하지 않고 값을 대입하는 이유는 단말노드가 아닌 오른쪽자식노드가 있을 경우를 대비
//			int delData = delete.getData();		//삭제대상 데이터 백업
//			delete.setData(replacement.getData());	//삭제대상 노드에 대체노드 값 대입
//			
//			//step3 : 대체할 노드의 부모와 자식을 연결
//			if(repParent.getLeft() == replacement)	//대체노드가 부모의 왼쪽서브트리일 때
//				repParent.setLeft(replacement.getRight());
//			else			//대체노드가 부모의 오른쪽서브트리일 때
//				repParent.setRight(replacement.getRight());
//		}
		
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
		
		root = rebalance(root);			//삭제 후 리밸런싱
		
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
	
	
	//리밸런싱
	public Node rebalance(Node root) {
		//균형인수가 양수면 왼쪽이 더 길고, 음수면 오른쪽이 더 길다
		int balFac = getHeightDiff(root);		//balance factor (균형인수)
		
		if(balFac > 1) {			//균형인수가 양수라면 LL이나 LR상태
			//case1 : LL
			if(getHeightDiff(root.getLeft()) > 0)	//root다음 node 기준으로 왼쪽으로 더 길면 LL상태
				root = rotateLL(root);
			//case2 : LR
			else									//오른쪽으로 더 길면 LR상태
				root = rotateLR(root);
		} else if(balFac < -1) {	//균형인수가 음수라면 RR이나 RL상태
			//case3 : RR
			if(getHeightDiff(root.getRight()) < 0)	//root다음 node 기준으로 오쪽으로 더 길면 RR상태
				root = rotateRR(root);
			//case4 : RL
			else									//왼쪽으로 더 길면 RL상태
				root = rotateRL(root);
		}
		
		return root;		//새로운 root 반환
	}
	
	public int getHeightDiff(Node node) {	//왼쪽과 오른쪽 서브트리의 차 구하는 함수
		if(node == null)
			return 0;
		
		int leftHeight = getHeight(node.getLeft());		//왼쪽서브트리 높이 계산
		int rightHeight = getHeight(node.getRight());	//오른쪽서브트리 높이 계산
		
		return leftHeight - rightHeight;		//왼쪽이 더 깊으면 +, 오른쪽이 더 깊으면 -
	}
	
	public int getHeight(Node node) {		//트리의 최대 높이 구하는 함수
		if(node == null)
			return 0;
		
		int leftHeight = getHeight(node.getLeft());		//왼쪽서브트리 높이 계산
		int rightHeight = getHeight(node.getRight());	//오른쪽서브트리 높이 계산
		
		if(leftHeight > rightHeight)
			return (leftHeight + 1);
		else
			return (rightHeight + 1);
	}
	
	public Node rotateLL(Node node) {
		Node parent = node;
		Node child = node.getLeft();		//해당 노드의 왼쪽서브트리
		
		//LL 회전
		parent.setLeft(child.getRight());	//parent의 왼쪽에 child의 오른쪽을 연결
		child.setRight(parent);				//child의 오른쪽에 parent를 연결
		
		return child;		//새로운 root를 반환
	}
	
	public Node rotateRR(Node node) {
		Node parent = node;
		Node child = node.getRight();
		
		//RR 회전
		parent.setRight(child.getLeft());	//parent의 오른쪽에 child의 왼쪽을 연결
		child.setLeft(parent);				//child의 왼쪽에 parent를 연결
		
		return child;		//새로운 root를 반환
	}
	
	public Node rotateLR(Node node) {		//부분적 RR회전 후, LL회전
		Node parent = node;
		Node child = parent.getLeft();
		
		parent.setLeft(rotateRR(child));	//먼저 child를 RR회전하고, parent의 왼쪽에 연결
		return rotateLL(parent);				//child를 LL회전하고, 새로운 root를 반환
	}
	
	public Node rotateRL(Node node) {		//부분적 LL회전 후, RR회전
		Node parent = node;
		Node child = parent.getRight();
		
		parent.setRight(rotateLL(child));	//먼저 child를 LL회전하고, parent의 오른쪽에 연결
		return rotateRR(parent);				//child를 RR회전하고, 새로운 root를 반환
	}
	
	
	//출력
	public void inorderTraverse(Node node) {		//오름차순으로 출력 가능
		if(node == null)
			return;
		
		inorderTraverse(node.getLeft());
		System.out.print(node.getData() + " ");
		inorderTraverse(node.getRight());
	}
	
	public void preorderTraverse(Node node) {
		if(node == null)
			return;
		
		System.out.print(node.getData() + " ");
		inorderTraverse(node.getLeft());
		inorderTraverse(node.getRight());
	}
	
	public void postorderTraverse(Node node) {
		if(node == null)
			return;
		
		inorderTraverse(node.getLeft());
		inorderTraverse(node.getRight());
		System.out.print(node.getData() + " ");
	}
}
