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
			String s = GUI.getUserButtonPressed("Vil du købe for prisen ? " + getPrice(), "ja" , "nej");
			if (s.equals("ja")) {
				buy(p);
			}
		}
		else{
			p.pay(getOwner(),rent);
			GUI.showMessage("Du har betalt " + rent + " kr til " + getOwner().getName());
		}
		
	}

}
