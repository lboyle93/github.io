/**
 * 
 */
package artemislite;

/**
 *
 *
 */
public class Square {
	
	public SquareName squareName;
	
	public Square() {
	}

	
	public Square(SquareName squareName) {
		this.squareName = squareName;

	}

	/**
	 * @return the squareName
	 */
	public SquareName getSquareName() {
		return squareName;
	}


	/**
	 * @param squareName the squareName to set
	 */
	public void setSquareName(SquareName squareName) {
		this.squareName = squareName;
	}
		
	public void showSquareDetails() {
		System.out.println();
		System.out.printf("Square Name: %s\n\n", this.getSquareName());
	}


	public Player getSquareOwner() {
		// TODO Auto-generated method stub
		return null;
	}


	public void setSquareOwner(Player player) {
		// TODO Auto-generated method stub
		
	}


	public int getPurchaseCost() {
		// TODO Auto-generated method stub
		return 0;
	}


	public SystemName getMemberOfSystem() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * @param memberOfSystem the memberOfSystem to set
	 */
	public void setMemberOfSystem(SystemName memberOfSystem) {
		// TODO Auto-generated method stub
		
	}

	public boolean isUpgradeable() {
		return false;
	}

	public void setUpgradeable(boolean upgradeable) {
		upgradeable = false;
	}
	
	/**
	 * @return 
	 */
	public int getDevelopmentCost() {
		return 0;

	}

	/**
	 * @param developmentCost the developmentCost to set
	 */
	public void setDevelopmentCost(int developmentCost) {
	
	}
	
	public DevelopmentStage getDevelopmentStage() {
		return null;
	
	}


	public void setDevelopmentStage(DevelopmentStage developmentStage) {
	
	}

	
}
