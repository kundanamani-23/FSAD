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
  System.out.println("\nDone");
 }
} 
