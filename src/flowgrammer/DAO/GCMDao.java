package flowgrammer.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import flowgrammer.model.PushId;
import flowgrammer.model.Team;


public class GCMDao {

	private static String jdbcUrl = "jdbc:mysql://localhost/servlet_test";
	private static String userId = "servletuser";
	private static String userPass = "servletpassword";
	
	private static boolean checkJdbc() {
		boolean jdbcEnabled = true;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
//			System.out.print("Class Not Found Exception" + e.getMessage());
			jdbcEnabled = false;
		}
		return jdbcEnabled;
	}
	
	public static List<Team> getTeam() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Team> teamList = new ArrayList<Team>();
		
		if (!checkJdbc()) {
			return teamList;
		}
		
		try {
			conn = DriverManager.getConnection(jdbcUrl, userId, userPass);
			
			String sql = "select * from team";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String name = rs.getString("NAME");
//				System.out.print("name : " + name);
				Team team = new Team();
				team.setName(name);
				teamList.add(team);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("exception : " + e.getMessage());
		} finally {
			if (rs != null) { try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (stmt != null) { try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		
		return teamList;
	}

	public static List<PushId> getPushId() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<PushId> pushIds = new ArrayList<PushId>();
		
		if (!checkJdbc()) {
			return pushIds;
		}
		
		try {
			conn = DriverManager.getConnection(jdbcUrl, userId, userPass);
			
			String sql = "select * from PUSH";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String id = rs.getString("REG_ID");
//				System.out.print("name : " + name);
				PushId pushId = new PushId();
				pushId.setPushId(id);
				pushIds.add(pushId);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("exception : " + e.getMessage());
		} finally {
			if (rs != null) { try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (stmt != null) { try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		
		return pushIds;
		
	}
}
