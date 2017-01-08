package com.glarimy.hbm.db;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Session {
	private Connection connection;
	private static String driver;
	private static String url;
	private static String user;
	private static String password;

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

	public Session() throws Exception {
		Class.forName(driver);
		connection = DriverManager.getConnection(url, user, password);
	}

	public void create(Object o) throws Exception {
		Class claz = o.getClass();
		String query = "insert into " + claz.getSimpleName().toLowerCase()
				+ "(";
		Method[] methods = claz.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("get")) {
				String field = method.getName().substring(3);
				query += field.toLowerCase() + ",";
			}
		}
		query = query.substring(0, query.length() - 1) + ") values (";

		for (Method method : methods) {
			if (method.getName().startsWith("get")) {
				Class type = method.getReturnType();
				Object value = method.invoke(o);
				if (type == int.class)
					query += ((Integer) value).intValue() + ",";
				if (type == String.class)
					query += "'" + (String) value + "',";
			}
		}
		query = query.substring(0, query.length() - 1) + ")";
		Statement statement = connection.createStatement();
		statement.execute(query);
		//insert into book (id, title) values (10, 'JDBC')
	}

	public Object get(Class claz, int id) throws Exception {
		Method[] methods = claz.getDeclaredMethods();
		String query = "select * from " + claz.getSimpleName() + " where id="
				+ id;
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		if (rs.next() == false)
			throw new Exception("No records found");
		Object o = claz.newInstance();
		for (Method method : methods) {
			if (method.getName().startsWith("get")) {
				String field = method.getName().substring(3);
				Class type = method.getReturnType();
				if (type == int.class) {
					Method setter = claz.getMethod("set" + field, int.class);
					setter.invoke(o, rs.getInt(field));
				}
				if (type == String.class) {
					Method setter = claz.getMethod("set" + field, String.class);
					setter.invoke(o, rs.getString(field));
				}
			}
		}
		return o;
	}

	public List get(Class claz) throws Exception {
		List list = new ArrayList();
		Method[] methods = claz.getDeclaredMethods();
		String query = "select * from " + claz.getSimpleName();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			Object o = claz.newInstance();
			for (Method method : methods) {
				if (method.getName().startsWith("get")) {
					String field = method.getName().substring(3);
					Class type = method.getReturnType();
					if (type == int.class) {
						Method setter = claz
								.getMethod("set" + field, int.class);
						setter.invoke(o, rs.getInt(field));
					}
					if (type == String.class) {
						Method setter = claz.getMethod("set" + field,
								String.class);
						setter.invoke(o, rs.getString(field));
					}
				}
			}
			list.add(o);
		}
		return list;
	}

	public void close() throws Exception {
		connection.close();
	}
}