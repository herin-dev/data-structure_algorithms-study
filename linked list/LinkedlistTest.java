import java.util.LinkedList;

public class Linkedlist {
	public static void main(String args[]) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		LinkedList<Integer> list2 = new LinkedList<Integer>();
		
		//데이터 추가
		list.add(2);		//end of the list
		list.addFirst(1);	//beginning of the list
		list.addLast(4);	//end of the list
		list.add(2, 3);		//specified position
		list.add(5);
		list.add(6);
		
		//appends all of the elements in the specified collection
		//to the end of this list
		for(int i=7; i<=10; i++)
			list2.add(i);
		
		//list.addAll(2, list2);
		list.addAll(list2);
		System.out.println(list);
		
		//탐색
		System.out.println(list.contains(11));
		System.out.println(list.contains(4));
		
		//데이터 삭제
		list.remove();		//head element of the list
		list.removeFirst();	//head element of the list
		list.removeLast();	//last element of the list
		list.remove(1);		//element at the specified position
		print(list);
		
		list.clear();
		System.out.println(list);
		
		////
		LinkedList list3 = new LinkedList();
		
		list3.add(1);
		list3.add("데이터");
		list3.add(0.3);
		
		System.out.println(list3);
	}

	public static void print(LinkedList<Integer> list) {
		if(list.size() == 0) {
			System.out.println("The list is empty.");
			return;
		}
		
		for(Integer i : list)
			System.out.print(i + " ");
		
		System.out.println();
	}
}
