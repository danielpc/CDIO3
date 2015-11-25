package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Lang {
	private static BufferedReader br;
	private static Map<String, String> strings = new HashMap<>();
	
	private Lang() {}
	
	public static boolean loadLanguage(String filename) {
		try {
			br = new BufferedReader(new FileReader(filename));
			
			String line = br.readLine();
			
			while(line != null) {
				String[] pair = line.split(Pattern.quote("|"));
				
				strings.put(pair[0], pair[1]);
				line = br.readLine();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static String get(String key) {
		return strings.get(key);
	}
}
