package game;

public class Account {

	// Variable fields
	private int balance;

	/**
	 * Creates an Account
	 * @param balance
	 */
	public Account(int balance) {
		this.balance = balance;
	}

	/**
	 * Returns the current balance
	 * 
	 * @return the balance
	 */
	public int getBalance() {
		return balance;
	}

		/**
		 * Retrieves an amount from the account above 0
		 * 
		 * @param amount
		 * @return the retrieved amount
		 */
	public int transaction(int amount) {
		balance += amount;
		int amountTransfered = amount;

		if (balance <= 0) {
			amountTransfered -= balance;
			balance = 0;
		}

		return amountTransfered;
	}
}