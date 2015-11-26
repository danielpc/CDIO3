package test;

import game.*;

public class TestDice {

	public static void main(String[] args) {
		final int ROLLS = 1000;
		DiceCup diceCup = new DiceCup();
		int diceValues[] = new int[6];

		for (int i = 0; i < ROLLS; i++) {
			diceCup.roll();
			int[] dv = diceCup.getDiceValues();
			diceValues[dv[0] - 1]++;
			diceValues[dv[1] - 1]++;
		}

		System.out.println("Terningerne blev kastet 1000 gange med følgende udfald:\n");

		for (int i = 0; i < diceValues.length; i++)
			System.out.printf("%d'ere: %d\t%.1f%%\n", i + 1, diceValues[i],
					((double) diceValues[i] / (ROLLS * 2)) * 100);
	}

}
