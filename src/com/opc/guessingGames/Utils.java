package com.opc.guessingGames;

public class Utils {

    /**
     * Test if the string entered as parameter is made of digits
     * @param myString chain of characters to be tested
     * @return true or false
     */
    public static boolean isAnInt(String myString){

        char[] tab = myString.toCharArray();
        int k = 0;
        for (char c: tab) {
            if (!Character.isDigit(c)){
                k--;
                return k >= 0;
            }
        }

        if (myString.equals("")) {
            k--;
        }
        return k >= 0;
    }


    /**
     * Inserts a break in the game flow
     * @param breakLength length of the break in milliseconds
     */
    public static void makeAbreak(int breakLength){
        try{
            Thread.sleep(breakLength);
        } catch (InterruptedException e){
            System.out.println("Everything is fine!");
        }
    }

    /**
     * Converts a string array to a string
     * @param myArray String array to be converted
     * @return the string made of all the elements of the String array.
     */
    public static String arrayToString(String myArray[]){
        String myString = myArray[0];
        for (int i = 1; i < myArray.length; i++) {
            myString += (' ' + myArray[i]);
        }
        return myString;
    }
}
