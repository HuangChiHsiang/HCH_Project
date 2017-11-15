package com.vehicle.model;

import java.util.*;

public interface Vehicle_interface {

	public void insert(VehicleVO vehicleVO);
	public void update(VehicleVO vehicleVO);
	public void delete(Integer vehicle_no);
	public VehicleVO findByPrimaryKey(Integer vehicle_no);
	public List<VehicleVO> getAll();
	public List<VehicleVO> getAllByCarType(Integer cartype_no);
	
}
