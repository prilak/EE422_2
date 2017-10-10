package assignment2;

import java.util.*;



public class Greeting {
	public static String[] greeting = {
		"Welcome to Mastermind. Here are the rules.",
		"This is a text version of the classic board game Mastermind.",
		"The computer will think of a secret code. The code consists of 4 colored pegs. The pegs MUST be one of six colors: blue, green, orange, purple, red, or yellow. A color may appear more than once in the code. You try to guess what colored pegs are in the code and what order they are in. After you make a valid guess the result (feedback) will be displayed.",
		"The result consists of a black peg for each peg you have guessed exactly correct (color and position) in your guess. For each peg in the guess that is the correct color, but is out of position, you get a white peg. For each peg, which is fully incorrect, you get no feedback.",
		"Only the first letter of the color is displayed. B for Blue, R for Red, and so forth. When entering guesses you only need to enter the first character of each color as a capital letter.",
		"You have 12 guesses to figure out the secret code or you lose the game. Are you ready to play? (Y/N): "
	};
}