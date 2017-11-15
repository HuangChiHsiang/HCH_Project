package com.cartype.model;

import java.util.*;

public interface CarType_interface {

	public void insert(CarTypeVO cartypeVO);
	public void update(CarTypeVO cartypeVO);
	public void delete(Integer cartypeno);
	public CarTypeVO findByPrimaryKey(Integer cartypeno);
	public List<CarTypeVO> getAll();
	
}
