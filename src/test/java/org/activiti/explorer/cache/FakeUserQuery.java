package org.activiti.explorer.cache;

import java.util.List;

import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;

public class FakeUserQuery implements UserQuery {
	
	private List<User> users;
	
	public FakeUserQuery(List<User> users){
		this.users = users;
	}
	
	public long count() {
		return users.size();
	}	
	
	public List<User> listPage(int skipNum, int resultSize) {
		int lastResult = skipNum + resultSize;
		if(lastResult > users.size()) lastResult = users.size();
		return users.subList(skipNum, lastResult);
	}
	
	public UserQuery asc() {
		throw new RuntimeException("Method not implemented for test!");
	}



	public UserQuery desc() {
		throw new RuntimeException("Method not implemented for test!");
	}

	public List<User> list() {
		throw new RuntimeException("Method not implemented for test!");
	}



	public User singleResult() {
		throw new RuntimeException("Method not implemented for test!");
	}

	public UserQuery memberOfGroup(String arg0) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public UserQuery orderByUserEmail() {
		throw new RuntimeException("Method not implemented for test!");
	}

	public UserQuery orderByUserFirstName() {
		throw new RuntimeException("Method not implemented for test!");
	}

	public UserQuery orderByUserId() {
		throw new RuntimeException("Method not implemented for test!");
	}

	public UserQuery orderByUserLastName() {
		throw new RuntimeException("Method not implemented for test!");
	}

	public UserQuery userEmail(String arg0) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public UserQuery userEmailLike(String arg0) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public UserQuery userFirstName(String arg0) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public UserQuery userFirstNameLike(String arg0) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public UserQuery userId(String arg0) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public UserQuery userLastName(String arg0) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public UserQuery userLastNameLike(String arg0) {
		throw new RuntimeException("Method not implemented for test!");
	}

}
