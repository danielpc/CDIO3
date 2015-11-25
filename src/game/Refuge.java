package game;

import desktop_resources.GUI;

public class Refuge extends Field {
	private int bonus;

	public Refuge(String name, int bonus) {
		super(name);
		this.bonus = bonus;
	}

	public int getBonus() {
		return this.bonus;
	}
	
	@Override
	public void land(Player p) {
		p.changeBalance(bonus);
		GUI.showMessage(String.format(Lang.get("bonus"), p.getName(), getName(), bonus));
	}

}
