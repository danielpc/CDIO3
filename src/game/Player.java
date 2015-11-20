package game;

public class Player {
	private String name;
	private int currentField;
	private Account account;

	public Player(String name, int balance) {
		this.name = name;
		currentField = 0;
		account = new Account(balance);
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return account.getBalance();
	}

	public int getCurrentField() {
		return currentField;
	}

	public void setPosition(int position) {
		// TODO greetings world
	}

	public int changeBalance(int amount) {
		return account.transaction(amount);
	}

	public boolean pay(Player recipient, int amount) {
		int amountPayed = changeBalance(amount);
		recipient.changeBalance(Math.abs(amountPayed));

		return amountPayed == amount;
	}

}
