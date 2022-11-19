package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Employee;

public class JdbcFile {
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;

	public void databaseOps() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-B7POKE87:1521:xe", "c##empms", "empms");
			System.out.println("Connection got established");

			String msgsql = "select empid, empname, empsal from tblemployee";

			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			rs = stmt.executeQuery(msgsql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void rcrInsert(int eid, String ename, int esal) {
		try {
			String msgsql = "insert into tblemployee values(?, ?, ?)";

			pstmt = con.prepareStatement(msgsql);
			pstmt.setInt(1, eid);
			pstmt.setString(2, ename);
			pstmt.setInt(3, esal);
			
			pstmt.executeQuery();
			
			System.out.println("Record got inserted successfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void rcrUpdate(int eid, String ename, int esal) {
		try {
			String msgsql = "update tblemployee set empid = ?, empname = ?, empsal = ?";

			pstmt = con.prepareStatement(msgsql);
			pstmt.setInt(1, eid);
			pstmt.setString(2, ename);
			pstmt.setInt(3, esal);
			
			pstmt.executeQuery();
			
			System.out.println("Record got updated successfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void rcrSearchEmployee(int eid) {
		
		String query = "select empid, empname, empsal from tblemployee where empid = ?";

		try {
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, eid);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				Employee empobj = new Employee();
				empobj.setEmpid(rs.getInt(1));
				empobj.setEmpname(rs.getString(2));
				empobj.setEmpsalary(rs.getInt(3));
				
				System.out.println(empobj);
			} else {
				System.err.println("Employee record does not exist");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void rcrDelete(int eid) {
		String query = "delete from tblemployee where empid = ?";

		try {
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, eid);

			pstmt.executeQuery();
			
			System.out.println("Record got deleted successfully for emp id : " + eid);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}