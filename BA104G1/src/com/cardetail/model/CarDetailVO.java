package com.cardetail.model;

import java.sql.Date;

public class CarDetailVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String detail_no;
	private String order_no;
	private Integer vehicle_no;
	private Date detail_date;
	private String detail_time;
	private String passenger_name;
	private String passenger_phone;
	private String getinto_address;
	private String arrival_address;
	private String sendcar_status;
	
	public String getDetail_no() {
		return detail_no;
	}
	public void setDetail_no(String detail_no) {
		this.detail_no = detail_no;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public Integer getVehicle_no() {
		return vehicle_no;
	}
	public void setVehicle_no(Integer vehicle_no) {
		this.vehicle_no = vehicle_no;
	}
	public Date getDetail_date() {
		return detail_date;
	}
	public void setDetail_date(Date detail_date) {
		this.detail_date = detail_date;
	}
	public String getDetail_time() {
		return detail_time;
	}
	public void setDetail_time(String detail_time) {
		this.detail_time = detail_time;
	}
	public String getPassenger_name() {
		return passenger_name;
	}
	public void setPassenger_name(String passenger_name) {
		this.passenger_name = passenger_name;
	}
	public String getPassenger_phone() {
		return passenger_phone;
	}
	public void setPassenger_phone(String passenger_phone) {
		this.passenger_phone = passenger_phone;
	}
	public String getGetinto_address() {
		return getinto_address;
	}
	public void setGetinto_address(String getinto_address) {
		this.getinto_address = getinto_address;
	}
	public String getArrival_address() {
		return arrival_address;
	}
	public void setArrival_address(String arrival_address) {
		this.arrival_address = arrival_address;
	}
	public String getSendcar_status() {
		return sendcar_status;
	}
	public void setSendcar_status(String sendcar_status) {
		this.sendcar_status = sendcar_status;
	}
	
	
	
}
