package com.student.managment_system.main.config;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.student.managment_system.main.model.Student;
import com.student.managment_system.main.repo.StudentRepo;

@Service
public class MyUserDetailService  implements UserDetailsService{

	@Autowired
	StudentRepo studentRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Student existing_student= this.studentRepo.findByEmail(email);
		if(existing_student==null) {
			throw new UsernameNotFoundException("student not register");
		}else {
			return new Student(existing_student.getEmail(), existing_student.getPassword(), existing_student.getRole().stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
		}
	}
}








