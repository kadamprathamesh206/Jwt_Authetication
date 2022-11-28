package com.student.managment_system.main.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Student implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	private String password;
	private String phone_no;
	@Transient
	private Collection<? extends GrantedAuthority> authorites;
	@ElementCollection
	private List<String> Role;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	
	public List<String> getRole() {
		return Role;
	}
	public void setRole(List<String> role) {
		Role = role;
	}
	public Collection<? extends GrantedAuthority> getAuthorites() {
		
		return authorites;
	}
	public void setAuthorites(Collection<? extends GrantedAuthority> authorites) {
		this.authorites = authorites;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
//		return Role.stream().map(role-> new SimpleGrantedAuthority(email)).collect(Collectors.toList());
		return this.authorites;
	}
	public Student(String email, String password, List<String> role) {
		super();
		this.email = email;
		this.password = password;
		Role = role;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	public Student(String email, String password, Collection<? extends GrantedAuthority> authorites) {
		super();
		this.email = email;
		this.password = password;
		this.authorites = authorites;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int id, String name, String email, String password, String phone_no) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone_no = phone_no;
	}
	

}
