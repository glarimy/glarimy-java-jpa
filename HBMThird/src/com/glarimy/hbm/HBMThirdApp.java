package com.glarimy.hbm;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class HBMThirdApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Person krishna = new Person();
		krishna.setName("Krishna");
		krishna.setPhone(973142316);
		
		SessionFactory factory = new AnnotationConfiguration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(krishna); // T->M
			tx.commit();
			System.out.println(krishna);
			System.out
					.println("Check the table and enter any character to continue...");
			scanner.nextLine();

			tx = session.beginTransaction();
			krishna.setPhone(99455000); //M -> DB
			tx.commit();
			System.out.println(krishna);
			System.out
					.println("Check the table, update the phone number and enter any character to continue...");
			scanner.nextLine();

			tx = session.beginTransaction();
			session.refresh(krishna); //M <- DB
			tx.commit();
			System.out.println(krishna);
			System.out
					.println("Check the table and enter any character to continue...");
			scanner.nextLine();

			tx = session.beginTransaction();
			session.evict(krishna);  //M, Not in cache
			tx.commit();
			System.out.println(krishna);
			System.out
					.println("Check the table and enter any character to continue...");
			scanner.nextLine();

			
			tx = session.beginTransaction();
			session.delete(krishna); //M -> D
			tx.commit();
			System.out.println(krishna);
			System.out
					.println("Check the table and enter any character to continue...");
			scanner.nextLine();
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