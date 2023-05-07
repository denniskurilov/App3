package com.denniskurilov.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value = "SELECT e FROM Employee e WHERE UPPER(e.firstName) LIKE '%' || UPPER(:keyword) || '%'"
			+ " OR UPPER(e.lastName) LIKE '%' || UPPER(:keyword) || '%'"
			+ " OR UPPER(e.email) LIKE '%' || UPPER(:keyword) || '%'"
			+ " ORDER BY e.id")
	public List<Employee> search(@Param("keyword") String keyword);

}
