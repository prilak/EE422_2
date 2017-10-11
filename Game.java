// Lawrence, Michael
// ML42797 
// EE422C-Assignment #2

package assignment2;

import java.util.*;


public class Game {
	private Scanner input;
	private Guesses g;
	private String code;
	boolean test;
	//private String answer;
	

	public class Guess {
		public int b;
		public int w;
		public String answer;
		public Guess(int b, int w, String answer){
			this.b = b;
			this.w = w;
			this.answer = answer;
		}
	}
	//-----------------------------------
	// guesses class implimentation
	//-----------------------------------

	public class Guesses extends LinkedList<Guess>{
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
	public Game(Scanner in, boolean test){
			this.test = test;
			this.input = in;
			this.g = new Guesses(GameConfiguration.guessNumber);// edit this later 12
		}
	public boolean runGame(){
		String answer;
		
		this.greet();
		//System.out.println("Ready to Play");
		String response = this.input.nextLine();//how precise does this need to be
		if(response.charAt(0) != 'Y'){
			return false;			
		}
		this.setup();
		
		while (this.g.getCount() > 0) {
			answer = getAnswer(false);
			while(authenticateAnswer(answer)==0){
				System.out.println(" -> INVALID GUESS");
				System.out.println("");
				answer = getAnswer(true);
			}
			if(authenticateAnswer(answer)==-1){
				history();
			} else {
				Guess result = getPegs(answer);
				g.add(result);
				if(result.b==GameConfiguration.pegNumber){//You WIN!!!
					System.out.print(result.b + "B_" + result.w + "W");
					System.out.println(" – You win !!");	
					return true;
				} else {
					//RRRR -> Result:  1 black peg
					System.out.println(result.answer + " -> Result: " + result.b + "B_" + result.w + "W");
				}
				g.decrimentCount();
			}
			
		}
		System.out.println("Sorry, you are out of guesses. You lose, boo-hoo.");
		return true;
	}
	private void greet(){
		int i;
		for(i = 0; i < Greeting.greeting.length - 1; i++){//this stops before the last statement to ensure user writes on same line as last string
			System.out.println(Greeting.greeting[i]);
			System.out.println("");
		}
		System.out.print(Greeting.greeting[i]);
	}
	private void setup(){
		System.out.println("Success!");
		//obtain secret code
		code = SecretCodeGenerator.getInstance().getNewSecretCode();
		if (test) {
			System.out.print("Generating secret code ...");
			System.out.println("(for this example the secret code is " + code + ")");
		} else {
			System.out.println("Generating secret code ...");

		}
	}

	private String getAnswer(boolean failed){
		if(!failed){
			System.out.println("You have " + g.getCount() + " guesses left.");
		}
		System.out.println("What is your next guess?");
		System.out.println("Type in the characters for your guess and press enter.");
		System.out.print("Enter guess: ");
		String result = this.input.nextLine();
		System.out.println("");
		return result;
	}

	private Guess getPegs(String answer){
		int b = 0;
		int w = 0;
		//System.out.println(code + answer);
		char[] codePH = new char[GameConfiguration.pegNumber];
		for(int i = 0; i < codePH.length; i++){
			codePH[i] = code.charAt(i);
		} 

		for(int i = 0; i < GameConfiguration.pegNumber; i++){
			for(int j = 0; j < GameConfiguration.pegNumber; j++){
				if(answer.charAt(i)==codePH[j]){
					if(i==j){
						b++;
					} else {
						w++;
					}
					//remove color from list
					codePH[j] = '-';
					break;
				}
			}
		}		 
		return new Guess(b, w, answer);

	}
	//1 is a proper code
	//0 is not a proper code or HISTORY
	//-1 is HISTORY
	private int authenticateAnswer(String answer){
		int j;
		if(answer.length()==0){
			return 0;
		}
		if (answer.length()!=GameConfiguration.pegNumber){
			if(answer.compareTo("HISTORY")==0){
				return -1;
			} else {
				return 0;
			}
		}
		for(int i = 0; i < GameConfiguration.pegNumber; i++){
			for(j = 0; j < GameConfiguration.colors.length; j++){
				if(answer.charAt(i)==GameConfiguration.colors[j].charAt(0)){// match found
					break;
				}
			}
			if(j==GameConfiguration.colors.length){//if the loop finishes then no match found
				return 0;
			}
		}
		//if loop finishes, everything matched
		return 1;
	}

	private void history(){
		for(int i = 0; i < g.size(); i++){
			Guess output = g.get(i);
			System.out.print(output.answer);
			System.out.println("		" + output.b + "B_" + output.w + "W");
		}
	} 

}