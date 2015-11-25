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
			String s = GUI.getUserButtonPressed(String.format(Lang.get("want_to_buy"), getName(), getPrice()), Lang.get("yes") , Lang.get("no"));
			if (s.equals(Lang.get("yes"))) {
				buy(p);
			}
		}
		else{
			GUI.showMessage(String.format(Lang.get("you_payed"), p.getName(), RENT[getOwner().getOwnedFleet()-1], getOwner().getName()));
			p.pay(getOwner(), RENT[getOwner().getOwnedFleet()-1]);
		}
	}

}
