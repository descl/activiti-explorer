package org.activiti.explorer.cache;

import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.identity.Account;

public class FakeIdentityService implements IdentityService {
	
	private List<User> myUsers;


	public FakeIdentityService(List<User> users){
		myUsers = users;
	}
	
	public UserQuery createUserQuery() {
		return new FakeUserQuery(myUsers);
	}
	
	public boolean checkPassword(String arg0, String arg1) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public GroupQuery createGroupQuery() {
		throw new RuntimeException("Method not implemented for test!");
	}

	public void createMembership(String arg0, String arg1) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public void deleteGroup(String arg0) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public void deleteMembership(String arg0, String arg1) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public void deleteUser(String arg0) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public void deleteUserAccount(String arg0, String arg1) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public void deleteUserInfo(String arg0, String arg1) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public Account getUserAccount(String arg0, String arg1, String arg2) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public List<String> getUserAccountNames(String arg0) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public String getUserInfo(String arg0, String arg1) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public List<String> getUserInfoKeys(String arg0) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public Picture getUserPicture(String arg0) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public Group newGroup(String arg0) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public User newUser(String arg0) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public void saveGroup(Group arg0) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public void saveUser(User arg0) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public void setAuthenticatedUserId(String arg0) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public void setUserAccount(String arg0, String arg1, String arg2,
			String arg3, String arg4, Map<String, String> arg5) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public void setUserInfo(String arg0, String arg1, String arg2) {
		throw new RuntimeException("Method not implemented for test!");
	}

	public void setUserPicture(String arg0, Picture arg1) {
		throw new RuntimeException("Method not implemented for test!");
	}

}
