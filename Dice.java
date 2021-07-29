/**
 * 
 */
package artemislite;

import java.util.Random;

/**
 * @author 
 *
 */
public class Dice {
	
	private static int diceA;
	private static int diceB;
	private static int beginningPosition;
	private static int newPosition;
	private static int playerRoll;
	private static int passedGo = 20;
	
	public static void diceRoll(Player player) {
		
	
		beginningPosition = player.getCurrentPosition();
		Random diceRollA = new Random();
		Random diceRollB = new Random();
		
		diceA = diceRollA.nextInt(6) + 1;
		diceB = diceRollB.nextInt(6) + 1;
		
		playerRoll = diceA + diceB;
		newPosition = player.getCurrentPosition() + playerRoll;	
		if (newPosition >= Board.board.size()) {
			newPosition = (newPosition -(Board.board.size()));
			player.setCurrentPosition(newPosition);
			System.out.println(player.getName() + ", you are currently on square " + beginningPosition
					+ ". You've rolled " + playerRoll + " . You are now on Square " + player.getCurrentPosition());
			if(player.getCurrentPosition()==0) {
				System.out.println("You have landed on Go! You have received extra resources ");
				player.setMoney(player.getMoney()+passedGo);
			}
			else {
				System.out.println("You have passed Go! You have received extra resources ");
			player.setMoney(player.getMoney()+passedGo);
			}
		} else {
			player.setCurrentPosition(newPosition);
		System.out.println(player.getName() + ", you are currently on square " + beginningPosition
				+ ". You've rolled " + playerRoll + " . You are now on Square " + player.getCurrentPosition());
		}

	}

}
