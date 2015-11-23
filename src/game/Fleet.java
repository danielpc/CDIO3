package game;

import desktop_resources.GUI;

public class Fleet extends Ownable {
	private final static int[] RENT = { 500, 1000, 2000, 4000 };

	public static int[] getRents() {
		return RENT;
	}
	
	public Fleet(String name, int price) {
		super(name, price);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getRent() {
		return 0;											//bruges ikke 
	}

	@Override
	public void land(Player p) {
		if(getOwner() == null){
			String s = GUI.getUserButtonPressed("Vil du købe for prisen ? " + getPrice(), "ja" , "nej");
			if (s.equals("ja")) {
				buy(p);
				p.setOwnedFleet(p.getOwnedFleet()+1);
			}
		}
		else{
			p.pay(getOwner(), RENT[getOwner().getOwnedFleet()-1]);
			GUI.showMessage("Du har betalt " +  RENT[p.getOwnedFleet()-1] + " kr til " + getOwner().getName());
		}
	}

}
