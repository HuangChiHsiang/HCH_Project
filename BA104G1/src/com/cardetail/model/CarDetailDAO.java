package com.cardetail.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.cardetail.model.CarDetailVO;

public class CarDetailDAO implements CarDetail_interface {

//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "BA104G1";
//	String passwd = "BA104G1";

	 private static DataSource ds = null;
	 static {
	 try {
	 Context ctx = new InitialContext();
	 ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G1");
	 } catch (NamingException e) {
	 e.printStackTrace();
	 }
	 }
	private static final String INSERT_STMT = "INSERT INTO CAR_DETAIL (DETAIL_NO,ORDER_NO,VEHICLE_NO,DETAIL_DATE,DETAIL_TIME,PASSENGER_NAME,PASSENGER_PHONE,GETINTO_ADDRESS,ARRIVAL_ADDRESS,SENDCAR_STATUS)"
	+" VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(cardetail_seq.Nextval),6,'0'),?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM CAR_DETAIL order by DETAIL_NO";
	private static final String GET_ONE_STMT = "SELECT DETAIL_NO,ORDER_NO,VEHICLE_NO,DETAIL_DATE,DETAIL_TIME,PASSENGER_NAME,PASSENGER_PHONE,GETINTO_ADDRESS,ARRIVAL_ADDRESS,SENDCAR_STATUS FROM CAR_DETAIL WHERE DETAIL_NO = ?";
	private static final String DELETE = "DELETE FROM CAR_DETAIL WHERE DETAIL_NO = ?";
	private static final String UPDATE = "UPDATE CAR_DETAIL SET VEHICLE_NO = ?, DETAIL_DATE = ?, DETAIL_TIME = ?,PASSENGER_NAME= ?,PASSENGER_PHONE=?,GETINTO_ADDRESS=?,ARRIVAL_ADDRESS=?,SENDCAR_STATUS=? WHERE DETAIL_NO = ?";

	@Override
	public void insert(CarDetailVO cardetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			 con = ds.getConnection();

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, cardetailVO.getOrder_no());
			pstmt.setInt(2, cardetailVO.getVehicle_no());
			pstmt.setDate(3, cardetailVO.getDetail_date());
			pstmt.setString(4, cardetailVO.getDetail_time());
			pstmt.setString(5, cardetailVO.getPassenger_name());
			pstmt.setString(6, cardetailVO.getPassenger_phone());
			pstmt.setString(7, cardetailVO.getGetinto_address());
			pstmt.setString(8, cardetailVO.getArrival_address());
			pstmt.setString(9, cardetailVO.getSendcar_status());
			pstmt.executeUpdate();

//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(CarDetailVO cardetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			 con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, cardetailVO.getVehicle_no());
			pstmt.setDate(2, cardetailVO.getDetail_date());
			pstmt.setString(3, cardetailVO.getDetail_time());
			pstmt.setString(4, cardetailVO.getPassenger_name());
			pstmt.setString(5, cardetailVO.getPassenger_phone());
			pstmt.setString(6, cardetailVO.getGetinto_address());
			pstmt.setString(7, cardetailVO.getArrival_address());
			pstmt.setString(8, cardetailVO.getSendcar_status());
			pstmt.setString(9, cardetailVO.getDetail_no());

			pstmt.executeUpdate();

//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String detail_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			 con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, detail_no);
			System.out.println("刪除編號:" + detail_no);
			pstmt.executeUpdate();

//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//
//			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public CarDetailVO findByPrimaryKey(String detail_no) {

		CarDetailVO cardetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			 con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, detail_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cardetailVO = new CarDetailVO();
				cardetailVO.setDetail_no(rs.getString("detail_no"));
				cardetailVO.setOrder_no(rs.getString("order_no"));
				cardetailVO.setVehicle_no(rs.getInt("vehicle_no"));
				cardetailVO.setDetail_date(rs.getDate("detail_date"));
				cardetailVO.setDetail_time(rs.getString("detail_time"));
				cardetailVO.setPassenger_name(rs.getString("passenger_name"));
				cardetailVO.setPassenger_phone(rs.getString("passenger_phone"));
				cardetailVO.setGetinto_address(rs.getString("getinto_address"));
				cardetailVO.setArrival_address(rs.getString("arrival_address"));
				cardetailVO.setSendcar_status(rs.getString("sendcar_status"));
				
				
			}

