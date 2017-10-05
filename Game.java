// Lawrence, Michael
// ML42797 
// EE422C-Assignment #2

package assignment2;

import java.util.*;


public class Game {
	private Scanner input;
	private Guesses g;
	private String code;
	private String answer;
	
	//-----------------------------------
	// guesses class implimentation
	//-----------------------------------

	public class Guesses extends LinkedList{
		private int count;
		
		public Guesses(int count){
			this.count = count;
		}
			
		public int getCount(){
			return this.count;
		}
		public void decrimentCount(){
			this.count--;
		}
	}
	
	//-----------------------------------
	// Game class implimentation
	//-----------------------------------
	public Game(Scanner in){
			this.input = in;
			this.g = new Guesses(GameConfiguration.guessNumber);// edit this later 12
		}
	public void runGame(){								
		System.out.println("Ready to Play");
		String response = this.input.nextLine();//how precise does this need to be
		while (response.charAt(0) != 'Y') {
			System.out.println("type \"Y\"");
			response = this.input.nextLine();
		}
		//game starting text output
		this.setup();
		
		while (this.g.getCount() > 0) {
			answer = getAnswer();
			while(!authenticateAnswer(answer)){
				System.out.println(" -> INVALID GUESS");
				System.out.println("");
				answer = getAnswer();
			}
			g.decrimentCount();
		}
		
	}

	private void setup(){
		System.out.println("Success!");
		//obtain secret code
		String code = SecretCodeGenerator.getInstance().getNewSecretCode();
		System.out.println("Generating secret code ...");
		System.out.println("Your code is " + code);// delete me later
	}
	private String getAnswer(){
		System.out.println("You have " + g.getCount() + " guesses left.");
		System.out.println("What is your next guess?");
		System.out.println("Type in the characters for your guess and press enter.");
		System.out.print("Enter guess: ");
		String result = this.input.nextLine();
		System.out.println("");
		return result;
	}
	private boolean authenticateAnswer(String answer){
		int j;

		if (answer.length()!=GameConfiguration.pegNumber){
			return false;
		}
		for(int i = 0; i < GameConfiguration.pegNumber; i++){
			for(j = 0; j < GameConfiguration.colors.length; j++){
				if(answer.charAt(i)==GameConfiguration.colors[j].charAt(0)){// match found
					break;
				}
			}
			if(j==GameConfiguration.colors.length){//if the loop finishes then no match found
				return false;
			}
		}
		//if loop finishes, everything matched
		return true;
	}


}