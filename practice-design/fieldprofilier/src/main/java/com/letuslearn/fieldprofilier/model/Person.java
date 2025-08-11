package com.letuslearn.fieldprofilier.model;

public class Person {
    private String name;
    private int age;
    private long salary;

    public void setName(String theName){
        this.name = theName;
    }

    public void setAge(int theAge){
        this.age = theAge;
    }

    public void setSalary(long theSalary){
        this.salary = theSalary;
    }

    public String getName(){
        return name;
    }

    public int getAge(){return age;}
    public long getSalary(){return salary;}

    @Override
    public String toString(){
        return "name: "+ name + ", age: " + age + ", salary: " + salary;
    }

}
