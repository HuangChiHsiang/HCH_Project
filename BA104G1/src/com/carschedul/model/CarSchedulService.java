package com.carschedul.model;

import java.sql.Date;
import java.util.List;

public class CarSchedulService {

	private CarSchedul_interface dao;

	public CarSchedulService() {
		dao = new CarSchedulDAO();
	}

	public CarSchedulVO addCarSchedul(String emp_no,Date year_month,String attendance,Integer work_hours) {

		CarSchedulVO carschedulVO = new CarSchedulVO();
		
		carschedulVO.setEmp_no(emp_no);
		carschedulVO.setYear_month(year_month);
		carschedulVO.setAttendance(attendance);
		carschedulVO.setWork_hours(work_hours);
		dao.insert(carschedulVO);

		return carschedulVO;
	}

	public CarSchedulVO updateCarSchedul(Integer serial_no,String emp_no,Date year_month,String attendance,Integer work_hours) {

		CarSchedulVO carschedulVO = new CarSchedulVO();
		
		carschedulVO.setSerial_no(serial_no);
		carschedulVO.setEmp_no(emp_no);
		carschedulVO.setYear_month(year_month);
		carschedulVO.setAttendance(attendance);
		carschedulVO.setWork_hours(work_hours);
		
		dao.update(carschedulVO);

		return carschedulVO;
	}

	public void deleteCarSchedul(Integer serial_no) {
		dao.delete(serial_no);
	}

	public CarSchedulVO getOneCarSchedul(Integer serial_no) {
		return dao.findByPrimaryKey(serial_no);
	}

	public List<CarSchedulVO> getAll() {
		return dao.getAll();
	}
	
	public List<CarSchedulVO> getMonthInfo(Integer cartype_no,Date year_month) {
		return dao.getMonthInfo(cartype_no,year_month);
	}
}
