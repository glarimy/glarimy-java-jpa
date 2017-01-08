package com.glarimy.hbm;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HBMFifteenth {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory factory = new AnnotationConfiguration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Query query;

		query = session.createQuery("from " + Person.class.getName());
		List<Person> allPersons = query.list();
		
		query = session.createQuery("select person.id, person.name from "
				+ Person.class.getName() + " person");
		List<Object[]> projectedRecords = query.list();

		query = session
				.createQuery(
						"from Employee employee where employee.name=? and employee.salary > :salary")
				.setDouble("salary", 10000).setString(0, "Krishna");
		List<Employee> restrictedEmployees = query.list();

		query = session
				.createQuery("from Person person inner join fetch person.addresses as addresses");
		List<Person> joinedPersons = query.list();

		List<Person> cityPeople = session.getNamedQuery("selectCityPersons")
				.setString("city", "Bangalore").list();

		List<Person> nativeCityPeople = session
				.getNamedQuery("selectNativeCityPersons")
				.setString("city", "Tadepalligudem").list();

		session.getTransaction().commit();
		session.close();

		System.out.println("ALL PERSONS");
		try {
			for (Person person : allPersons)
				System.out.println(person);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("PROJECTED RECORDS");
		try {
			for (Object[] record : projectedRecords) {
				for (Object field : record) {
					System.out.println(field);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("RESTRICTED EMPLOYEES");
		try {
			for (Employee employee : restrictedEmployees)
				System.out.println(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
/*
		System.out.println("JOINED PERSONS");
		try {
			for (Person person : joinedPersons)
				System.out.println(person);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("CITY PEOPLE");
		try {
			for (Person person : cityPeople)
				System.out.println(person);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("NATIVE CITY PEOPLE");
		try {
			for (Person person : nativeCityPeople)
				System.out.println(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
	*/
	}
}
