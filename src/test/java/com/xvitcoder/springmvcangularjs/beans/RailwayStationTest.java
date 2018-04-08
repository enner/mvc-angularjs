package com.xvitcoder.springmvcangularjs.beans;

import static org.junit.Assert.*;

import org.junit.Test;

public class RailwayStationTest {

	@Test
	public void testRailWayStation() {
		RailwayStation rw = new RailwayStation();
		rw.setName("Station");
		assertEquals(rw.getName(), "Station");
	}

}
