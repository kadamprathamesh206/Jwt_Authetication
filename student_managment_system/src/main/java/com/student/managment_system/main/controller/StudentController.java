package com.student.managment_system.main.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.managment_system.main.config.JwtUtil;
import com.student.managment_system.main.model.Student;
import com.student.managment_system.main.serviceImpl.StudentServiceImpl;
import com.student.managment_system.main.util.ApiResponse;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentServiceImpl studentServiceImpl;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtil jwtUtil;

	@PostMapping("/register")
	public ResponseEntity<?> registerStudent(@RequestBody Student student){
		Student existing_student=this.studentServiceImpl.findByEmail(student.getEmail());
		if(existing_student!= null) {
			return new ResponseEntity<>(new ApiResponse("Email already present"),HttpStatus.BAD_REQUEST);
		}
		Student saved_student=this.studentServiceImpl.registerStudent(student);
		return new ResponseEntity<>(new ApiResponse("Student Resiter suceessfully", saved_student),HttpStatus.OK);
	}


	@PostMapping("/signIn")
	public ResponseEntity<?> signIn(@RequestParam("email") String email,@RequestParam ("password") String password) {
		try {
			Authentication authentication=    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
			Student student=  (Student) authentication.getPrincipal();
			return new ResponseEntity<>(new ApiResponse("Student signIn successfully",jwtUtil.generateToken(student)),HttpStatus.OK);
		}catch (BadCredentialsException badCredentialsException) {
			return new ResponseEntity<>(new ApiResponse("Student is unauthorize",null),HttpStatus.UNAUTHORIZED);
		}
	}




	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<?> deletStudent(@PathVariable("id") int id){
		this.studentServiceImpl.deleteStudent(id);
		return new ResponseEntity<>(new ApiResponse("Student deleted successfully"),HttpStatus.OK);
	}



	@PutMapping("/updateStudent")
	public ResponseEntity<?> updateStudent(@RequestBody Student student){
		return new ResponseEntity<>(new ApiResponse("Student updated successfully",this.studentServiceImpl.updateStudent(student)),HttpStatus.OK);
	}




	@GetMapping("getAllStudent")
	public ResponseEntity<?> getAllStudent(){

		return new ResponseEntity<>(new ApiResponse("StudentList Display successfully", this.studentServiceImpl.getAllStudent()),HttpStatus.OK);     
	}




}
