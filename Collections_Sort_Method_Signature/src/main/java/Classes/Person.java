/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author mauriciobedoya
 */
public class Person implements Comparable<Person> {
    private int age;
    public Person(int age){
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        return this.age - o.age;
    }
    
    @Override
    public String toString(){
        return "" + this.age;
    }
}
