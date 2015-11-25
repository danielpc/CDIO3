package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import game.*;

public class RefugeTest {
	private Player player;
	private Field refuge200;
	private Field refuge0;

	@Before
	public void setUp() throws Exception {
		this.player = new Player("Anders And", 1000);
		this.refuge200 = new Refuge("Helle +200", 200);
		this.refuge0 = new Refuge("Helle 0", 0);
	}

	@After
	public void tearDown() throws Exception {
		this.player = new Player("Anders And", 1000);
	}

	@Test
	public void testEntities() {
		Assert.assertNotNull(this.player);
		Assert.assertNotNull(this.refuge200);
		Assert.assertNotNull(this.refuge0);
		Assert.assertTrue(this.refuge200 instanceof Refuge);
		Assert.assertTrue(this.refuge0 instanceof Refuge);
	}

	@Test
	public void testLandOnField200() {
		int expected = 1000;
		int actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		// Perform the action to be tested
		this.refuge200.land(this.player);
		expected = 1000 + 200;
		actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testLandOnField200Twice() {
		int expected = 1000;
		int actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		// Perform the action to be tested
		this.refuge200.land(this.player);
		this.refuge200.land(this.player);
		expected = 1000 + 200 + 200;
		actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testLandOnField0() {
		int expected = 1000;
		int actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		// Perform the action to be tested
		this.refuge0.land(this.player);
		expected = 1000;
		actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testLandOnField0Twice() {
		int expected = 1000;
		int actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		// Perform the action to be tested
		this.refuge0.land(this.player);
		this.refuge0.land(this.player);
		expected = 1000;
		actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
	}

}
