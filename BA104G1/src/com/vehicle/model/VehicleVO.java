package com.vehicle.model;


public class VehicleVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer vehicle_no;
	private Integer cartype_no;
	private String emp_no;
	private String licenseplate_no;
	private String color;
	private String vehicle_year;
	private String emp_branches;
	
	public Integer getVehicle_no() {
		return vehicle_no;
	}
	public void setVehicle_no(Integer vehicle_no) {
		this.vehicle_no = vehicle_no;
	}
	public Integer getCartype_no() {
		return cartype_no;
	}
	public void setCartype_no(Integer cartype_no) {
		this.cartype_no = cartype_no;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getLicenseplate_no() {
		return licenseplate_no;
	}
	public void setLicenseplate_no(String licenseplate_no) {
		this.licenseplate_no = licenseplate_no;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getVehicle_year() {
		return vehicle_year;
	}
	public void setVehicle_year(String vehicle_year) {
		this.vehicle_year = vehicle_year;
	}
	public String getEmp_branches() {
		return emp_branches;
	}
	public void setEmp_branches(String emp_branches) {
		this.emp_branches = emp_branches;
	}
	
	
}
