package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import game.*;

public class FleetTest {
	private Player player, player2;
	private Ownable fleet, fleet2;
	
	@Before
	public void setUp() throws Exception {
		Lang.loadLanguage("DA.lang");
		this.player = new Player("Søren Goku", 10000);
		this.player2 = new Player("Daniel Goku", 10000);
		this.fleet = new Fleet("Molslinjen", 4000);
		this.fleet2 = new Fleet("Fregatten-Jylland", 4000);
	}

	@After
	public void tearDown() throws Exception {
		this.player = new Player("Søren Goku", 10000);
		this.player2 = new Player("Daniel Goku", 10000);
		this.fleet = new Fleet("Molslinjen", 4000);
		this.fleet2 = new Fleet("Fregatten-Jylland", 4000);
	}
	
	@Test
	public void testLandOnSingleFleetOwned() {
		int p1Expected = 10000, p2Expected = 10000;
		Assert.assertEquals(p1Expected, this.player.getBalance());
		Assert.assertEquals(p2Expected, this.player2.getBalance());
		
		this.fleet.buy(this.player);
		p1Expected -= this.fleet.getPrice();
		
		Assert.assertEquals(p1Expected, this.player.getBalance());
		
		
		this.fleet.land(this.player2);
		p2Expected -= Fleet.getRents()[this.player.getOwnedFleet() - 1];
		Assert.assertEquals(p2Expected, this.player2.getBalance());
	}
	
	@Test
	public void testLandOnTwoFleetOwned() {
		int p1Expected = 10000, p2Expected = 10000;
		Assert.assertEquals(p1Expected, this.player.getBalance());
		Assert.assertEquals(p2Expected, this.player2.getBalance());
		
		this.fleet.buy(this.player);
		this.fleet2.buy(this.player);
		p1Expected -= this.fleet.getPrice() + this.fleet2.getPrice();
		
		Assert.assertEquals(p1Expected, this.player.getBalance());
	
		this.fleet.land(this.player2);
		p2Expected -= Fleet.getRents()[this.player.getOwnedFleet() - 1];
		Assert.assertEquals(p2Expected, this.player2.getBalance());
	
		this.fleet2.land(this.player2);
		p2Expected -= Fleet.getRents()[this.player.getOwnedFleet() - 1];
		Assert.assertEquals(p2Expected, this.player2.getBalance());
	
	}
}
