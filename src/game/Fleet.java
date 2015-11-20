package game;

public class Fleet extends Ownable {
	private final static int[] RENT = { 500, 1000, 2000, 4000 };

	public Fleet(String name, int price) {
		super(name, price);
		// TODO Auto-generated constructor stub
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
