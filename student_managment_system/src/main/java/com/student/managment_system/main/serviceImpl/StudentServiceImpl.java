package com.student.managment_system.main.serviceImpl;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.student.managment_system.main.config.JwtUtil;
import com.student.managment_system.main.model.Student;
import com.student.managment_system.main.repo.StudentRepo;
import com.student.managment_system.main.service.StudentService;
import com.student.managment_system.main.util.EncryptAndDecrypt;

@Service
public class StudentServiceImpl implements StudentService{


	@Autowired
	StudentRepo studentRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
    
    @Autowired
    JwtUtil jwtutil;
   
	/*
	 * 
	 *  registerStudent is the method use for register new Student
	 * 
	 * 
	 */

	@Override
	public Student registerStudent(Student student) {
		  Student new_student=new Student();
		  new_student.setEmail(student.getEmail());
		  new_student.setName(student.getName());
		  new_student.setPassword(passwordEncoder.encode(student.getPassword()));
		  new_student.setPhone_no(student.getPhone_no());
		  new_student.setRole(student.getRole());
		Student saved_student=this.studentRepo.save(new_student);
		return saved_student;
	}

	/*
	 * 
	 * 
	 *  updateStudent method use for update existing student 
	 */

	@Override
	public Student updateStudent(Student student) {
		Student existting_student=this.studentRepo.findById(student.getId()).orElseThrow(null);
		existting_student.setEmail(student.getEmail());
		existting_student.setName(student.getName());
		existting_student.setPassword(passwordEncoder.encode(student.getPassword()));
		existting_student.setPhone_no(student.getPhone_no());
		Student updated_student=  this.studentRepo.save(existting_student);
		return updated_student;
	}
	
	/*
	 * 
	 * deletStudent method use for deleting student
	 * 
	 */

	@Override
	public void deleteStudent(int id) {
		Student existting_student=this.studentRepo.findById(id).orElseThrow(null);
		this.studentRepo.delete(existting_student);

	}

	/*
	 * getAllStudent method is use for fetching all the student from the database
	 * 
	 *  
	 */
	@Override
	public List<Student> getAllStudent() {
		List<Student> studentList=	this.studentRepo.findAll();
		return studentList;
	}

	
	/*
	 * 
	 * 
	 * findByEmail method is use for finding the student from email
	 * 
	 */
	@Override
	public Student findByEmail(String email) {
		return  this.studentRepo.findByEmail(email);
	}


}
