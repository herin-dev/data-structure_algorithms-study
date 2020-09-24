package classTest;

import java.util.LinkedList;
import java.util.Queue;

public class QueueClass {
	public static void main(String args[]) {
		Queue<Integer> queue = new LinkedList<Integer>();

		for(int i=1; i<=4; i++)
			queue.add(i);			//1~4 차례대로 추가
		
		System.out.println("queue: " + queue);
		
		System.out.println("offer 5: " + queue.offer(5));
		System.out.println("queue: " + queue);
		
		System.out.println("element: " + queue.element());	//제일 앞에 있는 데이터 반환
		System.out.println("peek: " + queue.peek());	//제일 앞에 있는 데이터 반환
		System.out.println("queue: " + queue);			//1이 아직 남아있다
		
		System.out.println("remove: " + queue.remove());	//제일 앞에 있는 데이터 삭제, 1
		System.out.println("pop: " + queue.poll());		//제일 앞에 있는 데이터 삭제, 2
		System.out.println("queue: " + queue);			//1과 2가 삭제되었다
	}
}
