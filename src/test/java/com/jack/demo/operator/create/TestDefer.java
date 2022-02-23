package com.jack.demo.operator.create;

import io.reactivex.Observable;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * defer 操作符会一直等待直到有观察者订阅它，然后他使用 Observable工厂方法 生成一个Observable
 * 它对每个观察者都这么做，因此尽管每个订阅者都以为自己订阅的是同一个Observable,
 * 但事实上每个订阅者获得的是他们自己单独的数据序列
 */
public class TestDefer {
    @Test
    public void test() throws InterruptedException {
        AtomicReference<Long> createObserableTime = new AtomicReference<>();
        AtomicReference<Long> recieveMsgTime = new AtomicReference<>();
        Observable observable = Observable.defer(()->{
            createObserableTime.set(System.currentTimeMillis());
            System.out.println("createObserableTime:"+createObserableTime.get());
            return Observable.just("hello defer");
        });
        TimeUnit.SECONDS.sleep(10);
        Long subscribeTime = System.currentTimeMillis();
        System.out.println("subscribeTime:"+subscribeTime);
        observable.subscribe(msg -> {
            recieveMsgTime.set(System.currentTimeMillis());
            System.out.println("recieveMsgTime:"+recieveMsgTime.get());
            System.out.println(msg);
        });
        Assert.assertTrue(createObserableTime.get() > subscribeTime);
    }
}
