package com.carorder.model;

import java.util.*;

import com.cardetail.model.CarDetailVO;

public interface CarOrder_interface {

	public String insert(CarOrderVO carorderVO, List<CarDetailVO> list);
	public void update(CarOrderVO carorderVO);
	public void delete(String order_no);
	public CarOrderVO findByPrimaryKey(String order_no);
	public List<CarOrderVO> getAll();
	
	
}
