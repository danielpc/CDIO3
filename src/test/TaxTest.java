package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import game.*;

import org.junit.Test;

public class TaxTest {

	private Player player;
	private Field tax;

	@Before
	public void setUp() throws Exception {
		Lang.loadLanguage("DA.lang");
		this.player = new Player("Son Goku", 9000);
		this.tax = new Tax("Skat", 2000, 10);
	}

	@After
	public void tearDown() throws Exception {
		this.player = new Player("Son Goku", 9000);
	}

	@Test
	public void testEntities() {
		Assert.assertNotNull(this.player);
		Assert.assertNotNull(this.tax);
		Assert.assertTrue(this.tax instanceof Tax);
	}

	@Test
	public void testTax() {
		int initialBalance = this.player.getBalance();
		// Perform the action to be tested
		this.tax.land(this.player);
		int balance = this.player.getBalance();
		boolean actual = (balance == initialBalance - 2000) || (balance == initialBalance * 0.9);
		Assert.assertTrue(actual);
	}


}
