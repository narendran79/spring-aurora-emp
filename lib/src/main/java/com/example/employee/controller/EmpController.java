package com.example.employee.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmpController {
	
	private final EmployeeService empService;
	
	public EmpController(EmployeeService empService) {
		this.empService = empService;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/get", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<List<Employee>> getEmployees(){
		List<Employee> employee = empService.getAllEmployees();
		return ResponseEntity.ok()
				.body(employee);
	}
	
    /*@GetMapping(value = "/get/{id}")
    @ResponseBody
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id, @RequestParam(required = false) String format) {
    	Employee employee = empService.getEmployeeById(id);
    	
    	if("xml".equalsIgnoreCase(format)) {
    		return ResponseEntity.ok()
    				.contentType(MediaType.APPLICATION_XML)
    				.body(employee);
    	}
    	
        return ResponseEntity.ok()
        		.contentType(MediaType.APPLICATION_JSON)
        		.body(employee);
    }*/
	
	@GetMapping(value = "/get/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
    	Employee employee = empService.getEmployeeById(id);
    	
        return ResponseEntity.ok()
        		.body(employee);
    }
	
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void createEmployee(@RequestBody Employee employee) {
        empService.createEmployee(employee);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        employee.setId(id);
        empService.updateEmployee(employee, id);
    }
    
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteEmployee(@PathVariable int id) {
        empService.deleteEmployee(id);
        return "Employee detail got deleted";
    }
    
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "API is working!";
    }
}
