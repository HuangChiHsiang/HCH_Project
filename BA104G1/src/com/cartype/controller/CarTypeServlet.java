package com.cartype.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.cartype.model.*;
@MultipartConfig(fileSizeThreshold = 5*1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class CarTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				String str = req.getParameter("cartypeno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入車型編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/cartype/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer cartypeno = null;
				try {
					cartypeno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("車型編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/cartype/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				CarTypeService cartypeSvc = new CarTypeService();
				CarTypeVO cartypeVO = cartypeSvc.getOneCarType(cartypeno);
				if (cartypeVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/cartype/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
		
				req.setAttribute("cartypeVO", cartypeVO); 
				String url = "/back/cartype/listOneCarType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/cartype/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				Integer cartypeno = new Integer(req.getParameter("cartypeno"));
				
				
				CarTypeService cartypeSvc = new CarTypeService();
				CarTypeVO cartypeVO = cartypeSvc.getOneCarType(cartypeno);
								
				
				req.setAttribute("cartypeVO", cartypeVO);         
				String url = "/back/cartype/update_cartype_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/cartype/listAllCarType.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				
				Integer cartypeno = new Integer(req.getParameter("cartypeno").trim());
				
				String cartypename = req.getParameter("cartypename");
				String cartype_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (cartypename == null || cartypename.trim().length() == 0) {
					errorMsgs.add("車型名稱: 請勿空白");
				} else if(!cartypename.trim().matches(cartype_nameReg)) {
					errorMsgs.add("車型名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String description = req.getParameter("description").trim();
				if (description == null || description.trim().length() == 0) {
					errorMsgs.add("車型描述請勿空白");
				}	
				
				

				Integer rentalrates = null;
				try {
					rentalrates = new Integer(req.getParameter("rentalrates").trim());
				} catch (NumberFormatException e) {
					rentalrates = 0;
					errorMsgs.add("出租價格請填數字.");
				}
				
				Part picPart = req.getPart("carpic");
				PhotoUL pu = new PhotoUL();
				byte[] carpic= pu.getPictureByteArray(picPart);

				

				CarTypeVO cartypeVO = new CarTypeVO();
				cartypeVO.setCartypeno(cartypeno);
				cartypeVO.setCartypename(cartypename);
				cartypeVO.setDescription(description);
				cartypeVO.setRentalrates(rentalrates);
				cartypeVO.setCarpic(carpic);
		
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cartypeVO", cartypeVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/cartype/update_cartype_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				
				CarTypeService cartypeSvc = new CarTypeService();
				cartypeVO = cartypeSvc.updateCarType(cartypeno,cartypename,description,rentalrates,carpic);
				//cartypeVO = cartypeSvc.updateCarType(cartypeno,cartypename,description,rentalrates);
				
				
				req.setAttribute("cartypeVO", cartypeVO); 
				String url = "/back/cartype/listOneCarType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

		
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/cartype/update_cartype_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
			
				String cartypename = req.getParameter("cartypename");
				String cartype_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (cartypename == null || cartypename.trim().length() == 0) {
					errorMsgs.add("車型名稱: 請勿空白");
				} else if(!cartypename.trim().matches(cartype_nameReg)) { 
					errorMsgs.add("車型名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String description = req.getParameter("description").trim();
				if (description == null || description.trim().length() == 0) {
					errorMsgs.add("車型描述請勿空白");
				}	
				
				Integer rentalrates = null;
				try {
					rentalrates = new Integer(req.getParameter("rentalrates").trim());
				} catch (NumberFormatException e) {
					rentalrates = 0;
					errorMsgs.add("出租價格請填數字.");
				}
				
				Part picPart = req.getPart("carpic");
				PhotoUL pu = new PhotoUL();
				byte[] carpic= pu.getPictureByteArray(picPart);
				
				CarTypeVO cartypeVO = new CarTypeVO();
				cartypeVO.setCartypename(cartypename);
				cartypeVO.setDescription(description);
				cartypeVO.setRentalrates(rentalrates);
				cartypeVO.setCarpic(carpic);
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cartypeVO", cartypeVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/cartype/addCarType.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				CarTypeService cartypeSvc = new CarTypeService();
				cartypeVO = cartypeSvc.addCarType(cartypename,description,rentalrates,carpic);
				
				
				String url = "/back/cartype/listAllCarType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/cartype/addCarType.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				
				Integer cartypeno = new Integer(req.getParameter("cartypeno"));
				
				
				CarTypeService cartypeSvc = new CarTypeService();
				cartypeSvc.deleteCarType(cartypeno);
				
										
				String url = "/back/cartype/listAllCarType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/cartype/listAllCarType.jsp");
				failureView.forward(req, res);
			}
		}
	}

	
}