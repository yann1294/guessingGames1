package com.opc.guessingGames.Games;

import com.opc.guessingGames.Main;
import com.opc.guessingGames.Utils;

import java.util.Arrays;

public abstract class GamePlusMoins extends Games {


    /**
     * ( +/- ) Game constructor
     *
     * @param nbDigits   number of digits
     * @param maxGuesses maximum number of digits allowed
     */
    public GamePlusMoins(int nbDigits, int maxGuesses) {
        super(nbDigits, maxGuesses);
    }

    // Common Methods...


    /**
     * Compares {@link #codeToBeFound} and {@link #codeProposal} and return a +/-
     * validation code.
     * @param codeToBeFound the secret code
     * @param codeProposal the code attempt made by the challenger
     * @return +/- validation code.
     */
    String[] validation(int[] codeToBeFound, int[] codeProposal) {
        if ((codeToBeFound.length) != (codeProposal.length)) {
            System.out.println("Input arrays must have same length!");
            return null;

        } else {
            String[] validation = new String[getNbDigits()];
            for (int i = 0; i < getNbDigits(); i++) {
                if (codeToBeFound[i] > codeProposal[i]) {
                    validation[i] = "+";
                } else if (codeToBeFound[i] < codeProposal[i]) {
                    validation[i] = "-";
                } else {
                    validation[i] = "=";
                }
            }
            return validation;
        }
    }

    /**
     * Generates a random code made of X digits
     *
     * @return the random code
     */
    @Override
    public int[] radomodeGenerator() {
        int[] randomCode = new int[getNbDigits()];
        for (int i = 0; i < getNbDigits(); i++) {
            randomCode[i] = (int) (10 * Math.random());
        }

        Main.logger.debug("random code = " + Arrays.toString(randomCode));
        return randomCode;
    }

    /**
     * inputs the code entered by the player
     *
     * @return the valid code entered by the player
     */
    @Override
    public int[] codeInputUser() {
        String inputUser = scan.nextLine();
        Main.logger.debug("User input: " + inputUser);

        while ((inputUser.length() != getNbDigits()) || (!Utils.isAnInt(inputUser))) {
            System.out.println("What do you mean? Please enter a combination of " + getNbDigits() + " digits.");
            Main.logger.debug("Entry not valid.");
            inputUser = scan.nextLine();
            Main.logger.debug("New user input: " + inputUser);
        }

        // Conversion String into int array
        int[] codeInputUser = new int[getNbDigits()];
        for (int j = 0; j < getNbDigits(); j++) {
            codeInputUser[j] = Character.getNumericValue(inputUser.charAt(j));
        }
        return codeInputUser;
    }


    void testCodeFound(String[] validation){
        int k = 0;
        for (String elementValidation : validation){
            if (( elementValidation.equals("+") ) || ( elementValidation.equals("-") ))
                k++;
        }
        if (k == 0){
            setCodeFound(true);
        }
    }
}
