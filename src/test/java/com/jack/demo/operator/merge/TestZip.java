package com.jack.demo.operator.merge;

import org.junit.jupiter.api.Test;
import rx.Observable;

public class TestZip {
    @Test
    public void test(){
        Observable.zip(Observable.just("hello jack"),Observable.just("how are you"),(sentence1,sentence2) -> sentence1+","+sentence2)
                .subscribe(msg -> System.out.println(msg));
    }
}
