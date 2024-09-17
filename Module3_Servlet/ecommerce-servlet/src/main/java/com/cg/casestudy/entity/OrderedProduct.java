package com.cg.casestudy.entity;

public class OrderedProduct {
	
	private int id;
	private String name;
	private int quantity;
	private int price;
	private String image;
	private int orderId;
	
	public OrderedProduct() {

	}

	public OrderedProduct(String name, int quantity, int price, String image, int orderId) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.image = image;
		this.orderId = orderId;
	}

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
}
