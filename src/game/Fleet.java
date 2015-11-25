package game;

import desktop_resources.GUI;

public class Fleet extends Ownable {
	private final static int[] RENT = { 500, 1000, 2000, 4000 };

	public static int[] getRents() {
		return RENT;
	}
	
	public Fleet(String name, int price) {
		super(name, price);
	}
	
	@Override
	public int getRent() {
		return 0;
	}
	
	@Override
	public void buy(Player buyer) {
		if(getOwner() == null)
			buyer.setOwnedFleet(buyer.getOwnedFleet() + 1);
		
		super.buy(buyer);
	}

	@Override
	public void land(Player p) {
		if(getOwner() == null){
			String s = GUI.getUserButtonPressed("Vil du købe for prisen ? " + getPrice(), "ja" , "nej");
			if (s.equals("ja")) {
				buy(p);
			}
		}
		else{
			GUI.showMessage("Du har betalt " +  RENT[getOwner().getOwnedFleet()-1] + " kr til " + getOwner().getName());
			p.pay(getOwner(), RENT[getOwner().getOwnedFleet()-1]);
		}
	}

}
