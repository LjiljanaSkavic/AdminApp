package beans;

import java.io.Serializable;
import java.util.ArrayList;

import dao.LocationDAO;
import dto.Location;

public class LocationBean implements Serializable  {

	private static final long serialVersionUID = 9163834496251196521L;

	public LocationBean() {}
	
	
	public boolean add(Location location) {
		return LocationDAO.insert(location);
	}
	
	public boolean deleteWithId(int id) {
		return LocationDAO.delete(id);
	}
	
	public ArrayList<Location> getAllLocations() {
		return LocationDAO.getAll();
	}
	
	public Location getLocationIfExist(String streetAddress, int streetNumber, String postalCode, String city, int countryId) {
		return LocationDAO.getLocationIfExist(streetAddress, streetNumber, postalCode, city, countryId);
	}
	
	public int addLocationAndReturnId(Location location) {
		return LocationDAO.insertAndReturnId(location);
	}

}
