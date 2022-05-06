package com.tjoeun.test.useInterface;
@FunctionalInterface
interface Operation {
	public int process(int[] nums);
}



public class Exam01 {
	// arr에 저장된 정수의 합이 화면에 표시되도록 
	// 함수형 인터페이스를 정의하고 활용해보세요.
	public static void main(String[] args) {
		int[] arr = {5, 5, 4, 3};
		// Lambda 방법
		exec(arr, nums->{
			int total = 0;
			for(int i =0; i<nums.length;i++) {
				total += nums[i];
			}
			return total;
		}) ;
		// Anonymous 방법
		exec(arr, new Operation() {
			public int process(int[] nums) {
				int total = 0;
				for(int i =0; i<nums.length;i++) {
					total += nums[i];
				}
				return total;
			}
		});
		
		// 배열에{3,5} 저장된 정수 중에서 최대값을 리턴.
		int[] arr1 = {3, 5};
		
		exec(arr1, a-> a[0]>a[1] ? a[0] : a[1]); // 문장이 1개일 때에는 return을 사용하지않아도된다.
		exec(arr1, a->{
			return a[0]>a[1] ? a[0] : a[1];
		}); // return을 사용하려면 {}를 사용하여 사용한다.
		
		exec(arr, a->{
			int max = 0;
			if(a[0] > a[1]) max = a[0];
			else max = a[1];
			return max;
		});
		
		// Anonymous 방법
		exec(arr1, new Operation() {
			
			@Override
			public int process(int[] a) {
				return (a[0] > a[1]) ? a[0] : a[1];
			}
		});
	}
	
	static void exec(int[] arr , Operation op) {
		int total = op.process(arr);
		System.out.println("total = " + total);
	}
}
