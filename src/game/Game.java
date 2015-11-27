package game;

import desktop_resources.GUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import desktop_fields.*;

public class Game {
	
	private Field[] fields;
	private List<Player> players;
	private DiceCup diceCup;
	
	
	public Game() {
		Lang.loadLanguage("DA.lang");
		
		fields = new Field[21];
		
		fields[0] = new Territory(Lang.get("tribe"), 1000, 100);
		fields[1] = new Refuge(Lang.get("walled_city"), 5000);
		fields[2] = new Territory(Lang.get("crater"), 1500, 300);
		fields[3] = new Tax(Lang.get("goldmine"), 2000);
		fields[4] = new Fleet(Lang.get("second_sail"), 4000);
		fields[5] = new LaborCamp(Lang.get("huts"), 2500, 100);
		fields[6] = new Territory(Lang.get("mountain"), 2000, 500);
		fields[7] = new Territory(Lang.get("cold_desert"), 3000, 700);
		fields[8] = new Fleet(Lang.get("sea_grover"), 4000);
		fields[9] = new Territory(Lang.get("black_cave"), 4000, 1000);
		fields[10] = new Territory(Lang.get("werewall"), 4300, 1300);
		fields[11] = new Refuge(Lang.get("monestary"),500);
		fields[12] = new Fleet(Lang.get("buccaneers"), 4000);
		fields[13] = new Territory(Lang.get("mountain_village"), 4750, 1600);
		fields[14] = new Territory(Lang.get("south_citadel"), 5000, 2000);
		fields[15] = new LaborCamp(Lang.get("pit"), 2500, 100);
		fields[16] = new Fleet(Lang.get("privateer_armade"), 4000);
		fields[17] = new Territory(Lang.get("palace_gates"), 5500, 2600);
		fields[18] = new Tax(Lang.get("caravan"), 4000, 10);
		fields[19] = new Territory(Lang.get("tower"), 6000, 3200);
		fields[20] = new Territory(Lang.get("castle"), 8000, 4000);
		
		players = new ArrayList<>();
		diceCup = new DiceCup();
		
		createGUI();
		createPlayers();
		run();		
	}
	
	public static void main(String[] args) {
		new Game();
		GUI.close();
	}
	
	private void createGUI() {	
		desktop_fields.Field[] fields = new desktop_fields.Field[this.fields.length];
		for (int i = 0; i < fields.length; i++) {
			if (this.fields[i] instanceof Territory) {
				fields[i] = new desktop_fields.Street.Builder()
						.setTitle(this.fields[i].getName())
						.setDescription("")
						.setSubText(Lang.get("price") + ((Territory)this.fields[i]).getPrice())
						.setRent(Integer.toString(((Territory)this.fields[i]).getRent()))
						.setBgColor(Color.GRAY)
						.build();
			}
			else if (this.fields[i] instanceof Refuge) {
				fields[i] = new desktop_fields.Refuge.Builder()
						.setTitle(this.fields[i].getName())
						.setDescription(Lang.get("bonus_text") + ((Refuge)this.fields[i]).getBonus())
						.setSubText(Lang.get("bonus_text") + ((Refuge)this.fields[i]).getBonus())
						.setBgColor(Color.WHITE)
						.build();
			}
			else if (this.fields[i] instanceof LaborCamp) {
				fields[i] = new desktop_fields.Brewery.Builder()
						.setTitle(this.fields[i].getName())
						.setSubText(Lang.get("rent") + ((LaborCamp)this.fields[i]).getBaseRent() + " x " + Lang.get("dice_eyes"))
						.setDescription(Lang.get("price") + ((LaborCamp)this.fields[i]).getPrice())
						.setBgColor(Color.DARK_GRAY)
						.build();
			}
			else if (this.fields[i] instanceof Tax) {
				fields[i] = new desktop_fields.Tax.Builder()
						/* bemærk: description og title er byttet om i GUI'en */
						.setDescription(this.fields[i].getName())
						.setSubText(Lang.get("tax") + ((Tax)this.fields[i]).getTax())
						.setTitle(Lang.get("tax") + ((Tax)this.fields[i]).getTax())
						.setBgColor(Color.YELLOW)
						.build();
			}
			else if (this.fields[i] instanceof Fleet) {
				String rents = "";
				int[] re = Fleet.getRents();
				
				for (int j = 0; j < re.length; j++) {
					if (j != 0) {
						rents += ", ";
					}
					
					rents += Integer.toString(re[j]);
				}
				
				fields[i] = new desktop_fields.Shipping.Builder()
						.setTitle(this.fields[i].getName())
						.setSubText(Lang.get("price") + ((Fleet)this.fields[i]).getPrice())
						.setDescription(Lang.get("rent") + rents)
						.setBgColor(Color.CYAN)
						.build();
			}
			else {
				/* err */
				continue;
			}
		}
		GUI.create(fields);
	}
	
	private void createPlayers(){
		int playerCount = GUI.getUserInteger(Lang.get("choose_players"), 2, 6);
		
		for(int i = 0; i < playerCount; i++)
		{
			boolean invalid = false;
			String name;
			
			do {
				name = GUI.getUserString(Lang.get("player") + (i+1) + Lang.get("enter_name"));
				invalid = false;
				
				if(name.isEmpty()) {
					invalid = true;
					GUI.showMessage(Lang.get("invalid_name"));
					continue;
				}
				
				for(int j = 0; j < players.size(); j++){
					if(players.get(j).getName().equals(name)) {
						invalid = true;
						GUI.showMessage(Lang.get("name_taken"));
						break;
					}
				}
			} while(invalid);
			
			Player p = new Player(name, 30000);
			players.add(p);
			GUI.addPlayer(p.getName(), p.getBalance());
		}
	}
	
	private void run(){
		int turn = 0;
		
		while(players.size() > 1){
			Player p = players.get(turn);
			
			GUI.getUserButtonPressed(String.format(Lang.get("click_to_roll"), p.getName()), Lang.get("roll"));
			diceCup.roll();
			int[] diceValues = diceCup.getDiceValues();
			GUI.setDice(diceValues[0], diceValues[1]);
			
			p.setPosition((p.getPosition() + diceCup.getDiceSum()) % (fields.length));
			GUI.removeAllCars(p.getName());
			GUI.setCar(p.getPosition()+1, p.getName());
			
			fields[p.getPosition()].land(p);
			GUI.setBalance(p.getName(), p.getBalance());
			
			if(p.getBalance() <= 0) {
				GUI.showMessage(String.format(Lang.get("player_lost"), p.getName()));
				
				for(Field f : fields) {
					if(f instanceof Ownable) {
						if(((Ownable) f).getOwner() == p) {
							((Ownable) f).setOwner(null);
						}
					}
				}
				
				GUI.removeAllCars(p.getName());
				players.remove(p);
			} 
			
			turn++;
			turn %= players.size();
		}
	
		GUI.showMessage(String.format(Lang.get("player_won"), players.get(0).getName()));
	}
	
	public String toString() {
		String out = "";
		
		for(Field f : fields)
			out += f + "\n";
		
		return out;
	}
}
