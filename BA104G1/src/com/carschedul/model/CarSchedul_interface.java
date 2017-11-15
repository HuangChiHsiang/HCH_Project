package com.carschedul.model;

import java.sql.Date;
import java.util.*;

public interface CarSchedul_interface {

	public void insert(CarSchedulVO carschedulVO);
	public void update(CarSchedulVO carschedulVO);
	public void delete(Integer serial_no);
	public CarSchedulVO findByPrimaryKey(Integer serial_no);
	public List<CarSchedulVO> getAll();
	public List<CarSchedulVO> getMonthInfo(Integer cartype_no,Date year_month);
}
