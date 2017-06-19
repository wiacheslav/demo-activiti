package com.example.activiti;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PoolTest {

    @Test
    @Repeat(value = 20)
    public void test() throws Exception {
        String poolFormat = "ServiceTaskPool-%d";
        AtomicInteger counter = new AtomicInteger();
        int coreSize = 3;
        int maxSize = 4;
        int queueSize = 1;
        int idleThreadLiveInSeconds = 35;
        ThreadFactory factory = r -> new Thread(r, String.format(poolFormat, counter.incrementAndGet()));
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(queueSize);
        final ThreadPoolExecutor service = new ThreadPoolExecutor(coreSize,
                maxSize, idleThreadLiveInSeconds, TimeUnit.SECONDS, queue, factory, new
                ThreadPoolExecutor.AbortPolicy());
        service.allowCoreThreadTimeOut(true);
        passTask(coreSize, service);
        Thread.sleep(5000);
        passTask(coreSize, service);
        System.out.printf("ThreadPool state %s\n", service);
        Thread.sleep(6000);
    }

    protected void passTask(int coreSize, ThreadPoolExecutor service) {
        for (int i = 0; i < coreSize; i++) {
            service.execute(() -> System.out.printf("Simple print from %s \n",
                    Thread.currentThread().getName()));
        }
    }
}
