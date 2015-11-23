package game;

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
	public void land(Player p) {
		// TODO Auto-generated method stub

	}

}
