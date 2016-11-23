package com.example.zhihudiary.manager;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.R.integer;

public class ThreadPoolProxy {
	private int corePoolSize;
	private int maximumPoolSize;
	private int keepAliveTime;
	private ThreadPoolExecutor	mThreadPoolExecutor;
	public ThreadPoolProxy(int corePoolSize,int maximumPoolSize,int keepAliveTime){
		this.corePoolSize = corePoolSize;
		this.maximumPoolSize = maximumPoolSize;
		this.keepAliveTime = keepAliveTime;
	}
	
	public ThreadPoolExecutor initThreadPoolExecutor(){
		if (mThreadPoolExecutor == null) {
			synchronized (ThreadPoolProxy.class) {
				if (mThreadPoolExecutor == null) {
					TimeUnit unit = TimeUnit.MILLISECONDS;
					BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
					ThreadFactory threadFactory = Executors.defaultThreadFactory();
					RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
					mThreadPoolExecutor = new ThreadPoolExecutor(
							corePoolSize,
							maximumPoolSize, 
							keepAliveTime, 
							unit,
							workQueue,
							threadFactory,
							handler);
				}
			}
			
		}
		
		return mThreadPoolExecutor;
		
		
	}
	
	
	public void execute(Runnable task) {
		initThreadPoolExecutor();
		mThreadPoolExecutor.execute(task);
	}
	
	public Future<?> submit(Runnable task) {
		initThreadPoolExecutor();
		return mThreadPoolExecutor.submit(task);
	}
	
	public void remove(Runnable task) {
		initThreadPoolExecutor();
		mThreadPoolExecutor.remove(task);
	}

}
