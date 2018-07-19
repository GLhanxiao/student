package com.lession4.utils;

import java.util.UUID;

public class MyUUID {
	//生成uuid的方法
	public static String getUUID(){
		return UUID.randomUUID().toString();
	}
}
