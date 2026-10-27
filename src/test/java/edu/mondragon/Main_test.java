package edu.mondragon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Main_test {
   
    @Test
	public void test0() {
		GlobPattern glob = new GlobPattern("?a?");

		assertEquals(1, glob.match("aaa"));
	}

        @Test
	public void test1() {
		GlobPattern glob = new GlobPattern("a");

		assertEquals(1, glob.match("a"));
	}

}
