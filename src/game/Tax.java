package game;

public class Tax extends Field {
	private int taxAmount;
	private int taxRate = -1;

	public Tax(String name, int taxAmount) {
		super(name);
		this.taxAmount = taxAmount;
	}

	@Override
	public void land(Player p) {
		// TODO Auto-generated method stub

	}

}
