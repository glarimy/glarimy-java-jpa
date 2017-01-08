package com.glarimy.hbm.common;

import java.util.List;

public interface Library {
	public void add(Book book) throws LibraryException;

	public List<Book> list() throws LibraryException;
}
