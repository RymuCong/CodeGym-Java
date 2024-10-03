package com.cg.casestudy.entity;

import java.sql.Timestamp;
import java.util.List;

public class Order {
	
	private int id;
	private String orderId;
	private String status;
	private Timestamp date;
	private String paymentType;
	private int userId;
	private List<Cart> cartProducts;
	
	public Order() {
	}

	public Order(String orderId, String status, Timestamp date, String paymentType, int userId, List<Cart> cartProducts) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.date = date;
		this.paymentType = paymentType;
		this.userId = userId;
		this.cartProducts = cartProducts;
	}

	public Order(String orderId, String status, String paymentType, int userId) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.paymentType = paymentType;
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public List<Cart> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<Cart> cartProducts) {
		this.cartProducts = cartProducts;
	}
}
