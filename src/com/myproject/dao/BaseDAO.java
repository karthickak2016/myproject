package com.myproject.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseDAO {

	Connection connection;
	
	public BaseDAO() {
		connection = null;
	}

	public Connection getConnection() {
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/postgres",
							"postgres", "root");
			if (connection != null) {
				System.out.println("Connection Success");
			} else {
				System.out.println("Connection Failure");
			}

		} catch (Exception exception) {
			System.out.println("Error: while getting Database Connection "
					+ exception);
		}
		return connection;
	}

	/*
	 * private DataSource dataSource;
	 * 
	 * public BaseDAO() { dataSource = null; }
	 * 
	 * public void setDataSource(DataSource dataSource) { this.dataSource =
	 * dataSource; }
	 * 
	 * public Connection getConnection() { Connection connection = null;
	 * 
	 * try { connection = dataSource.getConnection(); if (connection != null) {
	 * System.out.println("Connection Success"); } else {
	 * System.out.println("Connection Failure"); }
	 * 
	 * } catch (Exception exception) {
	 * System.out.println("Error: while getting Database Connection " +
	 * exception); } return connection; }
	 */

	public void close(PreparedStatement pstmt, Connection conn, ResultSet rs,
			Statement stmt, CallableStatement csmt) {

		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (csmt != null) {
				csmt.close();
			}

		} catch (Exception exception) {
			System.out.println("Error While Closing Database Connection "
					+ exception);
		}
	}

}
