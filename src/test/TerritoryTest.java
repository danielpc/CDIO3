package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import game.*;

public class TerritoryTest {
	
	private Player player, player2;
	private Ownable territory;

	@Before
	public void setUp() throws Exception {
		Lang.loadLanguage("DA.lang");
		this.player = new Player("Søren Goku", 10000);
		this.player2 = new Player("Daniel Goku", 10000);
		this.territory = new Territory("Planeten Mars", 2000, 200);
	}

	@After
	public void tearDown() throws Exception {
		this.player = new Player("Søren Goku", 10000);
		this.player2 = new Player("Daniel Goku", 10000);
		this.territory = new Territory("Planeten Mars", 2000, 200);
	}

	@Test
	public void testEntities() {
		Assert.assertNotNull(this.player);
		Assert.assertNotNull(this.territory);
		Assert.assertTrue(this.territory instanceof Territory);
	}

	@Test
	public void testBuyField() {
		int expected = 10000;
		Assert.assertEquals(expected, this.player.getBalance());
		this.territory.buy(this.player);
		expected -= this.territory.getPrice();
		Assert.assertEquals(expected, this.player.getBalance());
		Assert.assertEquals(this.territory.getOwner(), this.player);
	}
	
	@Test
	public void testPayOwner() {
		int p1Expected = 10000, p2Expected = 10000;
		Assert.assertEquals(p1Expected, this.player.getBalance());
		Assert.assertEquals(p2Expected, this.player2.getBalance());
		
		this.territory.buy(this.player);
		p1Expected -= this.territory.getPrice();
		
		this.territory.land(this.player2);
		p2Expected -= this.territory.getRent();
		p1Expected += this.territory.getRent();
		
		Assert.assertEquals(p1Expected, player.getBalance());
		Assert.assertEquals(p2Expected, player2.getBalance());
	}

	@Test
	public void testBuyOwnedField() {
		int p1Expected = 10000, p2Expected = 10000;
		Assert.assertEquals(p1Expected, this.player.getBalance());
		Assert.assertEquals(p2Expected, this.player2.getBalance());
		
		this.territory.buy(this.player);
		p1Expected -= this.territory.getPrice();
		
		this.territory.buy(this.player2);
		
		Assert.assertEquals(p1Expected, this.player.getBalance());
		Assert.assertEquals(p2Expected, this.player2.getBalance());
		Assert.assertEquals(this.player, this.territory.getOwner());
	}
	
	@Test
	public void testRebuy() {
		int p1Expected = 10000, p2Expected = 10000;
		Assert.assertEquals(p1Expected, this.player.getBalance());
		Assert.assertEquals(p2Expected, this.player2.getBalance());
		
		this.territory.buy(this.player);
		p1Expected -= this.territory.getPrice();
		
		Assert.assertEquals(p1Expected, this.player.getBalance());
		Assert.assertEquals(this.player, this.territory.getOwner());
	
		this.territory.setOwner(null);
		this.territory.buy(this.player2);
		p2Expected -= this.territory.getPrice();
		
		Assert.assertEquals(p2Expected, this.player2.getBalance());
		Assert.assertEquals(this.player2, this.territory.getOwner());
	}
}
