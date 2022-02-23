package com.jack.demo;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import rx.plugins.RxJavaPlugins;

import java.util.concurrent.TimeUnit;

public class CompletableDemo {

    public static void main(String[] args) {
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                TimeUnit.SECONDS.sleep(1);
                emitter.onComplete();
                //emitter.onComplete()执行完成之后，表明Completable已经执行完毕，接下来执行andThen里的操作。
            }
        }).andThen(Observable.range(0,10))
        //completable 经常结合andThen 操作符使用
        .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }
}
