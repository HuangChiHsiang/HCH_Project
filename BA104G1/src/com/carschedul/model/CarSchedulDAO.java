package com.carschedul.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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


public class CarSchedulDAO implements CarSchedul_interface {

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
	private static final String INSERT_STMT = "INSERT INTO CAR_SCHEDUL (SERIAL_NO,EMP_NO,YEAR_MONTH,ATTENDANCE,WORK_HOURS)"
	+" VALUES (carschedul_seq.Nextval,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM CAR_SCHEDUL order by SERIAL_NO";
	private static final String GET_ONE_STMT = "SELECT SERIAL_NO,EMP_NO,YEAR_MONTH,ATTENDANCE,WORK_HOURS FROM CAR_SCHEDUL WHERE SERIAL_NO = ?";
	private static final String DELETE = "DELETE FROM CAR_SCHEDUL WHERE SERIAL_NO = ?";
	private static final String UPDATE = "UPDATE CAR_SCHEDUL SET YEAR_MONTH = ?, ATTENDANCE = ?, WORK_HOURS = ? WHERE SERIAL_NO = ?";
	private static final String GET_MONINFO_STMT = "SELECT * FROM CAR_SCHEDUL JOIN VEHICLE ON CAR_SCHEDUL.EMP_NO = VEHICLE.EMP_NO WHERE CARTYPE_NO = ? and YEAR_MONTH = ? order by work_hours";
	
	@Override
	public void insert(CarSchedulVO carschedulVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			 con = ds.getConnection();

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, carschedulVO.getEmp_no());
			pstmt.setDate(2, carschedulVO.getYear_month());
			pstmt.setString(3, carschedulVO.getAttendance());
			pstmt.setInt(4, carschedulVO.getWork_hours());
			pstmt.executeUpdate();

//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public void update(CarSchedulVO carschedulVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			 con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, carschedulVO.getEmp_no());
			pstmt.setDate(2, carschedulVO.getYear_month());
			pstmt.setString(3, carschedulVO.getAttendance());
			pstmt.setInt(4, carschedulVO.getWork_hours());
			
			pstmt.setInt(5, carschedulVO.getSerial_no());

			pstmt.executeUpdate();

//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any driver errors
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
	public void delete(Integer serial_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			 con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, serial_no);
			System.out.println("刪除編號:" + serial_no);
			pstmt.executeUpdate();

//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

			// Handle any SQL errors
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
	public CarSchedulVO findByPrimaryKey(Integer serial_no) {

		CarSchedulVO carschedulVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			 con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, serial_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				carschedulVO = new CarSchedulVO();
				carschedulVO.setSerial_no(rs.getInt("serial_no"));
				carschedulVO.setEmp_no(rs.getString("emp_no"));
				carschedulVO.setYear_month(rs.getDate("year_month"));
				carschedulVO.setAttendance(rs.getString("attendance"));
				carschedulVO.setWork_hours(rs.getInt("work_hours"));
				
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
		return carschedulVO;
	}

	@Override
	public List<CarSchedulVO> getAll() {
		List<CarSchedulVO> list = new ArrayList<CarSchedulVO>();
		CarSchedulVO carschedulVO = null;

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

				carschedulVO = new CarSchedulVO();
				carschedulVO.setSerial_no(rs.getInt("serial_no"));
				carschedulVO.setEmp_no(rs.getString("emp_no"));
				carschedulVO.setYear_month(rs.getDate("year_month"));
				carschedulVO.setAttendance(rs.getString("attendance"));
				carschedulVO.setWork_hours(rs.getInt("work_hours"));

				list.add(carschedulVO);
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
	
	@Override
	public List<CarSchedulVO> getMonthInfo(Integer cartype_no,Date year_month) {
		List<CarSchedulVO> list = new ArrayList<CarSchedulVO>();
		CarSchedulVO carschedulVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			 con = ds.getConnection();

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MONINFO_STMT);
			
			pstmt.setInt(1, cartype_no);
			pstmt.setDate(2, year_month);
			
			rs = pstmt.executeQuery();
				
			while (rs.next()) {

				carschedulVO = new CarSchedulVO();
				carschedulVO.setSerial_no(rs.getInt("serial_no"));
				carschedulVO.setEmp_no(rs.getString("emp_no"));
				carschedulVO.setYear_month(rs.getDate("year_month"));
				carschedulVO.setAttendance(rs.getString("attendance"));
				carschedulVO.setWork_hours(rs.getInt("work_hours"));

				list.add(carschedulVO);
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

	public static void main(String[] args) throws IOException {
		// String picName = "WebContent/images/tomcat.png";
		// File pic = new File(picName);
		// byte[] buffer = Files.readAllBytes(pic.toPath());

		CarSchedulDAO dao = new CarSchedulDAO();

		// 修改

		// CarSchedulVO vehicleVO2 = new CarSchedulVO();
		// vehicleVO2.setVehicle_no(1007);
		// vehicleVO2.setCartype_no(1001);
		// vehicleVO2.setEmp_no("EMP0001");
		// vehicleVO2.setLicenseplate_no("ABCC4553");
		// vehicleVO2.setColor("貴族黑");
		// vehicleVO2.setVehicle_year("2016");
		// vehicleVO2.setEmp_branches("10");
		// dao.update(vehicleVO2);

		// 新增
		// CarSchedulVO vehicleVO1 = new CarSchedulVO();
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

		// CarSchedulVO vehicleVO2 = dao.findByPrimaryKey(1002);
		// System.out.print(vehicleVO2.getVehicle_no() + ",");
		// System.out.print(vehicleVO2.getCartype_no() + ",");
		// System.out.print(vehicleVO2.getEmp_no() + ",");
		// System.out.print(vehicleVO2.getLicenseplate_no() + ",");
		// System.out.print(vehicleVO2.getColor() + ",");
		// System.out.print(vehicleVO2.getVehicle_year() + ",");
		// System.out.print(vehicleVO2.getEmp_branches());

		// 查詢全部
//		List<CarSchedulVO> list = dao.getAll();
//		for (CarSchedulVO carschedulList : list) {
//			System.out.print(carschedulList.getSerial_no() + ",");
//			System.out.print(carschedulList.getEmp_no() + ",");
//			System.out.print(carschedulList.getYear_month() + ",");
//			System.out.print(carschedulList.getAttendance() + ",");
//			System.out.print(carschedulList.getWork_hours() + ",");
//		
//			System.out.println();
//		}
		
//		List<CarSchedulVO> list = dao.getMonthInfo(1001,java.sql.Date.valueOf("2017-10-01"));
//		for (CarSchedulVO carschedulList : list) {
//			System.out.print(carschedulList.getSerial_no() + ",");
//			System.out.print(carschedulList.getEmp_no() + ",");
//			System.out.print(carschedulList.getYear_month() + ",");
//			System.out.print(carschedulList.getAttendance() + ",");
//			System.out.print(carschedulList.getWork_hours() + ",");
//		
//			System.out.println();
//		}
		
		
	}

	
}
