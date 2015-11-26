package game;

public class Player {
	private String name;
	private int currentField;
	private Account account;
	private int ownedFleet;
	private int ownedLabor;

	/**
	 *
	 * @param name
	 * @param balance
	 */
	public Player(String name, int balance) {
		this.name = name;
		currentField = -1;
		ownedFleet = 0;
		ownedLabor = 0;
		account = new Account(balance);
	}

	/**
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 *
	 * @return balance
	 */
	public int getBalance() {
		return account.getBalance();
	}
	
	/**
	 *
	 * @return number of owned fleets
	 */
	public int getOwnedFleet() {
		return ownedFleet;
	}
	
	/**
	 * Set number of owned fleets
         * 
	 * @param ownedFleet
	 */
	public void setOwnedFleet(int ownedFleet) {
		this.ownedFleet = ownedFleet;
	}

	/**
	 * Set number of owned laber camps
         * 
	 * @param ownedLabor
	 */
	public void setOwnedLabor(int ownedLabor) {
		this.ownedLabor = ownedLabor;
	}

	/**
	 *
	 * @return number of owned labor camps
	 */
	public int getOwnedLabor() {
		return ownedLabor;
	}

	/**
	 *
	 * @return current field
	 */
	public int getPosition() {
		return currentField;
	}

	/**
	 * Sets players current field (position)
         * 
	 * @param position
	 */
	public void setPosition(int position) {
		currentField = position;
	}

	/**
	 * Changes a players account balance
         * 
	 * @param amount
	 * @return changed balance above 0
	 */
	public int changeBalance(int amount) {
		return account.transaction(amount);
	}

	/**
	 * Makes a player pay another player an amount
         * 
	 * @param recipient
	 * @param amount
	 */
	public void pay(Player recipient, int amount) {
		int amountPayed = changeBalance(-amount);
		recipient.changeBalance(Math.abs(amountPayed));
	}

}
