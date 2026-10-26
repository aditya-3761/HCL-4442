package org.abes.hrmsdatabase.repository;

import org.abes.hrmsdatabase.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}