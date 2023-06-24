package dto;

import java.io.Serializable;
import java.util.Objects;

public class Country implements Serializable {

	private static final long serialVersionUID = 6499625077654825229L;
	private int id;
	private String name;

	public Country() {}
	
	public Country(int id) {
		this.id = id;
	}
	
	public Country(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + "]";
	}
}
