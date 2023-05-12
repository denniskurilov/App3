package com.denniskurilov.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {
    
	@Autowired 
    EmployeeRepository repository;
    
    public void save(Employee employee) {
    	repository.save(employee);
    }
     
    public List<Employee> getAll() {
        return (List<Employee>) repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
     
    public Employee get(long id) {
        return repository.findById(id).get();
    }
     
    public void delete(long id) {
    	repository.deleteById(id);
    }
     
    public List<Employee> search(String keyword) {
        return repository.search(keyword);
    }
    
}