package com.carorder.model;

import java.util.*;

public interface CarOrder_interface {

	public void insert(CarOrderVO carorderVO);
	public void update(CarOrderVO carorderVO);
	public void delete(String order_no);
	public CarOrderVO findByPrimaryKey(String order_no);
	public List<CarOrderVO> getAll();
	
}
