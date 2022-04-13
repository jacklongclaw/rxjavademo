package com.jack.demo.common.entity;

import java.util.List;

public class Student {
  public String name;
  public int rank;
  public int score;

  public Student(String name, int rank, int score) {
    this.name = name;
    this.rank = rank;
    this.score = score;
  }

  public static List<Student> students = List.of(new Student("zhangsan", 1, 10),
      new Student("lisi", 1, 1000),
      new Student("wangwu", 1, 100),
      new Student("zhaoliu", 3, 100));
}
