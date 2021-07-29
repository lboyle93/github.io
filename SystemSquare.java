package artemislite;

import java.lang.System;

public class SystemSquare extends Square {

	private Player squareOwner;
	private SystemName memberOfSystem;
	private boolean upgradeable;
	private DevelopmentStage developmentStage;
	private int developmentCost;
	private int purchaseCost;

	public SystemSquare() {

	}

	/**
	 * @param squareOwner
	 * @param memberOfSystem
	 * @param isOwned
	 * @param isFullyDeveloped
	 * @param developmentStage
	 * @param developmentCost
	 * @param purchaseCost
	 */
	public SystemSquare(SquareName squareName, Player squareOwner, SystemName memberOfSystem, boolean isFullyDeveloped,
			DevelopmentStage developmentStage, int developmentCost, int purchaseCost) {
		super(squareName);
		this.squareOwner = squareOwner;
		this.memberOfSystem = memberOfSystem;
		this.developmentStage = developmentStage;
		this.developmentCost = developmentCost;
		this.purchaseCost = purchaseCost;
	}

	/**
	 * @return the memberOfSystem
	 */
	@Override
	public SystemName getMemberOfSystem() {
		return memberOfSystem;
	}

	/**
	 * @param memberOfSystem the memberOfSystem to set
	 */

	@Override
	public void setMemberOfSystem(SystemName memberOfSystem) {
		this.memberOfSystem = memberOfSystem;
	}

	/**
	 * @return the squareOwner
	 */
	@Override
	public Player getSquareOwner() {
		return squareOwner;
	}

	/**
	 * @param squareOwner the squareOwner to set
	 */
	@Override
	public void setSquareOwner(Player squareOwner) {
		this.squareOwner = squareOwner;
	}

	/**
	 * @return the developmentStage
	 */
	@Override
	public DevelopmentStage getDevelopmentStage() {
		return developmentStage;
	}

	/**
	 * @param developmentStage the developmentStage to set
	 */
	@Override
	public void setDevelopmentStage(DevelopmentStage developmentStage) {
		this.developmentStage = developmentStage;
	}

	/**
	 * @return the developmentCost
	 */
	@Override
	public int getDevelopmentCost() {
		return developmentCost;
	}

	/**
	 * @param developmentCost the developmentCost to set
	 */
	@Override
	public void setDevelopmentCost(int developmentCost) {
		this.developmentCost = developmentCost;
	}

	/**
	 * @return the purchaseCost
	 */
	public int getPurchaseCost() {
		return purchaseCost;
	}

	/**
	 * @param purchaseCost the purchaseCost to set
	 */
	public void setPurchaseCost(int purchaseCost) {
		this.purchaseCost = purchaseCost;
	}

	@Override
	public boolean isUpgradeable() {
		return upgradeable;
	}

	@Override
	public void setUpgradeable(boolean upgradeable) {
		this.upgradeable = upgradeable;
	}

	@Override
	public void showSquareDetails() {
		if (this.getSquareOwner() == null) {
			System.out.printf("Square Name: %s,Member of System: %s, Cost: %d, is Upgradeable : %b,",
					this.getSquareName().toString().replaceAll("_", " "),
					this.memberOfSystem.toString().replaceAll("_", " "), this.purchaseCost, this.isUpgradeable());
		} else if (this.getSquareOwner() != null && this.getDevelopmentStage() == DevelopmentStage.UNDEVELOPED) {
			System.out.printf(
					"Square Name: %s,Member of System: %s, Cost: %d,is Upgradeable : %b, Development Stage : %s",
					this.getSquareName().toString().replaceAll("_", " "),
					this.memberOfSystem.toString().replaceAll("_", " "), this.purchaseCost, this.isUpgradeable(),
					this.getDevelopmentStage().toString().replaceAll("_", " "));
			System.out.println();
			System.out.println("This Square is owned by " + this.getSquareOwner().getName());
		} else {
			System.out.printf("Square Name: %s,Member of System: %s, Cost: %d, Development Stage : %s",
					this.getSquareName().toString().replaceAll("_", " "),
					this.memberOfSystem.toString().replaceAll("_", " "), this.purchaseCost,
					this.getDevelopmentStage().toString().replaceAll("_", " "));
			System.out.println();
			System.out.println("This Square is owned by " + this.getSquareOwner().getName());
		}

	}

}
