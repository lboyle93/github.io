/**
 * 
 */
package artemislite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Board {
	protected static ArrayList<Square> board = new ArrayList<>();

	private static int dearSquare = 40;
	private static int normalSquare = 20;
	private static int cheapSquare = 10;
	private static int dearUpgrade = 20;
	private static int normalUpgrade = 10;
	private static int cheapUpgrade = 5;
		
	public static void setUpBoard() {
		
		Square Go = new Square(SquareName.RESOURCE_UP);
		board.add(Go);
		
		SystemSquare A = new SystemSquare(SquareName.LAUNCH_VEHICLE_A, null, SystemName.LAUNCH_VEHICLE, 
				false, DevelopmentStage.UNDEVELOPED, cheapUpgrade, cheapSquare);
		board.add(A);
		
		SystemSquare B = new SystemSquare(SquareName.LAUNCH_VEHICLE_B, null, SystemName.LAUNCH_VEHICLE, 
			false, DevelopmentStage.UNDEVELOPED, cheapUpgrade, cheapSquare);
		board.add(B);
		
		SystemSquare C = new SystemSquare(SquareName.LAUNCH_VEHICLE_C, null, SystemName.LAUNCH_VEHICLE, 
				false, DevelopmentStage.UNDEVELOPED, cheapUpgrade, cheapSquare);
		board.add(C);

		SystemSquare D = new SystemSquare(SquareName.SPACE_CRAFT_A, null, SystemName.SPACE_CRAFT, 
				false, DevelopmentStage.UNDEVELOPED, normalUpgrade, normalSquare);
		board.add(D);
		
		SystemSquare E = new SystemSquare(SquareName.SPACE_CRAFT_B, null, SystemName.SPACE_CRAFT, 
				false, DevelopmentStage.UNDEVELOPED, normalUpgrade, normalSquare);
		board.add(E);
		
		Square Crater = new Square(SquareName.CRATER);
		board.add(Crater);
		
		SystemSquare F = new SystemSquare(SquareName.LANDING_SYSTEM_A, null, SystemName.LANDING_SYSTEM, 
				false, DevelopmentStage.UNDEVELOPED, normalUpgrade, normalSquare);
		board.add(F);
		
		SystemSquare G = new SystemSquare(SquareName.LANDING_SYSTEM_B, null, SystemName.LANDING_SYSTEM, 
				false, DevelopmentStage.UNDEVELOPED, normalUpgrade, normalSquare);
		board.add(G);
		
		SystemSquare H = new SystemSquare(SquareName.LANDING_SYSTEM_C, null, SystemName.LANDING_SYSTEM, 
				false, DevelopmentStage.UNDEVELOPED, normalUpgrade, normalSquare);
		board.add(H);
		
		SystemSquare I = new SystemSquare(SquareName.LUNAR_VEHICLE_A, null, SystemName.LUNAR_VEHICLE, 
				false, DevelopmentStage.UNDEVELOPED, dearUpgrade, dearSquare);
		board.add(I);
		
		SystemSquare J = new SystemSquare(SquareName.LUNAR_VEHICLE_B, null  , SystemName.LUNAR_VEHICLE, 
				false, DevelopmentStage.UNDEVELOPED, dearUpgrade, dearSquare);
		board.add(J);
	}

}
