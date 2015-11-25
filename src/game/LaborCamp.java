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
			String s = GUI.getUserButtonPressed(String.format(Lang.get("want_to_buy"), getName(), getPrice()), Lang.get("yes") , Lang.get("no"));
			if (s.equals(Lang.get("yes"))) {
				buy(p);
			}
		}
		else{
			GUI.getUserButtonPressed(String.format(Lang.get("click_to_roll"), getName()), Lang.get("roll"));
			DiceCup dc = new DiceCup();
			dc.roll();
			GUI.setDice(dc.getDiceValues()[0], dc.getDiceValues()[1]);
			int amount = dc.getDiceSum() * 100 * getOwner().getOwnedLabor();
			p.pay(getOwner(), amount);
			GUI.showMessage(String.format(Lang.get("you_payed"), p.getName(), amount, getOwner().getName()));
		}

	}

}
