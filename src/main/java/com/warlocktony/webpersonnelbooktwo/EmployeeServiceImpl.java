package com.warlocktony.webpersonnelbooktwo;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee>employeeMap;
    private static final int EMPLOYEE_MAX_SIZE = 10;

    public EmployeeServiceImpl(){
        this.employeeMap=new HashMap<>();

    }
    @Override
    public Employee addEmployee(String firstName, String lastName){

        if(employeeMap.size()==EMPLOYEE_MAX_SIZE){
            throw new EmployeeStorageIsFullException("staff limit is over");
        }
        Employee employee = new Employee(firstName,lastName);
        String key = (firstName + lastName);
        if(employeeMap.containsKey(key)){
            throw new EmployeeAlreadyAddedException("this staff already added");
        }
        employeeMap.put(key, employee);
        return employee;
    }
    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = employeeMap.remove(firstName + lastName);
        if(employee == null){
            throw new EmployeeNotFoundException("staff not found");
        }
        return employee;

    }
    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = employeeMap.get(firstName + lastName);
        if(employee == null){
            throw new EmployeeNotFoundException("staff not found");
        }
        return employee;
    }
    @Override
    public Collection<Employee> findAll(){
        return employeeMap.values();
    }



}
