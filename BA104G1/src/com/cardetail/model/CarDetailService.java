package com.cardetail.model;

import java.sql.Date;
import java.util.List;

public class CarDetailService {

	private CarDetail_interface dao;

	public CarDetailService() {
		dao = new CarDetailDAO();
	}

	public CarDetailVO addCarDetail(String order_no,Integer vehicle_no,Date detail_date,String detail_time,String passenger_name,String passenger_phone,String getinto_address,String arrival_address,String sendcar_status) {

		CarDetailVO cardetailVO = new CarDetailVO();
		
		cardetailVO.setOrder_no(order_no);
		cardetailVO.setVehicle_no(vehicle_no);
		cardetailVO.setDetail_date(detail_date);
		cardetailVO.setDetail_time(detail_time);
		cardetailVO.setPassenger_name(passenger_name);
		cardetailVO.setPassenger_phone(passenger_phone);
		cardetailVO.setGetinto_address(getinto_address);
		cardetailVO.setArrival_address(arrival_address);
		cardetailVO.setSendcar_status(sendcar_status);
		
		dao.insert(cardetailVO);

		return cardetailVO;
	}

	public CarDetailVO updateCarDetail(String detail_no,String order_no,Integer vehicle_no,Date detail_date,String detail_time,String passenger_name,String passenger_phone,String getinto_address,String arrival_address,String sendcar_status) {

		CarDetailVO cardetailVO = new CarDetailVO();
		
		cardetailVO.setDetail_no(detail_no);
		cardetailVO.setOrder_no(order_no);
		cardetailVO.setVehicle_no(vehicle_no);
		cardetailVO.setDetail_date(detail_date);
		cardetailVO.setDetail_time(detail_time);
		cardetailVO.setPassenger_name(passenger_name);
		cardetailVO.setPassenger_phone(passenger_phone);
		cardetailVO.setGetinto_address(getinto_address);
		cardetailVO.setArrival_address(arrival_address);
		cardetailVO.setSendcar_status(sendcar_status);
		
		dao.update(cardetailVO);

		return cardetailVO;
	}

	public void deleteCarDetail(String detail_no) {
		dao.delete(detail_no);
	}

	public CarDetailVO getOneCarDetail(String detail_no) {
		return dao.findByPrimaryKey(detail_no);
	}

	public List<CarDetailVO> getAll() {
		return dao.getAll();
	}
}
