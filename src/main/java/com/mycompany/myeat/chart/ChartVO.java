package com.mycompany.myeat.chart;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("chartVO")
public class ChartVO {
	private String price;
	private String orderno;
	private String quantity;
	private String cat;
	private Date date_from;
	private Date date_to;

	public ChartVO() {
		
	}
	public ChartVO(Date date_from, Date date_to) {
		this.date_from = date_from;
		this.date_to = date_to;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public Date getDate_from() {
		return date_from;
	}

	public void setDate_from(Date date_from) {
		this.date_from = date_from;
	}

	public Date getDate_to() {
		return date_to;
	}

	public void setDate_to(Date date_to) {
		this.date_to = date_to;
	}

	
}
