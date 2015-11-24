package game;

public abstract class Ownable extends Field {

	private int price;
	private Player owner;

	public Ownable(String name, int price) {
		super(name);
		this.price = price;
		owner = null;
	}

	public abstract int getRent();
	
	public int getPrice(){
		return price;
	}
	
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public void buy(Player buyer) {
		buyer.changeBalance(-price);
		owner = buyer;
	}

}
