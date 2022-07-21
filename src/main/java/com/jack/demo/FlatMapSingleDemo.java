package com.jack.demo;

import com.jack.demo.common.entity.Student;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.concurrent.TimeUnit;

/**
 * flatMap中的  max可以同时订阅的 ObservableSource 的最大数量
 */

public class FlatMapSingleDemo {
  public static void main(String[] args) throws InterruptedException {

    Observable.fromIterable(Student.getDefaultStudents()).flatMapSingle(student -> {
      System.out.println("flatMapSingle1 ... " + Thread.currentThread().getName());
      return Single.just(student);
    }).flatMap(student -> {
      System.out.println("...1 " + Thread.currentThread().getName());
      return Observable.just(student);
    }).blockingSubscribe(student -> System.out.println("block ...1" + Thread.currentThread().getName()));

    Observable.fromIterable(Student.getDefaultStudents()).flatMap(student -> {
      System.out.println("flatMap2 ... " + Thread.currentThread().getName());
      return Observable.just(student);
    }).flatMap(student -> {
      System.out.println("...2 " + Thread.currentThread().getName());
      return Observable.just(student);
    }).blockingSubscribe(student -> System.out.println("block ...2" + Thread.currentThread().getName()));

    TimeUnit.SECONDS.sleep(10);
  }
}
