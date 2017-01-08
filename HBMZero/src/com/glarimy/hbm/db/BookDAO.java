package com.glarimy.hbm.db;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.glarimy.hbm.common.Book;

public class BookDAO {
	private Connection connection;
	private PreparedStatement createStatement;
	private PreparedStatement getStatement;
	private PreparedStatement getAllStatement;
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql//localhost:3612/glarimy";
	private static String user = "user";
	private static String password = "password";
	private String createQuery = "insert into book (id, title) values(?,?)";
	private String getQuery = "select * from book where id=?";
	private String getAllQuery = "select * from book";
	private Map<Integer, Book> cache;

	static {
		try {
			Properties properties = new Properties();
			properties.load(new FileReader("lib.properties"));
			url = properties.getProperty("url");
			user = properties.getProperty("user", "root");
			password = properties.getProperty("password", "admin");
			driver = properties.getProperty("driver");
		} catch (Exception e) {
			System.out
					.println("Failed to db properties. Applying default properties");
		}
	}

	public BookDAO() throws Exception {
		Class.forName(driver);
		connection = DriverManager.getConnection(url, user, password);
		createStatement = connection.prepareStatement(createQuery);
		getStatement = connection.prepareStatement(getQuery);
		getAllStatement = connection.prepareStatement(getAllQuery);
		cache = new HashMap<Integer, Book>();
	}

	public void create(Book book) throws Exception {
		createStatement.setInt(1, book.getId());
		createStatement.setString(2, book.getTitle());
		createStatement.executeUpdate();
		cache.put(book.getId(), book);
	}

	public Book get(int id) throws Exception {
		Book book;

		book = cache.get(id);
		if (book != null)
			return book;

		book = new Book();
		getStatement.setInt(1, id);
		ResultSet rs = getStatement.executeQuery();
		if (!rs.next())
			throw new Exception("Book Not Found");
		book.setId(rs.getInt("id"));
		book.setTitle(rs.getString("title"));
		cache.put(id, book);
		return book;
	}

	public List<Book> get() throws Exception {
		List<Book> list = new ArrayList<Book>();
		ResultSet rs = getAllStatement.executeQuery();
		while (rs.next()) {
			Book book = new Book();
			book.setId(rs.getInt("id"));
			book.setTitle(rs.getString("title"));
			list.add(book);
		}
		return list;
	}

	public void close() throws Exception {
		getAllStatement.close();
		getStatement.close();
		createStatement.close();
		connection.close();
	}
}