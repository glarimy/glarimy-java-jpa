package com.glarimy.hbm;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class HBMSecondApp {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Person swapnika = new Person();
		swapnika.setName("Swapnika");
		swapnika.setDob(new Date(7, 26, 2008));
		swapnika.setIndian(true);
		swapnika.setPhone(973142316);
		swapnika.setHeight(5.6);
		swapnika.setProfile("Senior Software Engineering working at Bangalore");

		Person krishna = new Person();
		krishna.setName("Krishna");
		krishna.setDob(new Date(7, 22, 1972));
		krishna.setIndian(true);
		krishna.setPhone(973142316);
		krishna.setHeight(5.8);
		krishna.setProfile("Principle Consultant at Glarimy Technology Services");

		SessionFactory factory = new AnnotationConfiguration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		try {
			session.save(swapnika);
			session.save(krishna);
			Person friend = (Person) session.get(Person.class.getName(),
					swapnika.getId());
			tx.commit();
			System.out.println(friend);
			if (friend == swapnika)
				System.out
						.println("There is only instance of managed object in the cache, but with two references");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			tx = null;
			session.close();
		}
	}
}