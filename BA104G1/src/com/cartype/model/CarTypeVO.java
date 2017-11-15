package com.cartype.model;


public class CarTypeVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer cartypeno;
	private String cartypename;
	private String description;
	private Integer rentalrates;
	private byte[] carpic;
	
	public Integer getCartypeno() {
		return cartypeno;
	}
	public void setCartypeno(Integer cartypeno) {
		this.cartypeno = cartypeno;
	}
	public String getCartypename() {
		return cartypename;
	}
	public void setCartypename(String cartypename) {
		this.cartypename = cartypename;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getRentalrates() {
		return rentalrates;
	}
	public void setRentalrates(Integer rentalrates) {
		this.rentalrates = rentalrates;
	}
	public byte[] getCarpic() {
		return carpic;
	}
	public void setCarpic(byte[] carpic) {
		this.carpic = carpic;
	}
	
	
}
