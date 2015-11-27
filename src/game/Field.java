package game;

public abstract class Field {
	private String name;

	/**
	 *
	 * @param name
	 */
	public Field(String name) {
		this.name = name;
	}
	
	/**
	 *
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 *
	 * @param p
	 */
	public abstract void land(Player p);

	public String toString() {
		return this.getClass().getSimpleName() + ":\t" + name;
	}
}
