package beans;

import java.io.Serializable;
import java.util.ArrayList;

import dao.CountryDAO;
import dao.LocationDAO;
import dao.UserDAO;
import dto.Country;
import dto.Location;
import dto.User;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 5022669426687458041L;
	private User user = new User();
	private boolean isLoggedIn = false;
	private User userForEdit;

	public UserBean() {}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	
	public User getUser() {
		return user;
	}
	
	public boolean checkLogin(String username, String password) {
		if ((user = UserDAO.selectByUsernameAndPassword(username, password)) != null) {
			isLoggedIn = true;
			return true;
		}
		return false;
	}
	
	public boolean isUsernameTeaken(String username) {
		return UserDAO.selectByUsername(username);
	}
	
	public boolean add(User user) {
		return UserDAO.insert(user);
	}
	
	public boolean update(int id, User user) {
		return UserDAO.update(id, user);
	}
	
	public boolean delete(int id) {
		return UserDAO.delete(id);
	}

	public boolean allFieldsFilled(String username, String password, String firstName,
			String lastName, String email, String country, String city,
			String streetAddress, String streetNumber, String postalCode) {
		return !isNullOrEmptyString(username) && !isNullOrEmptyString(password) && !isNullOrEmptyString(firstName) && !isNullOrEmptyString(lastName);
	}
	
	public boolean loginFieldsFilled(String username, String password) {
		return !isNullOrEmptyString(username) && !isNullOrEmptyString(password);
	}
	
	public boolean isNullOrEmptyString(String string) {
		return string == null || string == "";
	}

	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = UserDAO.getUsers();
		for(User u : users){
			u.setCountry(CountryDAO.selectById(u.getCountryId()));
			u.setLocation(LocationDAO.getById(u.getLocationId()));
		}
		return users;
	}
	
	public int getNumOfUsersFromCountry(int countryId) {
		return UserDAO.getNumberOfUsersFromCountry(countryId);
	}
	
	public int getNumOfUsersFromLocation(int locationId) {
		return UserDAO.getNumberOfUsersFromLocation(locationId);
	}
	
	public void setUserForEdit(int userId) {
		this.userForEdit = UserDAO.selectById(userId);
		Country country = CountryDAO.selectById(this.userForEdit.getCountryId());
		Location location = LocationDAO.getById(this.userForEdit.getLocationId());
		this.userForEdit.setCountry(country);
		this.userForEdit.setLocation(location);
	}

	public User getUserForEdit() {
		return this.userForEdit;
	}
}
