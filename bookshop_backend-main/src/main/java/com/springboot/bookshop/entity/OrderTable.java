package com.springboot.bookshop.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "order_table")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrderTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIdentityReference(alwaysAsId = true)
	private User user;

	@Column(name = "date_created")
	private LocalDate dateCreated;

	@Column(name = "address_delivery")
	private String addressDelivery;

	@Column(name = "total_price")
	private int totalPrice;

	@Column(name = "payment_method")
	private int paymentMethod; // 0: payment on delivery 1:

	@Column(name = "paid")
	private int paid; // 0:no 1:yes

	@Column(name = "status")
	private int status;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private List<OrderDetail> orderDetails;

	public OrderTable() {

	}

	public OrderTable(int id, User user, String addressDelivery, int totalPrice, int paymentMethod, int status) {
		this.id = id;
		this.user = user;
		this.addressDelivery = addressDelivery;
		this.totalPrice = totalPrice;
		this.paymentMethod = paymentMethod;
		this.status = status;
	}

	public OrderTable(int id, User user, LocalDate dateCreated, String addressDelivery, int totalPrice,
			int paymentMethod, int status, List<OrderDetail> orderDetails) {
		this.id = id;
		this.user = user;
		this.dateCreated = dateCreated;
		this.addressDelivery = addressDelivery;
		this.totalPrice = totalPrice;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.orderDetails = orderDetails;
	}

	public OrderTable(int id, User user, LocalDate dateCreated, String addressDelivery, int totalPrice,
			int paymentMethod, int paid, int status, List<OrderDetail> orderDetails) {
		this.id = id;
		this.user = user;
		this.dateCreated = dateCreated;
		this.addressDelivery = addressDelivery;
		this.totalPrice = totalPrice;
		this.paymentMethod = paymentMethod;
		this.paid = paid;
		this.status = status;
		this.orderDetails = orderDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getAddressDelivery() {
		return addressDelivery;
	}

	public void setAddressDelivery(String addressDelivery) {
		this.addressDelivery = addressDelivery;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public String toString() {
		return "OrderTable [id=" + id + ", dateCreated=" + dateCreated + ", addressDelivery=" + addressDelivery
				+ ", totalPrice=" + totalPrice + ", paymentMethod=" + paymentMethod + ", paid=" + paid + ", status="
				+ status + "]";
	}

}
