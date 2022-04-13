package com.jack.demo;

import com.jack.demo.common.entity.Student;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;

import static com.jack.demo.common.entity.Student.students;

public class ListSingleToSingleListDemo {
  public static void main(String[] args) {
    List<Single<Student>> singleStream = students.stream().map(student -> Single.just(student)).toList();
    Single<List<Student>> listSingle = Observable.fromIterable(singleStream)
        .flatMap(studentSingle -> {
          Observable<Student> studentObservable = studentSingle.flatMapObservable(student -> Observable.just(student));
          return studentObservable;
        }).toList();

  }
}
