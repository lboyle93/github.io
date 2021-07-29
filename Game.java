/**
 * 
 */
package artemislite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {

	private static final int minimumPlayers = 2;
	private static final int maximumPlayers = 4;
	private static final int initialMoney = 30;
	private static final int initialPosition = 0;
	public static ArrayList<String> orgArray = new ArrayList<>();
	public static ArrayList<Player> players = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);
	public static Set<SystemName> completedSystems = new HashSet<>();

	public static void main(String[] args) throws InterruptedException {

		Board.setUpBoard();
		startMenu();
		registerPlayers();

		System.out.println("\nYour mission begins now, good luck!");
		do {
			for (Player p : players) {
				takeTurn(p);
			}
		} while (completedSystems.size() < 4);
	}

	/**
	 * Will give players option to begin game or review rules firstly
	 */
	public static void startMenu() {
		System.out.println("Welcome to ArtemisLite. Please choose from the following options:");
		System.out.println("1. Start Game");
		System.out.println("2. View Instructions");
		Scanner start = new Scanner(System.in);
		int entry = start.nextInt();
		switch (entry) {
		case 1:
			break;
		case 2:
			StarterIntro.instructions();
			startMenu();
			break;
		default:
			System.err.println("Please enter a number between 1 & 2");
			startMenu();
		}
	}

	public static void takeTurn(Player player) throws InterruptedException {
		System.out.printf("\nDo you want to play %s? (Y/N)\n", player.getName());
		Scanner turn = new Scanner(System.in);
		String userInput = turn.next().toUpperCase();
		switch (userInput) {

		case "Y":
			System.out.println("Completed Systems " + completedSystems.size() + "/" + "4");
			upgradeCheck(player);
			Dice.diceRoll(player);
			processTurn(player);
			break;
		case "N":
			System.out.printf(
					"Unfortunately, %s could no longer continue the mission for ArtemisLite and so it ends for all.",
					player.getName());
			System.out.println(
					"\nBut don't give up! The future could hold another chance for the first woman and next man to land on the moon!");
			System.out.println("\nPlayer stats:");
			printAllPlayersDetails();
			System.out.println("Game over");
			Thread.currentThread().join();
			break;
		default:
			System.out.println("Please try again");
			takeTurn(player);
		}

		if (completedSystems.size() == 4) {
			System.out.println(completedSystems.size());
			System.out.println("Game over");
			Thread.currentThread().join();
		}

	}

	public static void processTurn(Player player) throws InterruptedException {
		Square squarelanded = Board.board.get(player.getCurrentPosition());
		if (squarelanded instanceof SystemSquare) {
			processSquare(squarelanded, player);
		} else {
			squarelanded.showSquareDetails();
		}
	}

	public static void processSquare(Square squarelanded, Player player) throws InterruptedException {
		System.out.println(player.getName() + ", you have landed on " + squarelanded.getSquareName());
		if (squarelanded.getSquareOwner() == player) {
			System.out.println("You already own this square");
		} else if (squarelanded.getSquareOwner() != player && squarelanded.getSquareOwner() != null) {
			playerCharge(squarelanded, player);
		} else if (player.getMoney() - squarelanded.getPurchaseCost() > 0) {
			buyElement(squarelanded, player);
		} else {
			System.out.println("Unfortunately you do not have the funds to purchase this square");
		}

	}

	public static void playerCharge(Square squarelanded, Player player) throws InterruptedException {
		Player owner = squarelanded.getSquareOwner();
		System.out.println(player.getName() + ", you have landed on " + squarelanded.getSquareName()
				+ ", this square is owned by " + owner.getName());
		System.out.println(owner.getName() + ", would you like to charge " + player.getName() + " a fee of "
				+ squarelanded.getPurchaseCost() + "?");
		Scanner playerCharge = new Scanner(System.in);
		String charge = playerCharge.next().toUpperCase();
		if (charge.contains("Y") && (player.getMoney() - squarelanded.getPurchaseCost()) > 0) {
			System.out.println(
					owner.getName() + " has charged " + player.getName() + " " + squarelanded.getPurchaseCost());
			player.setMoney(player.getMoney() - squarelanded.getPurchaseCost());
			System.out.println(player.getName() + " your balance is now " + player.getMoney());
			owner.setMoney(owner.getMoney() + squarelanded.getPurchaseCost());
			System.out.println(owner.getName() + " your balance is now " + owner.getMoney());
		} else if (charge.contains("Y") && (player.getMoney() - squarelanded.getPurchaseCost()) <= 0) {
			System.out.println(owner.getName() + ", are you sure? " + player.getName()
					+ " does not have the funds for this. If you charge them the game will end");
			if (playerCharge.next().toUpperCase().contains("Y")) {
				System.out.println("Game Over");
				Thread.currentThread().join();
			}
		} else {
			System.out.println(owner.getName() + " you have decided not to charge " + player.getName());
		}

		if ((player.getMoney() - squarelanded.getPurchaseCost()) > 0
				&& squarelanded.getDevelopmentStage() == DevelopmentStage.UNDEVELOPED) {
			playerPurchase(squarelanded, owner, player);
		}
	}

	public static void playerPurchase(Square square, Player buyer, Player seller) throws InterruptedException {
		System.out.println(buyer.getName() + ", you have the funds to purchas this square from " + seller.getName()
				+ " for" + square.getPurchaseCost() + ". Would you like to make an offer to buy this square? Y/N");
		Scanner playerPurchase = new Scanner(System.in);
		String purchase = playerPurchase.nextLine().toUpperCase();
		if (purchase.contains("Y")) {
			System.out.println(seller.getName() + ", " + seller.getName() + " has made an offer to purchase "
					+ square.squareName + ". Would you like to sell for " + square.getPurchaseCost() + "? Y/N");
			String sale = playerPurchase.nextLine().toUpperCase();
			if (sale.contains("Y")) {
				square.setSquareOwner(seller);
				buyer.setMoney(buyer.getMoney() + square.getPurchaseCost());
				seller.setMoney(seller.getMoney() - square.getPurchaseCost());
				System.out.println(buyer.getName() + " you are now the owner of " + square.getSquareName()
						+ ". Your balance is " + buyer.getMoney());
				System.out.println(seller.getName() + ", your balance is now " + seller.getMoney());
				systemCheck(square, seller);

			} else {
				System.out.println("You have decided not to sell");
			}
		} else {
			System.out.println("You have decided not to purchase");
		}

	}

	public static void buyElement(Square squarelanded, Player player) throws InterruptedException {
		System.out.println(player.getName() + " you are able to purchase this square. See below for details");
		System.out.println("Name : " + squarelanded.getSquareName());
		System.out.println("System : " + squarelanded.getMemberOfSystem());
		System.out.println("Price : " + squarelanded.getPurchaseCost());
		System.out.println("You have funds currently of " + player.getMoney() + ". Do you wish to purchase? Y/N");
		Scanner buyElement = new Scanner(System.in);
		String purchaseChoice = buyElement.next().toUpperCase();
		if (purchaseChoice.contains("Y")) {
			player.setMoney(player.getMoney() - squarelanded.getPurchaseCost());
			squarelanded.setSquareOwner(player);
			System.out.printf("%s, you have purchased the %s square. Your balance is now %d", player.getName(),
					squarelanded.getSquareName(), player.getMoney());
			systemCheck(squarelanded, player);
		}
	}

	public static void upgradeCheck(Player player) throws InterruptedException {
		for (Square s : Board.board) {
			if (s.getSquareOwner()==player && s.isUpgradeable() == true && !completedSystems.contains(s.getMemberOfSystem())) {
				DevelopSystem(s);
			}
		}
	}

	public static void systemCheck(Square square, Player player) throws InterruptedException {
		SystemName system = square.getMemberOfSystem();
		int systemCount = 0;
		int ownerCount = 0;
		for (Square s : Board.board) {
			if (s.getMemberOfSystem() == system) {
				systemCount++;
			}
			if (s.getMemberOfSystem() == system && s.getSquareOwner() == player) {
				ownerCount++;
			}
		}
		if (systemCount == ownerCount) {
			for (Square s : Board.board) {
				if (s.getMemberOfSystem() == system) {
					s.setUpgradeable(true);
				}
			}
			DevelopSystem(square);
		} else {
			for (Square s : Board.board) {
				if (s.getMemberOfSystem() == system) {
					s.setUpgradeable(false);
				}
			}
		}
		System.out.println();
	}

	/**
	 * If a system is ready to upgrade it will be passed through here.
	 * 
	 * @param systemSquare
	 * @throws InterruptedException
	 */
	public static void DevelopSystem(Square systemSquare) throws InterruptedException {
		if ((systemSquare.getSquareOwner().getMoney() - systemSquare.getDevelopmentCost() > 0
				&& systemSquare.isUpgradeable() == true
				&& systemSquare.getDevelopmentStage().equals(DevelopmentStage.MINOR_DEVELOPMENT))) {
			System.out.println(systemSquare.getSquareOwner().getName() + " , you are able to complete "
					+ systemSquare.getMemberOfSystem() + " for a fee of " + systemSquare.getDevelopmentCost()
					+ ". Please enter Y/N to confirm if you would like to upgrade this system");
			Scanner MinorDev = new Scanner(System.in);
			String entry = MinorDev.next();
			if (entry.toUpperCase().contains("Y")) {
				System.out.println("Congralutions! " + systemSquare.getMemberOfSystem()
						+ " is now complete. You are one step closer to completing the mission");
				for (Square s : Board.board) {
					if (s.getMemberOfSystem() == systemSquare.getMemberOfSystem()) {
						s.setDevelopmentStage(DevelopmentStage.MAJOR_DEVELOPMENT);
						completedSystems.add(s.getMemberOfSystem());
						if (completedSystems.size() == 4) {
							System.out.println(completedSystems.size());
							System.out.println("Game over");
							Thread.currentThread().join();
						}
					}
				}
			} else {
				System.out.println("You have decided not to upgrade");
			}
		} else if ((systemSquare.getSquareOwner().getMoney() - systemSquare.getPurchaseCost()) > 0
				&& systemSquare.isUpgradeable() == true) {
			System.out.println(systemSquare.getSquareOwner().getName() + " , you are able to develop "
					+ systemSquare.getMemberOfSystem() + " for a fee of " + systemSquare.getDevelopmentCost()
					+ ". Please enter Y/N to confirm if you would like to upgrade this system");
			Scanner MajorDev = new Scanner(System.in);
			String entry = MajorDev.next();
			if (entry.toUpperCase().contains("Y")) {
				System.out.println("Congralutions! " + systemSquare.getMemberOfSystem()
						+ " is now under development. You are one step closer to completing the mission");
				for (Square s : Board.board) {
					if (s.getMemberOfSystem() == systemSquare.getMemberOfSystem()) {
						s.setDevelopmentStage(DevelopmentStage.MINOR_DEVELOPMENT);
					}
				}
			} else {
				System.out.println("You have decided not to upgrade");
			}
		}
	}

	public static void printAllPlayersDetails() {
		for (Player player : players) {
			player.displayAll();
		}
	}

	public static void registerPlayers() {
		System.out.println("How many players would like to play");
		System.out.printf("Enter number from %d to %d please ...", minimumPlayers, maximumPlayers);
		int playerNumber = scanner.nextInt();
		if (playerNumber >= minimumPlayers && playerNumber <= maximumPlayers) {
			for (int loop = 1; loop < (playerNumber + 1); loop++) {
				System.out.println("Would you like to enter a name or an organisation for Player " + loop);
				createPlayer();
			}
		}
	}

	public static boolean nameCheck(String nameToCheck) {
		boolean namePresent = false;
		for (Player p : players) {
			if (p.getName().equals(nameToCheck)) {
				namePresent = true;
			}
		}
		return namePresent;
	}

	public static void createPlayer() {
		Player player = new Player();
		System.out.println("Please enter N for name or O for organisation");
		Scanner scan = new Scanner(System.in);
		String nameOrOrg = scan.next().toUpperCase();
		if (nameOrOrg.equals("N")) {
			System.out.println("Please enter your name");
			String name = scan.next();
			player = new Player(name, initialMoney, initialPosition);
			if (nameCheck(name) == false) {
				players.add(player);
			} else {
				System.out.println("Unfortunately that name is unavailable. Please enter another for your player");
				createPlayer();
			}
		}

		else if (nameOrOrg.equals("O")) {
			organisationOptions();
			System.out.println("Please select from the list above");
			int choice = (scan.nextInt() - 1);
			String name = orgArray.get(choice);
			player = new Player(name, initialMoney, initialPosition);
			if (nameCheck(name) == false) {
				players.add(player);
			} else {
				System.out.println("Unfortunately that name is unavailable. Please enter another for your player");
				createPlayer();
			}
		} else {
			System.out.printf("Invalid Entry. Please enter a number betweem %d & %d", minimumPlayers, maximumPlayers);
			createPlayer();
		}

	}

	public static void organisationOptions() {
		orgArray.clear();
		for (Organisation organisatonName : Organisation.values()) {
			String orgName = organisatonName.toString().replace("_", " ");
			orgArray.add(orgName);
		}
		for (int loop = 0; loop < orgArray.size(); loop++) {
			System.out.println((loop + 1) + ". " + orgArray.get(loop));
		}
	}

}
