package game;

public class Player 
{
	private String name;
	private int currentField;
	private Account account;
	
	public Player(String name, int balance)
	{
		this.name = name;
		currentField = 0;
		account = new Account(balance);
	}

	public String getName() { return name; }	
	public int getBalance() { return account.getBalance(); }
	public int getCurrentField() { return currentField; }
	
	public void setPosition(int position)
	{
		//TODO greetings world
	}
	
	
	public boolean changeBalance()
	{
		//TODO logic here
		return true; 
	}
	

	
	
}
