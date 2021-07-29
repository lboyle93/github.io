package artemislite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class StarterIntro {

	public static void introText() {

		try {
			FileReader fr = new FileReader(new File("Intro.txt"));
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			
			while (line!=null) {
				System.out.println(line);
				line = br.readLine();
			}
		} catch (Exception e) {
			System.out.println("Cant find file!");
		}
		
		System.out.println();
	}

	public static void instructions() {
		System.out.println("Here's how to play");
		introText();
	}
}
