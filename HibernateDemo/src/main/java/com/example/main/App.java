package com.example.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.entity.User;
import com.example.util.HibernateUtil;

public class App {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        User user = new User("John Doe", "john@example.com");
        session.save(user);

        tx.commit();
        session.close();

        System.out.println("User saved successfully!");
    }
}

