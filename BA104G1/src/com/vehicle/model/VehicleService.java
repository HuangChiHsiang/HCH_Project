package com.vehicle.model;

import java.util.List;

public class VehicleService {

	private Vehicle_interface dao;

	public VehicleService() {
		dao = new VehicleDAO();
	}

	public VehicleVO addVehicle(Integer cartype_no, String emp_no,String licenseplate_no,String color,String vehicle_year,String emp_branches) {

		VehicleVO vehicleVO = new VehicleVO();
		
		vehicleVO.setCartype_no(cartype_no);
		vehicleVO.setEmp_no(emp_no);
		vehicleVO.setLicenseplate_no(licenseplate_no);
		vehicleVO.setColor(color);
		vehicleVO.setVehicle_year(vehicle_year);
		vehicleVO.setEmp_branches(emp_branches);
		
		dao.insert(vehicleVO);

		return vehicleVO;
	}

	public VehicleVO updateVehicle(Integer vehicle_no,Integer cartype_no, String emp_no,String licenseplate_no,String color,String vehicle_year,String emp_branches) {

		VehicleVO vehicleVO = new VehicleVO();
		
		vehicleVO.setVehicle_no(vehicle_no);
		vehicleVO.setCartype_no(cartype_no);
		vehicleVO.setEmp_no(emp_no);
		vehicleVO.setLicenseplate_no(licenseplate_no);
		vehicleVO.setColor(color);
		vehicleVO.setVehicle_year(vehicle_year);
		vehicleVO.setEmp_branches(emp_branches);
		
		dao.update(vehicleVO);

		return vehicleVO;
	}

	public void deleteVehicle(Integer vehicle_no) {
		dao.delete(vehicle_no);
	}

	public VehicleVO getOneVehicle(Integer vehicle_no) {
		return dao.findByPrimaryKey(vehicle_no);
	}

	public List<VehicleVO> getAll() {
		return dao.getAll();
	}
}
