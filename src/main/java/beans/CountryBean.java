package beans;

import java.io.Serializable;
import java.util.ArrayList;

import dao.CountryDAO;
import dto.Country;

public class CountryBean implements Serializable {

	private static final long serialVersionUID = -3766388434480561490L;

	public CountryBean() {
	}

	public boolean add(Country country) {
		return CountryDAO.insert(country);
	}

	public boolean deleteWithId(int id) {
		return CountryDAO.delete(id);
	}

	public int addAndReturnId(Country country) {
		return CountryDAO.insertAndReturnId(country);
	}

	public ArrayList<Country> getAllCountries() {
		return CountryDAO.getAll();
	}

	public Country getCountryWithName(String country) {
		return CountryDAO.selectByName(country);
	}
}
