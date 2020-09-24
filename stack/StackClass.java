package classTest;

import java.util.Stack;

public class StackClass {
	public static void main(String args[]) {
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i=1; i<=5; i++)
			stack.push(i);			//1~5 차례대로 push
		
		System.out.println("stack: " + stack);
		
		System.out.println("peek: " + stack.peek());	//제일 위에 있는 데이터 조회(삭제X)
		System.out.println("stack: " + stack);			//5가 그대로 남아있다.
		
		System.out.println("pop: " + stack.pop());	//제일 위에 있는 데이터 삭제
		System.out.println("stack: " + stack);			//5가 삭제되었다
		
		System.out.println("search 5: " + stack.search(5));	//삭제된 5
		System.out.println("search 2: " + stack.search(2));
		
		System.out.println("empty: " + stack.empty());	//결과를 boolean으로 반환
		
		stack.clear();		//스택 비우기
		System.out.println("empty: " + stack.empty());	//결과를 boolean으로 반환
		
		System.out.println("stack: " + stack);
	}
}
