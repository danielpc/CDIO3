package game;

import desktop_resources.GUI;

public class LaborCamp extends Ownable {
	private int baseRent;

	public LaborCamp(String name, int price, int baseRent) {
		super(name, price);
		this.baseRent = baseRent;
	}

	public int getBaseRent() {
		return this.baseRent;
	}
	@Override
	public int getRent() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void buy(Player buyer) {
		if(getOwner() == null)
			buyer.setOwnedLabor(buyer.getOwnedLabor() + 1);
		
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
			GUI.getUserButtonPressed("du skal nu rulle", "rul");
			DiceCup dc = new DiceCup();
			dc.roll();
			GUI.setDice(dc.getDiceValues()[0], dc.getDiceValues()[1]);
			int amount = dc.getDiceSum() * 100 * getOwner().getOwnedLabor();
			p.pay(getOwner(), amount);
			GUI.showMessage("Du har betalt " +  amount + " kr til " + getOwner().getName());
		}

	}

}
