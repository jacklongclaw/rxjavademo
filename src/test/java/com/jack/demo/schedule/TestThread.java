package com.jack.demo.schedule;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

public class TestThread {
    @Test
    public void test() {
        Observable.create(emitter -> {
            System.out.println(Thread.currentThread());
            emitter.onNext("hello thread");
        }).subscribe(msg -> {
            System.out.println(Thread.currentThread());
            System.out.println(msg);
        });
    }

    @Test
    public void testNewThread() {
        Observable.create(emitter -> {
            System.out.println(Thread.currentThread());
            emitter.onNext("hello 1");
            emitter.onNext("hello 2");
        }).observeOn(Schedulers.from(Executors.newFixedThreadPool(3))).subscribe(msg -> {
            System.out.println(Thread.currentThread());
            System.out.println(msg);
        });
    }
}