//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return cardetailVO;
	}

	@Override
	public List<CarDetailVO> getAll() {
		List<CarDetailVO> list = new ArrayList<CarDetailVO>();
		CarDetailVO cardetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			 con = ds.getConnection();

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				cardetailVO = new CarDetailVO();
				cardetailVO.setDetail_no(rs.getString("detail_no"));
				cardetailVO.setOrder_no(rs.getString("order_no"));
				cardetailVO.setVehicle_no(rs.getInt("vehicle_no"));
				cardetailVO.setDetail_date(rs.getDate("detail_date"));
				cardetailVO.setDetail_time(rs.getString("detail_time"));
				cardetailVO.setPassenger_name(rs.getString("passenger_name"));
				cardetailVO.setPassenger_phone(rs.getString("passenger_phone"));
				cardetailVO.setGetinto_address(rs.getString("getinto_address"));
				cardetailVO.setArrival_address(rs.getString("arrival_address"));
				cardetailVO.setSendcar_status(rs.getString("sendcar_status"));

				list.add(cardetailVO);
			}
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

//	public static void main(String[] args) throws IOException {
		// String picName = "WebContent/images/tomcat.png";
		// File pic = new File(picName);
		// byte[] buffer = Files.readAllBytes(pic.toPath());

//		CarDetailDAO dao = new CarDetailDAO();

		// 修改

		// CarDetailVO vehicleVO2 = new CarDetailVO();
		// vehicleVO2.setVehicle_no(1007);
		// vehicleVO2.setCartype_no(1001);
		// vehicleVO2.setEmp_no("EMP0001");
		// vehicleVO2.setLicenseplate_no("ABCC4553");
		// vehicleVO2.setColor("貴族黑");
		// vehicleVO2.setVehicle_year("2016");
		// vehicleVO2.setEmp_branches("10");
		// dao.update(vehicleVO2);

		// 新增
		// CarDetailVO vehicleVO1 = new CarDetailVO();
		// vehicleVO1.setCartype_no(1001);
		// vehicleVO1.setEmp_no("EMP0001");
		// vehicleVO1.setLicenseplate_no("ABCC4553");
		// vehicleVO1.setColor("炫麗紅");
		// vehicleVO1.setVehicle_year("2016");
		// vehicleVO1.setEmp_branches("10");
		// dao.insert(vehicleVO1);

		// 刪除

		// dao.delete(1006);
		// System.out.println("刪除成功");

		// 查詢單筆

		// CarDetailVO vehicleVO2 = dao.findByPrimaryKey(1002);
		// System.out.print(vehicleVO2.getVehicle_no() + ",");
		// System.out.print(vehicleVO2.getCartype_no() + ",");
		// System.out.print(vehicleVO2.getEmp_no() + ",");
		// System.out.print(vehicleVO2.getLicenseplate_no() + ",");
		// System.out.print(vehicleVO2.getColor() + ",");
		// System.out.print(vehicleVO2.getVehicle_year() + ",");
		// System.out.print(vehicleVO2.getEmp_branches());

		// 查詢全部
//		List<CarDetailVO> list = dao.getAll();
//		for (CarDetailVO cardetailList : list) {
//			System.out.print(cardetailList.getDetail_no() + ",");
//			System.out.print(cardetailList.getOrder_no() + ",");
//			System.out.print(cardetailList.getVehicle_no() + ",");
//			System.out.print(cardetailList.getDetail_date() + ",");
//			System.out.print(cardetailList.getDetail_time() + ",");
//			System.out.print(cardetailList.getPassenger_name() + ",");
//			System.out.print(cardetailList.getPassenger_phone() + ",");
//			System.out.print(cardetailList.getGetinto_address() + ",");
//			System.out.print(cardetailList.getArrival_address() + ",");
//			System.out.print(cardetailList.getSendcar_status());
//			System.out.println();
//		}
//	}
}
