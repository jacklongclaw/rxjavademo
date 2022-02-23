package com.jack.demo.operator.create;

import io.reactivex.Observable;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class TestTimer {
    @Test
    public void test() throws InterruptedException {
        AtomicLong calculateTime = new AtomicLong(System.currentTimeMillis());
        final long delaySec = 2;
        Observable.timer(delaySec, TimeUnit.SECONDS).subscribe(aLong -> {
            calculateTime.set((System.currentTimeMillis() - calculateTime.get()) / 1000);
            System.out.println(String.format("delay %s  hello timer",delaySec));
        });
        TimeUnit.SECONDS.sleep(3);
        Assert.assertEquals(delaySec,calculateTime.get());
    }
}
