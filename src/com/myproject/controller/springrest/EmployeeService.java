package com.myproject.controller.springrest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.dao.BaseDAO;
import com.myproject.model.Employee;

@RestController
@RequestMapping("/emp")
public class EmployeeService {
	
	@Autowired
	private BaseDAO baseDAO;
	
	public EmployeeService() {
	}
	
	public EmployeeService(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	@RequestMapping(value = "/getemp/{empid}", method=RequestMethod.GET)
	public @ResponseBody Employee getEmpDetails(
			@PathVariable(value = "empid") int empid) {
		Connection connection = null;
		Employee employee = new Employee();
		try {			
			connection = baseDAO.getConnection();
			connection.setAutoCommit(false);
			PreparedStatement sql = connection.prepareStatement("SELECT ID,NAME,AGE,ADDRESS,SALARY FROM COMPANY Where ID=?");
			sql.setInt(1, empid);
			ResultSet rs = sql.executeQuery();
			while (rs.next())
			{
				employee.setEmpid(empid);
				employee.setName(rs.getString(2));
				employee.setAge(rs.getInt(3));
				employee.setAddress(rs.getString(4));
				employee.setSalary(rs.getInt(5));
			}
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	@RequestMapping(value = "/addemployee", method=RequestMethod.POST)
	public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee) {
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = baseDAO.getConnection();
			connection.setAutoCommit(false);
			stmt = connection.createStatement();
			String sql = "INSERT into COMPANY(ID,NAME,AGE,ADDRESS,SALARY)"
					+ "VALUES ( "+ employee.getEmpid() +", "+"'"+employee.getName()+"'"+", "+employee.getAge()+", "+"'"+ employee.getAddress()+"'" +", "+ employee.getSalary() +");";
			stmt.executeUpdate(sql);
			stmt.close();
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
		
	@RequestMapping(value = "/updateemp", method=RequestMethod.POST)
	public ResponseEntity<Employee> updateEmpDetails(@RequestBody Employee employee) {
		Connection connection = null;
		try {			
			connection = baseDAO.getConnection();
			connection.setAutoCommit(false);
			PreparedStatement sql = connection.prepareStatement("UPDATE COMPANY SET name = ?, age = ?, address = ?, salary = ? WHERE id = ?");
			sql.setString(1, employee.getName());
			sql.setInt(2, employee.getAge());
			sql.setString(3, employee.getAddress());
			sql.setInt(4, employee.getSalary());
			sql.setInt(5, employee.getEmpid());
			sql.executeUpdate();
			sql.close();
			
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteemp", method=RequestMethod.POST)
	public ResponseEntity<Employee> deleteEmpDetails(@RequestBody Employee employee) {
		Connection connection = null;
		try {			
			connection = baseDAO.getConnection();
			connection.setAutoCommit(false);
			PreparedStatement sql = connection.prepareStatement("delete from COMPANY WHERE id = ?");
			sql.setInt(1, employee.getEmpid());
			sql.executeUpdate();
			sql.close();			
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

}