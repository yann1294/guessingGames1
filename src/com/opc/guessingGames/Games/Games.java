package com.opc.guessingGames.Games;

import com.opc.guessingGames.Main;

import java.util.Scanner;

public abstract class Games {


    /**
     * Instance Variables.
      */

    // number of digits
    private int nbDigits;

    // max number of guesses
    private  int maxGuesses;

    // number of values each digits can possibly take
    private int nbVariations;

    // secret code generated by the computer( challenger mode) or the player( Defender mode).
    private int[] codeToBeFound = new int[nbDigits];

    // secret code attempt from the player ( challenger mode ) or the computer ( Defender mode ).
    private int[] codeProposal = new int[nbDigits];

    // counts the number of guesses ( challenger mode )
    private int nbGuesses = 1;

    // secret code broken by the challenger
    private boolean codeFound = false;

    // Object scanner, used for input
    static final Scanner scan = new Scanner(System.in);

    /**
     * Constructors
     */


    /**
     * ( +/- ) Game constructor
     * @param nbDigits number of digits
     * @param maxGuesses maximum number of digits allowed
     */
    public Games(int nbDigits, int maxGuesses) {
        this.nbDigits = nbDigits;
        this.maxGuesses = maxGuesses;
    }

    /**
     * Mastermind Game constructor
     * @param nbDigits number of digits
     * @param maxGuesses maximum number of guesses
     * @param nbVariations nb of values that each digit can take
     */
    public Games(int nbDigits, int maxGuesses, int nbVariations) {
        this.nbDigits = nbDigits;
        this.maxGuesses = maxGuesses;
        this.nbVariations = nbVariations;
    }

    /**
     * Getters and Setters
     */

    int getNbDigits() {
        return nbDigits;
    }

    void setNbDigits(int nbDigits) {
        this.nbDigits = nbDigits;
    }

    int getMaxGuesses() {
        return maxGuesses;
    }

    void setMaxGuesses(int maxGuesses) {
        this.maxGuesses = maxGuesses;
    }

    int getNbVariations() {
        return nbVariations;
    }

    void setNbVariations(int nbVariations) {
        this.nbVariations = nbVariations;
    }

    int[] getCodeToBeFound() {
        return codeToBeFound;
    }

    void setCodeToBeFound(int[] codeToBeFound) {
        this.codeToBeFound = codeToBeFound;
    }

    int[] getCodeProposal() {
        return codeProposal;
    }

    void setCodeProposal(int[] codeProposal) {
        this.codeProposal = codeProposal;
    }

    int getNbGuesses() {
        return nbGuesses;
    }

    void setNbGuesses(int nbGuesses) {
        this.nbGuesses = nbGuesses;
    }

    boolean isCodeFound() {
        return codeFound;
    }

    void setCodeFound(boolean codeFound) {
        this.codeFound = codeFound;
    }


    /**
     * Abstract Methods
     */


    /**
     * wrapper-method that starts and executes he game until the end.
     * His implementation is different for each game.
     */
    public abstract void play();


    /**
     * Generates a random code made of X digits
     * @return the random code
     */
    public abstract int[] radomodeGenerator();

    /**
     * Take a guess and display the validation ( challenger Mode ).
     * Generates a guess, takes a validation code and generates the next guess ( defender Mode ).
     */
    public abstract void guessValidationUnit();

    /**
     * inputs the code entered by the player
     *
     * @return the valid code entered by the player
     */
    public abstract int[] codeInputUser();


    /**
     *  Methods
     */


    /**
     * Calls the selection menu at the end of the game.
     */
    void EndingMenu(){
        System.out.printf("\n\n%s\n%s\n%s\n%s\n\n%s", "Do you want to: ", "1: play again this game?", "2: come back to the game menu?",
                "3: stop loosing your time playing?", "Enter your selection: ");
        String selectorEnding = scan.nextLine();
        Main.logger.debug("Choice @end of the game=" + selectorEnding);
        System.out.println("\n");

        while ((!selectorEnding.equals("1") && !selectorEnding.equals("2") && !selectorEnding.equals("3"))) {
            System.out.print("What do you mean? Select 1, 2 or 3: ");
            Main.logger.debug("Entry not valid.");
            selectorEnding = scan.nextLine();
            Main.logger.debug("New choice @end of the game=" + selectorEnding);

        }

        switch (selectorEnding) {
            case "1":
                Main.gameStart(Main.getChoiceGame(), Main.getChoiceMode());
                break;
            case "2":
                Main.menu();
                Main.gameStart(Main.getChoiceGame(), Main.getChoiceMode());
                break;
            case "3":
                System.out.println("Bye bye, hope to see you soon!");
                break;
        }
    }

    /**
     * This method displays the instance variable {@link #codeToBeFound} when the Developer mode is activated
     * @param codeToBeFound the mystery code.
     */
    void displayModeDev(int[] codeToBeFound){
        if (Main.isDevMode()) {
            System.out.print("###### DEVELOPER MODE ! Superbrain's secret code = ");
            for (int x : codeToBeFound) {
                System.out.print(x + " ");
            }
            System.out.println("######\n");
        }
    }

    /**
     * displays the result of the game at the end of the game.
     */
    void messageEndOfGame() {
        if (this.isCodeFound()) {
            System.out.print("Superbrain made it!");
        } else {
            System.out.print("How does it feel to defeat Superbrain? ");
        }
    }

    /**
     * Increments the instance variable {@link #nbGuesses}
     */
    void incrementNbGuesses(){
        nbGuesses++;
    }

    /**
     * Resets the instance variable {@link #nbGuesses}
     */
    void resetNbGuesses(){
        nbGuesses = 1;
    }

}
