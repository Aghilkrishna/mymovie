
package com.app.mymovieserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.mymovieserver.entities.Employee;


/**
 * @author aghil
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>{


}
