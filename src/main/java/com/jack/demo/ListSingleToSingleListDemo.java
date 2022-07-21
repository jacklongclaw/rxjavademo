package com.jack.demo;

import com.jack.demo.common.entity.Student;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;


public class ListSingleToSingleListDemo {
  public static void main(String[] args) {
    List<Single<Student>> singleStream = Student.getDefaultStudents().stream().map(student -> Single.just(student)).toList();
    Single<List<Student>> listSingle = Observable.fromIterable(singleStream)
        .flatMap(studentSingle -> {
          Observable<Student> studentObservable = studentSingle.flatMapObservable(student -> Observable.just(student));
          return studentObservable;
        }).toList();

    List<Observable<Student>> observableStream =
        Student.getDefaultStudents().stream().map(student -> Observable.just(student)).toList();
    Observable<Student> observableObservable =
        Observable.fromIterable(observableStream).flatMap(observable -> observable);
    Single<List<Student>> listSingle1 = observableObservable.toList();

  }
}
