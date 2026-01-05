package com.example.main;

import java.util.List;

import com.example.dao.ProductDao;
import com.example.model.Product;

public class App {

 public static void main(String[] args) {

  ProductDao dao = new ProductDao();

  // SKILL 2 : CRUD
  dao.save(new Product("Laptop", "Electronics", 60000, 5));
  dao.save(new Product("Mobile", "Electronics", 30000, 10));
  dao.save(new Product("Shoes", "Fashion", 2500, 20));
  dao.save(new Product("TV", "Electronics", 45000, 6));
  dao.save(new Product("Chair", "Furniture", 5000, 12));

  Product p = dao.getById(1L);
  System.out.println("Fetched product: " + p.getName());

  dao.update(1L, 58000, 7);
  dao.delete(2L);

  
   //  SKILL 3 : HQL 
   

  System.out.println("\nProducts sorted by price (ASC):");
  List<Product> asc = dao.sortByPriceAsc();
  asc.forEach(pr ->
  System.out.println(pr.getName() + " - " + pr.getPrice()));

  System.out.println("\nProducts sorted by price (DESC):");
  dao.sortByPriceDesc().forEach(pr ->
  System.out.println(pr.getName() + " - " + pr.getPrice()));

  System.out.println("\nFirst 3 products (Pagination):");
  dao.firstThree().forEach(pr ->
  System.out.println(pr.getName()));

  System.out.println("\nTotal products: " + dao.countProducts());

  
   Object[] minMax = dao.minMaxPrice();
  System.out.println("Min price: " + minMax[0]);
  System.out.println("Max price: " + minMax[1]);
  System.out.println("\nGROUP BY Description:");
  dao.groupByDescription().forEach(o ->
  System.out.println(o[0] + " -> " + o[1]));

  System.out.println("\nPrice Range 2000 - 50000:");
  dao.filterByPriceRange(2000, 50000)
  .forEach(pr-> System.out.println(pr.getName()));

  System.out.println("\nNames starting with 'L':");
  dao.nameStartsWith("L").forEach(pr -> System.out.println(pr.getName())); 

  System.out.println("\nDONE");
 }
}