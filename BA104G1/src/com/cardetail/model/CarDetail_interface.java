package com.cardetail.model;

import java.util.*;

public interface CarDetail_interface {

	public void insert(CarDetailVO cardetailVO);
	public void update(CarDetailVO cardetailVO);
	public void delete(String detail_no);
	public CarDetailVO findByPrimaryKey(String detail_no);
	public List<CarDetailVO> getAll();
	
}
