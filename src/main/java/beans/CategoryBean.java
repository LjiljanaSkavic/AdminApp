package beans;

import java.io.Serializable;
import java.util.ArrayList;

import dao.CategoryDAO;
import dto.Category;

public class CategoryBean implements Serializable {
	
	private static final long serialVersionUID = 2234838546106808147L;

	public CategoryBean() {}
	
	public boolean categoryExists(String name) {
		return CategoryDAO.isNameUsed(name);
	}
	
	public boolean addCategory(Category category) {
		return CategoryDAO.insertCategory(category);
	}
	
	public boolean addSubcategory(int parentCategoryId, Category category) {
		return CategoryDAO.insertSubcategory(parentCategoryId, category);
	}
	
	public boolean delete(int id) {
		return CategoryDAO.delete(id);
	}
	
	public boolean edit(int id, String name) {
		return CategoryDAO.update(id, name);
	}
	
	public ArrayList<Category> getAllCategories() {
		return CategoryDAO.getAll();
	}
}
