package com.glarimy.hbm.biz;

import java.util.List;

import com.glarimy.hbm.common.Book;
import com.glarimy.hbm.common.Library;
import com.glarimy.hbm.common.LibraryException;
import com.glarimy.hbm.db.BookDAO;

public class SimpleLibrary implements Library {
	private BookDAO dao;
	
	public SimpleLibrary() throws Exception {
		dao = new BookDAO();
	}

	@Override
	public void add(Book book) throws LibraryException {
		if (book == null || book.getId() < 1
				|| book.getTitle().trim().length() == 0)
			throw new LibraryException("Invalid Book");
		try {
			dao.get(book.getId());
			throw new LibraryException("Duplicate Book Found");
		} catch (Exception e) {
			try {
				dao.create(book);
			} catch (Exception oe) {
				throw new LibraryException("Failed to add the book");
			}
		}
	}

	@Override
	public List<Book> list() throws LibraryException {
		try {
			return dao.get();
		} catch (Exception e) {
			throw new LibraryException("Failed to fetch the books");
		}
	}

	@Override
	protected void finalize() throws Throwable {
		dao.close();
	}

}