package com.xpanxion.solution;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.xpanxion.java.assignments.DataAccess;
import com.xpanxion.java.assignments.model.*;

public class Worker {
    public void ex1 () {
        // System.out.println("Start here")// ;
        List<Product> productList = DataAccess.getProducts();
        Map<Integer, String> departmentList = DataAccess.getDepartments()
                        .stream()
                                .collect(Collectors.toMap(Department::getId, Department::getName));
        List<Product> products = productList.stream()
                .map(x -> new Product(x.getId(), x.getDepartmentId(), departmentList.get(x.getDepartmentId()), x.getName(), x.getPrice(), x.getSku()))
                .collect(Collectors.toList());
        System.out.println(products);
    }
    public void ex2 (){
        List<Product> products = DataAccess.getProducts();
        List<Product> productList = products.stream()
                .map(x -> new Product(x.getId(), x.getDepartmentId(), "N/A", x.getName(),x.getPrice(), x.getSku()))
                .collect(Collectors.toList());
        System.out.println(productList);
    }
    public void ex3(){
        List<Product> products = DataAccess.getProducts()
                .stream()
                .filter(x -> x.getDepartmentId() == 1 && x.getPrice() >= 10 )
                .collect(Collectors.toList());
        System.out.println(products);
    }
    public void ex4(){
        double products = DataAccess.getProducts()
                .stream()
                .filter(x -> x.getDepartmentId() == 2)
                .mapToDouble(x -> x.getPrice())
                .sum();
        System.out.println(products);
    }
    public void ex5(){

        List<Person> people = DataAccess.getPeople()
                .stream()
                .filter(x -> x.getId() <= 3)
                .map(x -> new Person(x.getId(), x.getFirstName(), x.getLastName(), x.getAge(), x.getSsn().substring(x.getSsn().length()-4)))
                .collect(Collectors.toList());
        System.out.println(people);
    }
    public void ex6(){
        List<Cat> cats = DataAccess.getCats()
                .stream()
                .sorted((a,b) -> a.getName().compareTo(b.getName()))
                .collect(Collectors.toList());
        System.out.println(cats);
    }
    public void ex7(){
         Arrays
                .stream(DataAccess.getWords().split(" "))
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet().stream()
                .forEach(System.out::println);
    }
    public void ex8(){
        List<Person> people = DataAccess.getPeople()
                .stream()
                .map(x -> new Person(x.getId(), x.getFirstName(), null, 0, null))
                .collect(Collectors.toList());
        System.out.println(people);
    }
    public void ex9(){
        Map<Integer, String> map = DataAccess.getDepartments()
                        .stream().collect(Collectors.toMap(Department::getId, Department::getName));
       double price = DataAccess.getProducts()
                .stream()
                .map(x -> new Product(x.getId(), x.getDepartmentId(), map.get(x.getDepartmentId()), x.getName(), x.getPrice(), x.getSku()))
               .collect(Collectors.toList()).stream()
               .filter(x -> x.getDepartmentName().equalsIgnoreCase("Electronics"))
               .mapToDouble(x -> x.getPrice() + 2)
               .sum();
        System.out.println(price);
    }
    public void ex10(){
        List<Cat> cats = DataAccess.getCats();
        List<PersonCat> personCats =
                DataAccess.getPeople()
                        .stream()
                        .map(x -> new PersonCat(x.getId(),x.getFirstName(),cats.stream().filter(c -> c.getId() == x.getId()).collect(Collectors.toList())))
                        .collect(Collectors.toList());
        System.out.println(personCats);
    }

}
