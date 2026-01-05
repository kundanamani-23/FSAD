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

 
 
 //  SKILL 3 : HQL 

 // Sort by price ascending
 public List<Product> sortByPriceAsc() {
  Session s = HibernateUtil.getSessionFactory().openSession();
  Query<Product> q =
    s.createQuery("from Product order by price asc", Product.class);
  List<Product> list = q.list();
  s.close();
  return list;
 }

 // Sort by price descending
 public List<Product> sortByPriceDesc() {
  Session s = HibernateUtil.getSessionFactory().openSession();
  Query<Product> q =
    s.createQuery("from Product order by price desc", Product.class);
  List<Product> list = q.list();
  s.close();
  return list;
 }

 // Sort by quantity (highest first)
 public List<Product> sortByQuantityDesc() {
  Session s = HibernateUtil.getSessionFactory().openSession();
  Query<Product> q =
    s.createQuery("from Product order by quantity desc", Product.class);
  List<Product> list = q.list();
  s.close();
  return list;
 }


 // Pagination – first 3 products
 public List<Product> firstThree() {
  Session s = HibernateUtil.getSessionFactory().openSession();
  Query<Product> q = s.createQuery("from Product", Product.class);
  q.setFirstResult(0);
  q.setMaxResults(3);
  List<Product> list = q.list();
  s.close();
  return list;
 }

 // Aggregate – count
 public long countProducts() {
  Session s = HibernateUtil.getSessionFactory().openSession();
  Query<Long> q =s.createQuery("select count(p) from Product p", Long.class);
  long count = q.uniqueResult();
  s.close();
  return count;
 }

 // Min & Max price
 public Object[] minMaxPrice() 
 {
  Session s = HibernateUtil.getSessionFactory().openSession();
  Query<Object[]> q = s.createQuery("select min(price), max(price) from Product",
    Object[].class);
  Object[] result = q.uniqueResult();
  s.close();
  return result;
 }

 //Group by description
 public List<Object[]> groupByDescription() {
  Session s = HibernateUtil.getSessionFactory().openSession();
  Query<Object[]> q = s.createQuery(
    "select description, count(*) from Product group by description",
    Object[].class
    );
  List<Object[]> list = q.list();
  s.close();
  return list;
 }

 //price range
 public List<Product> filterByPriceRange(double min, double max) {
  Session s = HibernateUtil.getSessionFactory().openSession();
  Query<Product> q = s.createQuery(
    "from Product where price between :min and :max",
    Product.class
    );
  q.setParameter("min", min);
  q.setParameter("max", max);
  List<Product> list = q.list();
  s.close();
  return list;
 }

 //names starting with letters
 public List<Product> nameStartsWith(String prefix) {
  Session s = HibernateUtil.getSessionFactory().openSession();
  Query<Product> q = s.createQuery(
    "from Product where name like :p",
    Product.class
    );
  q.setParameter("p", prefix + "%");
  List<Product> list = q.list();
  s.close();
  return list;
 }



}