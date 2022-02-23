package com.jack.demo;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

import java.util.function.Consumer;

public class ComposeDemo {
    public static void main(String[] args) {
        //rxjava中compose的使用
        //在安卓中我们使用rxjava进行操作的时候经常会在子线程进行一下常见的操作
        //然后会回到主线程中进行一些更新UI视图的操作
        /*Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        System.out.println("subscribe: 处理耗时的一些操作");
                    }
                })
                .subscribeOn(Schedulers.computation()) // 指定 subscribe() 发生在 运算 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) {
                        System.out.println("accept: 在主线程main中进行视图的更新");
                    }
                });*/
    }
}
