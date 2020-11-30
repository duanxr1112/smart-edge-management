package com.lenovo.ailab.smartedge.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Title: FixedThreadPoolExecutor.java
 * 
 * @Autohr "chenpeng"
 * @data 2019年2月21日
 * @Email chenpeng10@lenovo.com
 * @description:
 **/
public class FixedLogsThreadPoolExecutor extends ThreadPoolExecutor {
	public static final Logger logger = LoggerFactory.getLogger(FixedLogsThreadPoolExecutor.class);
	private String poolName;

	public FixedLogsThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveSecond, String poolName) {
		super(corePoolSize, maximumPoolSize, keepAliveSecond, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(corePoolSize), Executors.defaultThreadFactory());
		this.poolName = poolName;
		setRejectedExecutionHandler(new AbortPolicy());
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
