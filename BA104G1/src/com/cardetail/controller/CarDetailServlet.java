package com.cardetail.controller;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.carschedul.model.CarSchedulService;
import com.carschedul.model.CarSchedulVO;
import com.cartype.model.CarTypeService;
import com.cartype.model.CarTypeVO;
import com.google.gson.Gson;
import com.vehicle.model.VehicleVO;
import com.cardetail.model.*;

public class CarDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String str = req.getParameter("detail_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String detail_no = null;
				try {
					detail_no = str;
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				CarDetailService cardetailSvc = new CarDetailService();
				CarDetailVO cardetailVO = cardetailSvc.getOneCarDetail(detail_no);
				if (cardetailVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("cardetailVO", cardetailVO); // 資料庫取出的empVO物件,存入req
				String url = "/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp 或
													// /dept/listEmps_ByDeptno.jsp
													// 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑:
																// 可能為【/emp/listAllEmp.jsp】
																// 或
																// 【/dept/listEmps_ByDeptno.jsp】
																// 或 【
																// /dept/listAllDept.jsp】

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String detail_no = new String(req.getParameter("detail_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				CarDetailService cardetailSvc = new CarDetailService();
				CarDetailVO cardetailVO = cardetailSvc.getOneCarDetail(detail_no);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("cardetailVO", cardetailVO); // 資料庫取出的empVO物件,存入req
				String url = "/emp/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑:
																// 可能為【/emp/listAllEmp.jsp】
																// 或
																// 【/dept/listEmps_ByDeptno.jsp】
																// 或 【
																// /dept/listAllDept.jsp】
																// 或 【
																// /emp/listEmps_ByCompositeQuery.jsp】

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String detail_no = req.getParameter("detail_no").trim();
				String order_no = req.getParameter("order_no").trim();
				Integer vehicle_no = new Integer(req.getParameter("vehicle_no").trim());

				java.sql.Date detail_date = null;
				try {
					detail_date = java.sql.Date.valueOf(req.getParameter("detail_date").trim());
				} catch (IllegalArgumentException e) {
					detail_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String detail_time = req.getParameter("detail_time").trim();
				String passenger_name = req.getParameter("passenger_name").trim();
				String passenger_phone = new String(req.getParameter("passenger_phone").trim());
				String getinto_address = req.getParameter("getinto_address").trim();
				String arrival_address = req.getParameter("arrival_address").trim();
				String sendcar_status = req.getParameter("sendcar_status").trim();

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

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cardetailVO", cardetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				CarDetailService cardetailSvc = new CarDetailService();
				cardetailVO = cardetailSvc.updateCarDetail(detail_no, order_no, vehicle_no, detail_date, detail_time,
						passenger_name, passenger_phone, getinto_address, arrival_address, sendcar_status);
				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("cardetailVO", cardetailVO);
				String url = "/cardetail/listOneCarDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/cardetail/update_cardetail_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getDisableDates".equals(action)) {

			Integer cartypeno = null;
			String inputCarTypeName = req.getParameter("cartypename").trim();
			CarTypeService cartypeSv = new CarTypeService();
			List<CarTypeVO> cartypeVO = cartypeSv.getAll();

			for (CarTypeVO cartypeAll : cartypeVO) {

				if (cartypeAll.getCartypename().equals(inputCarTypeName)) {
					cartypeno = cartypeAll.getCartypeno();

				}
				;
			}
			;
			System.out.println("車型編號:" + cartypeno);

			java.sql.Date sysdate = new java.sql.Date(System.currentTimeMillis());
			sysdate.setDate(1);
			System.out.println("系統日期:" + sysdate);

			CarSchedulService carSchedulSv = new CarSchedulService();
			List<CarSchedulVO> carSchedulVO = carSchedulSv.getMonthInfo(cartypeno, sysdate);
			int detail_time_no = new Integer(req.getParameter("detail_time_no").trim());
			System.out.println("預約時段數字:" + detail_time_no);

			List<Integer> dayStatusList = new ArrayList<Integer>();
			int i;

			for (i = 1; i < 32; i++) {
				System.out.println("本月" + i + "號");

				for (CarSchedulVO schedulList : carSchedulVO) {
					String schedul = schedulList.getAttendance();
					System.out.println("班表:" + schedul);

					int endIndex = 1;
					String dayStatus = schedul.substring((detail_time_no), (detail_time_no + endIndex));
					System.out.println("起始index:" + (detail_time_no) + "," + "結束index:" + (detail_time_no + endIndex));

					detail_time_no = detail_time_no + 3;
					endIndex = detail_time_no + 3;
					System.out.println("當日該時段狀態:" + dayStatus);
					if (!dayStatus.equals("空")) {
						dayStatusList.add(i);
						System.out.println("將" + i + "號放進List.");
					}
				}
				;
			}
			;

			JSONObject myObj = new JSONObject();
			try {
				myObj.put("dayStatusList", dayStatusList);
			} catch (JSONException e) {

				e.printStackTrace();
			}

			PrintWriter out = res.getWriter();

			out.write(myObj.toString());
			System.out.println(myObj.toString());
			out.flush();
			out.close();
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/* try { */
			/***********************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 *************************/

			String order_no = "20171115-000003";

			Integer cartypeno = null;
			String inputCarTypeName = req.getParameter("cartypename").trim();
			CarTypeService cartypeSv = new CarTypeService();
			List<CarTypeVO> cartypeVO = cartypeSv.getAll();

			for (CarTypeVO cartypeAll : cartypeVO) {

				if (cartypeAll.getCartypename().equals(inputCarTypeName)) {
					cartypeno = cartypeAll.getCartypeno();

				}
				;
			}
			;
			System.out.println("車型編號:" + cartypeno);

			java.sql.Date sysdate = new java.sql.Date(System.currentTimeMillis());
			sysdate.setDate(1);

			CarSchedulService carSchedulSv = new CarSchedulService();
			List<VehicleVO> vehicleVO = carSchedulSv.getVehicleInfo(cartypeno, sysdate);
			Integer vehicle_no = vehicleVO.get(0).getVehicle_no();
			System.out.println("車輛編號:" + vehicle_no);
			// Integer vehicle_no = new
			// Integer(req.getParameter("vehicle_no").trim());

			java.sql.Date detail_date = null;

			try {
				System.out.println(req.getParameter("detail_date").trim());
				detail_date = java.sql.Date.valueOf(req.getParameter("detail_date").trim());

			} catch (IllegalArgumentException e) {
				detail_date = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
				System.out.println("請輸入日期!");
			}
			System.out.println("日期:" + detail_date);

			String detail_time = req.getParameter("detail_time").trim();
			String passenger_name = req.getParameter("passenger_name").trim();
			String passenger_phone = req.getParameter("passenger_phone").trim();

			String county = req.getParameter("county").trim();
			String district = req.getParameter("district").trim();
			String address = req.getParameter("address").trim();

			String getinto_address = county + district + address;

			String arrival_address = req.getParameter("arrival_address").trim();

			String sendcar_status = req.getParameter("sendcar_status").trim();

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

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("cardetailVO", cardetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front/carorder/CarOrder.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			CarDetailService cardetailSvc = new CarDetailService();
			cardetailVO = cardetailSvc.addCarDetail(order_no, vehicle_no, detail_date, detail_time, passenger_name,
					passenger_phone, getinto_address, arrival_address, sendcar_status);

			/***************************
			 * 3.新增完成,準備轉交(Send the Success view)
			 ***********/
			String url = "/front/carorder/CarOrder.jsp";
			String formCheck = "ok";
			req.setAttribute("formCheck", formCheck);
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
			/*
			 * } catch (Exception e) { errorMsgs.add(e.getMessage());
			 * RequestDispatcher failureView = req
			 * .getRequestDispatcher("/front/carorder/CarOrder.jsp");
			 * failureView.forward(req, res); }
			 */
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp 或
										// /dept/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑:
																// 可能為【/emp/listAllEmp.jsp】
																// 或
																// 【/dept/listEmps_ByDeptno.jsp】
																// 或 【
																// /dept/listAllDept.jsp】
																// 或 【
																// /emp/listEmps_ByCompositeQuery.jsp】

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String detail_no = req.getParameter("detail_no");

				/*************************** 2.開始刪除資料 ***************************************/
				CarDetailService cardetailSvc = new CarDetailService();
				CarDetailVO cardetailVO = cardetailSvc.getOneCarDetail(detail_no);
				cardetailSvc.deleteCarDetail(detail_no);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

	}
}
