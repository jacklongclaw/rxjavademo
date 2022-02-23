package com.jack.demo.operator.create;

import io.reactivex.Observable;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestFrom {

    @Test
    public void test(){
        Observable.fromFuture(Executors.newCachedThreadPool().submit(()->{
            System.out.println("模拟一些耗时的任务...");
            TimeUnit.SECONDS.sleep(5);
            return "OK";
        })).subscribe(msg -> System.out.println(msg));
    }
}
