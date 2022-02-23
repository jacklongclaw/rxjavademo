package com.jack.demo.operator.merge;

import org.junit.jupiter.api.Test;
import rx.Observable;

import java.util.concurrent.TimeUnit;

public class TestConcat {

    @Test
    public void testConcat() throws InterruptedException {
        Observable.concat(makeOne(1000), makeOne(900), makeOne(800), makeOne(700), makeOne(600), makeOne(500)).subscribe(msg -> {
            System.out.println("---subscribe---" + Thread.currentThread());
            System.out.println(msg);
        });


        Observable.concat(makeFirst("111"), makeSecond("2222")).subscribe(msg -> {
            System.out.println("---subscribe---" + Thread.currentThread());
            System.out.println(msg);
        });
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    public void testMerge() throws InterruptedException {
        //merge操作符和concat操作符基本一样，只是concat是串行发送事件，而merge是并行发送事件
        Observable.merge(makeOne(1000), makeOne(900), makeOne(800), makeOne(700), makeOne(600), makeOne(500)).subscribe(msg -> {
            System.out.println("---subscribe---" + Thread.currentThread());
            System.out.println(msg);
        });
        //makeSecond比较耗时，所以merge会执行makeFirst，而不是单纯的按顺序执行
        Observable.merge(makeFirst("111"), makeSecond("2222")).subscribe(msg -> {
            System.out.println("---subscribe---" + Thread.currentThread());
            System.out.println(msg);
        });
        TimeUnit.SECONDS.sleep(5);
    }

    private Observable makeOne(long delay) {
        return Observable.create(emitter -> {
            try {
                System.out.println("---emitter---" + Thread.currentThread());
                TimeUnit.MILLISECONDS.sleep(delay);
                emitter.onNext(delay);
                //如果没有执行onCompleted方法，concat方法发射第一个不再发送，merge方法则会继续发送
                //emitter.onCompleted();
            } catch (InterruptedException e) {

            }
        });
    }


    private static Observable<Integer> makeSecond(String query) {
        return Observable.just(query).map(str -> str.length());
    }

    private static Observable<Integer> makeFirst(String query) {
        return Observable.just(query).delay(200, TimeUnit.MILLISECONDS).map(str -> str.length());
    }
}
