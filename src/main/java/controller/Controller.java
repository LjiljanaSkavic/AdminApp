package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AccountBean;
import beans.CategoryBean;
import beans.UserBean;
import beans.LocationBean;
import beans.CountryBean;
import dto.Category;
import dto.User;
import dto.Location;
import dto.Country;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String address = "/WEB-INF/pages/login.jsp";
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		session.setAttribute("notification", "");

		if (action == null || action.equals("") || action.equals("loginPage")) {
			address = "/WEB-INF/pages/login.jsp";
		} else if (action.equals("logout")) {
			session.invalidate();
			address = "/WEB-INF/pages/login.jsp";
		} else if (action.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			AccountBean accountBean = new AccountBean();
			if (accountBean.loginFieldsFilled(username, password)) {
				try {
					if (accountBean.checkLogin(username, password)) {
						session.setAttribute("accountBean", accountBean);
						address = "/WEB-INF/pages/mainpage.jsp";
					} else {
						session.setAttribute("notification", "Wrong username or password");
						address = "/WEB-INF/pages/login.jsp";
					}
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			} else {
				session.setAttribute("notification", "All fields are required.");
				address = "/WEB-INF/pages/login.jsp";
			}
		} else if (action.equals("users")) {
			address = "/WEB-INF/pages/users.jsp";
		} else if (action.equals("categories")) {
			address = "/WEB-INF/pages/categories.jsp";
		} else if (action.equals("saveCategory")) {
			CategoryBean categoryBean = new CategoryBean();
			String name = request.getParameter("newCategory");
			if (name != "" && !categoryBean.categoryExists(name)) {
				categoryBean.addCategory(new Category(0, name));
			}
			address = "/WEB-INF/pages/categories.jsp";
		} else if (action.equals("saveUser")) {
			UserBean userBean = new UserBean();
			CountryBean countryBean = new CountryBean();
			LocationBean locationBean = new LocationBean();

			String username = request.getParameter("username");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String password = request.getParameter("password");
			String email = request.getParameter("email");

			String countryName = request.getParameter("country");
			String city = request.getParameter("city");
			String streetAddress = request.getParameter("streetAddress");
			String streetNumber = request.getParameter("streetNumber");
			String postalCode = request.getParameter("postalCode");

			if (userBean.allFieldsFilled(username, password, firstName, lastName, email, countryName, city,
					streetAddress, streetNumber, postalCode)) {
				if (!userBean.isUsernameTeaken(username)) {
					User newUser = new User(0, username, password, firstName, lastName, email, 0);
					newUser.setCountryId(-1);
					newUser.setLocationId(-1);

					Country country = countryBean.getCountryWithName(countryName);
					if (country != null) {
						// country exists, check if location exists
						newUser.setCountryId(country.getId());

						Location location = locationBean.getLocationIfExist(streetAddress,
								Integer.parseInt(streetNumber), postalCode, city, country.getId());
						if (location != null) {
							// location exists
							newUser.setLocationId(location.getId());
						} else {
							// location doesn't exist, add location
							newUser.setLocationId(locationBean.addLocationAndReturnId(location));
						}
					} else {
						// the country does not exist and therefore neither does the location
						int newCountryId = countryBean.addAndReturnId(new Country(0, countryName));
						newUser.setCountryId(newCountryId);
						int newLocationId = locationBean.addLocationAndReturnId(new Location(0, streetAddress,
								Integer.parseInt(streetNumber), postalCode, city, newCountryId));
						newUser.setLocationId(newLocationId);

					}

					if (userBean.add(newUser)) {
						session.setAttribute("notification", "Registration success!");
					}

				} else {
					session.setAttribute("notification", "Username is already teaken.");
					address = "/WEB-INF/pages/users.jsp";
				}
			} else {
				session.setAttribute("notification", "All fields are required.");
				address = "/WEB-INF/pages/users.jsp";
			}
			address = "/WEB-INF/pages/users.jsp";
		} else if (action.equals("deleteCategory")) {
			if (request.getParameter("categoryId") != null) {
				CategoryBean categoryBean = new CategoryBean();
				categoryBean.delete(Integer.parseInt(request.getParameter("categoryId")));
				address = "/WEB-INF/pages/categories.jsp";
				response.sendRedirect(request.getContextPath() + "?action=categories");
				return;
			}
		} else if (action.equals("openEditCategory")) {
			address = "/WEB-INF/pages/edit-category.jsp";
		} else if (action.equals("editCategory")) {
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			String newName = request.getParameter("newName");
			CategoryBean categoryBean = new CategoryBean();
			categoryBean.edit(categoryId, newName);
			response.sendRedirect(request.getContextPath() + "?action=categories");
			return;
		} else if (action.equals("deleteUser")) {
			if (request.getParameter("userId") != null) {
				UserBean userBean = new UserBean();
				
				int numberOfUsersFromLocationWithId = userBean.getNumOfUsersFromLocation(Integer.parseInt(request.getParameter("locationId")));
				if(numberOfUsersFromLocationWithId <= 1) {
					LocationBean locationBean = new LocationBean();
					locationBean.deleteWithId(Integer.parseInt(request.getParameter("locationId")));
				}
				
				int numberOfUsersFromCountryWithId = userBean.getNumOfUsersFromCountry(Integer.parseInt(request.getParameter("countryId")));			
				if(numberOfUsersFromCountryWithId <= 1) {
					CountryBean countryBean = new CountryBean();
					countryBean.deleteWithId(Integer.parseInt(request.getParameter("countryId")));
				}
				
				userBean.delete(Integer.parseInt(request.getParameter("userId")));
				
				address = "/WEB-INF/pages/users.jsp";
				response.sendRedirect(request.getContextPath() + "?action=users");
				return;
			}
		} else if (action.equals("openEditUser")) {
			address = "/WEB-INF/pages/edit-user.jsp";
			UserBean userBean = (UserBean) session.getAttribute("userBean");
			userBean.setUserForEdit(Integer.parseInt(request.getParameter("userId")));
			session.setAttribute("userBean", userBean);
			address = "/WEB-INF/pages/edit-user.jsp";
		} else if(action.equals("editUser")) {
			int userdId = Integer.parseInt(request.getParameter("userId"));
			System.out.println("EDITIIIIIIIING");
			UserBean userBean = new UserBean();
			CountryBean countryBean = new CountryBean();
			LocationBean locationBean = new LocationBean();

			String username = request.getParameter("username");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String password = request.getParameter("password");
			String email = request.getParameter("email");

			String countryName = request.getParameter("country");
			String city = request.getParameter("city");
			String streetAddress = request.getParameter("streetAddress");
			String streetNumber = request.getParameter("streetNumber");
			String postalCode = request.getParameter("postalCode");

			if (userBean.allFieldsFilled(username, password, firstName, lastName, email, countryName, city,
					streetAddress, streetNumber, postalCode)) {
				if (!userBean.isUsernameTeaken(username)) {
					User editedUser = new User(0, username, password, firstName, lastName, email, 0);
					editedUser.setCountryId(-1);
					editedUser.setLocationId(-1);

					Country country = countryBean.getCountryWithName(countryName);
					System.out.println(country);
					if (country != null) {
						// country exists, check if location exists
						editedUser.setCountryId(country.getId());

						Location location = locationBean.getLocationIfExist(streetAddress,
								Integer.parseInt(streetNumber), postalCode, city, country.getId());
						System.out.println(location);
						if (location != null) {
							editedUser.setLocationId(location.getId());
						} else {
							Location newLocation = new Location(0, streetAddress, Integer.parseInt(streetNumber), postalCode, city,  country.getId());
							editedUser.setLocationId(locationBean.addLocationAndReturnId(newLocation));
						}
					} else {
						// the country does not exist and therefore neither does the location
						int newCountryId = countryBean.addAndReturnId(new Country(0, countryName));
						editedUser.setCountryId(newCountryId);
						int newLocationId = locationBean.addLocationAndReturnId(new Location(0, streetAddress,
								Integer.parseInt(streetNumber), postalCode, city, newCountryId));
						editedUser.setLocationId(newLocationId);
					}
					if (userBean.update(userdId, editedUser)) {
						session.setAttribute("notification", "Registration success!");
					}

				} else {
					session.setAttribute("notification", "Username is already teaken.");
					address = "/WEB-INF/pages/users.jsp";
				}
			} else {
				session.setAttribute("notification", "All fields are required.");
				address = "/WEB-INF/pages/users.jsp";
			}
			address = "/WEB-INF/pages/users.jsp";
		}
		else {
			address = "/WEB-INF/pages/404.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
