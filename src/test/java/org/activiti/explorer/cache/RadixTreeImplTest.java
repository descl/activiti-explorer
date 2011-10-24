package org.activiti.explorer.cache;

import static org.junit.Assert.*;

import org.junit.Test;

public class RadixTreeImplTest {

	
	@Test
	public void test() {
		RadixTree<Integer> rt = new RadixTreeImpl<Integer>();
		
		assertEquals(rt.getSize(), 0);
		
		/*
		 * This should be the sequence of inserts and deletes performed by the
		 * TrieBasedUserCacheTest#testSetIdentityServiceWithThreeUsers test.
		 */
		rt.insert("jed", 48);
		rt.insert("covington", 87);
		rt.delete("jed");
		rt.insert("jed", 842584);
		rt.insert("gilkeson", 4982892);
		rt.delete("jed");
		rt.insert("jed", 4892894);
		
		assertEquals(rt.getSize(), 3);
	}

	@Test
	public void simplerTest() {
		RadixTree<Integer> rt = new RadixTreeImpl<Integer>();
		rt.insert("jed", 48);
		rt.delete("jed");
		rt.insert("jed", 842584);
		rt.delete("jed");
		rt.insert("jed", 4892894);
		
		assertEquals(rt.getSize(), 1);
	}
}
