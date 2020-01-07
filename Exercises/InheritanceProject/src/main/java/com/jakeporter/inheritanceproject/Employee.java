package com.jakeporter.inheritanceproject;

/**
 *
 * @author jake
 */
public class Employee extends Person{

    private double salary;

    public Employee(String name, int age, Address address, double salary) {
        super(name, age, address);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    @Override
    public String toString(){
        return super.toString() + "Salary: " + salary;
    }
}
