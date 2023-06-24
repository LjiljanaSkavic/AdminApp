package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Country;

public class CountryDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_COUNTRIES = "SELECT * FROM country";
	private static final String SQL_INSERT_COUNTRY = "INSERT INTO country (name) VALUES (?)";
	private static final String SQL_SELECT_COUNTRY_WITH_ID = "SELECT * FROM country WHERE id = ?";
	private static final String SQL_SELECT_COUNTRY_WITH_NAME = "SELECT * FROM country WHERE name=?";
	private static final String  SQL_DELETE_COUNTRY = "DELETE FROM country WHERE id=?";

	public CountryDAO() {
	}

	public static ArrayList<Country> getAll() {
		ArrayList<Country> countries = new ArrayList<Country>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_COUNTRIES, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				countries.add(new Country(rs.getInt("id"), rs.getString("name")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return countries;
	}

	public static Country selectById(int id) {
		Country country = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_COUNTRY_WITH_ID, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				country = new Country(rs.getInt("id"), rs.getString("name"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return country;
	}

	public static Country selectByName(String name) {
		Country country = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { name };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_COUNTRY_WITH_NAME, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				country = new Country(rs.getInt("id"), rs.getString("name"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return country;
	}

	public static boolean insert(Country country) {
		boolean isInserted = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { country.getName() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_COUNTRY, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if (pstmt.getUpdateCount() > 0) {
				isInserted = true;
			}
			if (generatedKeys.next())
				country.setId(generatedKeys.getInt(1));
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return isInserted;
	}

	public static int insertAndReturnId(Country country) {
		int id = -1;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { country.getName() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_COUNTRY, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if (pstmt.getUpdateCount() > 0 && generatedKeys.next()) {
				country.setId(generatedKeys.getInt(1));
				id = country.getId();
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return id;
	}
	
	public static boolean delete(int id) {
		boolean idDeleted = false;
		Connection connection = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_DELETE_COUNTRY, false, values);
			pstmt.executeUpdate();
			idDeleted = true;
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return idDeleted;
	}
}
