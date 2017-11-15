package com.cartype.model;

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

public class CarTypeDAO implements CarType_interface {

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
	private static final String INSERT_STMT = "INSERT INTO CARTYPE (CARTYPE_NO, CARTYPE_NAME,DESCRIPTION,RENTAL_RATES,CAR_PIC) VALUES (cartype_seq.NEXTVAL,?,?,?,?)";
	private static final String UPDATE = "UPDATE CARTYPE SET CARTYPE_NAME = ?, DESCRIPTION = ?, RENTAL_RATES = ?,CAR_PIC= ? WHERE CARTYPE_NO = ?";
	//private static final String INSERT_STMT = "INSERT INTO CARTYPE (CARTYPE_NO, CARTYPE_NAME,DESCRIPTION,RENTAL_RATES) VALUES (cartype_seq.NEXTVAL,?,?,?)";
	//private static final String UPDATE = "UPDATE CARTYPE SET CARTYPE_NAME = ?, DESCRIPTION = ?, RENTAL_RATES = ? WHERE CARTYPE_NO = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM CARTYPE order by CARTYPE_NO";
	private static final String GET_ONE_STMT = "SELECT CARTYPE_NO,CARTYPE_NAME,DESCRIPTION,RENTAL_RATES FROM CARTYPE WHERE CARTYPE_NO = ?";
	private static final String DELETE = "DELETE FROM CARTYPE WHERE CARTYPE_NO = ?";
	

	@Override
	public void insert(CarTypeVO cartypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,cartypeVO.getCartypename());
			pstmt.setString(2,cartypeVO.getDescription());
			pstmt.setInt(3,cartypeVO.getRentalrates());
			pstmt.setBytes(4,cartypeVO.getCarpic());
			int i = pstmt.executeUpdate();
			System.out.println("成功新增"+i+"筆");
			
			
			
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
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
	public void update(CarTypeVO cartypeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1,cartypeVO.getCartypename());
			pstmt.setString(2,cartypeVO.getDescription());
			pstmt.setInt(3,cartypeVO.getRentalrates());
			pstmt.setBytes(4,cartypeVO.getCarpic());
			pstmt.setInt(5,cartypeVO.getCartypeno());

			pstmt.executeUpdate();
			
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
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
	public void delete(Integer cartypeno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1,cartypeno);
			System.out.println("ca"+cartypeno);
			pstmt.executeUpdate();

//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			
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
	public CarTypeVO findByPrimaryKey(Integer cartypeno) {

		CarTypeVO cartypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, cartypeno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cartypeVO = new CarTypeVO();
				cartypeVO.setCartypeno(rs.getInt("cartype_no"));
				cartypeVO.setCartypename(rs.getString("cartype_name"));
				cartypeVO.setDescription(rs.getString("description"));
				cartypeVO.setRentalrates(rs.getInt("rental_rates"));
				//cartypeVO.setCarpic(rs.getBytes("car_pic"));
			}
			
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			
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
		return cartypeVO;
	}

	@Override
	public List<CarTypeVO> getAll() {
		List<CarTypeVO> list = new ArrayList<CarTypeVO>();
		CarTypeVO cartypeVO = null;

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
				
				cartypeVO = new CarTypeVO();
				cartypeVO.setCartypeno(rs.getInt("cartype_no"));
				cartypeVO.setCartypename(rs.getString("cartype_name"));
				cartypeVO.setDescription(rs.getString("description"));
				cartypeVO.setRentalrates(rs.getInt("rental_rates"));
//				cartypeVO.setCarpic(rs.getBytes("car_pic"));
				
				list.add(cartypeVO); 
			}
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			
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

//	public static void main(String[] args) throws IOException{
//		String picName = "WebContent/images/tomcat.png";
//		File pic = new File(picName);
//		byte[] buffer = Files.readAllBytes(pic.toPath());
//		
//		
//		CarTypeDAO dao = new CarTypeDAO();
		
		// 修改
        
//		CarTypeVO cartypeVO2 = new CarTypeVO();
		//cartypeVO2.setCartypeno(1005);
//		cartypeVO2.setCartypename("四人座1231");
//		cartypeVO2.setDescription("豪華四人座房車1231");
//		cartypeVO2.setRentalrates(500);
//		cartypeVO2.setCarpic(buffer);
//		dao.update(cartypeVO2);

		// 新增
//		CarTypeVO cartypeVO1 = new CarTypeVO();
//		cartypeVO1.setCartypename("四人座");
//		cartypeVO1.setDescription("豪華四人座房車");
//		cartypeVO1.setRentalrates(500);
//      cartypeVO1.setCarpic(buffer);
//		dao.insert(cartypeVO1);
//		
//		
		
    	// 刪除
//				
//		dao.delete(1007);
//		System.out.println("刪除成功");
				
		//查詢單筆
		
//		CarTypeVO cartypeVO2 = dao.findByPrimaryKey(1002);
//		System.out.print(cartypeVO2.getCartypeno() + ",");
//		System.out.print(cartypeVO2.getCartypename() + ",");
//		System.out.print(cartypeVO2.getDescription() + ",");
//		System.out.print(cartypeVO2.getRentalrates()+ ",");
		
		
		// 查詢全部
//				List<CarTypeVO> list = dao.getAll();
//				for (CarTypeVO aCarType : list) {
//					System.out.print(aCarType.getCartypeno() + ",");
//					System.out.print(aCarType.getCartypename() + ",");
//					System.out.print(aCarType.getDescription() + ",");
//					System.out.print(aCarType.getRentalrates() + ",");
//					
//					System.out.println();
//				}
//	}
}

