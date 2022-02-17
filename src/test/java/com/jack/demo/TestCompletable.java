package com.jack.demo;

import io.reactivex.Completable;
import io.reactivex.Observable;
import org.junit.jupiter.api.Test;

public class TestCompletable {

    @Test
    public void test(){
        Completable.create(emitter ->  emitter.onComplete()).andThen(Observable.range(0,10))
        .subscribe(intValue -> System.out.println(intValue));
    }
}
