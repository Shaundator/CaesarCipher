package Projekt;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int userChoice;
        Scanner sc = new Scanner(System.in);
        boolean goOn = true;
        while (goOn) {
            System.out.println("Indtast et tal\n2: Krypter Besked\n1: Dekrypter Beske\n0: Afslutter programmet: ");
            userChoice = sc.nextInt();

            if(userChoice == 2){
                encryptCaesarMenu();
            }
            else if (userChoice == 1){
                decryptCaesarMenu();
            }
            else if (userChoice == 0){
                goOn = false;
            }
            else{
                System.out.println("Du indtastede: " + userChoice);
            }
        } //Bruger Menu
        System.out.println("Programmet er afsluttet");
    }

    public static void encryptCaesarMenu(){
        Scanner scEncryptText = new Scanner(System.in);
        Scanner scEncryptShift = new Scanner(System.in);
        System.out.println("Indtast plaintext:");
        String userPlaintext = scEncryptText.nextLine();
        System.out.println("Indtast shiftværdi:");
        int userShift = scEncryptShift.nextInt();
        String resultat = caesarCrypt(userPlaintext,userShift);
        System.out.println("Den enkrypterede besked er: ");
        System.out.println(resultat);
    }
    public static void decryptCaesarMenu(){
        Scanner scDecryptText = new Scanner(System.in);
        Scanner scDecryptShift = new Scanner(System.in);
        System.out.println("Indtast ciphertext:");
        String userPlaintext = scDecryptText.nextLine();
        System.out.println("Indtast shiftværdi:");
        int userShift = scDecryptShift.nextInt();
        String result = caesarCrypt(userPlaintext,(-userShift));
        System.out.println("Den dekrypterede besked er: ");
        System.out.println(result);
    }
    public static String caesarCrypt( String ciphertext, int shift){
        int[] numberList = textToListOfNumbers(ciphertext);
        int[] numberListShifted = shiftListOfNumbers(numberList,shift);
        return listOfNumbersToText(numberListShifted);
    }

    public static String listOfNumbersToText(int[]numbers) {
        int i;
        char temp;
        char[] tempResultat = new char[numbers.length];

        for(i = 0; i < numbers.length; i++){
            temp = numberToLetter(numbers[i]);
            tempResultat[i] = temp;
        }
        return String.valueOf(tempResultat);
    }
    public static char numberToLetter( int number ) {
        char[] alfabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ".toCharArray();
        return alfabet[number];
    }

    public static int[] textToListOfNumbers( String text ) {
        int[] resultat = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            char tempLetter = text.charAt(i);
            int tempNumber = letterToNumber(tempLetter);
            resultat[i] = tempNumber;
        }
        return resultat;
    }
    public static int letterToNumber( char letter ) {
        char[] alfabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ".toCharArray();
        char capLet = Character.toUpperCase(letter);
        int nummer = 0;
        for (int i = 0; i < alfabet.length; i++){
            char bogstavViTjekker = alfabet[i];
            if( capLet == bogstavViTjekker) {
                nummer = i;
            }
        }
        return nummer;
    }

    public static int[] shiftListOfNumbers( int[] numbers , int shift){
        int[] listOfNumbers = new int[numbers.length];
        int i;
        for (i = 0; i < numbers.length; i++){
            listOfNumbers[i] = shiftNumber(numbers[i],shift);
        }
        return listOfNumbers;
    }
    public static int shiftNumber( int number, int shift ){
        if(number == 0){
            return number;
        }
        int shiftedNumber = number + shift;
        if(shiftedNumber > 29){
            shiftedNumber = shiftedNumber - 29;
        }
        if(shiftedNumber <= 0){
            shiftedNumber = shiftedNumber + 29;
        }
        return shiftedNumber;
    }
}
