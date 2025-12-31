package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.model.Product;
import com.example.util.HibernateUtil;

public class ProductDao {

 // ================= SKILL 2 : CRUD =================

 // CREATE
 public void save(Product p) {
  Session s = HibernateUtil.getSessionFactory().openSession();
  Transaction tx = s.beginTransaction();
  s.save(p);
  tx.commit();
  s.close();
 }

 // READ
 public Product getById(Long id) {
  Session s = HibernateUtil.getSessionFactory().openSession();
  Product p = s.get(Product.class, id);
  s.close();
  return p;
 }

 // UPDATE
 public void update(Long id, double price, int qty) {
  Session s = HibernateUtil.getSessionFactory().openSession();
  Transaction tx = s.beginTransaction();

  Product p = s.get(Product.class, id);
  if (p != null) {
   p.setPrice(price);
   p.setQuantity(qty);
  }

  tx.commit();
  s.close();
 }

 // DELETE
 public void delete(Long id) {
  Session s = HibernateUtil.getSessionFactory().openSession();
  Transaction tx = s.beginTransaction();

  Product p = s.get(Product.class, id);
  if (p != null) {
   s.delete(p);
  }

  tx.commit();
  s.close();
 }
}