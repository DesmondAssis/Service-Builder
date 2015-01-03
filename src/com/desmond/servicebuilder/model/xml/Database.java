package com.desmond.servicebuilder.model.xml;

public class Database {
	private String jdbcUrl;
	private String username;
	private String password;
	private String driverClass;

	public Database() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Database(String jdbcUrl, String username, String password,
			String driverClass) {
		super();
		this.jdbcUrl = jdbcUrl;
		this.username = username;
		this.password = password;
		this.driverClass = driverClass;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	@Override
	public String toString() {
		return "Database [jdbcUrl=" + jdbcUrl + ", username=" + username
				+ ", password=" + password + ", driverClass=" + driverClass
				+ "]";
	}

}
