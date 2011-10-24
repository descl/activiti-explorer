package org.activiti.explorer.cache;

import org.activiti.engine.identity.User;

public class FakeUser implements User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FakeUser(String email, String firstName, String id, String lastName,
			String password) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.id = id;
		this.lastName = lastName;
		this.password = password;
	}

	private String email, firstName, id, lastName, password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
