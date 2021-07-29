/**
* 
*/
package artemislite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.lang.System;

public class Player {

	private String name;
	private int money;
	private int currentPosition;

	/**
	 * Constructor for player
	 * 
	 * @param name
	 * @param money
	 * @param currentPosition
	 * @param isOrg
	 */
	public Player(String name, int money, int currentPosition) {
		this.setName(name);
		this.money = money;
		this.currentPosition = currentPosition;
	}

	public Player() {
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) throws IllegalArgumentException {

		this.name = name.replaceAll("_", " ");

	}

	/**
	 * @return the money owned by the player
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	/**
	 * @return the currentPosition
	 */
	public int getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * @param currentPosition the currentPosition to set
	 */
	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

	public void displayName() {
		System.out.printf("Player Name: %s", this.getName().replaceAll("_", " "));
	}

	public void displayAll() {
		System.out.printf("\nPlayer Name: %s\n", this.getName().replaceAll("_", " "));
		System.out.printf("Resources: %d\n", this.getMoney());

	}

}
