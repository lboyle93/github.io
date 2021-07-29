package artemislite;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;

/**
 * 
 * 
 */
public class Epilogue extends Game{

	/**
	 * Read from file a default story end
	 */

	public static void PlayerStats() {
	}

	public static void WinningNarration() {

		try {
			FileReader fr = new FileReader(new File("Epilogue.txt"));
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();

			while (line != null) {
				System.out.println(line);
				line = br.readLine();

			}
		} catch (Exception e) {
			System.out.println("File does not exist");
		}
	}

	public static void endGame() {
		System.out.println("Unfortunately your team has ran out of resources and could not successfully complete ArtemisLite");
		System.out.println("but don't give up! The future could hold another chance for the first woman and next man to land on the moon!");
		System.out.println("Player stats:");
		printAllPlayersDetails();
	}
}
