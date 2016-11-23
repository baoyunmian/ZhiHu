package com.example.zhihudiary.factory;

import com.example.zhihudiary.manager.ThreadPoolProxy;

public class ThreadPoolFactory {
	private static ThreadPoolProxy	normalPoolProxy;

	public static ThreadPoolProxy getNormalPool(){
		if (normalPoolProxy == null) {
			synchronized (ThreadPoolProxy.class) {
				if (normalPoolProxy == null) {
					normalPoolProxy = new ThreadPoolProxy(5, 5, 3000);
				}
				
			}
		}
		
		return  normalPoolProxy;
		
		
		
	}

}
