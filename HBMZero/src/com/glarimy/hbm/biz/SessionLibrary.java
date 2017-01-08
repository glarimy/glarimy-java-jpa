package com.glarimy.hbm.biz;

import java.util.List;

import com.glarimy.hbm.common.Book;
import com.glarimy.hbm.common.Library;
import com.glarimy.hbm.common.LibraryException;
import com.glarimy.hbm.db.Session;

public class SessionLibrary implements Library {
	private Session session;
	
	public SessionLibrary() throws Exception {
		session = new Session();
	}

	@Override
	public void add(Book book) throws LibraryException {
		if (book == null || book.getId() < 1
				|| book.getTitle().trim().length() == 0)
			throw new LibraryException("Invalid Book");
		try {
			session.get(Book.class, book.getId());
			throw new LibraryException("Duplicate Book Found");
		} catch (Exception e) {
			try {
				session.create(book);
			} catch (Exception oe) {
				throw new LibraryException("Failed to add the book");
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> list() throws LibraryException {
		try {
			return session.get(Book.class);
		} catch (Exception e) {
			throw new LibraryException("Failed to fetch the books");
		}
	}

	@Override
	protected void finalize() throws Throwable {
		session.close();
	}

}