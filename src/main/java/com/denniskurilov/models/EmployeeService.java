package com.denniskurilov.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {
    @Autowired EmployeeRepository repo;
    
    public void save(Employee employee) {
        repo.save(employee);
    }
     
    public List<Employee> getAll() {
        return (List<Employee>) repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
     
    public Employee get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
     
    public List<Employee> search(String keyword) {
        return repo.search(keyword);
    }
    
}