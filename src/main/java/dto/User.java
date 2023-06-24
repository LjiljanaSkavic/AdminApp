package dto;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

	private static final long serialVersionUID = -3029572118325171012L;
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int isLoggedIn;
	
	private int countryId;
	private int locationId;
	
	private Country country;
	private Location location;
	
	public User() {}
	
	public User(int id, String username, String password, String firstName, String lastName, String email, int isLoggedIn, int countryId, int locationId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isLoggedIn = isLoggedIn;
		this.countryId = countryId;
		this.locationId = locationId;
	}
	
	public User(int id, String username, String password, String firstName, String lastName, String email, int isLoggedIn) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isLoggedIn = isLoggedIn;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(int isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Location getLocation() {
		return location;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", isLoggedIn=" + isLoggedIn + ", countryId="
				+ countryId + ", locationId=" + locationId + ", country=" + country + ", location=" + location + "]";
	}
}
