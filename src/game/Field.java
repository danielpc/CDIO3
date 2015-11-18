package game;

public abstract class Field {
	private String name;
	
	public Field(String name)
	{
		this.name = name;
	}
	
	public abstract void land(Player p);
	
	
}
