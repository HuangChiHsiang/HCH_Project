package com.carorder.model;

import java.sql.Date;
import java.util.List;

public class CarOrderService {

	private CarOrder_interface dao;

	public CarOrderService() {
		dao = new CarOrderDAO();
	}

	public CarOrderVO addCarOrder(String mem_no, Date order_date,String order_status) {

		CarOrderVO carorderVO = new CarOrderVO();
		
		carorderVO.setMem_no(mem_no);
		carorderVO.setOrder_date(order_date);
		carorderVO.setOrder_status(order_status);
		
		
		dao.insert(carorderVO);

		return carorderVO;
	}

	public CarOrderVO updateCarOrder(String order_no,String mem_no, Date order_date,String order_status) {

		CarOrderVO carorderVO = new CarOrderVO();
		
		carorderVO.setOrder_no(order_no);
		carorderVO.setMem_no(mem_no);
		carorderVO.setOrder_date(order_date);
		carorderVO.setOrder_status(order_status);
		
		dao.update(carorderVO);

		return carorderVO;
	}

	public void deleteCarOrder(String order_no) {
		dao.delete(order_no);
	}

	public CarOrderVO getOneCarOrder(String order_no) {
		return dao.findByPrimaryKey(order_no);
	}

	public List<CarOrderVO> getAll() {
		return dao.getAll();
	}
}
