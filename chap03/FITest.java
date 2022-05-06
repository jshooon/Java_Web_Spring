package com.tjoeun.test.useInterface;

import java.util.Date;

class FITest1 implements Runnable{

	@Override
	public void run() {
		while(true) {
			System.out.println(new Date());
			try {
				//1초 면 1000, 따라서 1초마다 실행.
				Thread.sleep(1000);  //컴퓨터는 1/1000초 단위로 시간을 센다.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}	
}



public class FITest {

	public static void main(String[] args) {
		
		FITest(new FITest1()); // Runnable 구현 클래스의 객체 참조 전달.
		FITest(new Runnable() { // Runnable 을 익명클래스의 객체 참조 전달.
			@Override
			public void run() {
				while(true) {
					System.out.println(new Date());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}	
			}
		}); 
		FITest(()->{
			while(true) {
				System.out.println(new Date());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}); // Runnable 을 Lambda 표현식으로 구현한 객체 참조 전달.
	}
	static void FITest(Runnable r) {
		new Thread(r).start();
	}
}
