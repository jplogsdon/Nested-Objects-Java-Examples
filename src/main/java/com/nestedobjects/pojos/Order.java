package com.nestedobjects.pojos;

import java.util.Date;

public class Order {
	private String comments;
	private Integer customerNumber;
	private Date orderDate;
	private Date requiredDate;
	private Date shippedDate;
	private String status;
	private OrderDetails[] orderDetails;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OrderDetails[] getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(OrderDetails[] orderDetails) {
		this.orderDetails = orderDetails;
	}

}
