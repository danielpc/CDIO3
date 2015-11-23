package game;

import desktop_resources.GUI;

public class Refuge extends Field {
	private int bonus;

	public Refuge(String name, int bonus) {
		super(name);
		this.bonus = bonus;
	}

	@Override
	public void land(Player p) {
		p.changeBalance(bonus);
		GUI.showMessage("Du har landet på den Refuge, du får " + bonus);
	}

}
