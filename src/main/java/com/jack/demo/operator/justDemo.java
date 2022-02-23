package com.jack.demo.operator;

import io.reactivex.Observable;

import java.util.stream.IntStream;

public class justDemo {
    public static void main(String[] args) {
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
