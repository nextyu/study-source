package com.nextyu.study.design.pattern.adapter;

import org.junit.Test;

public class MyTest {

	/**
	 * 采用组合的方式测试.
	 */
	@Test
	public void testCombination() {
		TwoPlug twoPlug = new TwoPlug();
		ThreePlugIf threePlugIf = new TwoPlugAdapterViaCombination(twoPlug);
		NoteBook noteBook = new NoteBook(threePlugIf);
		noteBook.charge();
	}

	/**
	 * 采用继承的方式测试.
	 */
	@Test
	public void testExtends() {
		ThreePlugIf threePlugIf = new TwoPlugAdapterViaExtends();
		NoteBook noteBook = new NoteBook(threePlugIf);
		noteBook.charge();
	}

}
