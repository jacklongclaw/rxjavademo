package com.jack.demo;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class TestCompletable {

    @Test
    public void test() {
        Completable.create(emitter -> emitter.onComplete()).andThen(Observable.range(0, 10))
                .subscribe(intValue -> System.out.println(intValue));
        System.out.println("test-maybe1");
    }

    @Test
    public void testMaybe() {
        Maybe.create(emitter -> {
            emitter.onSuccess("hello world");
            emitter.onSuccess("hello kitty");
        }).subscribe(s -> System.out.println("---------------------" + s));
        System.out.println("test-maybe2");
    }

    @Test
    public void testMaybeOnCompletableWithAction() {
        Maybe.create(emitter -> {
            emitter.onSuccess("hello kitty");
            emitter.onComplete();
            //emitter.onError(new NullPointerException("yo"));
            //只有onSuccess才会发送，但是如果执行了onComplete()之后，则onSuccess不会继续发送，因为maybe最多发送一次
        }).subscribe(s -> System.out.println("---------------------" + s),
                throwable -> System.out.println(throwable),
                () -> System.out.println("Maybe onCompletable"));
        //Maybe.subscribe(Consumer<? super T> onSuccess, Consumer<? super Throwable> onError,Action onComplete)
        //只要emitter调用了onError方法，那么订阅后将执行Maybe的onError,如果有元素发送了，那么订阅后将调用Maybe的onSuccess，并能获得元素，如果没有发送，那么订阅后将执行Maybe的onComplete
    }

    @Test
    public void testMaybeOnComplete() {
        Maybe.create(emitter -> {
            emitter.onComplete();
            emitter.onSuccess("hello kitty");
        }).subscribe(msg -> System.out.println(msg));
    }

    @Test
    public void testCreate() {
        Observable.create(emitter -> {
            //Rxjava 建议我们在传递给create方法的函数时，先检查一下观察者的isDisposed状态
            try {
                if (!emitter.isDisposed()) {
                    IntStream.range(0, 10).forEach(emitter::onNext);
                }
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        }).subscribe(intV -> System.out.println("next:" + intV),
                throwable -> System.out.println("error:" + throwable),
                () -> System.out.println("sequence complete"));
    }
}
