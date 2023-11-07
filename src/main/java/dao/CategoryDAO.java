package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Category;

public class CategoryDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT_CATEGORY = "INSERT INTO category (name) VALUES (?)";
	private static final String SQL_INSERT_SUBCATEGORY = "INSERT INTO category (name, parent_category_id) VALUES (?,?)";
	private static final String SQL_SELECT_CATEGORIES = "SELECT * FROM category";
	private static final String SQL_IS_NAME_USED = "SELECT * FROM category WHERE name = ?";
	private static final String SQL_UPDATE_CATEGORY = "UPDATE category SET name = ? WHERE id = ?";
	private static final String SQL_DELETE_CATEGORY_WITH_ID = "DELETE from category where id=?";

	public CategoryDAO() {
	}

	public static ArrayList<Category> getAll() {
		ArrayList<Category> categories = new ArrayList<Category>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_CATEGORIES, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				categories.add(new Category(rs.getInt("id"), rs.getString("name")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return categories;
	}

	public static boolean isNameUsed(String name) {
		boolean result = false;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { name };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_IS_NAME_USED, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

	public static boolean insertCategory(Category category) {
		boolean isInserted = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { category.getName() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_CATEGORY, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if (pstmt.getUpdateCount() > 0) {
				isInserted = true;
			}
			if (generatedKeys.next())
				category.setId(generatedKeys.getInt(1));
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return isInserted;
	}

	public static boolean insertSubcategory(int parentcategoryId, Category category) {
		boolean isInserted = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { category.getName(), parentcategoryId };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_SUBCATEGORY, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if (pstmt.getUpdateCount() > 0) {
				isInserted = true;
			}
			if (generatedKeys.next())
				category.setId(generatedKeys.getInt(1));
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return isInserted;
	}

	public static boolean update(int id, String name) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { name, id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE_CATEGORY, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if (pstmt.getUpdateCount() > 0) {
				result = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

	public static boolean delete(int id) {
		boolean idDeleted = false;
		Connection connection = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_DELETE_CATEGORY_WITH_ID, false, values);
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
