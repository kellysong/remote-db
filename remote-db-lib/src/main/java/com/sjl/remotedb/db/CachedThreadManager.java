package com.sjl.remotedb.db;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 根据程序的运行情况自动来调整线程池中的线程数量
 *
 * @author songjiali
 * @version 1.0.0
 * @filename CachedThreadManager.java
 * @time 2018/11/6 14:23
 * @copyright(C) 2018 song
 */
public class CachedThreadManager {
    /**
     * 由于最大线程数为无限大，所以每当我们添加一个新任务进来的时候，如果线程池中有空闲的线程，则由该空闲的线程执行新任务，如果没有空闲线程，则创建新线程来执行任务。
     * 根据CachedThreadPool的特点，我们可以在有大量任务请求的时候使用CachedThreadPool，因为当CachedThreadPool中没有新任务的时候，它里边所有的线程都会因为超时而被终止
     */
    final ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

    private CachedThreadManager() {
    }

    public static CachedThreadManager getInstance() {
        return SocketThreadHolder.INSTANCE;
    }


    /**
     * 执行任务
     *
     * @param runnable
     */
    public void execute(Runnable runnable) {
        newCachedThreadPool.execute(runnable);

    }

    private static class SocketThreadHolder {
        private static CachedThreadManager INSTANCE = new CachedThreadManager();
    }


}
