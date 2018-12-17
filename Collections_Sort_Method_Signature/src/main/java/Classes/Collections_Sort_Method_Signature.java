/*
 * Signature found in Collections.sort
 *
 * Let's analyze  the following: 
 * public static <T extends Comparable<? super T>> void sort(List<T> list){ ..... }
 *
 * Observations:
 * 1) Method is public.
 * 2) Method is static, then we do not need an instance of the class to use it.
 * 3) List<T> input parameter "T" is used it.
 * 4) <? super T> means that we are using a Lower Bound T. This means, see if any class including "T" or
 *    "T" super types implements Comparable interface. To understant this better, draw the inheritance
 *    diagram, position your eyes in class Student and go up (super) in the inheritance tree:
 *    Does Student class implements Comparable directly ? Answer: No
 *    Does Person class implements Comparable directly ? Answer: Yes (JVM stop searching)
 * 5) In our example, the following expresion can be translate into three options (JVM only use two):
 *    public static<Student extends Comparable<? super Student>> void sort(List<Student> list){ ... }
 *
 *    a) public static<Student extends Comparable<Student>> void sort(List<Student> list){ ... }  
 *       JVM says: hey Student class did not implement Comparable interface. Let's keep looking.
 *
 *    b) public static<Student extends Comparable<Person>> void sort(List<Student> list){ ... }  
 *       JVM says: This is ok. Student class implements via Inheritance Comparable. According to the
 *       inheritance tree a Student is a Person. mmm, this means that if I (JVM) can order Person, than I (JVM)
 *       can sort Students too. (JVM stop looking, because JVM founds a solution)
 *    
 *    c) public static<Student extends Comparable<Object>> void sort(List<Student> list){ ... }
 *       JVM did NOT reach this, because solution found in item b).
 *    
 * Activity
 * If we change the method signature to:
 * public static <T extends Comparable<? extends T>> void sort(List<T> list){ ..... } in the collections class:
 * a) Are we using Upper or Lower bound.
 * b) Is it possible to sort: List<Student> = Arrays.asList(student1, student2, student3) ?
 * c) Is it possible to sort: List<Person> = Arrays.asList(student1, student2, student3) ?
 * d) Is it possible to sort: List<Person> = Arrays.asList(student1, student2, student3) ?
 * e) Is it possible to sort: List<Person> = Arrays.asList(person1, person2, person3) ?
 */

package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author mauriciobedoya
 */

public class Collections_Sort_Method_Signature {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        
        personList.add(new Student(12));
        personList.add(new Student(10));
        personList.add(new Student(22));
        personList.add(new Student(15));
        
        Collections.sort(personList);
        personList.forEach(System.out::println);
    }
}
