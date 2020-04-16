package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.persist.Contact;
import ru.geekbrains.persist.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Example1 {

    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = factory.createEntityManager();

        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("1", "1"));
        contacts.add(new Contact("2", "2"));

        em.getTransaction().begin();
        em.persist(new Person("hhh", "mmm", LocalDate.of(2020, 1, 1), contacts));
        em.getTransaction().commit();

        Person person = em.createQuery("from Person p where p.firstName = 'hhh'", Person.class).getSingleResult();
        System.out.println(person);
    }
}
