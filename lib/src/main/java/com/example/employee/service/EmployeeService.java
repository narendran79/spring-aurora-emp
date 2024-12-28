package com.example.employee.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.employee.model.Employee;

@Service
public class EmployeeService {

	private final JdbcTemplate jdbcTemplate;

    public EmployeeService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM Emp.employees";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("dept"))
        );
    }

    @SuppressWarnings("deprecation")
	public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("dept"))
        );
    }
    

    public void createEmployee(Employee employee) {
        String sql = "INSERT INTO employees (id, name, dept) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, employee.getId(), employee.getName(), employee.getDept());
    }

    public void updateEmployee(Employee employee, int id) {
    	String empid = String.valueOf(id);
        String sql = "UPDATE employees SET name = ?, dept = ? WHERE id = ?";
        jdbcTemplate.update(sql, employee.getName(), employee.getDept(), empid);
    }

    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
