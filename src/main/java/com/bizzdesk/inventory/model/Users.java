package com.bizzdesk.inventory.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class Users implements Serializable{
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "users_id", nullable = false, unique = true)
	private long usersId;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "email")
	private String emailAddress;
	
	@Column(name = "phone")
	private String phoneNumber;
	
	@JsonIgnore
	@Column(name = "password")
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Stock> stock;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
    private Role role;
	    
	//@JsonIgnore
	@Column(name = "active")
	private boolean active;
	    
    @JsonIgnore
    @Column(name = "date_created", nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
		
   // @JsonIgnore
    @Column(name = "activation_code")
	private String activationCode;
		
	@JsonIgnore
	@Column(name = "password_reset_code")
	private String passwordResetCode;

	
	
	public long getUsersId() {
		return usersId;
	}

	public void setUsersId(long usersId) {
		this.usersId = usersId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Stock> getStock() {
		return stock;
	}

	public void setStock(List<Stock> stock) {
		this.stock = stock;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getPasswordResetCode() {
		return passwordResetCode;
	}

	public void setPasswordResetCode(String passwordResetCode) {
		this.passwordResetCode = passwordResetCode;
	}
	

	
}
