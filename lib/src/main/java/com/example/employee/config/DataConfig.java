package com.example.employee.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {
	
	@Autowired
	private DataSource dataSource;

	public void testConnection() {
	    try (Connection connection = dataSource.getConnection()) {
	        if (connection != null) {
	            System.out.println("Connection to Aurora DB successful!");
	            try (Statement stmt = connection.createStatement()) {
	                stmt.execute("SELECT 1");
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Connection failed: " + e.getMessage());
	    }
	}
}
