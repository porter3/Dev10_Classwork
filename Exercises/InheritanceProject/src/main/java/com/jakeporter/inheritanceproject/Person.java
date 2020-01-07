package com.jakeporter.inheritanceproject;

/**
 *
 * @author jake
 */
public class Person {

    private String name;
    private int age;
    private Address address;

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
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
    
    @Override
    public String toString(){
        return "Name: " + name + "\nAge: " + age + "\nAddress: " + address.toString();
    }
    
    public static void main(String[] args){
        
        Address jakeAddress = new Address("8014 Bristle Ln", "Charlotte", "28214");
        Person jake = new Person("Jake", 24, jakeAddress);
        
        System.out.println(jake);
    }
}
