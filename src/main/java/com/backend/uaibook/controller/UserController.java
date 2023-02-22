package com.backend.uaibook.controller;

import com.backend.uaibook.entity.Employee;
import com.backend.uaibook.entity.Users;
import com.backend.uaibook.repository.EmployeeRepository;
import com.backend.uaibook.repository.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    public UserController(UserRepository userRepository,
                          EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/user")
    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/empl")
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

}
