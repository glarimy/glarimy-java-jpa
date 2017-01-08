package com.glarimy.hbm;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class HBMNinthApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Address current = new Address();
		current.setCity("Bangalore");
		current.setPin(560016);

		Address permanent = new Address();
		permanent.setCity("Tadepalligudem");
		permanent.setPin(534101);

		Address office = new Address();
		office.setCity("E-City");
		office.setPin(560100);
		
		Person krishna = new Person();
		krishna.setName("Krishna");
		krishna.getAddresses().add(current);
		krishna.getAddresses().add(permanent);
		
		Person swapnika = new Person();
		swapnika.setName("Swapnika");
		swapnika.getAddresses().add(current);
		swapnika.getAddresses().add(permanent);
		swapnika.getAddresses().add(office);

		Person person = null;
		
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
			session.save(swapnika);
			tx.commit();
			System.out.println(swapnika);
			System.out
					.println("Check the table and enter any character to continue...");
			scanner.nextLine();

			tx = session.beginTransaction();
			current.setPin(560034);
			tx.commit();
			System.out.println(krishna);
			System.out.println(swapnika);
			System.out
					.println("Check the table and enter any character to continue...");
			scanner.nextLine();
		
			tx = session.beginTransaction();
			krishna.getAddresses().remove(0);
			tx.commit();
			System.out.println(krishna);
			System.out.println(swapnika);
			System.out
					.println("Check the table and enter any character to continue...");
			scanner.nextLine();
		
			session.evict(krishna);
			
			person = (Person) session.get(Person.class.getName(), krishna
					.getId());
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			tx = null;
			session.close();
		}

		System.out.println(person);//Doesn't work
		System.out
				.println("Check the table and enter any character to continue...");
		scanner.nextLine();
	}
}