package game;

import desktop_resources.GUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import desktop_fields.*;

public class Game {
	
	private Field[] fields;
	private List<Player> players;
	
	
	public Game() {
		fields = new Field[21];
		
		fields[0] = new Territory("Stammelejren", 1000, 100);
		fields[1] = new Refuge("Den Bemurede By", 5000);
		fields[2] = new Territory("Krateret", 1500, 300);
		fields[3] = new Tax("Guldminen", 2000);
		fields[4] = new Fleet("Det Andet Segl", 4000);
		fields[5] = new LaborCamp("Bjerghytterne", 2500, 100);
		fields[6] = new Territory("Bjerget", 2000, 500);
		fields[7] = new Territory("Den Kolde Ørken", 3000, 700);
		fields[8] = new Fleet("Sømonsteret", 4000);
		fields[9] = new Territory("Den Sorte Grotte", 4000, 1000);
		fields[10] = new Territory("Varulvevæggen", 4300, 1300);
		fields[11] = new Refuge("Klosteret",500);
		fields[12] = new Fleet("Sørøverne", 4000);
		fields[13] = new Territory("Bjerglandsbyen", 4750, 1600);
		fields[14] = new Territory("Syd-Citadellet", 5000, 2000);
		fields[15] = new LaborCamp("Afgrunden", 2500, 100);
		fields[16] = new Fleet("Skibsflåden", 4000);
		fields[17] = new Territory("Paladsporten", 5500, 2600);
		fields[18] = new Tax("Campingvognen", 4000, 10);
		fields[19] = new Territory("Tårnet", 6000, 3200);
		fields[20] = new Territory("Slottet", 8000, 4000);
		
		players = new ArrayList<>();
		
		createGUI();
		createPlayers();
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		GUI.close();
	}
	
	private void createGUI() {	
		desktop_fields.Field[] fields = new desktop_fields.Field[this.fields.length];
		for (int i = 0; i < fields.length; i++) {
			if (this.fields[i] instanceof Territory) {
				fields[i] = new desktop_fields.Street.Builder()
						.setTitle(this.fields[i].getName())
						.setDescription("")
						.setSubText("Pris: " + ((Territory)this.fields[i]).getPrice())
						.setRent(Integer.toString(((Territory)this.fields[i]).getRent()))
						.setBgColor(Color.GRAY)
						.build();
			}
			else if (this.fields[i] instanceof Refuge) {
				fields[i] = new desktop_fields.Refuge.Builder()
						.setTitle(this.fields[i].getName())
						.setDescription("Bonus: " + ((Refuge)this.fields[i]).getBonus())
						.setSubText("Bonus: " + ((Refuge)this.fields[i]).getBonus())
						.setBgColor(Color.WHITE)
						.build();
			}
			else if (this.fields[i] instanceof LaborCamp) {
				fields[i] = new desktop_fields.Brewery.Builder()
						.setTitle(this.fields[i].getName())
						.setSubText("Rent: " + ((LaborCamp)this.fields[i]).getBaseRent() + " x terninge-øjne.")
						.setDescription("Pris: " + ((LaborCamp)this.fields[i]).getPrice())
						.setBgColor(Color.DARK_GRAY)
						.build();
			}
			else if (this.fields[i] instanceof Tax) {
				fields[i] = new desktop_fields.Tax.Builder()
						/* bemærk: description og title er byttet om i GUI'en */
						.setDescription(this.fields[i].getName())
						.setSubText("Tax: " + ((Tax)this.fields[i]).getTax())
						.setTitle("Tax: " + ((Tax)this.fields[i]).getTax())
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
						.setSubText("Pris: " + ((Fleet)this.fields[i]).getPrice())
						.setDescription("Rent: " + rents)
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
		int playerCount = GUI.getUserInteger("Vælg antal spillere mellem 2 og 6: ", 2, 6);
		
		for(int i = 0; i < playerCount; i++)
		{
			boolean invalid = false;
			String name;
			
			do {
				name = GUI.getUserString("Spiller " + (i+1) + " indtast navn");
				invalid = false;
				
				if(name.isEmpty()) {
					invalid = true;
					GUI.showMessage("Skriv venligst et gyldigt navn");
					continue;
				}
				
				for(int j = 0; j < players.size(); j++){
					if(players.get(j).getName().equals(name)) {
						invalid = true;
						GUI.showMessage("Navnet er allerede taget");
						break;
					}
				}
			} while(invalid);
			
			Player p = new Player(name, 30000);
			players.add(p);
			GUI.addPlayer(p.getName(), p.getBalance());
		}
	}
	
	
	
}
