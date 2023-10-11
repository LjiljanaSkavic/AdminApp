package dto;

public class Account {
	
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int accountTypeId;
	
	public Account() {}
	
	public Account(int id, String username, String password, String firstName, String lastName,
			int accountTypeId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountTypeId = accountTypeId;
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

	public int getAccountType() {
		return accountTypeId;
	}

	public void setAccountType(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", accountTypeId=" + accountTypeId + "]";
	}
}
