package Projekt;

import java.util.Arrays;
import java.util.Scanner;

public class CaesarCipher {

    public static void main(String[] args) {
        int userChoice;
        Scanner sc = new Scanner(System.in);
        boolean goOn = true;
        while (goOn) {
            System.out.println("""
                    Indtast et tal:
                    4: Krypter Besked til Tal
                    3: Dekrypter Tal til Besked
                    2: Krypter Besked
                    1: Dekrypter Besked
                    0: Afslutter programmet\s""");
            userChoice = sc.nextInt();

            if(userChoice == 4){
                encryptNumbers();
            }
            else if (userChoice == 3){
                decryptNumbers();
            }
            else if (userChoice == 2){
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
        Scanner scEncrypt = new Scanner(System.in);
        System.out.println("Indtast plaintext:");
        String userPlaintext = scEncrypt.nextLine();
        System.out.println("Indtast shiftværdi:");
        int userShift = scEncrypt.nextInt();
        String resultat = caesarCrypt(userPlaintext,userShift);
        System.out.println("Den enkrypterede besked er: ");
        System.out.println(resultat);
    } //
    public static void decryptCaesarMenu(){
        Scanner scDecrypt = new Scanner(System.in);
        System.out.println("Indtast ciphertext:");
        String userPlaintext = scDecrypt.nextLine();
        System.out.println("Indtast shiftværdi:");
        int userShift = scDecrypt.nextInt();
        String result = caesarCrypt(userPlaintext,(-userShift));
        System.out.println("Den dekrypterede besked er: ");
        System.out.println(result);
    } //Den eneste forskel er at der bliver sat "-" foran shiftværdien
    public static void encryptNumbers(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Indtast sætning der skal krypteres");
        String userText = sc.nextLine();
        System.out.println("indtast shift værdi"); //test
        int userShift = sc.nextInt(); //test
        int[] userNumberCode = textToListOfNumbers(userText);
        int[] resultat = shiftListOfNumbers(userNumberCode,userShift);
        System.out.println("Den krypterede kode er: " + Arrays.toString(resultat));
    }
    public static void decryptNumbers(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Indtast kode der skal krypteres(brug komma til at splitte tal)");
        String userNumbers = sc.nextLine();
        System.out.println("indtast shift værdi");
        int userShift = sc.nextInt();
        int[] userNumbers2 = Arrays.stream(userNumbers.split(",")).mapToInt(Integer::parseInt).toArray();
        int[] resultat = shiftListOfNumbers(userNumbers2,(-userShift));
        System.out.println("Den krypterede besked er: " + listOfNumbersToText(resultat));

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
