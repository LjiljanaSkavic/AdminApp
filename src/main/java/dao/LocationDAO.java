package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Country;
import dto.Location;
import dto.User;

public class LocationDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_LOCATIONS = "SELECT * FROM location";
	private static final String SQL_SELECT_LOCATION_IF_EXIST = "SELECT * FROM location where street_address=? AND street_number=? AND postal_code=? AND city=? AND country_id=?";
	private static final String SQL_INSERT_LOCATION = "INSERT INTO location (street_address, street_number, postal_code, city, country_id) VALUES (?,?,?,?,?)";
	private static final String SQL_SELECT_LOCATION_WITH_ID = "SELECT * FROM location WHERE id = ?";
	private static final String SQL_DELETE_LOCATION_WITH_ID = "DELETE FROM location WHERE id = ?";
	
	public LocationDAO() {}

	public static boolean insert(Location location) {
		boolean isInserted = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { location.getStreetAddress(), location.getStreetNumber(), location.getPostalCode(), location.getCity() , location.getCountryId()};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_LOCATION, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(pstmt.getUpdateCount()>0) {
				isInserted = true;
			}
			if (generatedKeys.next())
				location.setId(generatedKeys.getInt(1));
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return isInserted;
	}
	
	public static int insertAndReturnId(Location location) {
		int id = -1;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { location.getStreetAddress(), location.getStreetNumber(), location.getPostalCode(), location.getCity(), location.getCountryId() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_LOCATION, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(pstmt.getUpdateCount()>0 && generatedKeys.next()) {
				location.setId(generatedKeys.getInt(1));
				id = location.getId();
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return id;
	}
	
	public static Location getLocationIfExist(String streetAddress, int streetNumber, String postalCode, String city, int countryId){
		Location location = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {streetAddress, streetNumber, postalCode, city, countryId };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_SELECT_LOCATION_IF_EXIST, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				location = new Location(rs.getInt("id"), rs.getString("street_address"), rs.getInt("street_number"),
						rs.getString("postal_code"), rs.getString("city"), rs.getInt("country_id"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return location;
	}
	
	public static Location getById(int id) {
		Location location = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_LOCATION_WITH_ID, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				location = new Location(rs.getInt("id"), rs.getString("street_address"), rs.getInt("street_number"),
						rs.getString("postal_code"), rs.getString("city"), rs.getInt("country_id"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return location;
	}
	
	public static ArrayList<Location> getAll(){
		ArrayList<Location> locations = new ArrayList<Location>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_LOCATIONS, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				locations.add(new Location(rs.getInt("id"), rs.getString("street_address"), rs.getInt("street_number"),
						rs.getString("postal_code"), rs.getString("city"), rs.getInt("country_id")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return locations;
	}
	
	public static boolean delete(int id) {
		boolean idDeleted = false;
		Connection connection = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_DELETE_LOCATION_WITH_ID, false, values);
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
