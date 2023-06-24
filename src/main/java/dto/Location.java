package dto;

import java.io.Serializable;

public class Location implements Serializable {

	private static final long serialVersionUID = 4118489451011456993L;
	private int id;
	private String streetAddress;
	private int streetNumber;
	private String postalCode;
	private String city;
	
	private int countryId;

	public Location() {}
	
	public Location(int id) {
		this.id = id;
	}

	public Location(int id, String streetAddress, int streetNumber, String postalCode, String city, int countryId) {
		super();
		this.id = id;
		this.streetAddress = streetAddress;
		this.streetNumber = streetNumber;
		this.postalCode = postalCode;
		this.city = city;
		this.countryId = countryId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", streetAddress=" + streetAddress + ", streetNumber=" + streetNumber
				+ ", postalCode=" + postalCode + ", city=" + city + ", countryId=" + countryId + "]";
	}
}
