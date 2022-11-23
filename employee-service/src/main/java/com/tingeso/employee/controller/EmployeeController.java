package com.tingeso.employee.controller;

import com.tingeso.employee.entity.Employee;
import com.tingeso.employee.entity.ExtraHours;
import com.tingeso.employee.entity.Justificative;
import com.tingeso.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee")
    public String getEmployee(Model model){
        List<Employee> employeeEntities = (List<Employee>) employeeService.getEmployees();
        model.addAttribute("employee", employeeEntities);
        return "employee";
    }
}
