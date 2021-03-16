package com.wallapop.entities;

import javax.persistence.*;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	@Column(unique=true)
	private String email;
	private String name;
	private String lastName;
	private String role;
	private double saldo;
	
	private String password;
	@Transient //No almacenada en la tabla
	private String passwordConfirm;
	
	public User(String email, String name, String lastName) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		//Aquí??
		this.saldo = 100;
	}
	
	public User() {
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setCredits(double saldo) {
		this.saldo = saldo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	public String getFullName() {
		return this.name + " " + this.lastName;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

}
