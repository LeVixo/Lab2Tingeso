package com.tingeso.employee.service;

import com.tingeso.employee.entity.Employee;
import com.tingeso.employee.repository.EmployeeRepository;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository;

  @Autowired
  RestTemplate restTemplate;

  public ArrayList<Employee> getEmployees(){
      return (ArrayList<Employee>) employeeRepository.findAll();
  }

  public Employee saveEmployee(Employee employee){
      return employeeRepository.save(employee);
  }

  public Employee getEmployeeByRut(String rut){
      return employeeRepository.findByRut(rut);
  }

  public ArrayList<Employee> getAllEmployees(){
      return (ArrayList<Employee>) employeeRepository.findAll();
  }
}
