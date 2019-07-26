package com.opc.guessingGames;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static final Logger logger = LogManager.getLogger();

    //Default game parameters
    private static int nbDigitsGame1 = 4;
    private static int maxGuessesGame1 = 4;

    private static int nbDigitsGame2 = 4;
    private static int maxGuessesGame2 = 10;
    private static int nbVariationsGame2 = 6;

    private static boolean devMode = false;

    private static String choiceGame;
    private static String choiceMode;

    /**
     * Getters
     */

    public static boolean isDevMode() {
        return devMode;
    }

    public static String getChoiceGame() {
        return choiceGame;
    }

    public static String getChoiceMode() {
        return choiceMode;
    }


    /**
     * The main method loads the configuration parameters.
     * @param args takes the argument dev to launch the developer
     *             mode.
     */
    public static void main(String[] args) {
	// write your code here

        if (args.length > 0){
            for (String arg : args){
                if (arg.equals("dev"))
                    devMode = true;
                    logger.info("Developer Mode activated...");
                    break;
            }
        }

        loadConfig();

        menu();

        gameStart(choiceGame, choiceMode);
    }

    /**
     * loads the config file.
     */
    private static void loadConfig(){

        Properties props = new Properties();

        try{
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("config.properties");
            props.load(inputStream);

            if (Integer.parseInt(props.getProperty("nbDigitsGame1")) > 0)
                nbDigitsGame1 = Integer.parseInt(props.getProperty("nbDigitsGame1"));

            if (Integer.parseInt(props.getProperty("maxGuessesGame1")) > 0)
                maxGuessesGame1 = Integer.parseInt(props.getProperty("maxGuessesGame1"));

            if (Integer.parseInt(props.getProperty("nbDigitsGame2")) > 0)
                nbDigitsGame2 = Integer.parseInt(props.getProperty("nbDigitsGame2"));

            if (Integer.parseInt(props.getProperty("maxGuessesGame2")) > 0)
                maxGuessesGame2 = Integer.parseInt(props.getProperty("maxGuessesGame2"));

            if (Integer.parseInt(props.getProperty("nbVariationsGame2")) > 3 && (Integer.parseInt(props.getProperty("nbVariationsGame2")) < 11))
                nbVariationsGame2 = Integer.parseInt(props.getProperty("nbVariationsGame2"));

            logger.info("comfiguration file loaded");

            if (props.getProperty("devMode").equals("Y")){
                devMode = true;
                logger.info("Developer mode activated");
            }
        } catch (Exception e) {
            System.out.println("The config properties file is missing or can not be imported correctly. " +
                    "The program will be executed with the default parameters.\n\n");

            logger.error("Error, configuration file is missing");
        }
    }


    /**
     * Menu which gives the user the possibility to select a game and a mode.
     */
    public static void menu(){

        if (devMode)
            System.out.println("\n###### DEVELOPER MODE ######\n");

        System.out.printf("%S\n\n%s\n%s\n%s\n%s", "Hello and welcome!",
                "Which game would you like to play?",
                "1. Game +/- , find the secret combination using +/- indications",
                "2. Digit Mastermind, like the traditional Mastermind but with digits",
                "Enter your selection: ");

        Scanner sc = new Scanner(System.in);
        choiceGame = sc.nextLine();
        logger.debug("choiceGame= " + choiceGame);

        while (!choiceGame.equals("1") && !choiceGame.equals("2")){
            System.out.println("Please enter 1, 2, or 3...");
            logger.debug("Invalid entry.");
            choiceMode = sc.nextLine();
            logger.debug("New entry = " + choiceMode);
        }
        System.out.println("\n");
    }

    /**
     * Instantiates game instance according to the player selection
     * @param choiceGame game 1 (+/-) or Mastermind digits
     * @param choiceMode Mode challenger, defender, or duel
     */
    public static void gameStart(String choiceGame, String choiceMode){


    }
}
