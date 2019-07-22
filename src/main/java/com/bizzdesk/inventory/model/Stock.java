package com.bizzdesk.inventory.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.metamodel.EntityType;

import org.hibernate.annotations.GenericGenerator;


import com.fasterxml.jackson.annotation.JsonIgnore;


@SuppressWarnings("serial")
@Entity
@Table
public class Stock implements Serializable{
	
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "stock_id")
	private String stockId;
		
	@JsonIgnore
	@Column(name = "date_created", nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
	private Date dateAssigned;
	
	@Column(name = "stock_location")
	private String stockLocation;

	@Column(name = "stock_name", nullable = false)
	private String stockName;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "users_id")
	private Users users;

	
	
	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public Date getDateAssigned() {
		return dateAssigned;
	}

	public void setDateAssigned(Date dateAssigned) {
		this.dateAssigned = dateAssigned;
	}

	public String getStockLocation() {
		return stockLocation;
	}

	public void setStockLocation(String stockLocation) {
		this.stockLocation = stockLocation;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	
	
	
	
}
