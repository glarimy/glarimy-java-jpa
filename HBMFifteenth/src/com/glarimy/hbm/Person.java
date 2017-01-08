package com.glarimy.hbm;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({ 
	@NamedQuery(
			name = "selectCityPersons", 
			query = "from Person p inner join fetch p.addresses as a where a.city=:city"
		) 
})
@NamedNativeQueries({ 
	@NamedNativeQuery(
			name = "selectNativeCityPersons", 
			query = "select * from person p, address a, person_address pa where p.id=pa.Person_id and pa.addresses_id=a.id and a.city=:city", 
			resultClass = Person.class
		) 
})
public class Person {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Address> addresses;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", addresses="
				+ addresses + "]";
	}

}
