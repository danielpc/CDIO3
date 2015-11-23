package game;

import desktop_resources.GUI;

public class Tax extends Field {
	private int taxAmount;
	private int taxRate;

	public Tax(String name, int taxAmount, int taxRate) {
		super(name);
		this.taxAmount = taxAmount;
		this.taxRate = taxRate;
	}

	@Override
	public void land(Player p) {
		if (taxRate < 0) {
			GUI.showMessage("Du er landet på Tax, du skal betale " + taxAmount + "kr");
			p.changeBalance(-taxAmount);
		} 
		else {
			String s = GUI.getUserButtonPressed("Du er landet på Tax, vælg mellem følgende: ", taxAmount + " kr",
					taxRate + "%");
			if (s.equals(taxAmount + " kr")) {
				p.changeBalance(-taxAmount);
			}
			else {
				int amount = (int)Math.ceil((double)p.getBalance()*(taxRate/100.0));
				p.changeBalance(-amount);
				GUI.showMessage("Du har betalt " + amount + " kr");
			}
		}

	}
}
