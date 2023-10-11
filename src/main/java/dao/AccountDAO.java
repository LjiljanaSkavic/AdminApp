package dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Account;
import services.HashService;

public class AccountDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String ACCOUNT_TYPE_ADMIN = "Admin";
	private static final String SQL_SELECT_ACCOUNTS = "SELECT * FROM account";
	private static final String SQL_SELECT_ACCOUNT_BY_USERNAME_AND_PASSWORD = "SELECT * FROM account WHERE username=? AND password=?";
	
	public static ArrayList<Account> getAccountTypes(){
		ArrayList<Account> accounts = new ArrayList<Account>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ACCOUNTS, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				accounts.add(new Account(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("firstName"), rs.getString("lastName"), rs.getInt("account_type_id")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return accounts;
	}
	
	public static Account selectByUsernameAndPassword(String username, String password) throws NoSuchAlgorithmException{
		String passwordHash = HashService.toHexString(HashService.getSHA(password));
		Account account = null;
		Connection connection = null;
		ResultSet rs = null;
		System.out.println(username + " " + passwordHash);
		Object values[] = {username, passwordHash};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_SELECT_ACCOUNT_BY_USERNAME_AND_PASSWORD, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				account = new Account(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("first_name"), rs.getString("last_name"), rs.getInt("account_type_id"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		System.out.println(account);
		return account;
	}

}
