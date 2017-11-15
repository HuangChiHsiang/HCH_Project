package com.carschedul.model;

import java.sql.Date;

public class CarSchedulVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer serial_no;
	private String emp_no;
	private Date year_month;
	private String attendance;
	private Integer work_hours;
	
	public Integer getSerial_no() {
		return serial_no;
	}
	public void setSerial_no(Integer serial_no) {
		this.serial_no = serial_no;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public Date getYear_month() {
		return year_month;
	}
	public void setYear_month(Date year_month) {
		this.year_month = year_month;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public Integer getWork_hours() {
		return work_hours;
	}
	public void setWork_hours(Integer work_hours) {
		this.work_hours = work_hours;
	}
	
	
}
