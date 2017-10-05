// Lawrence, Michael
// ML42797 
// EE422C-Assignment #2

package assignment2;

import java.util.*;
//import assignment2.Game;

public class Driver {
	static private boolean test;
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		Game test = new Game(input);
		test.runGame();
	}
}
