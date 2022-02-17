package com.jack.demo;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import org.junit.jupiter.api.Test;

public class TestCompletable {

    @Test
    public void test(){
        Completable.create(emitter ->  emitter.onComplete()).andThen(Observable.range(0,10))
        .subscribe(intValue -> System.out.println(intValue));
    }

    @Test
    public void testMaybe(){
        Maybe.create(emitter -> {
            emitter.onSuccess("hello world");
            emitter.onSuccess("hello kitty");
        }).subscribe(s-> System.out.println("---------------------"+s));
    }
}
