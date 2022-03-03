package com.example.sqlitetest;

public class Student {
    private int number;
    private String name;
    private String sex;
    private int grade;
    public Student(int number, String name, String sex, int grade){
       this.number=number;
       this.name = name;
       this.sex =sex;
       this.grade =grade;
    }
    public  int getNumber(){
        return number;
    }
    public void setNumber(int number){
        this.number =number;
    }
    public  String getName(){
        return name;
    }
    public void setName(String name){
        this.name =name;
    }
    public  String getSex(){
        return sex;
    }
    public void setSex(String sex){
        this.sex =sex;
    }

    public int getGrade(){
        return grade;
    }
    public void setGrade(int grade){
        this.grade =grade;
    }


}
