package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setStudents(List<Student> students) {
        this.students = students;

    }

    public University(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student student : students) {
            if (student.getAverageGrade() == averageGrade) {
                return student;
            }
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        if (students.isEmpty()) return null;
        Student maxGradeStudent = students.get(0);
        double maxGrade = maxGradeStudent.getAverageGrade();
        for (Student student : students) {
            if (student.getAverageGrade() > maxGrade) {
                maxGrade = student.getAverageGrade();
                maxGradeStudent = student;
            }
        }

        return maxGradeStudent;
    }

    public Student getStudentWithMinAverageGrade() {
        if (students.isEmpty()) return null;
        Student minAverageGradeStudent = students.get(0);
        double minAverageGrade = minAverageGradeStudent.getAverageGrade();
        for (Student student : students) {
            if (student.getAverageGrade() < minAverageGrade) {
                minAverageGrade = student.getAverageGrade();
                minAverageGradeStudent = student;
            }
        }
        return minAverageGradeStudent;
    }

    public void expel(Student student) {
        students.remove(student);
    }
}