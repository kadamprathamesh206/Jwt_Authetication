package com.student.managment_system.main.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.managment_system.main.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{

	public Student findByEmailAndPassword(String email,String password);
	
	public Student findByEmail(String email);
}
