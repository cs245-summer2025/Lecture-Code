import com.sun.jdi.InvalidLineNumberException;

import java.util.Random;
import java.util.Scanner;

/*
    We are going to create a simple game in Java where we have to guess the number
    the computer has picked. The arguments to this program are: the lower bound, the upper bound
    and how many guesses the user has.

    1. Once the range is set, the computer picks a secret number between the upper and lower bound.

    2. We try to guess the number. After each guess, the computer tells us whether we guessed
    it right, or if our guess was too high or low. We use the hints to narrow down the target.
 */
public class Main {
    // Helper function used to determine if certain inputs match what the program expects
    public static boolean isValidNumber(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    //Helper function that simulates the computer picking a number between the specified bounds.
    public static int pickNumber(int lowerBound, int upperBound){
        Random randomGen = new Random();
        System.out.println(String.format("I picked a number between %d and %d", lowerBound, upperBound));
        return randomGen.nextInt(upperBound) + lowerBound;
    }

    public static void playGuessingGame(Scanner sc, int guessesLeft, int numberToGuess){
        boolean didGuessNumber = false;
        String nextUserGuess;
        String invalidNumberGuess = "Be sure to guess only integers. You have %d guesses left";
        String winningMessage = "Congratulation. You guessed my number!";
        String guessHigherMessage = "My number is higher than %d. You have %d guesses left";
        String guessLowerMessage = "My number is lower than %d. You have %d guesses left";
        String gameOverMessage = "The game is over. My number was %d";

        while(!didGuessNumber && guessesLeft > 0) {
            System.out.print("Please enter your guess: ");
            nextUserGuess = sc.next();
            if(!isValidNumber(nextUserGuess)) {
                guessesLeft -= 1;
                System.out.println(String.format(invalidNumberGuess, guessesLeft));
            } else {
                int userGuess = Integer.parseInt(nextUserGuess);
                if(userGuess < numberToGuess) {
                    guessesLeft -= 1;
                    System.out.println(String.format(guessHigherMessage, userGuess, guessesLeft));
                } else if(userGuess > numberToGuess) {
                    guessesLeft -= 1;
                    System.out.println(String.format(guessLowerMessage, userGuess, guessesLeft));
                } else {
                    didGuessNumber = true;
                    System.out.println(String.format(winningMessage, guessesLeft));
                }
            }
        }

        if(!didGuessNumber) {
            System.out.println(String.format(gameOverMessage, numberToGuess));
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object

        /*
            We omit it here, but it would be advised to check the arguments here to make sure
            they meet certain constraints. For example, the number of guesses allowed should be a
            positive integer.
         */
        int lowerBound = Integer.parseInt(args[0]);
        int upperBound = Integer.parseInt(args[1]);
        int numGuessesAllowed = Integer.parseInt(args[2]);

        int numberToGuess = pickNumber(lowerBound, upperBound);
        playGuessingGame(sc, numGuessesAllowed, numberToGuess);
    }
}