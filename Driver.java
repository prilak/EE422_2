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
		boolean test = false;
		
		//System.out.println(args[0] + " length " + args.length);
		if (args.length > 0 && args[0].matches("1")) {
			test = true;
		}
		//System.out.println(test);
		Game mm = new Game(input, test);
		while(mm.runGame()){
			System.out.print("Are you ready for another game (Y/N): ");
			String again = input.nextLine();
			if(again.charAt(0) == 'N'){
				break;
			} else {
				mm = new Game(input, test);
			}
		}
		
	}
}
