package com.cartype.model;

import java.util.List;

public class CarTypeService {

	private CarType_interface dao;

	public CarTypeService() {
		dao = new CarTypeDAO();
	}

	public CarTypeVO addCarType(String cartypename,String description,Integer rentalrates,byte[] carpic) {
	//public CarTypeVO addCarType(String cartypename,String description,Integer rentalrates) {
		CarTypeVO cartypeVO = new CarTypeVO();

		cartypeVO.setCartypename(cartypename);
		cartypeVO.setDescription(description);
		cartypeVO.setRentalrates(rentalrates);
		cartypeVO.setCarpic(carpic);
		dao.insert(cartypeVO);

		return cartypeVO;
	}

	public CarTypeVO updateCarType(Integer cartypeno,String cartypename,String description,Integer rentalrates,byte[] carpic) {
	//public CarTypeVO updateCarType(Integer cartypeno,String cartypename,String description,Integer rentalrates) {

		CarTypeVO cartypeVO = new CarTypeVO();

		cartypeVO.setCartypeno(cartypeno);
		cartypeVO.setCartypename(cartypename);
		cartypeVO.setDescription(description);
		cartypeVO.setRentalrates(rentalrates);
		cartypeVO.setCarpic(carpic);
		dao.update(cartypeVO);

		return cartypeVO;
	}

	public void deleteCarType(Integer cartypeno) {
		dao.delete(cartypeno);
	}

	public CarTypeVO getOneCarType(Integer cartypeno) {
		return dao.findByPrimaryKey(cartypeno);
	}

	public List<CarTypeVO> getAll() {
		return dao.getAll();
	}
}
