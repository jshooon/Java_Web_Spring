/*
파일 이름 : SystemOutPrintln.java
작 성 자 : 지 성훈
작 성 일 : 2022. 01. 25.(화)
프로그램 설명 : SystemOutPrintln에 대한 실습 내용.
*/
package com.tjoeun.test.interfaceintro;

// 함수형 인터페이스(Functional Interface)
//인터페이스는 객체생성이 되지않는다. 기능이 없기 때문이다.(메소드가 헤드만있다)
// 메소드가 하나만 가지고 하나만을 위한 것을 함수형 인터페이스라 한다.
// 인터페이스는 반드시 반드시 다른클래스에서 구현해야 한다. 
// 그리고 꼭 오버라이드를 해줘야한다.

@FunctionalInterface
interface MyInterface{
	void println(String msg);
//	void pringtMsg();
}

// 인터페이스를 사용하는 방법 1
// 인터페이스를 구현한 클래스를 정의한다.
class ImplMyinterface implements MyInterface{
	
	@Override //오버라이드 문법 검사를 해준다.
	public void println(String msg) {
		System.out.println(msg);
		
	}
}

public class Main {

	public static void main(String[] args) {
//		MyInterface mi = new ImplMyinterface();
//		mi.println("Hello");
		
		
		
		//인터페이스를 구현한 익명 클래스를 선언하고 사용하는 방법
		// Anonymous Class(이름 없는 클래스 선언/인스턴스생성/사용)
		// 이름없는 클래스가 생성되고,  MyInterface의 메소드를 구현한다 라는 뜻.
		// 1번째 방법
		 new MyInterface() { // 이름없는 클래스가 MyInterface를 
			@Override		// 구현하고 인스턴스를 생성한다.
			public void println(String msg) {
				System.out.println(msg);
			}
		}.println("익명클래스 테스트");
		//인터페이스 구현한 클래스 정의, 인스턴스 생성, 메소드 호출.
		
		// 이름없는 클래스를 변수에 저장하는 법.
		MyInterface mi2 = new MyInterface() { // 이름없는 클래스가 MyInterface를 
			@Override							// 구현하고 인스턴스를 생성한다.
			public void println(String msg) {
				System.out.println(msg);
			}
		};
		
		//3번째 Lambda 표현식으로 함수형 인터페이스를 구현하는 방법.
		// msg는 파라미터, System.out.println(msg)은 메소드 바디다.
		// 가능한 이유는 함수형 인터페이스이기 때문. (메소드가 하나인 인터페이스)
		MyInterface mi3 = msg->System.out.println(msg); //new가 들어있다. 객체참조
		mi3.println("람다식으로 표현한 익명 클래스");
		
		test("테스트1", mi3); // 객체 참조, 메소드 오버라이드(메소드구현)
		test("테스트2",(msg)->System.out.println(msg));  
		test("테스트2",(msg)->{
			System.out.println(msg);
			// 중괄호 있다면 여러개의 문장 정의 가능.
			return; // 리턴할 값이 있는 경우에만 사용.
		}); 
		
		// 배열 생성
		// 1. 배열변수 선언, 2. 메모리 할당, 3. 원소 초기화, 4. 사용
		
		int[] nums;
		nums = new int[3];
		nums[0] = 2;
		nums[1] = 3;
		nums[2] = 5;
		System.out.println(nums[0] + nums[2]);
		
		// 브레이스를 사용하여 배열변수 선언을 하려면
		// 선언과 동시에 원소 초기화를 해줘야 한다.
		int[] sal = {100, 200, 150};
		
		// 선언과 동시에 원소 초기화를 하지 않아서 에러난다.
//		int[] price;
//		price = {100, 200, 150};
		
		// 이렇게 한다면 사용이 가능하다.
		int[] price;
		price = new int[] {100, 200, 150};
		
		arrTest(price);
		
		// 배열을 선언하지안고 간편하게 전달하기 위한 방법
		arrTest(new int[]{100, 200, 150});
		
	}
	
	static void test(String msg, MyInterface mi) {
		mi.println(msg);
	}

	static void arrTest(int[] num) {
		int res = 1;
		for(int i = 0; i < num.length; i++) {
			res *= num[i];
		}
		System.out.println("누적 곱 = " + res);
	}
	
}
