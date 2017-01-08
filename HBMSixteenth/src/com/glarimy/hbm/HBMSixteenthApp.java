package com.glarimy.hbm;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transaction.ResinTransactionManagerLookup;

public class HBMSixteenthApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Address address = new Address();
		address.setCity("Bangalore");
		address.setPin(560016);

		Person krishna = new Person();
		krishna.setId(1);
		krishna.setName("Krishna");
		krishna.setAddress(address);

		Employee swapnika = new Employee();
		swapnika.setId(2);
		swapnika.setName("Swapnika");
		swapnika.setSalary(30000);
		swapnika.setAddress(address);

		SessionFactory factory = new AnnotationConfiguration().configure()
				.buildSessionFactory();
		Session session = null;
		Transaction tx = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(krishna);
			session.save(swapnika);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			tx = null;
			session.close();
		}

		// criteria, alias, restriction, projections
		session = factory.openSession();
		Criteria criteria = session.createCriteria(Person.class);
		// select * from person
		criteria.add(Restrictions.like("name", "%k%"));
		// select * from person where name like '%k%
		criteria.setMaxResults(5);
		// select * from person where name like '%k% limit 5
		criteria.addOrder(Order.desc("name"));
		// select * from person where name like '%k% limit 5 order by name desc
		criteria.createAlias("address", "location").add(
				Restrictions.eq("location.city", "Bangalore"));
		// select * from person p, address location where
		// p.address_id=location.id and location.city = 'bangalore' and p.name like
		// '%k% limit 5 order by p.name desc
		criteria.setProjection(Projections.max("location.pin"));
		// select max(location.pin) from person p, address location where
		// p.address_id=location.id and location.city = 'bangalore' and p.name like
		// '%k% limit 5 order by p.name desc

		// List<Person> persons = criteria.list();
		System.out.println(criteria.uniqueResult());
		session.close();

		// example
		session = factory.openSession();
		criteria = session.createCriteria(Person.class);
		Person example = new Person();
		Address location = new Address();
		location.setPin(560016);
		example.setAddress(location);
		criteria.add(Example.create(example));
		List<Person> persons = criteria.list();
		System.out.println(persons);

	}
}