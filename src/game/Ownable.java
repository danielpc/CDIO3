package game;

import desktop_resources.GUI;

public abstract class Ownable extends Field {

	private int price;
	private Player owner;

	/**
	 *
	 * @param name
	 * @param price
	 */
	public Ownable(String name, int price) {
		super(name);
		this.price = price;
		owner = null;
	}

	/**
	 *
	 * @return rent
	 */
	public abstract int getRent();
	
	/**
	 *
	 * @return price
	 */
	public int getPrice(){
		return price;
	}
	
	/**
	 * Sets owner for Ownable
         * 
	 * @param owner
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	/**
	 *
	 * @return owner
	 */
	public Player getOwner() {
		return owner;
	}
	
	/**
	 * Makes player try to buy an Ownable
	 * 
	 * @param buyer
	 */
	public void buy(Player buyer) {
		if(buyer.getBalance() < price) {
			GUI.showMessage(Lang.get("broke"));
		} else if(owner != null) {
			GUI.showMessage(Lang.get("already_owned"));
		} else {
			buyer.changeBalance(-price);
			owner = buyer;
		}
	}

}
