package Projekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CaesarCipher {

    public static void main(String[] args) {
        int userSubMenu;

        boolean mainMenu = true;
        while(mainMenu) {
            System.out.println("""
                    
                    Vælg en krypteringsmenu
                    1: Tekst til tekst kryptering
                    2: Tal til tekst kryptering
                    0: Afslut programmet""");
            userSubMenu = scanForNumber();
            if (userSubMenu == 2) {
                boolean goOn = true;
                while (goOn) {
                    System.out.println("""
                            
                            Indtast et tal:
                            1: Krypter Besked
                            2: Dekrypter Besked
                            0: Tilbage til hovedmenu\s""");
                    userSubMenu = scanForNumber();
                    if (userSubMenu == 1) {
                        encryptNumbers();
                    }
                    else if (userSubMenu == 2) {
                        decryptNumbers();
                    }
                    else if (userSubMenu == 0) {
                        goOn = false;
                    }
                    else {
                        System.out.println("Du indtastede: " + userSubMenu);
                    }
                }
            }
            else if (userSubMenu == 1) {
                boolean goOn = true;
                while (goOn) {
                    System.out.println("""
                            
                            Indtast et tal:
                            1: Krypter Besked
                            2: Dekrypter Besked
                            0: tilbage til hovedmenu\s""");
                    userSubMenu = scanForNumber();
                    if (userSubMenu == 1) {
                        encryptCaesarMenu();
                    }
                    else if (userSubMenu == 2) {
                        decryptCaesarMenu();
                    }
                    else if (userSubMenu == 0) {
                        goOn = false;
                    }
                    else {
                        System.out.println("Du indtastede: " + userSubMenu);
                    }
                }
            }
            else if (userSubMenu == 0) {
                System.out.println("Farvel");
                mainMenu = false;
            }
            else{
                System.out.println("Du indtastede " + userSubMenu);
            }
        }
    }

    //Krypteringsmenuer
    public static void encryptCaesarMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Indtast plaintext:");
        String userPlaintext = sc.nextLine();
        System.out.println("Indtast shift værdi:");
        int userShift = scanForNumber();
        String result = caesarCrypt(userPlaintext,userShift);
        System.out.println("Besked er dekrypteret:\n" + result);
    }
    public static void decryptCaesarMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Indtast ciphertext:");
        String userPlaintext = sc.nextLine();
        System.out.println("Indtast shiftværdi:");
        int userShift = scanForNumber();
        String result = caesarCrypt(userPlaintext,(-userShift));
        System.out.println("Den dekrypterede besked er:\n" + result);
    }
    public static void encryptNumbers(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Indtast sætning der skal krypteres");
        String userText = sc.nextLine();
        System.out.println("indtast shift værdi");
        int userShift = scanForNumber();
        int[] resultat = shiftListOfNumbers(textToListOfNumbers(userText),userShift);
        System.out.println("Den krypterede kode er:\n" + Arrays.toString(resultat));
    }
    public static void decryptNumbers(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Indtast numre der skal krypteres og afslut med Q");
        List<Integer> userNumberInput = new ArrayList<>();
        String input;
        while (true) {
            input = sc.nextLine();
            if (input.equalsIgnoreCase("Q")) {
                break;
            }
            if (!input.matches("\\d+")) {
                System.out.println("Ugyldig");
            } else {
                userNumberInput.add(Integer.parseInt(input));
            }
        } //nummer indtasts funktion, fandt den her online
        System.out.println("indtast shift værdi");
        int userShift = scanForNumber();
        int[] userNumbers = userNumberInput.stream().mapToInt(i->i).toArray();
        int[] resultat = shiftListOfNumbers(userNumbers,(-userShift));
        System.out.println("Den krypterede besked er:\n" + listOfNumbersToText(resultat));

    }
    
    //Krypteringsprocess
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
        char capLet = Character.toUpperCase(letter); //Fjern den her hvis du vil tilføje små bogstaver til alfabetet
        int nummer = 0;
        for (int i = 0; i < alfabet.length; i++){
            char bogstavViTjekker = alfabet[i];
            if( capLet == bogstavViTjekker) {
                nummer = i;
            } //ændre capLet til letter, hvis du fjerner capLet
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
        if(shiftedNumber > (alfabet.length-1)){
            shiftedNumber = shiftedNumber - (alfabet.length-1);
        }
        if(shiftedNumber <= 0){
            shiftedNumber = shiftedNumber + (alfabet.length-1);
        }
        return shiftedNumber;
    }
    
    //Værktøjer
    public static int scanForNumber(){
        Scanner sc = new Scanner(System.in);
        while(!sc.hasNextInt()){
            System.out.println("Ugyldig");
            sc.next();
            //Looper ved ugyldigt input, som ikke er et tal
        }
        return sc.nextInt();
    } //looper ved ikke int input
    public static char[] alfabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ".toCharArray(); //Du kan frit tilføje flere tegn til alfabet, men gå til linje 162 hvis du gør
}
