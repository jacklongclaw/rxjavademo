package com.jack.demo.operator.create;

import org.junit.jupiter.api.Test;
import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * interval 操作符返回一个Observable,他按固定的时间间隔发射一个无限递增的整数序列
 * interval操作符默认在computation调度器上执行
 */
public class TestInterval {
    @Test
    public void test() throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS).subscribe(along-> System.out.println(along));
        TimeUnit.SECONDS.sleep(5);
    }
}
