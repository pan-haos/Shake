package com.ph.connection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者：潘浩
 * 项目：Shake
 * 时间：18-3-7  下午10:12
 */
public class Schedules {


    /**
     * IO 密集型运算线程池
     *
     * @return
     */
    public static ExecutorService IO() {
        return Executors.newCachedThreadPool();
    }

    /**
     * cpu（计算）密集型线程池
     *
     * @return
     */
    public static ExecutorService CPU() {
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }


}
