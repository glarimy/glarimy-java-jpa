package com.glarimy.hbm;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class HBMTwelvthApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Person krishna = new Person();
		krishna.setId(1);
		krishna.setName("Krishna");

		Employee swapnika = new Employee();
		swapnika.setId(2);
		swapnika.setName("Swapnika");
		swapnika.setSalary(30000);

		SessionFactory factory = new AnnotationConfiguration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(krishna);
			session.save(swapnika);
			
			Query query = session.createQuery("from com.glarimy.hbm.Person");
			List<Person> persons = query.list();
			for (Person p : persons)
				System.out.println(p);
			tx.commit();
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