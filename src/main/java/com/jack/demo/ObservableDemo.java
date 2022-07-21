package com.jack.demo;

import com.jack.demo.common.entity.Student;
import io.reactivex.Observable;

public class ObservableDemo {
  public static void main(String[] args) {
    /*Observable.interval(1, TimeUnit.SECONDS).forEach(System.out::println);
    while(true){

    }*/
    Observable<Student> students = getStudents();
    students.map(student -> student).forEach(student -> {
      System.out.println("------------first-----------------");
    });
    students.map(student -> student).forEach(student -> {
      System.out.println("------------second-----------------");
    });
  }

  public static Observable<Student> getStudents() {
    System.out.println("--------------getStudents------------");
    return Observable.fromIterable(Student.getDefaultStudents());
  }
}
