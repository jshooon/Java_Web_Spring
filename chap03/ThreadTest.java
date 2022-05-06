package com.tjoeun.test.thread;

import java.util.Date;

public class ThreadTest {

	public static void main(String[] args) {
		threadTest();
	}
	static void threadTest() {
		// VCPU(Virtual CPU)에게 아래의 코드를 할당하고 실행하게 한다.
		// VCPU(Java Thread)에게 아래의 코드를 할당하려면 따라야 할 규칙이 있다.
		// Thread에게 전달할 코드는 Runnable 인터페이스(함수형 인터페이스)를 구현해야 한다.
		class DatePrinter implements Runnable{ // 가상CPU 실행
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
		
		class NumPrinter implements Runnable{
			@Override
			public void run() {
				int num = 0;
				while(true) {
					System.out.println(++num);
					try {
						Thread.sleep(1000); // 1찍고 1초쉰다.
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
//		DatePrinter code1 = new DatePrinter();
//		NumPrinter code2 = new NumPrinter();
//		
//		Thread t1 = new Thread(code1);
//		Thread t2 = new Thread(code2);
//		
//		t1.start();
//		t2.start();
		

		
	}
	
}
