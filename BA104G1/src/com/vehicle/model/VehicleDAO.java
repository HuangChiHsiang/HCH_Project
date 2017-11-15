package com.vehicle.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vehicle.model.VehicleVO;

public class VehicleDAO implements Vehicle_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G1";
	String passwd = "BA104G1";

	// private static DataSource ds = null;
	// static {
	// try {
	// Context ctx = new InitialContext();
	// ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G1");
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// }
	private static final String INSERT_STMT = "INSERT INTO VEHICLE (VEHICLE_NO, CARTYPE_NO,EMP_NO,LICENSEPLATE_NO,COLOR,VEHICLE_YEAR,EMP_BRANCHES) VALUES (vehicle_seq.NEXTVAL,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM VEHICLE order by VEHICLE_NO";
	private static final String GET_ONE_STMT = "SELECT VEHICLE_NO,CARTYPE_NO,EMP_NO,LICENSEPLATE_NO,COLOR,VEHICLE_YEAR,EMP_BRANCHES FROM VEHICLE WHERE VEHICLE_NO = ?";
	private static final String DELETE = "DELETE FROM VEHICLE WHERE VEHICLE_NO = ?";
	private static final String UPDATE = "UPDATE VEHICLE SET CARTYPE_NO = ?, EMP_NO = ?, LICENSEPLATE_NO = ?,COLOR= ?,VEHICLE_YEAR=?,EMP_BRANCHES=? WHERE VEHICLE_NO = ?";
	private static final String GET_CARTYPE_STMT = "SELECT * FROM VEHICLE WHERE CARTYPE_NO = ?";
	
	
	@Override
	public void insert(VehicleVO vehicleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			// con = ds.getConnection();

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, vehicleVO.getCartype_no());
			pstmt.setString(2, vehicleVO.getEmp_no());
			pstmt.setString(3, vehicleVO.getLicenseplate_no());
			pstmt.setString(4, vehicleVO.getColor());
			pstmt.setString(5, vehicleVO.getVehicle_year());
			pstmt.setString(6, vehicleVO.getEmp_branches());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(VehicleVO vehicleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			// con = ds.getConnection();
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, vehicleVO.getCartype_no());
			pstmt.setString(2, vehicleVO.getEmp_no());
			pstmt.setString(3, vehicleVO.getLicenseplate_no());
			pstmt.setString(4, vehicleVO.getColor());
			pstmt.setString(5, vehicleVO.getVehicle_year());
			pstmt.setString(6, vehicleVO.getEmp_branches());
			pstmt.setInt(7, vehicleVO.getVehicle_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(Integer vehicle_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			// con = ds.getConnection();
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, vehicle_no);
			System.out.println("刪除編號:" + vehicle_no);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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
	public VehicleVO findByPrimaryKey(Integer vehicle_no) {

		VehicleVO vehicleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			// con = ds.getConnection();
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, vehicle_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				vehicleVO = new VehicleVO();
				vehicleVO.setVehicle_no(rs.getInt("vehicle_no"));
				vehicleVO.setCartype_no(rs.getInt("cartype_no"));
				vehicleVO.setEmp_no(rs.getString("emp_no"));
				vehicleVO.setLicenseplate_no(rs.getString("licenseplate_no"));
				vehicleVO.setColor(rs.getString("color"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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
		return vehicleVO;
	}

	@Override
	public List<VehicleVO> getAll() {
		List<VehicleVO> list = new ArrayList<VehicleVO>();
		VehicleVO vehicleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			// con = ds.getConnection();

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				vehicleVO = new VehicleVO();
				vehicleVO.setVehicle_no(rs.getInt("vehicle_no"));
				vehicleVO.setCartype_no(rs.getInt("cartype_no"));
				vehicleVO.setEmp_no(rs.getString("emp_no"));
				vehicleVO.setLicenseplate_no(rs.getString("licenseplate_no"));
				vehicleVO.setColor(rs.getString("color"));

				list.add(vehicleVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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
	public List<VehicleVO> getAllByCarType(Integer cartype_no) {
		List<VehicleVO> list = new ArrayList<VehicleVO>();
		VehicleVO vehicleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			// con = ds.getConnection();

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_CARTYPE_STMT);
			rs = pstmt.executeQuery();
			
			pstmt.setInt(1, cartype_no);
			
			while (rs.next()) {

				vehicleVO = new VehicleVO();
				vehicleVO.setVehicle_no(rs.getInt("vehicle_no"));
				vehicleVO.setCartype_no(rs.getInt("cartype_no"));
				vehicleVO.setEmp_no(rs.getString("emp_no"));
				vehicleVO.setLicenseplate_no(rs.getString("licenseplate_no"));
				vehicleVO.setColor(rs.getString("color"));

				list.add(vehicleVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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

		VehicleDAO dao = new VehicleDAO();

		// 修改

		// VehicleVO vehicleVO2 = new VehicleVO();
		// vehicleVO2.setVehicle_no(1007);
		// vehicleVO2.setCartype_no(1001);
		// vehicleVO2.setEmp_no("EMP0001");
		// vehicleVO2.setLicenseplate_no("ABCC4553");
		// vehicleVO2.setColor("貴族黑");
		// vehicleVO2.setVehicle_year("2016");
		// vehicleVO2.setEmp_branches("10");
		// dao.update(vehicleVO2);

		// 新增
		// VehicleVO vehicleVO1 = new VehicleVO();
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

		// VehicleVO vehicleVO2 = dao.findByPrimaryKey(1002);
		// System.out.print(vehicleVO2.getVehicle_no() + ",");
		// System.out.print(vehicleVO2.getCartype_no() + ",");
		// System.out.print(vehicleVO2.getEmp_no() + ",");
		// System.out.print(vehicleVO2.getLicenseplate_no() + ",");
		// System.out.print(vehicleVO2.getColor() + ",");
		// System.out.print(vehicleVO2.getVehicle_year() + ",");
		// System.out.print(vehicleVO2.getEmp_branches());

		// 查詢全部
		List<VehicleVO> list = dao.getAll();
		for (VehicleVO vehicleList : list) {
			System.out.print(vehicleList.getVehicle_no() + ",");
			System.out.print(vehicleList.getCartype_no() + ",");
			System.out.print(vehicleList.getEmp_no() + ",");
			System.out.print(vehicleList.getLicenseplate_no() + ",");
			System.out.print(vehicleList.getColor() + ",");
			System.out.print(vehicleList.getVehicle_year() + ",");
			System.out.print(vehicleList.getEmp_branches());
			System.out.println();
		}
	}

	
}
