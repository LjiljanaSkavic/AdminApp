package beans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import dao.AccountDAO;
import dto.Account;

public class AccountBean implements Serializable {

	private static final long serialVersionUID = 5022669426687458041L;
	private Account account = new Account();
	private boolean isLoggedIn = false;

	public AccountBean() {
	}

	public boolean checkLogin(String username, String password) throws NoSuchAlgorithmException {
		if ((account = AccountDAO.selectByUsernameAndPassword(username, password)) != null) {
			if (account.getAccountType() == 1) {
				isLoggedIn = true;
				return true;
			}
		}
		return false;
	}

	public boolean loginFieldsFilled(String username, String password) {
		return !isNullOrEmptyString(username) && !isNullOrEmptyString(password);
	}

	public boolean isNullOrEmptyString(String string) {
		return string == null || string == "";
	}
}
