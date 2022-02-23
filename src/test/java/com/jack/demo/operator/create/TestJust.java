package com.jack.demo.operator.create;

import io.reactivex.Observable;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestJust {

    @Test
    public void test(){
        Observable.just("1","2","3","4","5","6","7","8","9","10").subscribe(intV -> System.out.println("next:" + intV),
                throwable -> System.out.println("error:" + throwable),
                () -> System.out.println("sequence complete"));
    }

    @Test
    public void testNull(){
        //just里不能传null,否则会报空指针异常
        Observable.just("1","2","3","4","5","6","7","8","9",null).subscribe(intV -> System.out.println("next:" + intV),
                throwable -> System.out.println("error:" + throwable),
                () -> System.out.println("sequence complete"));
    }

    @Test
    public void testDiffBetweenJustAndFrom(){
        //just 类似于from，from会将数组或者Iterable的数据取出然后逐个发射，而just只是将数组Iterable当成一个元素简单原样发射
        Observable.just(List.of("1","2","3","4","5","6","7","8","9","10")).subscribe(intV -> System.out.println("next:" + intV),
                throwable -> System.out.println("error:" + throwable),
                () -> System.out.println("sequence complete"));

        Observable.fromIterable(List.of("1","2","3","4","5","6","7","8","9","10")).subscribe(intV -> System.out.println("next:" + intV),
                throwable -> System.out.println("error:" + throwable),
                () -> System.out.println("sequence complete"));
    }

}
