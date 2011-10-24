package org.activiti.explorer.cache;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.User;
import org.junit.Test;

public class TrieBasedUserCacheTest {

	private static List<User> allUsers;
	static {
		allUsers = new ArrayList<User>();
		allUsers.add(new FakeUser("mf6443876@fakemail.com", "Jed", "f6443876", "Covington", ""));
		allUsers.add(new FakeUser("m94fbedfb@fakemail.com", "Jed", "94fbedfb", "Gilkeson", ""));
		allUsers.add(new FakeUser("m03d4c904@fakemail.com", "Jed", "03d4c904", "Gororth", ""));
		allUsers.add(new FakeUser("m31d12b01@fakemail.com", "Jed", "31d12b01", "Harden", ""));
		allUsers.add(new FakeUser("m61586a58@fakemail.com", "Jed", "61586a58", "Headden", ""));
		allUsers.add(new FakeUser("m1f762eff@fakemail.com", "Jed", "1f762eff", "Hoefler", ""));
		allUsers.add(new FakeUser("m58b5fcb8@fakemail.com", "Jed", "58b5fcb8", "Paddock", ""));
		allUsers.add(new FakeUser("m9a23533c@fakemail.com", "Jed", "9a23533c", "Stubblefield", ""));
		allUsers.add(new FakeUser("mbf20409c@fakemail.com", "Jed", "bf20409c", "Thomas", ""));
		allUsers.add(new FakeUser("m32af1b82@fakemail.com", "Jed", "32af1b82", "Watson", ""));
		allUsers.add(new FakeUser("m94b51244@fakemail.com", "Jed", "94b51244", "Aston Jones", ""));
		allUsers.add(new FakeUser("m53c7e9f3@fakemail.com", "Jed", "53c7e9f3", "Fink", ""));
		allUsers.add(new FakeUser("m4380de06@fakemail.com", "Jed", "4380de06", "Onorato", ""));
		allUsers.add(new FakeUser("mf6d6c4f4@fakemail.com", "Jed", "f6d6c4f4", "Hager", ""));
		allUsers.add(new FakeUser("m5614a15e@fakemail.com", "Marie", "5614a15e", "Jed", ""));
		allUsers.add(new FakeUser("mc64d55bf@fakemail.com", "Jed", "c64d55bf", "Malloy", ""));
		allUsers.add(new FakeUser("m0984e351@fakemail.com", "Jed", "0984e351", "Melancon", ""));
		allUsers.add(new FakeUser("m93c43aff@fakemail.com", "Jed", "93c43aff", "Semb", ""));
		allUsers.add(new FakeUser("m210dfb02@fakemail.com", "Jed", "210dfb02", "Mahanes", ""));
		allUsers.add(new FakeUser("mcbf89b1d@fakemail.com", "Jed", "cbf89b1d", "Huebner", ""));
		allUsers.add(new FakeUser("m20ee2026@fakemail.com", "Jed", "20ee2026", "Fredericks", ""));
		allUsers.add(new FakeUser("m44f83ccc@fakemail.com", "Jed", "44f83ccc", "Burkowski", ""));
		allUsers.add(new FakeUser("mb22e3a76@fakemail.com", "Jed", "b22e3a76", "Argraves", ""));
		allUsers.add(new FakeUser("m8b58dd07@fakemail.com", "Jed", "8b58dd07", "Black", ""));
		allUsers.add(new FakeUser("m65285bb0@fakemail.com", "Jed", "65285bb0", "Stupp", ""));

	}
	@Test
	public void testSetIdentityServiceWithAllUsers() {
		TrieBasedUserCache tbuc = new TrieBasedUserCache();
		tbuc.setIdentityService(new FakeIdentityService(allUsers));
		assertTrue(true);
	}
	
	@Test
	public void testSetIdentityServiceWithThreeUsers() {
		TrieBasedUserCache tbuc = new TrieBasedUserCache();
		tbuc.setIdentityService(new FakeIdentityService(allUsers.subList(0,3)));
		assertTrue(true);
	}	
	@Test
	public void testSetIdentityServiceWithTwoUsers() {
		TrieBasedUserCache tbuc = new TrieBasedUserCache();
		tbuc.setIdentityService(new FakeIdentityService(allUsers.subList(0,2)));
		assertTrue(true);
	}
}
