package com.jack.demo;

import com.jack.demo.common.entity.Student;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComparatorDemo {


  public static void main(String[] args) {
    Student student1 = new Student("zhangsan",1,10);
    Student student2 = new Student("lisi",1,1000);
    Student student3 = new Student("wangwu",1,100);
    Student student4 = new Student("zhaoliu",3,100);
    Comparator<Pair<Student, Integer>> priorityCompare =
        Comparator.comparingInt(Pair::getRight);

    Comparator<Pair<Student, Integer>> rankCompare =
        Comparator.comparingInt(pair -> pair.getLeft().score);
    List<Student> collect = List.of(student1, student2, student3, student4)
        .stream()
        .map(student -> Pair.of(student, student.rank))
        .sorted(priorityCompare.reversed())
        //.sorted(rankCompare.reversed())
        .map(Pair::getLeft)
        .collect(Collectors.toList());


    List<Student> collect2 = List.of(student1, student2, student3, student4)
        .stream()
        .map(student -> Pair.of(student, student.rank))
        .sorted(priorityCompare.reversed().thenComparing(rankCompare.reversed()))
        .map(Pair::getLeft)
        .collect(Collectors.toList());

    collect.forEach(student -> System.out.println(student.name));
    System.out.println("--------------------");
    collect2.forEach(student -> System.out.println(student.name));
  }

}
