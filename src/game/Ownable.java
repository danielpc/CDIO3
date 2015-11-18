package game;

public abstract class Ownable extends Field{

	private int price;
	private Player owner;
	
	public Ownable(String name, int price) {
		super(name);
		this.price = price;
	}

	public abstract int getRent();
	
}
