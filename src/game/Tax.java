package game;

import desktop_resources.GUI;

public class Tax extends Field {
	private int taxAmount;
	private int taxRate;

	/**
	 *
	 * @param name
	 * @param taxAmount
	 * @param taxRate
	 */
	public Tax(String name, int taxAmount, int taxRate) {
		super(name);
		this.taxAmount = taxAmount;
		this.taxRate = taxRate;
	}
	
	/**
	 *
	 * @param name
	 * @param taxAmount
	 */
	public Tax(String name, int taxAmount) {
		super(name);
		this.taxAmount = taxAmount;
		this.taxRate = -1;
	}

	/**
	 *
	 * @return tax
	 */
	public String getTax() {
		if (this.taxRate == -1)
			return Integer.toString(this.taxAmount);
		else {
			return String.format(Lang.get("amount_or_rate"), this.taxAmount, this.taxRate);
		}
	}
	
	/**
	 *
	 * @param p
	 */
	@Override
	public void land(Player p) {
		if (taxRate < 0) {
			GUI.showMessage(String.format(Lang.get("pay_tax"), p.getName(), getName(), taxAmount));
			p.changeBalance(-taxAmount);
		} 
		else {
			String s = GUI.getUserButtonPressed(String.format(Lang.get("landed_tax"), p.getName(), getName()), taxAmount + Lang.get("currency_tax"), taxRate + "%");
			if (s.equals(taxAmount + " kr")) {
				p.changeBalance(-taxAmount);
			}
			else {
				int amount = (int)Math.ceil((double)p.getBalance()*(taxRate/100.0));
				p.changeBalance(-amount);
				GUI.showMessage(String.format(Lang.get("you_payed"), p.getName(), amount, Lang.get("tax")));
			}
		}

	}
}
