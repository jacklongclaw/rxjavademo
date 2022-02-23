package com.jack.demo.operator.function;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class TestObserveOn {
    @Test
    public void testObserveOn() throws InterruptedException{
        Observable.create(emitter -> {
                    System.out.println("subscribe ... " + Thread.currentThread());

                    //耗时操作，模拟网络请求 安卓如果在主线程中进行耗时操作，那么会直接回崩溃
                    TimeUnit.SECONDS.sleep(2);
                    emitter.onNext("aaa");
                    emitter.onNext("bbb");
                    emitter.onComplete();
                }).subscribeOn(Schedulers.io())//主要决定执行的subscribe方法所处的线程，也就是产生事件，发射事件所在的线程
                .observeOn(Schedulers.computation())//决定下游事件处理所处的线程
                .map(obj -> {
                    System.out.println("map apply ... "+Thread.currentThread());
                    return obj+"mapped";
                })
                //.subscribeOn(Schedulers.newThread())//多次调用，只有最上游的subscribeOn指定的线程生效
                .observeOn(Schedulers.single())//决定下游事件处理所处的线程
                .subscribe(new Observer<>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe ... " + Thread.currentThread());
                    }

                    @Override
                    public void onNext(String o) {
                        System.out.println("onNext ... " + o + " ... " + Thread.currentThread());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError ... " + Thread.currentThread());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete ... " + Thread.currentThread());
                    }
                });

        TimeUnit.SECONDS.sleep(3);
    }
}
