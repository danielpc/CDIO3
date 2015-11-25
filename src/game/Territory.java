package game;

import desktop_resources.GUI;

public class Territory extends Ownable {
	private int rent;

	public Territory(String name, int price, int rent) {
		super(name, price);
		this.rent = rent;
	}

	@Override
	public int getRent() {
		return rent;
	}

	@Override
	public void land(Player p) {
		if(getOwner() == null){
			String s = GUI.getUserButtonPressed(String.format(Lang.get("want_to_buy"), getName(), getPrice()), Lang.get("yes"), Lang.get("no"));
			if (s.equals(Lang.get("yes"))) {
				buy(p);
			}
		}
		else{
			p.pay(getOwner(),rent);
			GUI.showMessage(String.format(Lang.get("you_payed"), p.getName(), rent, getOwner().getName()));
		}
		
	}

}
