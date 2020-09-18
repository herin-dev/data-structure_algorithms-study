package classTest;

import java.util.HashMap;

public class Hashmap {
	public static void main(String args[]) {
		HashMap<Integer, String> hash = new HashMap<Integer, String>();
		HashMap<Integer, String> hash2 = new HashMap<Integer, String>();
		
		//추가
		hash.put(1001, "Hong");		//key: 1001, value: Hong
		hash.put(1003, "Kim");		//key: 1002, value: Kim
		System.out.println("hash: " + hash);	//출력

		hash2.put(1004, "Park");	//key: 1003, value: Park
		hash2.put(1002, "Kang");	//key: 1004, value: Kang
		System.out.println("hash2: " + hash);	//출력

		hash.putAll(hash2);			//hash 뒤에 hash2 추가
		System.out.println("hash: " + hash);	//출력
		
		hash.put(1001, "Jeong");	//중복된 키 추가
		System.out.println("hash: " + hash);	//출력
		
		//탐색
		System.out.print("1001: " + hash.get(1001));
		System.out.println(", 1005: " + hash.get(1005));
		
		System.out.print("1003: "+hash.containsKey(1003));
		System.out.println(", 1005: "+hash.containsKey(1005));
		System.out.print("Kim: "+hash.containsValue("Kim"));
		System.out.println(", Yu: "+hash.containsValue("Yu"));
		
		//그 외
		System.out.println("size: " + hash.size());
		System.out.println("key=value: " + hash);	//출력
		System.out.println("only values: " + hash.values());
		
		//삭제
		System.out.println("삭제 전: " + hash);	//출력
		
		hash.remove(1001);
		System.out.println("rm 1001: " + hash);	//출력
		
		hash.clear();
		System.out.println("clear: " + hash);	//출력
		
		
	}
}
