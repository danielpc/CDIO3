package game;

public class Player {
	private String name;
	private int currentField;
	private Account account;
	private int ownedFleet;
	private int ownedLabor;

	public Player(String name, int balance) {
		this.name = name;
		currentField = 0;
		ownedFleet = 0;
		ownedLabor = 0;
		account = new Account(balance);
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return account.getBalance();
	}
	
	public int getOwnedFleet() {
		return ownedFleet;
	}
	
	public void setOwnedFleet(int ownedFleet) {
		this.ownedFleet = ownedFleet;
	}

	public void setOwnedLabor(int ownedLabor) {
		this.ownedLabor = ownedLabor;
	}

	public int getOwnedLabor() {
		return ownedLabor;
	}

	public int getPosition() {
		return currentField;
	}

	public void setPosition(int position) {
		currentField = position;
	}

	public int changeBalance(int amount) {
		return account.transaction(amount);
	}

	public void pay(Player recipient, int amount) {
		int amountPayed = changeBalance(-amount);
		recipient.changeBalance(Math.abs(amountPayed));
	}

}
