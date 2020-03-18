package com.touhou.video.rank.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lists {

	public static <T> List<T> of(T[] t){
		return new ArrayList<>(Arrays.asList(t));
	}

}
