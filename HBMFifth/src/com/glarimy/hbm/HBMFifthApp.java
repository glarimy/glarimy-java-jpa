package com.glarimy.hbm;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class HBMFifthApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Person krishna = new Person();
		krishna.setName(new Name("Krishna Mohan", "Koyya"));
		
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

			Person mohan = (Person) session.get(Person.class.getName(),
					new Name("Krishna Mohan", "Koyya"));
			System.out.println(mohan);
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			tx = null;
			session.close();
		}
	}
}