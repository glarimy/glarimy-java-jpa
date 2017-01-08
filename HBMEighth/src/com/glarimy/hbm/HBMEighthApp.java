package com.glarimy.hbm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class HBMEighthApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Address current = new Address();
		current.setCity("Bangalore");
		current.setPin(560016);

		Address permanent = new Address();
		permanent.setCity("Tadepalligudem");
		permanent.setPin(534101);

		List<Address> addresses = new ArrayList<Address>();
		addresses.add(current);
		addresses.add(permanent);

		Person person = null;
		Person krishna = new Person();
		krishna.setName("Krishna");
		krishna.setAddresses(addresses);

		SessionFactory factory = new AnnotationConfiguration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(krishna);
			tx.commit();
			System.out.println(krishna);
			System.out
					.println("Check the table and enter any character to continue...");
			scanner.nextLine();

			tx = session.beginTransaction();
			current.setPin(560034);
			tx.commit();
			System.out.println(krishna);
			System.out
					.println("Check the table and enter any character to continue...");
			scanner.nextLine();

			tx = session.beginTransaction();
			// session.delete(current); //doesn't work
			// krishna.getAddresses().remove(current); //works
			krishna.getAddresses().remove(0); // works
			tx.commit();
			System.out.println(krishna);
			System.out
					.println("Check the table and enter any character to continue...");
			scanner.nextLine();

			tx = session.beginTransaction();
			krishna.getAddresses().remove(0);
			tx.commit();
			System.out.println(krishna);
			System.out
					.println("Check the table and enter any character to continue...");
			scanner.nextLine();

			session.evict(krishna);
			person = (Person) session.get(Person.class.getName(),
					krishna.getId());
			if (krishna != person)
				System.out.println("The person is in cache and krishna just only memory ... too different");
			Hibernate.initialize(person.getAddresses());  
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			tx = null;
			session.close();
		}

		int pin = person.getAddresses().get(0).getPin();
		
		System.out
				.println("Check the table and enter any character to continue...");
		scanner.nextLine();
		
		
		
		
		
		
		
		
	}
}