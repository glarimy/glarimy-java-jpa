package com.glarimy.hbm.client;

import java.util.List;

import com.glarimy.hbm.biz.SessionLibrary;
import com.glarimy.hbm.biz.SimpleLibrary;
import com.glarimy.hbm.common.Book;
import com.glarimy.hbm.common.Library;

public class HBMZeroApplication {
	public static void main(String[] args) throws Exception {

		Book jdbc = new Book();
		jdbc.setId(10231987);
		jdbc.setTitle("JDBC");

		Book hbm = new Book();
		hbm.setId(22071972);
		hbm.setTitle("Hibernate");

		//Library library = new SimpleLibrary();
		Library library = new SessionLibrary();

		System.out.println("Adding the books into library");
		library.add(jdbc);
		library.add(hbm);

		System.out.println("Listing the books from library");
		List<Book> books = library.list();
		for (Book book : books)
			System.out.println(book);
	}
}