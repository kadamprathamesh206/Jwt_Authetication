package com.student.managment_system.main.service;

import java.util.List;

import com.student.managment_system.main.model.Student;

public interface StudentService {

	public Student registerStudent(Student student);
	
//	public Student signStudent(String email,String password);
	
	public Student updateStudent(Student student);
	
	public void deleteStudent(int id);
	
	public List<Student> getAllStudent();
	
	public Student findByEmail(String email);

}
