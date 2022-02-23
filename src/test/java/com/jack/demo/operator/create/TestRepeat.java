package com.jack.demo.operator.create;

import io.reactivex.Observable;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TestRepeat {
    @Test
    public void test() {
        //repeat 不是创建一个Observable,而是重复发射原始Observable的数据序列，如果repeat操作符不带参数，那么发射次数则无限制
        Observable.just("hello repeat")
                .repeat(10)
                .subscribe(msg -> System.out.println(msg));
    }

    @Test
    public void testRepeatWhen() throws InterruptedException {
        Observable.range(0, 9)
                .repeatWhen(objectObservable -> Observable.timer(10, TimeUnit.SECONDS))
                .subscribe(intV -> System.out.println(intV));
        TimeUnit.SECONDS.sleep(12);
    }

    @Test
    public void testRepeatUtil() throws InterruptedException {
        final long startTimeMillis = System.currentTimeMillis();
        List<Long> list = new ArrayList<>();
        Observable.interval(500, TimeUnit.MILLISECONDS)
                .take(5)
                .repeatUntil(() -> (System.currentTimeMillis() - startTimeMillis) > 3000)
                .subscribe(longV -> {
                            list.add(longV);
                            System.out.println(longV);
                        }
                );
        TimeUnit.SECONDS.sleep(5);
        Assert.assertEquals("0123401234", list.stream().map(Objects::toString).collect(Collectors.joining()));
        //FIXME 为什么会成功，按道理应该是012340才对吧，而且多次运行，还会发现失败的情况
    }
}
