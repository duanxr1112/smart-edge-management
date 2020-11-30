package com.lenovo.ailab.smartedge.config;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title: FixedThreadPoolExecutor.java
 * 
 * @Autohr "chenpeng"
 * @data 2019年2月21日
 * @Email chenpeng10@lenovo.com
 * @description:
 **/
public class FixedThreadPoolExecutor extends ThreadPoolExecutor {
	public static final Logger logger = LoggerFactory.getLogger(FixedThreadPoolExecutor.class);
	private String poolName;

	public FixedThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveSecond, String poolName) {
		super(corePoolSize, maximumPoolSize, keepAliveSecond, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(corePoolSize), Executors.defaultThreadFactory());
		this.poolName = poolName;
		setRejectedExecutionHandler(new DiscardPolicy() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
				StackTraceElement stes[] = Thread.currentThread().getStackTrace();
				for (StackTraceElement ste : stes) {
					logger.warn(ste.toString());
				}
			}

		});
	}

	@Override
	public void execute(Runnable command) {
		super.execute(command);
		if (super.getCorePoolSize() - this.getQueue().remainingCapacity() > 100) {
			logger.error(poolName + " ThreadPool blocking Queue  size : "
					+ (super.getCorePoolSize() * 10 - this.getQueue().remainingCapacity()));
		}
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		if (task == null)
			throw new NullPointerException();
		RunnableFuture<T> ftask = newTaskFor(task);
		execute(ftask);
		return ftask;
	}
}
