package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Log;

public class LogDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_LOGS = "SELECT * FROM log";

	public LogDAO() {
	}

	public static ArrayList<Log> getLogs() {
		ArrayList<Log> logs = new ArrayList<Log>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_LOGS, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				logs.add(new Log(rs.getInt("id"), rs.getString("content"), rs.getString("date_time")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return logs;
	}
}
