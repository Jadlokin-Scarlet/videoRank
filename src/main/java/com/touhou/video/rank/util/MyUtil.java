package com.touhou.video.rank.util;

public class MyUtil {

	public static boolean isInRange(int min,int a,int max){
		return a>=min&&a<=max;
	}

	public static boolean isMoreThanZero(int a){
		return MyUtil.isInRange(0,a,999999999);
	}

	public static <T> T getEnd(T[] t){
		return t[t.length-1];
	}

}
