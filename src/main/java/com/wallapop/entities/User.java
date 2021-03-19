package com.wallapop.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true)
	private String email;
	private String name;
	private String lastName;
	private String role;
	private double saldo;

	private String password;
	@Transient // No almacenada en la tabla
	private String passwordConfirm;

	// Cada usuario puede tener varias ofertas pero no viceversa
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<ProductOffer> offers = new HashSet<ProductOffer>();

	// Cada compra pertenece a un único usuario, pero un usuario puede realizar más
	// de una compra
	@OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
	private Set<ProductPurchase> purchased = new HashSet<ProductPurchase>();

	public User(String email, String name, String lastName) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		// Aquí??
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
	
	public Set<ProductOffer> getOffers() {
		return this.offers;
	}

	public void setOffers(Set<ProductOffer> offers) {
		this.offers = offers;
	}


}
